package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;

/**
 * The mapView class is responsible for outputting the text map and holding the
 * place that will be used for the graphical map.
 * 
 * @author ryanmorris
 *
 */
public class MapView extends Container implements Observer {

	private GameWorld gw;

	/**
	 * Constructor sets the needed values and style for Mapiew
	 * 
	 * @param newGw
	 */
	MapView(GameWorld newGw) {
		
		this.gw = newGw;
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255, 10, 10)));
		this.getAllStyles().setPadding(5, 5, 5, 5);
		this.getAllStyles().setMargin(2, 2, 2, 2);
		
	}

	/**
	 * Update is called by the observable class and is used to output the text map
	 * when ever a change is made.
	 * 
	 */
	public void update(Observable observable, Object data) {
		helpGameWorld();
		repaint(); // calls paint
		gw.outputMap();
		for (int clear = 0; clear < 10; clear++) {
			System.out.println("\b");
		}
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
//		int w = getWidth();
//		int h = getHeight(); 
		
//		gw.getLadybug().draw(g, getLoc());
		IIterator itr = 	gw.getGameObjects();
		while (itr.hasNext()) {
			((GameObject) itr.getNext()).draw(g, this.getLoc());
		}
	}
	
	
	/**
	 * This important method is called before GameWorld.Init() in order to set the
	 * width and height of the map
	 * 
	 */
	public void helpGameWorld() {
		System.out.println("MapView : Width " + getWidth() + " Height " + getHeight() );
		
		gw.setMapView(this);
	}

	public Point getLoc() {
		Point loc = new Point(getX(), getY());
		return loc;
	}
	
	public Point getAbsoluteLoc() {
		Point loc = new Point(getAbsoluteX(), getAbsoluteY());
		return loc;
	}


}
