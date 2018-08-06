
package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a3.GameObjectCollection.MyIterator;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UITimer;

import commandObjects.*;

import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Label;

import java.lang.String;

/**
 * The game class holds methods that deal with input from the user and
 * 
 * @author ryanmorris
 *
 */
public class Game extends Form implements Runnable {
	private GameWorld gw;
	private boolean paused;
	private final static int msPerFrame = 100;
	UITimer timer;

	// ToolBar
	private static Label ladyBugTitle = new Label("Lady Bug");
	private Toolbar myToolbar;
	private CheckBox myCheckBox;

	// Text Areas
	// private TextField inputFlagNum;

	// Containers
	private MapView myMapView;
	private ScoreView myScoreView;
	private Container leftContainer;
	private Container rightContainer;
	private Container bottomContainer;

	// Dialog Boxes
	// private static Dialog flagDialog;
	private Dialog exitDialog;

	// Commands
	private TurnLeft myLeft;
	private TurnRight myRight;
	private SlowDown mySlowDown;
	private SpeedUp mySpeedUp;

	// private SpiderAttack mySpiderAttack;
	// private FlagCollision myFlagCollision;
	// private Eat myEat;
	// private Tick myTick;

	private Pause myPause;
	private Position myPosition;
	private SelectObj mySelectObj;

	private SoundToggle mySound;
	private Help myHelp;
	private Exit myExit;
	private About myAbout;
	// private FlagNumOkay flagNumOkay;
	private CancelExit myCancelExit;
	private ConfirmExit myConfirmExit;

	// Buttons
	private Button buttonTurnLeft;
	private Button buttonTurnRight;
	private Button buttonSpeedUp;
	private Button buttonSlowDown;

	private Button buttonPause;
	private Button buttonPosition;

	private Button buttonExit;
	private Button buttonAbout;
	private Button buttonConfirmExit;
	private Button buttonCancelExit;
	private Button buttonSpeedUpSide;

	// Sound
	private Mp3Sound themeSong;


	/**
	 * This constructor generates and activates the gameWorld object Then calls the
	 * play() method.
	 * 
	 * @param Nothing
	 * @return Nothing
	 */
	public Game() {
		gw = new GameWorld();
		paused = false;
		this.setLayout(new BorderLayout());
		themeSong = new Mp3Sound("Automation.mp3");

		createCommands(gw);
		createCommandButtons(); // also some styling
		addCommandsToButtons();

		// Tool bar and side bar
		myCheckBox = new CheckBox("Sound");
		myCheckBox.getAllStyles().setBgTransparency(255);
		myCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		myCheckBox.setCommand(mySound);
		myToolbar = new Toolbar();
		this.setToolbar(myToolbar);
		myToolbar.setTitleComponent(ladyBugTitle);
		myToolbar.setTitleCentered(true);
		myHelp = new Help(this);
		myToolbar.addCommandToRightBar(myHelp);
		myToolbar.addComponentToSideMenu(buttonSpeedUpSide);
		myToolbar.addComponentToSideMenu(myCheckBox);
		myToolbar.addComponentToSideMenu(buttonAbout);
		myToolbar.addComponentToSideMenu(buttonExit);

		// ScoreView
		myScoreView = new ScoreView(gw);
		this.add(BorderLayout.NORTH, myScoreView);

		// MapView
		myMapView = new MapView(gw);
		myMapView.addPointerPressedListener(mySelectObj);

		this.add(BorderLayout.CENTER, myMapView);

		// LeftContainer
		leftContainer = new Container();
		leftContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		this.add(BorderLayout.WEST, leftContainer);
		addButtonsToLeftContainer();

		// Right Container
		rightContainer = new Container();
		rightContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		this.add(BorderLayout.EAST, rightContainer);
		addButtonsToRightContainer();

		// Bottom Container
		bottomContainer = new Container();
		bottomContainer.setLayout(new FlowLayout(Component.CENTER));
		this.add(BorderLayout.SOUTH, bottomContainer);
		addButtonsToBottomContainer();

		// DialogBoxes
		PrepareDialogBoxes();
		// Key Listeners
		PrepareKeyListeners();

		gw.addObserver(myScoreView);
		gw.addObserver(myMapView);

		this.show();

		myMapView.helpGameWorld(); // prepare Width and Height values in GameWorld
		gw.init(); // activate creation on GW objects
		myScoreView.init();
		timer = new UITimer(this);
		themeSong.play();

		timer.schedule(msPerFrame, true, this);
	} // end Game() constructor

	

