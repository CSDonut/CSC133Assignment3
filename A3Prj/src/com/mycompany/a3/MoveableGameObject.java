package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;

public abstract class MoveableGameObject extends GameObject implements IMoveable{
	private int speed;
    private int heading;
    MoveableGameObject(GameWorldProxy gw) {
		super(gw);
		speed = 2 + randomNumGen.nextInt(9); // range of 0 - 10
		heading = randomNumGen.nextInt(359); // range of 0 - 359
	}

    public int getHeading() { 
        return heading;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}