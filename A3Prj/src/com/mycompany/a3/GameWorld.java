package com.mycompany.a3;

import java.util.Observable;
import com.codename1.ui.Dialog;
import com.mycompany.a3.GameObjectCollection.MyIterator;

/**
 * GameWorld() contains all the game objects and methods that interact with and
 * activate those game objects.
 * 
 * @author ryanmorris
 */
public class GameWorld extends Observable {
	private static int numFlags;
	private int livesLeft;
	private static long clockTicks;
	private boolean soundOn;
	private GameObjectCollection myCollection;
	private static MapView myMapView;
	private static ISelectable mySelectedObj;

	// Action Sounds
	private Sound spiderAttackSound;
	private Sound deathSound;
	private Sound eatFoodSound;
	private Sound getFlagSound;

	/**
	 * this constructor declares the variables needed
	 */
	public GameWorld() {
		soundOn = true;
		numFlags = 6;
		livesLeft = 3;
		clockTicks = 0;

		// System.out.println("Width= " + mapWidth + " Height= " + mapHeight);

	}

	/**
	 * init() makes the GameObject arrayList and initializes all the object
	 * creation.
	 * 
	 */
	public void init() {
		System.out.println("Initialize new world");
		myCollection = new GameObjectCollection();
		myCollection.makeFlags(numFlags);
		myCollection.makeLadyBug(this);
		myCollection.makeSpiders(6);
		myCollection.makeFood(4);
		outputMap();
		setUpSounds();
//		deathSound.play();
		// code here to create the
		// initial game objects/setup
	}

	private void setUpSounds() {
		spiderAttackSound = new Sound("scream.wav");
		eatFoodSound = new Sound("apple.WAV");
		getFlagSound = new Sound("woosh1.wav");
		deathSound = new Sound("Big Scream Male 02.wav");
	}

	/**
	 * tick() activates all the gameObjects update methods and checks to see if the
	 * ladyBug lives are
	 */
	public void tick(int elaspedMs) {
		// System.out.println("TESSSSSSSST");
		float elaspedSec = ((float) elaspedMs) / 1000;
		// System.out.println("elaspedSec= "+elaspedSec+" elaspedMs= "+elaspedMs);

		MyIterator itr = getGameObjects();
		while (itr.hasNext()) {
			(itr.getNext()).update(elaspedSec);
		}
		clockTicks++;

		if (getLadybug().endedLife() == true) {
			if (soundOn) {
				deathSound.play();
			}
			newLife();
			if (livesLeft <= 0) {
				gameLost();
			}
		}
		setChanged();
		notifyObservers();
		// System.out.println("Time= " + clockTicks);
	}

	/**
	 * Called by MapView to set the width and height needed for object creation
	 * 
	 * @param newWidth
	 * @param newHeight
	 */
	public void setMapView(MapView newMapView) {
		GameWorld.myMapView = newMapView;
		// System.out.println("setWidthHeight: Width "+mapWidth+" Height "+ mapHeight);
	}

	/**
	 * getGameObjects() calls the GameObjectsCollection to return an Iterator
	 * containing all the GameObjects
	 * 
	 * @return
	 */
	public MyIterator getGameObjects() {
		MyIterator iter = myCollection.getIterator();
		return iter;
	}

	/**
	 * getLadyBug() returns the ladybug. If the ladybug doesn't exist, a new one is
	 * created and returned
	 * 
	 * @return Ladybug
	 */
	public Ladybug getLadybug() {
		return ((Ladybug) GameObjectCollection.getLadyBug());
	}

	/**
	 * outputMap() prints a description of all the objects
	 * 
	 */
	public void outputMap() {
		MyIterator iter = getGameObjects();
		while (iter.hasNext()) {
			System.out.println(iter.getNext().toString());
		}
		System.out.println("Map outputted");
	}