	public void run() {

		if (paused == false) {

			MyIterator itr = gw.getGameObjects();
			Ladybug myLady = gw.getLadybug();

			while (itr.hasNext()) {
				GameObject obj = itr.getNext();

				if (obj != Ladybug.getInstance()) {
					if (myLady.collidesWith(obj)) {
						myLady.handleCollision(obj);
					}
				}
			}

			gw.tick(msPerFrame); // milliseconds elapsed 
		} else {
			timer.cancel();
		}
	} // end run()

	/**
	 * Functional method that prepares and adds Key listeners to the Game class
	 * 
	 */
	private void PrepareKeyListeners() {

		this.addKeyListener('a', mySpeedUp);
		this.addKeyListener('b', mySlowDown);
		this.addKeyListener('l', myLeft);
		this.addKeyListener('r', myRight);
		// this.addKeyListener('f', myEat);
		// this.addKeyListener('g', mySpiderAttack);
		// this.addKeyListener('t', myTick);
		this.addKeyListener('x', myExit);
	}

	/**
	 * Functional method that prepares and adds various dialog boxes to the Game
	 * class
	 * 
	 */
	private void PrepareDialogBoxes() {

		// Exit Dialog
		exitDialog = new Dialog("Are you sure you want to exit?");
		exitDialog.setLayout(new BorderLayout());
		Style exitDialogStyle = exitDialog.getAllStyles();
		exitDialogStyle.setBorder(Border.createEmpty());
		exitDialogStyle.setBgTransparency(255);
		exitDialogStyle.setBgColor(0xffffff);
		// exitDialog.add(BorderLayout.NORTH, "Confirm Exit");
		exitDialog.add(BorderLayout.WEST, buttonCancelExit);
		exitDialog.add(BorderLayout.EAST, buttonConfirmExit);

	}

	/**
	 * Activates the exit Dialog Box
	 * 
	 */
	public void activateExitDialog() {

		exitDialog.show();
	}

	/**
	 * Deactivates the exit Dialog Box
	 * 
	 */
	public void deactivateExitDialog() {

		exitDialog.dispose();
	}

	/**
	 * Activates the help Dialog Box that shows the user which keys will perform an
	 * action
	 * 
	 */
	public void activateHelpDialog() {
		String helpText = ("Keys:\n" + "\"a\"  to accelerate\n" + "\"b\" to break\n" + "\"l\" to turn left\n"
				+ "\"r\" to turn right\n" + "\"x\" to exit");
		Dialog.show("Help", helpText, "close", null);
	}

	/**
	 * Activates the About Dialog Box that shows the user into about this project
	 * 
	 */
	public void activateAboutDialog() {
		String aboutText = ("Author: Ryan Morris\n" + "Version: Project 2\n" + "Date: Aprilh 23rd, 2018\n"
				+ "Class: Csc 133 - OOP Graphics");
		Dialog.show("About", aboutText, "close", null);
	}

	/**
	 * Functional Method that creates all the command objects
	 * 
	 * @param gw
	 */
	private void createCommands(GameWorld gw) {
		myLeft = new TurnLeft(gw);
		myRight = new TurnRight(gw);
		mySlowDown = new SlowDown(gw);
		mySpeedUp = new SpeedUp(gw);

		// mySpiderAttack = new SpiderAttack(gw);
		// myFlagCollision = new FlagCollision(this);
		// myEat = new Eat(gw);
		// myTick = new Tick(gw);
		// flagNumOkay = new FlagNumOkay(this, gw);
		myPause = new Pause(this);
		mySelectObj = new SelectObj(gw, this);
		myPosition = new Position(gw, mySelectObj);

		myExit = new Exit(this);
		myAbout = new About(this);
		myCancelExit = new CancelExit(this);
		myConfirmExit = new ConfirmExit();
		mySound = new SoundToggle(gw,this);
	}

