package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;

public class GameObjectCollection implements ICollection {

	private ArrayList<GameObject> myObjects;

	public GameObjectCollection() {
		myObjects = new ArrayList<GameObject>();
		
		System.out.println("MyObjects instantiated: "+ myObjects);
	}

	public static Ladybug getLadybug() {
		return Ladybug.getInstance();
	}

	/**
	 * adds a type GameObject to the myObject collection
	 */
	public void add(GameObject myObj) {
		//System.out.println("Added " +myObj + " to the list" );
		myObjects.add(myObj);
		//System.out.println("size= " +myObjects.size());
	}

	/**
	 * Makes a number of FoodStation Objects and puts them into the ObjectArrayList
	 * 
	 * @param num
	 */

	public void makeFood(int num) {
		
		while (num > 0) {
			add(new FoodStation(getRandX(), getRandY()));
			num--;
		}
	}

	/**
	 * Makes a number of Spider Objects and puts them into the ObjectArrayList
	 * 
	 * @param num
	 */

	public void makeSpiders(int num) {
		while (num > 0) {
			add(new Spider(getRandX(), getRandY()));
			num--;
		}
	}

	/**
	 * makeFlags() makes a number of flags minimum of 2 and max of 10. Flags have
	 * pseudo randomly generated coordinates, but each level (2-10) will have the
	 * same flag placement respectively. Loads the flags into the myObjects
	 * arraylist.
	 * 
	 * @param num
	 */

	public void makeFlags(int num) {
		Random rand = new Random();
		if (num > 10) {
			num = 10;
		}
		if (num < 2) {
			num = 2;
		}
		int nextY = 20;
		int nextX = 20;
		int seqNum = 1;

		for (int i = num; i >= 1; i--) {
//			System.out.println("x= "+nextX +" y= "+nextY +" Seq= "+seqNum);

			add(new Flag(nextX, nextY, seqNum));
//			System.out.println("i= "+i);
			seqNum++;
			if (i == 2) {
				System.out.println("Map loc: "+GameWorld.getMapWidth()+", "+GameWorld.getMapHeight());
				nextY = GameWorld.getMapHeight() - 30-63;
				nextX= GameWorld.getMapWidth() - 30;
			} else {
				nextY = rand.nextInt(GameWorld.getMapHeight()-60-63)+30;
				nextX = rand.nextInt(GameWorld.getMapWidth() -60   )+30;
			}
		}
	}


	public static Ladybug getLadyBug() {
		return Ladybug.getInstance();
	}

	/**
	 * makeLadyBug()makes a Ladybug for the user the ladybug is located at (20,20)
	 * which is the default position of the first flag
	 * @param gameWorld 
	 * 
	 */
	public void makeLadyBug(GameWorld gameWorld) {
		add(Ladybug.createInstance(gameWorld));
	}

	/**
	 * Generates a random Y coordinate to be used within the scope of the map
	 * 
	 * @return int random y
	 */
	public int getRandY() {
		Random rand = new Random();
		return rand.nextInt(GameWorld.getMapHeight()-20-63);
	}

	/**
	 * Generates a random X coordinate to be used within the scope of the map
	 * 
	 * @return int random X
	 */
	public int getRandX() {
		Random rand = new Random();
		return rand.nextInt(GameWorld.getMapWidth()-20);
	}

	/**
	 * Clears the Objects collection and destroys the singleton LadyBug
	 */
	public void clear() {
		myObjects = null;
		getLadyBug().destroy();
	}

	public MyIterator getIterator() {
		if (myObjects != null) {
			MyIterator iter = new MyIterator(myObjects.size());
			return iter;
		} else {
			System.out.println("ERROR: line 156: GameWorldObjects not created: " + myObjects);
			return null;  
		}
	}

	/**
	 * Class Iterator
	 * 
	 * Contains the objects of the game objects collection
	 */
	public class MyIterator implements IIterator {
		private int index;
		private int length;
		Object[] Iter;

		/**
		 * Constructor for the MyIterator class
		 * 
		 * @param newLength
		 *            // the length of the GameObjectsCollection.
		 */
		private MyIterator(int newLength) {
			if (myObjects == null) {
				System.out.println("ERROR: line 180: GameWorldObjects not created: " + myObjects);
				return;
			}
			this.length = newLength;
			Iter = new Object[length];
			myObjects.toArray(Iter);
			index = 0;
			// System.out.println("This:" + Iterator[0]);
			// System.out.println("Line 166: index = " +index +" Length = "+ length);
		}

		// Returns true if there is at least one more element in the iterator array
		public boolean hasNext() {
			// System.out.println("Line 173: index = " + index + " Length = " + length);
			if (index < length) {
				return true;
			} else
				return false;
		}

		// Returns the next object is the Iterator array.
		public GameObject getNext() {
			// System.out.println(" Gotten index= " + index);
			// System.out.println("This:" + Iterator[0]);
			return (GameObject) Iter[index++];
		}
	}

}// end GameObjectCollection
