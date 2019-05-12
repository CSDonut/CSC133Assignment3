package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixGameObject {
    //BlinkRate and LightOn
    private int blinkRate;
    private boolean lightOn;
    private int base, height;

    public SpaceStation(GameWorldProxy gw){
    	super(gw);
        this.blinkRate = randomNumGen.nextInt(4);
        this.lightOn = true;
        base = 50;
        height = 150;
    }

    public void setBlinkRate(int blinkRate) {
        this.blinkRate = blinkRate;
    }

    public int getBlinkRate() {
        return blinkRate;
    }

    public void setLightOn(boolean lightOn) {
        this.lightOn = lightOn;
    }

    public boolean getLightOn(){
        return lightOn;
    }

    public boolean collideWith(ICollider gameObject) {
        GameObject obj = (GameObject) gameObject;
        boolean result = false;
        int thisCenterX = (int) (this.getLocation().getX() + (height/2)); // find centers
        int thisCenterY = (int) (this.getLocation().getY() + (height/2));
        int otherCenterX = (int) (obj.getLocation().getX() + (obj.getSize()/2));
        int otherCenterY = (int) (obj.getLocation().getY() + (obj.getSize()/2));
        // find dist between centers (use square, to avoid taking roots)
        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;
        int distBetweenCentersSqr = (dx*dx + dy*dy);
        // find square of sum of radii
        int thisRadius = height/2;
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
        String text = ("Space Station: " +
                " Location: " + this.getLocation() +
                " Color: [" + ColorUtil.red(getColor()) + ", "  +
                ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "]" +
                "rate = " + this.blinkRate
        );
        return text;
    }
    
    public void draw(Graphics g, Point pCmpRelPrnt) {
    	g.setColor(ColorUtil.rgb(0, 255, 200));
		g.fillRect((int)this.getLocation().getX(), 
				   (int)this.getLocation().getY(), 
				   base, height, (byte)100);
    }
    
}
