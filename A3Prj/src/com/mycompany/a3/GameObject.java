package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Top of the hierarchy for all GameObects. represents a 2 dimensional object.
 * 
 */
public abstract class GameObject implements ICollider, IDrawable {

	private float x; // location x
	private float y; // location y
	private int size;
	private int color; // color of object
	private ArrayList<GameObject> collidingList;

	GameObject(){
		collidingList = new ArrayList<GameObject>();
	}
	
	/**
	 * a detailed toString()
	 * 
	 * @param nothing
	 * @return returns a String detailing this object
	 */
	@SuppressWarnings("deprecation")
	public String toString() {
		String str;
		str = this.getClass().getSimpleName() + ": ";
		str += toStringLocation();
		str += " color: " + "[" + ColorUtil.red(color) + ", " + ColorUtil.green(color) + ", " + ColorUtil.blue(color)
				+ "]";
		str += " size=" + size;
		return str;
	}

	/**
	 * update() needs to be usable in all concrete objects. It's called every time
	 * the clock ticks to update all the relevant positions and values of each
	 * class
	 * 
	 * @param nothing
	 */
	public abstract void update(float elaspedSec);

	/**
	 * setLocation() sets the (x,y) position of each GameObject.
	 * 
	 * @param newX
	 * @param newY
	 */
	public void setLocation(float newX, float newY) {
		// System.out.println("GameObject set x= " + newX + " \ty= " + newY);
		this.setX(newX);
		this.setY(newY);
	}

	/**
	 * addLocation() adds new values to the X and Y coordinates of this object.
	 * 
	 * @param newX
	 * @param newY
	 */
	public void addLocation(float newX, float newY) {
		setLocation(newX + this.getX(), newY + this.getY());
	}

	/**
	 * toStringLocation() returns a String detailing the (x,y)location of this
	 * object
	 * 
	 * @return String
	 */
	public String toStringLocation() {
		return "  \tloc=(" + (int) x + ".0," + (int) y + ".0)";
	}

	/**
	 * getX() returns the X coordinate of this object
	 * 
	 */
	public float getX() {
		return x;
	}

	/**
	 * setX() set the X coordinate of this object within the scope of the map. Any
	 * attempt to change the coordinates to outside the map will stay within the
	 * edge instead
	 * 
	 * @param x
	 */
	public void setX(float x) {
		if (x < 0 + getSize()/2) {
			this.x = 0 + getSize()/2;
			return;
		} else if (!(GameWorld.MapViewNull()) && x > GameWorld.getMapWidth() - getSize()/2) {
			this.x = GameWorld.getMapWidth() - getSize()/2;
			return;
		} else {
			this.x = x;
			// System.out.print("this.x = " + this.x);
			return;
		}
	}

	/**
	 * getY() returns the Y coordinate of this object
	 * 
	 */
	public float getY() {
		return y;
	}

	/**
	 * setY() set the Y coordinate of this object within the scope of the map. Any
	 * attempt to change the coordinates to outside the map will stay within the
	 * edge instead
	 * 
	 * @param x
	 */
	public void setY(float y) {
		if (y < 0 + getSize()/2) {
			this.y = 0 + getSize()/2;
			return;
		} else if (!(GameWorld.MapViewNull()) && y > GameWorld.getMapHeight() - getSize()/2) {
			this.y = GameWorld.getMapHeight() - getSize()/2;
			return;
		} else {
			this.y = y; 
			// System.out.println("\tthis.y = " + this.y);
			return;
		}
	}

	/**
	 * returns false unless implemented in a class that contains food.
	 * 
	 * @return boolean
	 */

	public boolean hasFood() {
		return false;

	}

	/**
	 * returns the size of this object
	 * 
	 * @return integer size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * sets size of this object
	 * 
	 * @return nothing
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * gets the color of this object
	 * 
	 * @return int representing the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Sets the color of this object
	 * 
	 * @param color
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * getRandSize() returns a random integer between 10 AND 50 which is a good size
	 * for FoodStation and Spider classes.
	 * 
	 * @return integer
	 */
	public int getRandSize() {
		Random rand = new Random();
		return rand.nextInt(40) + 10;
	}

	public Point getLocPoint() {
		return new Point((int) getX(), (int) getY());
	}

	public abstract void draw(Graphics newG, Point p);

	public boolean collidesWith(GameObject obj) {

		boolean result = false;

		int thisCenterX = (int) this.getX() + getSize() / 2; // find centers
		int thisCenterY = (int) this.getY() + getSize() / 2;

		int otherCenterX = (int) (obj).getX() + (getSize() / 2);
		int otherCenterY = (int) (obj).getY() + (getSize() / 2);

		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;

		int distBetweenCentersSqr = (dx * dx + dy * dy);

		// find square of sum of radii
		int thisRadius = getSize() / 2;
		int otherRadius = getSize() / 2;

		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);

		if (distBetweenCentersSqr <= radiiSqr) {
			System.out.println("Collision detected");
			result = true;
	
		}else { // this collision isn't happening right now
			if(collidingList.contains(obj)) {
				collidingList.remove(obj);
			}
		}
		return result;
	}
	public boolean collidingListContains(GameObject obj) {
		return collidingList.contains(obj);
	}
	public void addToCollidingList(GameObject obj) {
			collidingList.add(obj);
	}
	
	public void handleCollision(GameObject newObj) {
		System.out.println("Unimplemented handleCollision");
	}
}
