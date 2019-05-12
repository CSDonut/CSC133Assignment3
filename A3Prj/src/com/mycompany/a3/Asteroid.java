package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import java.lang.Math;

public class Asteroid extends MoveableGameObject implements ICollider, ISelectable{
    private int size;
    private int color;
    private boolean selected;
    private GameWorldProxy gw;

    public Asteroid(GameWorldProxy gw){
    	super(gw);
    	super.setSize(this.size);
    	this.gw = gw;
        this.size = 50 + randomNumGen.nextInt(60);
        this.selected = false;
        this.color = ColorUtil.rgb(0, 0, 0);
    }

    public void move(int ticker){
        //Sorry to whoever has to read this
        setLocation(Math.round(this.getLocation().getX() + (Math.cos(Math.toRadians(90 - getHeading())) * (1 + getSpeed() * (double)ticker/10))),
                Math.round(this.getLocation().getY() + (Math.sin(Math.toRadians(90 - getHeading())) * (1 + getSpeed() * (double)ticker/10))));
    
    	if (this.getLocation().getX() + this.size > gw.getMapWidth() + gw.getMapX()) {
            this.setLocation(gw.getMapX(), this.getLocation().getY());
        } 
    	else if (this.getLocation().getX() < gw.getMapX()) {
            this.setLocation(gw.getMapWidth() + gw.getMapX() - this.getSize(), this.getLocation().getY());
        }
    	
        if (this.getLocation().getY() + this.size > gw.getMapHeight() + gw.getMapY()) {
            this.setLocation(this.getLocation().getX(), gw.getMapY());
        } 
        else if(this.getLocation().getY() < gw.getMapY()) {
            this.setLocation(this.getLocation().getX(), gw.getMapHeight() + gw.getMapY() - this.getSize());
        }
    	

    }

    public int getSize(){
        return size;
    }

    public String toString(){
        String Desc = "Asteroid: loc = " + this.getLocation() +
                " color: [" + ColorUtil.red(getColor()) + ", " + ColorUtil.green(getColor()) +
                ", " + ColorUtil.blue(getColor()) + "]" + " speed = " + getSpeed() +
                " dir = " + getHeading() + " size = " + getSize();
        return Desc;
    }
    
    public void draw(Graphics g, Point pCmpRelPrnt) {
    	g.setColor(this.color);
    	g.fillRoundRect((int)this.getLocation().getX(), (int)this.getLocation().getY(), this.size, this.size, 360, 360);
    	
    }
    
    public boolean collideWith(ICollider gameObject) {
        GameObject obj = (GameObject) gameObject;
        boolean result = false;
        int thisCenterX = (int) (this.getLocation().getX() + (this.size/2)); // find centers
        int thisCenterY = (int) (this.getLocation().getY() + (this.size/2));
        int otherCenterX = (int) (obj.getLocation().getX() + (obj.getSize()/2));
        int otherCenterY = (int) (obj.getLocation().getY() + (obj.getSize()/2));
        // find dist between centers (use square, to avoid taking roots)
        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;
        int distBetweenCentersSqr = (dx*dx + dy*dy);
        // find square of sum of radii
        int thisRadius = this.size/2;
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

	@Override
	public void setSelected(boolean yesNo) {
		this.selected = yesNo;
		
	}

	@Override
	public boolean isSelected() {
		return this.selected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		boolean isContained = false;
		int ptrX = pPtrRelPrnt.getX();
		int ptrY = pPtrRelPrnt.getY();
		int locX = (int) this.getLocation().getX();
		int locY = (int) this.getLocation().getY();

		if ((ptrX <= (locX + this.size)) && (ptrX >= locX) && (ptrY <= (locY + this.size)) && (ptrY >= locY)) {
			isContained = true;
			this.color = (ColorUtil.rgb(255, 0, 0));
		} else {
			this.color = (ColorUtil.rgb(0, 0, 0));
		}

		return isContained;
	}

}
