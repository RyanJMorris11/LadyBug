package com.mycompany.a3;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * CLass Ladybug is the main user object
 * 
 * @author ryanmorris
 *
 */
public class Ladybug extends Movable implements ISteerable {

//	units per sec
	private final float totalMaxSpeedInUnitsPerSec=130; // 
	private float curMaxSpeed; // 0-10 pixels / units per sec
	private float foodLevel; // 0-100
	private final int consumptionRate = 5; // food per second
	private final float totalMaxHealthLevel=10; // 0-10
	private float healthLevel; // 0-10
	private int lastFlagReached;
	private final int attackPenalty = 3;
	private GameWorld myWorld;
	private static Ladybug myLady;

	/**
	 * Constructs an object of Ladybug class
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 * @param newWorld 
	 */
	private Ladybug(float x, float y, GameWorld newWorld) {
		myWorld = newWorld;
		super.setLocation(x, y);
		super.setColor(ColorUtil.rgb(255, 0, 0));
		super.setSize(30); // radius of this object
		super.setHeading(90);
		super.setSpeed(0);
		curMaxSpeed = totalMaxSpeedInUnitsPerSec;
		healthLevel = totalMaxHealthLevel;
		foodLevel = 100;
		lastFlagReached = 0;
	}



	/**
	 * getInstance() implements the singleton design pattern. If the Ladybug is
	 * null, then location of this Ladybug is the default position of (20,20) which
	 * is also the default position of the 1st flag.
	 * 
	 * @param myFlag
	 * @return Ladybug
	 */
	public static Ladybug getInstance() {
		
		return myLady;
	}
	/**
	 * getInstance() implements the singleton design pattern. If the Ladybug is
	 * null, then location of this Ladybug is the default position of (20,20) which
	 * is also the default position of the 1st flag.
	 * 
	 * @param myFlag
	 * @return Ladybug
	 */
	public static Ladybug createInstance(GameWorld newWorld) {
		if (myLady == null) {
			myLady = new Ladybug(20, 20, newWorld);
		}
		return myLady;
	}


	/**
	 * a detailed toString()
	 * 
	 * @param nothing
	 * @return returns a String detailing this object
	 */
	public String toString() {
		String str = super.toString();
		str += "\n\t";
		str += "maxSpeed=" + curMaxSpeed + " health=" + healthLevel;
		str += " foodLevel=" + foodLevel + " consumptionRate=" + consumptionRate + " lastFlag=" + lastFlagReached + " ";
		return str;
	}

	/**
	 * Update() is called every clock tick and updates the position and other values
	 * of the Ladybug
	 * @Override
	 */
	public void update(float elaspedSec) { //elapsed milliSecs since last call
		System.out.println("Updating Ladybug: curMapSpeed: " + curMaxSpeed +" vs Speed: "+getSpeed());
		super.update(elaspedSec);
		updateHungry(elaspedSec);
		updateMaxSpeed();
	}

	/**
	 * Steer() takes an integer value and adds it to the heading(AKA direction) of
	 * the Ladybug via passing that int to addHeading(). A positive int turns right.
	 * 
	 * @param int
	 * @return nothing
	 * @calls AddHeading() is used by other movable objects
	 */
	public void steer(int changeDir) {
		this.addHeading(changeDir);
	}

	/**
	 * Updates the MaxSpeed of the ladybug depending on foodLevel and healthLevel
	 * Also calls updateColor()
	 * 
	 */
	public void updateMaxSpeed() {
		
		curMaxSpeed = Math.min(healthLevelSpeedLimiter(), foodLevelSpeedLimiter());
		
		if (curMaxSpeed < getSpeed()) { // if the current speed is faster then the current limit
			setSpeed(curMaxSpeed);
		}
		
		updateColor();
//		System.out.println("new curMaxSpeed: "+ curMaxSpeed+"getSpeed(): "+ getSpeed());
	}
	
	private float healthLevelSpeedLimiter() {
		float healthSpeedRatio = 1;
		if(healthLevel<=0) {
			return 0;
		}
		healthSpeedRatio = healthLevel / totalMaxHealthLevel;
		if(healthSpeedRatio < 0.5) {
			healthSpeedRatio = (float) Math.max(healthSpeedRatio, 0.3);
		}
		System.out.println("new healthSpeedRatio*totalMaxSpeedInUnitsPerSec: "+ healthSpeedRatio*totalMaxSpeedInUnitsPerSec);

		return healthSpeedRatio*totalMaxSpeedInUnitsPerSec;
	}



	public float foodLevelSpeedLimiter() {
		float foodSpeedLimit=totalMaxSpeedInUnitsPerSec;
		if(foodLevel < 70) {
			foodSpeedLimit = (totalMaxSpeedInUnitsPerSec / 70) *foodLevel;
			
			if(foodLevel <=0) {
				return 0;
			}
		
		}
		
		if(foodSpeedLimit<totalMaxSpeedInUnitsPerSec/8) {
			foodSpeedLimit=totalMaxSpeedInUnitsPerSec/8;
		}
		System.out.println("new foodSpeedLimit: "+ foodSpeedLimit);

		return foodSpeedLimit;
	}

	/**
	 * returns true if maxSpeed=0 and no movement is possible
	 * 
	 * @return boolean
	 */

	public boolean endedLife() {
		if (curMaxSpeed <= 0) {
			return true;
		} else
			return false;
	}

