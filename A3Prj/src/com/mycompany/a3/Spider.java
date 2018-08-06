package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * A terrifying black Spider of type movable Wants to eat the Ladybug
 * 
 * @author ryanmorris
 *
 */
public class Spider extends Movable {
	private Random rand = new Random();

	/**
	 * Constructor set relevant values of Ladybug
	 * 
	 * @param x
	 * @param y
	 */
	public Spider(int x, int y) {
		super();
		super.setLocation(x, y);
		super.setColor(ColorUtil.BLACK);
		super.setSize(rand.nextInt(40) + 15);
		super.setHeading(genRandHeading());
		super.setSpeed(125);

	}

	/**
	 * Update() is called every clock tick and updates the position and other values
	 * of this Spider object
	 * 
	 */
	public void update(float elaspedSec) {
		super.update(elaspedSec);
		updateRandHeading();
	}
	

	/**
	 * Randomly changes the heading of the spider + or - 5 degrees
	 * 
	 */
	public void updateRandHeading() {
		
		int num = rand.nextInt(60) - 30;
		super.addHeading(num);
		if(	this.getX() < 15 || 
			this.getX() > GameWorld.getMapWidth()-15 ||
			this.getY() < 15||
			this.getY() > GameWorld.getMapHeight()-15
			) 
		{
		super.addHeading(60);
		}

	}

	/**
	 * generates a new random heading between 0 to 360
	 * 
	 * @return int
	 */
	private int genRandHeading() {
		return rand.nextInt(360);
	}

	/**
	 * the color doesn't change
	 * 
	 */
	@Override
	public void setColor(int x) {
		return;
	}

	/**
	 * The size doesn't change
	 * 
	 */
	@Override
	public void setSize(int x) {
		return;
	}

	public void draw(Graphics g, Point loc) {

		g.setColor(getColor());
		int[] xPoints = {loc.getX()+ (int)this.getX()- getSize()/2 , loc.getX()+ (int)this.getX(), loc.getX()+ (int)this.getX()+getSize()/2}; 
		int[] yPoints = {loc.getY()+ (int)this.getY()-getSize()/2, loc.getY()+(int)this.getY()+getSize()/2, loc.getY()+(int)this.getY()-getSize()/2};
		
		g.drawPolygon(xPoints, yPoints, 3);
		
	}
}
