package com.mycompany.a3;

/**
 * Movable() type objects has the methods necessary to move and update heading
 * and speed within the necessary limits.
 * 
 * @author ryanmorris
 *
 */
public abstract class Movable extends GameObject {

	private int heading; // Compass like angle: 0=up 90=right 180=down 270=left etc
	private float speedUnitsPerSec; // in units(pixels?)/second

	/**
	 * Contructs a Movable class.
	 * 
	 */
	public Movable() {
		super();
	}

	/**
	 * Update is called with every clock tick to update the necessary values.
	 * 
	 * @Override
	 */
	public void update(float elaspedSec) { //elapsed milliSecs since last call
//		 System.out.println("Update movable");
		move(elaspedSec);
		updateLts(elaspedSec);

	}

	private void updateLts(float elaspedSec) {
	}

	/**
	 * a detailed toString()
	 * 
	 * @param nothing
	 * @return returns a String detailing this object
	 */
	@Override
	public String toString() {
		String str = super.toString();
		str += " heading=" + heading + " speed=" + speedUnitsPerSec;
		return str;
	}

	/**
	 * setter for the heading
	 * 
	 * @param newHeading
	 */
	public void setHeading(int newHeading) {
		heading = newHeading;
	}

	/**
	 * setter for the Speed
	 * 
	 * @param maxSpeedInUnitsPerMs
	 */
	public void setSpeed(float newSpeed) {
		speedUnitsPerSec = newSpeed;
	}

	/**
	 * Getter for the heading
	 * 
	 */
	public int getHeading() {
		return heading;
	}

	/**
	 * Getter for the speed
	 * 
	 * @return int speed
	 */
	public float getSpeed() {
		return speedUnitsPerSec;
	}

	/*
	 * function move() Uses heading and speed to update X and Y to their new values
	 * @Param time in milliseconds 
	 */
	public void move(float elaspedSec) {
		
		float deltaX;
		float deltaY;
		
		
		deltaX = (float) (Math.cos(Math.toRadians(90 - heading)) * speedUnitsPerSec*elaspedSec);
		deltaY = (float) (Math.sin(Math.toRadians(90 - heading)) * speedUnitsPerSec*elaspedSec);
		// System.out.println("deltaX= "+deltaX+" deltaY= "+deltaY);
		addLocation(deltaX, deltaY);
	}

	/**
	 * Called to add a deltaX and DeltaY to the coordinates (x,y) of a type Movable
	 * object
	 * 
	 * @param double
	 *            deltaX
	 * @param double
	 *            deltaY
	 */
	public void addLocation(float deltaX, float deltaY) {
		super.setLocation(deltaX + super.getX(), deltaY + super.getY());
	}

	/**
	 * Adds an (int) heading to the existing heading value of Type Movable objects
	 * accepts positive or negative values
	 * 
	 * @param newHead
	 */
	public void addHeading(int newHead) {
		heading = (heading + newHead) % 360;
		if (heading < 0) {
			addHeading(360);
		}
	}

	/**
	 * Adds speed to the existing Speed Value of Type Movable objects accepts
	 * positive or negative values
	 * 
	 * @param newSpeed
	 */
	public void addSpeed(float newSpeed) {
		speedUnitsPerSec = speedUnitsPerSec + newSpeed;
	}
}
