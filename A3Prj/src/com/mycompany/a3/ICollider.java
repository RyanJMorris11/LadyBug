package com.mycompany.a3;

public interface ICollider {

	public boolean collidesWith(GameObject newObj);
	public void handleCollision(GameObject newObj);
}
