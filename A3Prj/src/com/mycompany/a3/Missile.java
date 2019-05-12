package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;

public class Missile extends MoveableGameObject{
    int fuelLevel;
    private GameWorldProxy gw;

    public Missile(GameWorldProxy gw){
    	super(gw);
    	this.gw = gw;
        fuelLevel = 200;
        this.setSpeed(1);
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public void decrementFuelLevel(){
        this.fuelLevel -= 1;
    }

    @Override
    public void move(int ticker) { 
    	setLocation(Math.round(this.getLocation().getX() + (Math.cos(Math.toRadians(90 - getHeading())) * (1 + getSpeed() * (double)ticker/10))),
                Math.round(this.getLocation().getY() + (Math.sin(Math.toRadians(90 - getHeading())) * (1 + getSpeed() * (double)ticker/10))));
    	
    	if (this.getLocation().getX() + 25 > gw.getMapWidth() + gw.getMapX()) {
            this.setLocation(gw.getMapX(), this.getLocation().getY());
        } else if (this.getLocation().getX() < gw.getMapX()) {
            this.setLocation(gw.getMapWidth() + gw.getMapX() - 25, this.getLocation().getY());
        }
        if (this.getLocation().getY() + 25 > gw.getMapHeight() + gw.getMapY()) {
            this.setLocation(this.getLocation().getX(), gw.getMapY());
        } else if(this.getLocation().getY() < gw.getMapY()) {
            this.setLocation(this.getLocation().getX(), gw.getMapHeight() + gw.getMapY() - 25);
        }
    }


}