	/**
	 * speedUp() speeds up the ladyBug by 1 speed.
	 * 
	 */
	public void speedUp() {
		System.out.print("Speed upp: ");
		((Ladybug) getLadybug()).speedUp();
		setChanged();
		notifyObservers();
	}

	/**
	 * slowDown() speeds up the ladyBug by 1 speed.
	 * 
	 */
	public void slowDown() {
		System.out.print("Break: ");
		((Ladybug) getLadybug()).slowDown();
		setChanged();
		notifyObservers();
	}

	/**
	 * turnLeft() add 15 to the ladybug's heading
	 * 
	 */
	public void turnLeft() {
		((Ladybug) getLadybug()).addHeading(-15);
		System.out.println("left heading= " + ((Ladybug) getLadybug()).getHeading());
		setChanged();
		notifyObservers();
	}

	/**
	 * turnRight() adds -15 to the ladybug's heading
	 * 
	 */
	public void turnRight() {
		getLadybug().addHeading(15);
		System.out.println("right heading= " + getLadybug().getHeading());
		setChanged();
		notifyObservers();
	}

	/**
	 * eat() finds a foodstation and transfers the foodLevel of that foodStation to
	 * the ladyBug and stops when the ladybug is full or when the food station is
	 * empty.
	 * 
	 */
	public void eat(FoodStation food) {
		int foodEaten;
		Ladybug myLady = getLadybug();

		// System.out.println("About to eat: " + food.toString());
		if (food.hasFood()) {
			if (soundOn) {
				eatFoodSound.play();
			}
			foodEaten = food.eat(); // returns the amount of food in the station
			myLady.eat(foodEaten);
			myCollection.makeFood(1);
			// System.out.println("new Food Station created");
			setChanged();
			notifyObservers();
		}

	}

	/**
	 * returns a food station that has food left
	 * 
	 * @return FoodStation
	 */
	@SuppressWarnings("deprecation")
	public FoodStation getFoodStation() {
		MyIterator itr = getGameObjects();
		Object isIt;
		while (itr.hasNext()) {
			isIt = itr.getNext();
			if (isIt.getClass().getSimpleName().equals("FoodStation")) {

				if (((FoodStation) isIt).hasFood()) {
					// System.out.println("Found Station with Food");
					return (FoodStation) isIt;
					// } else {
					// System.out.println("Found Station NO food");
				}
				// }else {
				// System.out.println("Not food");

			}
		}
		System.out.println("No food found.");
		return null;
	}

