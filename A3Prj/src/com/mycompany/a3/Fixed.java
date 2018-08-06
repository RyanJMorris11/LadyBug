package com.mycompany.a3;

/**
 * Objects of the Fixed class need to override several method to avoid moving
 * and updating positions after they are initially created.
 * 
 * @author ryanmorris
 */
public abstract class Fixed extends GameObject {

	public Fixed(float x, float y, int color, int size) {
//		super.setLocation(x, y); //This eventually calls the overridden setX and setY  - which is an error.
		super.setX(x);
		super.setY(y);

		super.setColor(color);
		super.setSize(size);
		// System.out.println(" Fixed Object Created: x= "+x +" y= "+y );
	}

	/**
	 * Fixed objects have nothing to update
	 * 
	 */
	@Override
	public void update(float elaspedSec) {
		return;
	}

	/**
	 * Fixed objects don't move
	 */
//	@Override
//	public void setLocation(float newX, float newY) {
//		System.out.println("Fixed - line 31: Should not print");
//
//		return;
//	}
//
//	/**
//	 * Fixed objects don't move
//	 */
//
//	@Override
//	public void setX(float x) {
//		System.out.println("Fixed - line 31: Should not print");
//
//		return;
//	}
//
//	/**
//	 * Fixed objects don't move
//	 */
//
//	@Override
//	public void setY(float y) {
//		System.out.println("Fixed - line 31: Should not print");
//
//		return;
//	}

	/**
	 * a detailed toString()
	 * 
	 * @param nothing
	 * @return returns a String detailing this object
	 */
	@Override
	public String toString() {
		String str = super.toString();
		return str;
	}
}
