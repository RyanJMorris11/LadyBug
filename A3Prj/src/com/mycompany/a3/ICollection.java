package com.mycompany.a3;

/** Interface ICollection 
 * forces classes that implement ICollection to have getIterator() 
 * 
 * @author ryanmorris
 *
 */
public interface ICollection {
	
	public void add(GameObject obj);

	public Object getIterator();
}