	/**
	 * attack() calls the attacked function in Ladybug class. checks to see if the
	 * game is over or if a life has been lost.
	 * 
	 */
	public void attack() {
		getLadybug().attacked();
//		if (getLadybug().endedLife() == true) {
//			newLife();
//			if (soundOn) {
//				deathSound.play();
//			}
//			if (livesLeft <= 0) {
//				gameLost();
//			}
//		}
		if (soundOn) {
			spiderAttackSound.play();
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * newLife() decrements livesLeft, notifies the user that lives were lost then,
	 * re-initializes the gameWorld.
	 * 
	 * 
	 */
	private void newLife() {
		livesLeft--;
		System.out.println("You were killed. \n\tLives left= " + livesLeft);
		if (livesLeft > 0) {
			Dialog.show("Ah-oh! ", "You were killed.", "Okay", null);
		}
		// remakeWorld();
		myCollection.clear();
		init();
	}

	/**
	 * Called when all lives have been used. Exits the program.
	 */
	private void gameLost() {
		System.out.println("Game over, you failed!");
		Dialog.show("Game Over", "You Failed!", "Okay", null);
		System.exit(0);
	}

	static void gameWon() {
		System.out.println("Game Completed, you win!\nTotal time: " + getSeconds() + " Seconds");
		String myString = ("You Win! \n Total time: " + getSeconds() + " Seconds");
		Dialog.show("Game Completed", myString, "Okay", null);
		System.exit(0);
	}

	/**
	 * GenDisplay() generate code to the command line that is relevent to game play.
	 * 
	 */
	public void genDisplay() {
		// System.out.println(
		// "\n \tlives=" + livesLeft + "\n \tclockValue=" + clockTicks + "\n \tflag=" +
		// getLadybug().getLastFlagReached()
		// + "\n \tfood=" + (Ladybug) getLadybug().getFoodLevel() + "\n \thealth=" +
		// getLadybug().getHealthLevel() + "\n");
	}

	/**
	 * Called when ladybug has arrived at a flag BY THE LADYBUG class. Checks to see
	 * if the last flag has been reaches and exits with "you won"
	 * 
	 * @param flagNum
	 */
	public void hitFlag(int flagNum) {
		if (soundOn) {
			getFlagSound.play();
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * getter for the width of the map
	 * 
	 * @return (int) width of the map
	 */
	public static int getMapWidth() {
		return myMapView.getWidth();
	}

	/**
	 * getter for the height of the map
	 * 
	 * @return (int) height of the map
	 */
	public static int getMapHeight() {
		return myMapView.getHeight();
	}

	// returns ticks
	public static long getTicks() {
		return clockTicks;
	}

	// returns the lives left
	public int getLives() {
		return livesLeft;
	}

	// Returns the Food level of the ladybug
	public float getFoodLevel() {
		return ((Ladybug) getLadybug()).getFoodLevel();
	}

	// returns the health of the ladybug
	public float getHealth() {
		return ((Ladybug) getLadybug()).getHealthLevel();
	}

	// Toggles the sound
	public void soundToggle() {
		if (soundOn == true) {
			soundOn = false;
		} else {
			soundOn = true;
		}
		setChanged();
		notifyObservers();
		// System.out.println("SoundOn = " + soundOn);
	}

	// returns boolean - true if sound is on, false if off
	public boolean getSound() {
		return soundOn;
	}

	// Returns the last flag reached by the ladybug
	public int getLastFlagReached() {
		return ((Ladybug) getLadybug()).getLastFlagReached();
	}

	public static boolean MapViewNull() {
		if (myMapView == null) {
			return true;
		} else
			return false;
	}

	public static int getNumFlags() {
		return numFlags;
	}

	public void findSelectedObj(int x, int y) {
		GameObject newSelectedObj;

		MyIterator itr = getGameObjects();
		Object isIt;
		while (itr.hasNext()) {
			isIt = itr.getNext();

			if (isIt.getClass().getSimpleName().equals("FoodStation")) {

				newSelectedObj = ((ISelectable) isIt).findSelectedObj(x, y);

				if (newSelectedObj != null) {
					changeSelectedObj((ISelectable) newSelectedObj);
					return;
				}

			}
			if (isIt.getClass().getSimpleName().equals("Flag")) {

				newSelectedObj = ((ISelectable) isIt).findSelectedObj(x, y);

				if (newSelectedObj != null) {
					changeSelectedObj((ISelectable) newSelectedObj);
					return;
				}

			}
		}
		changeSelectedObj();
		return;
	}

	private void changeSelectedObj(ISelectable newSelectedObj) {

		if (mySelectedObj != null) {
			mySelectedObj.unselectObj();
		}

		newSelectedObj.selectGameObj();
		mySelectedObj = newSelectedObj;

		setChanged();
		notifyObservers();
	}

	public void changeSelectedObj() {

		if (mySelectedObj != null) {
			mySelectedObj.unselectObj();
		}
		mySelectedObj = null;

		setChanged();
		notifyObservers();
	}

	public boolean hasSelectedObj() {
		if (mySelectedObj != null) {
			return true;
		} else
			return false;
	}

	public void moveSelected(int newX, int newY) {

		((GameObject) mySelectedObj).setLocation(newX, newY);
		System.out.println("New Location Set : " + newX + ", " + newY);
		setChanged();
		notifyObservers();
	}

	public static long getSeconds() {
		return Game.getMsPerFrame() * getTicks() / 1000;
	}

	public boolean soundOn() {
		return soundOn;
	}

}
