package com.mycompany.a3;

/**
 * Interface that allows objects to steer such as the ladyBug
 * 
 * @author ryanmorris
 *
 */
public interface ISteerable {

	/**
	 * abstract method which is required in all "steerable" objects. Will allow
	 * Objects to change their heading.
	 * 
	 * @param changeDir
	 */
	public abstract void steer(int changeDir);

}
