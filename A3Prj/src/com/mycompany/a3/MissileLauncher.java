package com.mycompany.a3;

public class MissileLauncher  extends MoveableGameObject{
	
    MissileLauncher(GameWorldProxy gw) {
		super(gw);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void move(int ticker) {
		setLocation(Math.round(this.getLocation().getX() + (Math.cos(Math.toRadians(90 - getHeading())) * (1 + getSpeed() * (double)ticker/10))),
                Math.round(this.getLocation().getY() + (Math.sin(Math.toRadians(90 - getHeading())) * (1 + getSpeed() * (double)ticker/10))));
    }
}
