package com.mycompany.a3;

public interface ICollider {
	public boolean collideWith(ICollider gameObj);
	public void handleCollision(ICollider gameObj);
	
}