	/**
	 * Called when the ladybug has been attacked be a dangerous creature. If the
	 * ladybug's maxSpeed is 0 then the lady bug has died. upDates Color
	 */
	public void attacked() {
		healthLevel = healthLevel - attackPenalty;
		updateMaxSpeed();
		System.out.println("Health=" + healthLevel);
//		if (healthLevel <= 0) {
			// System.out.println("You have died");
//		}
		updateColor();
	}

	/**
	 * Called every clock tick. Minuses the foodLevel of the Ladybug
	 * 
	 */
	public void updateHungry(float elaspedSec) {
//		System.out.print("updateHungry foodLevel=" + foodLevel);
		
		foodLevel -= (elaspedSec*consumptionRate);
		if(foodLevel <0){
			foodLevel=0;
		}
//		System.out.println("  new foodLevel=" + foodLevel);
	}

	/**
	 * returns true is the (flag) parameter is the next flag needed to be reached by
	 * the ladybug
	 * 
	 * @param myFlag
	 * @return boolean
	 */
	public boolean flagReached(int myFlag) {
		if (lastFlagReached == myFlag - 1) {
			lastFlagReached++;
			System.out.println("LastFlag = " + lastFlagReached);
			return true;
		}
		return false;
	}

	/**
	 * Returns the maxSpeed of the LadyBug
	 * 
	 */
	public double getMaxSpeed() {
		return curMaxSpeed;
	}

	/**
	 * Adds speed to the Ladybug but keeps the speed of the Ladybug from going
	 * backwards or faster than the msxSpeed allows
	 * 
	 */
	public void addSpeed(int addSpeed) {
		float newSpeed;
		
		if (addSpeed > 0 && getSpeed() < totalMaxSpeedInUnitsPerSec || addSpeed < 0 && getSpeed() > 0) {
			
			newSpeed = getSpeed() + addSpeed;
			setSpeed(newSpeed);
			System.out.println("New Speed= " + newSpeed);
		} else {
			System.out.println("Speed Limited to= " + super.getSpeed());
		}
	}

	/**
	 * returns the foodLevel that the Ladybug has 1-10
	 * 
	 * @return int
	 */
	public float getFoodLevel() {
		return foodLevel;
	}

	/**
	 * Returns the health level of the Ladybug 1-10
	 * 
	 * @return int
	 */
	public float getHealthLevel() {
		return healthLevel;
	}

	/**
	 * returns the last flag reached
	 * 
	 * @return int representing the flag number
	 */
	public int getLastFlagReached() {
		return lastFlagReached;
	}

	/**
	 * Called when the ladybug is supposed to eat. Also Calls updateMaxSpeed()
	 * 
	 * @param foodEaten
	 */
	public void eat(int foodEaten) {
		foodLevel += foodEaten;
		System.out.println("FoodEaten= " + foodEaten + "  New FoodLevel= " + foodLevel);
		updateMaxSpeed();
	}

	/**
	 * Called everyTick to make sure the ladybug is the right color
	 * 
	 * @param nothing
	 */
	private void updateColor() {
		// System.out.println("Update color");
		int c =  (int) (255 * ((curMaxSpeed)/totalMaxSpeedInUnitsPerSec) );		
		int newColor = ColorUtil.rgb(c, 0, 0);
		super.setColor(newColor);
	}

	/**
	 * Destroys this simpleton instance of the ladybug so it can be regenerated
	 * Called whenever the ladybug dies.
	 * 
	 * @return Nothing
	 */
	public void destroy() {
		if (myLady != null) {
			myLady = null;
		}
		return;
	}

	public void draw(Graphics g, Point loc) {

		// System.out.println("Draw Ladybug: cx: "+ loc.getX() +" cy: "+ loc.getY() +"
		// this.x: "+(int)this.getX() +" this.y: "+(int)this.getY());
		
		
		g.setColor(this.getColor());

		g.fillArc(loc.getX() + (int) getX() - getSize()/2, loc.getY() + (int) getY() - getSize()/2, getSize(), getSize(), 0, 360);
	}

	@Override
	public void handleCollision(GameObject obj) {
		String simpleName = obj.getClass().getSimpleName();
		
		if (simpleName.equals("FoodStation")) {
			handleFoodCollision((FoodStation) obj );
		
		}else
		if (simpleName.equals("Flag")) {
			handleFlagCollision( ((Flag) obj).getSeqNum() );
			
		}else
		if (simpleName.equals("Spider")) {
			
			if (!collidingListContains(obj) ){
				addToCollidingList(obj);
				handleSpiderCollision();
			}
		}
		

	}//end handleCollision

	private void handleSpiderCollision() {
		myWorld.attack();	
	}



	private void handleFoodCollision(FoodStation obj) {
		myWorld.eat(obj);
		
	}

	private void handleFlagCollision(int flagSeqNum) {

		if (lastFlagReached == flagSeqNum - 1) {
			
			lastFlagReached++;
			System.out.println("LastFlag = " + lastFlagReached);
			myWorld.hitFlag(flagSeqNum);
			if(lastFlagReached == GameWorld.getNumFlags()) {
				GameWorld.gameWon();
			}
		}
		
		
	}//end handleFlagCollision



	public void speedUp() {
		addSpeed(curMaxSpeed/3);	
		
		if(getSpeed() > curMaxSpeed) {
			setSpeed(curMaxSpeed);
		}
	}



	public void slowDown() {
		addSpeed(-curMaxSpeed/3);
		
		if(getSpeed() < 0) {
			setSpeed(0);
		}
	}
}