	/**
	 * Functional Method that creates all the command Buttons
	 * 
	 */
	private void createCommandButtons() {
		buttonTurnLeft = new MySideButton();
		buttonTurnRight = new MySideButton();
		buttonSlowDown = new MySideButton();
		buttonSpeedUp = new MySideButton();

		buttonPause = new MyXButton();
		buttonPosition = new MyXButton();
		Style bPosStyle = buttonPosition.getDisabledStyle();
		bPosStyle.setBgColor(ColorUtil.rgb(40, 40, 40));

		buttonExit = new Button();
		buttonAbout = new Button();

		buttonConfirmExit = new MyXButton();
		buttonCancelExit = new MyXButton();
		buttonSpeedUpSide = new MySideButton();
	}

	/**
	 * Functional Method that adds all the commands to all the buttons
	 * 
	 * @param gw
	 */
	private void addCommandsToButtons() {
		buttonTurnLeft.setCommand(myLeft);
		buttonTurnRight.setCommand(myRight);
		buttonSlowDown.setCommand(mySlowDown);
		buttonSpeedUp.setCommand(mySpeedUp);

		buttonPause.setCommand(myPause);
		buttonPosition.setCommand(myPosition);

		buttonExit.setCommand(myExit);
		buttonAbout.setCommand(myAbout);
		buttonConfirmExit.setCommand(myConfirmExit);
		buttonCancelExit.setCommand(myCancelExit);
		buttonSpeedUpSide.setCommand(mySpeedUp);
	}

	/**
	 * Adds particular buttons the left Container
	 * 
	 */
	private void addButtonsToLeftContainer() {
		leftContainer.add(buttonSpeedUp);
		leftContainer.add(buttonTurnLeft);
	}

	/**
	 * Function method that adds certain buttons to the right container
	 * 
	 */
	private void addButtonsToRightContainer() {
		rightContainer.add(buttonSlowDown);
		rightContainer.add(buttonTurnRight);
	}

	private void addButtonsToBottomContainer() {
		bottomContainer.add(buttonPause);
		bottomContainer.add(buttonPosition);
		buttonPosition.setEnabled(false);

	}

	/**
	 * Getter for the map view width
	 * 
	 * @return
	 */
	public int getMapViewWidth() {
		return myMapView.getWidth();
	}

	/**
	 * Getter for the map view height
	 * 
	 * @return
	 */
	public int getMapViewHeight() {
		return myMapView.getHeight();
	}

	public void togglePause() {
		
		if (paused == false) {
			
			paused = true;
			
			
			themeSong.pause();
			
			
			setButtonsPaused();
		} else {
			
			if(gw.soundOn()) {
				themeSong.play();
			}
			paused = false;
			gw.changeSelectedObj();
			setButtonsNotPaused();
			timer.schedule(msPerFrame, true, this);
		}
	}
	
	public void updateSoundPlay() {
		if(gw.soundOn() && paused==false) {
			
			themeSong.play();
		}else {
			themeSong.pause();
		}
	}

	private void setButtonsPaused() {
		// turn off buttons
		buttonTurnLeft.setEnabled(false);
		buttonTurnRight.setEnabled(false);
		buttonSpeedUp.setEnabled(false);
		buttonSlowDown.setEnabled(false);
		buttonSpeedUpSide.setEnabled(false);

		// turn on buttons
		buttonPosition.setEnabled(true);

	}

	private void setButtonsNotPaused() {
		// turn on buttons
		buttonTurnLeft.setEnabled(true);
		buttonTurnRight.setEnabled(true);
		buttonSpeedUp.setEnabled(true);
		buttonSlowDown.setEnabled(true);
		buttonSpeedUpSide.setEnabled(true);

		// turn off buttons
		buttonPosition.setEnabled(false);

	}

	public Point getMapOrigin() {
		return myMapView.getAbsoluteLoc();
	}

	public boolean isPaused() {
		return paused;
	}

	public static int getMsPerFrame() {
		return msPerFrame;
	}
}// end Game CLASS
