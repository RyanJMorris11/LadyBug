package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

/** The ScoreView Container is used to display basic information about the game status:
 * Ticks, Lives Left, Food Level, Health, etc
 * 
 * @author ryanmorris
 *
 */
public class ScoreView extends Container implements Observer {

	Label myTime;
	Label myLastFlag;
	Label myLives;
	Label myFood;
	Label myHealth;
	Label mySound;
	
	Label myTimeValue;
	Label myLastFlagValue;
	Label myLivesValue;
	Label myFoodValue;
	Label myHealthValue;
	Label mySoundValue;

	long time;
	int lastFlag;
	int lives;
	int food;
	int health;
	String sound;

	GameWorld gw;
	
/** constructor of ScoreView
 * 
 * @param newGw
 */
	ScoreView(GameWorld newGw) {

		gw = newGw;

		myTime = new Label(" Time: ");
		myLastFlag = new Label(" Last Flag: ");
		myLives = new Label(" Lives: ");
		myFood = new Label(" Food Level: ");
		myHealth = new Label(" Health Level: " );
		mySound = new Label(" Sound: " );
		
		this.setLayout(new FlowLayout(Component.CENTER));


	}
	public void init(){

		updateFields();

		myTimeValue = new Label(padString4(""+time) );

		myLastFlagValue = new Label("" + lastFlag);
		myLivesValue = new Label("" + lives);
		myFoodValue = new Label(""+padString4("" + food));

		myHealthValue = new Label("" + health);
		mySoundValue = new Label("" + sound);
		
		setAllPadding();
		
		this.add(myTime);
		this.add(myTimeValue);
		this.add(myLastFlag);
		this.add(myLastFlagValue);
		this.add(myLives);
		this.add(myLivesValue);
		this.add(myFood);
		this.add(myFoodValue);
		this.add(myHealth);
		this.add(myHealthValue);
		this.add(mySound);
		this.add(mySoundValue);
		revalidateAll();
		
	}
	
	/** Update is called by the observable class and is used to keep the scoreView Labels up to 
	 * date whenever a change is made.
	 * 
	 */
	public void update(Observable observable, Object data) {
		updateFields();
		updateLabels();
	}

	/** Functional method that updates the fields whenever update is called.
	 * 
	 */
	private void updateFields() {
		time = gw.getSeconds();
		try {
		lastFlag = gw.getLastFlagReached();
		}catch(NullPointerException e) {
			lastFlag=0;
		}
		lives = gw.getLives();
		food = (int) gw.getFoodLevel();
		health = (int) gw.getHealth();
		if (gw.getSound()) {
			sound = "On";
		} else {
			sound = "Off";
		}
	}

	/** Functional Method that updates the Labels whenever update is called.
	 * 
	 */
	private void updateLabels() {
		
		myTimeValue.setText(padString4(""+time) );
		myLastFlagValue.setText("" + lastFlag);
		myLivesValue.setText("" + lives);
		myFoodValue.setText(padString4("" + food));
		myHealthValue.setText(padString2("" + health));
		mySoundValue.setText(padString3("" +sound));
//		revalidateAll();
		
	}
	
	/** These helpful functions pad the content within the value labels so... 
	 * that they don't shift around as the values change
	 * 
	 * @param str
	 * @return a padded String
	 */
	private String padString4(String str) {
		str.trim();
		while(str.length()<4) {
			str = str+"    ";
		}
		return str;
	}
	private String padString3(String str) {
		str.trim();
		while(str.length()<3) {
			str = str+"    ";
;
		}
		return str;
	}
	private String padString2(String str) {
		str.trim();
		while(str.length()<2) {
			str = str+"    ";
		}
		return str;
	}

	
	/** This method is not used because it causes the labels of ScoreView to shift as the labels change.
	 * 	padString is used instead.
	 */
	private void revalidateAll() {
		myTimeValue.getParent().revalidate();
		myLastFlagValue.getParent().revalidate();
		myLivesValue.getParent().revalidate();
		myFoodValue.getParent().revalidate();
		myHealthValue.getParent().revalidate();
		mySoundValue.getParent().revalidate();
	}

	/** Sets the padding of all the value labels
	 * 
	 */
	private void setAllPadding() {
		setPadding(myTimeValue);
		setPadding(myLastFlagValue);
		setPadding(myLivesValue);
		setPadding(myFoodValue);
		setPadding(myHealthValue);
		setPadding(mySoundValue);	
	}
	
	/** Sets the padding of an individual label
	 * 
	 * @param myLabel
	 */
	private void setPadding(Label myLabel ){
		myLabel.getAllStyles().setPadding(LEFT, 2);
		myLabel.getAllStyles().setPadding(RIGHT, 2);
		
		myLabel.getAllStyles().setMargin(LEFT, 2);
		myLabel.getAllStyles().setMargin(RIGHT, 2);

	}

}
