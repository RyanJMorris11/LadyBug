package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Class foodStation acts like food that is needed to give the user foodPoints.
 * 
 * @author ryanmorris
 *
 */
public class FoodStation extends Fixed implements ISelectable{
	private static Random rand = new Random();
	private int foodLevel;
	private boolean selected;

	public FoodStation(float newX, float newY) {
		super(newX, newY, ColorUtil.MAGENTA, (rand.nextInt(30) + 17));
		foodLevel = (int) ((float) getSize()*2.2 - 6);
		updateColor();
	}

	/**
	 * a detailed toString()
	 * 
	 * @param nothing
	 * @return returns a String detailing this object
	 */
	public String toString() {
		String str = super.toString();
		str += " FoodLevel= " + foodLevel;
		return str;
	}

	/**
	 * Called every tick. updates the values associated with FoodStation.
	 * 
	 *
	 * @Override
	 */
	public void update() {
		updateColor();
	}

	/**
	 * Called every tick. updates the values associated with color. Color depends on
	 * the foodLevel of the foodStation
	 *
	 * @Override
	 */
	private void updateColor() {
		int c = (int) (foodLevel * 5);
		if (c > 255) {
			c = 255;
		}
		int newColor = ColorUtil.rgb(c, 30, c);
		super.setColor(newColor);
	}

	/** 
	 * 
	 */
	private void updateSize() {
		super.setSize(foodLevel + 6);
	}

	/**
	 * Called when the ladybug eats
	 * 
	 * @param eatRate
	 *            the amount of food requested to be eaten by ladybug
	 * @return integer amount of food eaten from the foodStation
	 */
	public int eat() {
		int temp = foodLevel;
		foodLevel = 0;
		updateColor();
		updateSize();
		return temp;
	}

	/**
	 * returns true if the foodStation still has food Used to find foodStations that
	 * still have food.
	 * 
	 * @return boolean
	 */
	public boolean hasFood() {
		if (foodLevel != 0) {
			return true;
		} else
			return false;
	}

	public void draw(Graphics g, Point loc) {
		g.setColor(this.getColor());
		
//		System.out.println("Draw Food: cx: "+ loc.getX() +" cy: "+ loc.getY() +" this.x: "+(int)this.getX() +" this.y: "+(int)this.getY());  
		
		if(selected) {
			g.drawRect (loc.getX() + (int)getX()- getSize()/2, loc.getY()+ (int)getY()- getSize()/2, getSize(), getSize());
		} else {
			g.fillRect (loc.getX() + (int)getX()- getSize()/2, loc.getY()+ (int)getY()- getSize()/2, getSize(), getSize());
		}
		g.setColor(ColorUtil.BLACK);

		g.drawString(""+this.foodLevel, loc.getX() + (int)getX()- getSize()/2-7, loc.getY()+ (int)getY()- getSize()/2);
	}


	public GameObject findSelectedObj(int newX, int newY) {
		int dx = Math.abs( newX- (int)this.getX() );
		int dy = Math.abs( newY- (int)this.getY() );
		
		if(dx < 30 && dy < 30) {	
			return this;
		}else {	
			return null;
		}
		
	}

	public void selectGameObj() {
		selected = true;
	}

	public void unselectObj() {
		selected = false;		
	}
}
