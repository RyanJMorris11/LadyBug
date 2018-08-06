package com.mycompany.a3;

public interface ISelectable {
	
	
	
	
	/** takes an x and y coordinate and checks to see if this matched this objects position
	 * 
	 */
	public GameObject findSelectedObj(int x, int y);
	
	/** changes the way this object looks so that we can tell that it's been selected
	 * 
	 */
	public void selectGameObj();
	
	/** changes the way this object looks upon not being selected so that it looks normal.
	 * 
	 */
	public void unselectObj();
}
