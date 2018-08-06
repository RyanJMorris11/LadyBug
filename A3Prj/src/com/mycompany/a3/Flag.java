package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Flag class creates objects that act as destinations for the user to reach in
 * this game
 * 
 * @author ryan morris
 */
public class Flag extends Fixed implements ISelectable{

	private int seqNum;
	private boolean selected;

	/**
	 * constructor sets the initial values of the flag
	 * 
	 * @param newX
	 * @param newY
	 * @param seqNum
	 */
	public Flag(float newX, float newY, int seqNum) {
		super(newX, newY, ColorUtil.CYAN, 40);
		
//		System.out.println("XXx= "+newX +" y= "+newY +" Seq= "+seqNum);
		this.seqNum = seqNum;
	}

	/**
	 * toString() returns a string that describes the object
	 * 
	 * @param nothing
	 * @return String
	 */
	@Override
	public String toString() {
		String str;
		str = "  " + super.toString();
		str += " seqNum=" + seqNum;
		return str;
	}

	/**
	 * GetSeqNumer() returns the sequence number of this Flag. A flag only counts if
	 * it has been reached in the proper order.
	 * 
	 * @return int 1-9
	 */
	public int getSeqNum() {
		return seqNum;
	}

	/**
	 * Color should not be changed
	 * 
	 */
	@Override
	public void setColor(int x) {
		return;
	}

	/**
	 * Size should not be changed
	 * 
	 */
	@Override
	public void setSize(int x) {
		return;
	}

	public void draw(Graphics g, Point loc) {
		g.setColor(getColor());
		int[] xPoints = {
				loc.getX()+ (int)this.getX()- getSize()/2 ,
				loc.getX()+ (int)this.getX(),
				loc.getX()+ (int)this.getX()+getSize()/2}; 
		int[] yPoints = {
				loc.getY()+ (int)this.getY()- getSize()/2 ,
				loc.getY()+ (int)this.getY()+getSize()/2,
				loc.getY()+(int)this.getY()-getSize()/2};
		
		if(selected) {
			g.drawPolygon(xPoints, yPoints,3);		
		} else {
			g.fillPolygon(xPoints, yPoints,3);		
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString(""+this.seqNum, loc.getX() + (int)getX()- getSize()/2+10, loc.getY()+ (int)getY()- getSize()/2);
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
