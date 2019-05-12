package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;

public class NonPlayerMissile extends Missile implements ICollider{

    public NonPlayerMissile(GameWorldProxy gw) {
		super(gw);
		// TODO Auto-generated constructor stub
	}

    public boolean collideWith(ICollider gameObject) {
        GameObject obj = (GameObject) gameObject;
        boolean result = false;
        int thisCenterX = (int) (this.getLocation().getX() + (25)); // find centers
        int thisCenterY = (int) (this.getLocation().getY() + (25));
        int otherCenterX = (int) (obj.getLocation().getX() + (obj.getSize()/2));
        int otherCenterY = (int) (obj.getLocation().getY() + (obj.getSize()/2));
        // find dist between centers (use square, to avoid taking roots)
        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;
        int distBetweenCentersSqr = (dx*dx + dy*dy);
        // find square of sum of radii
        int thisRadius = 25;
        int otherRadius = obj.getSize()/2;
        int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
        + otherRadius*otherRadius);
        if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
        return result ;
    }
    
    public void handleCollision(ICollider gameObject) {
    		this.setIsCollided(true);
    		((GameObject)gameObject).setIsCollided(true);
    }
    
	public String toString() {
        String Desc =  "NPS Missile loc = " + this .getLocation() +
                " color: " + "[" + ColorUtil.red(getColor()) + ", " + ColorUtil.green(getColor()) +
                ", " + ColorUtil.blue(getColor()) + "]" + " speed = " + this.getSpeed() +
                " dir = " + this.getHeading() + " fuel = " + this.getFuelLevel();
        return Desc;
    }
}
