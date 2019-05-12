package com.mycompany.a3;
import java.lang.Math;
import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


public class NonPlayerShips extends Ship implements IMoveable, ICollider{
    private int size;
    private Triangle tri;
    private GameWorldProxy gw;
    
    public NonPlayerShips(GameWorldProxy gw){
        super(gw, 2);
        this.gw = gw;
        
        if(randomNumGen.nextInt(10) %2 / 2 == 0 ){
            size = 40;
        }
        else
            size = 60;
        
        tri = new Triangle((size * 2), (size * 2), ColorUtil.rgb(0, 0, 0));
    }
    
    
    @Override
    public void move(int ticker) {
    	setLocation(Math.round(this.getLocation().getX() + (Math.cos(Math.toRadians(90 - getHeading())) * (1 + getSpeed() * (double)ticker/10))),
                Math.round(this.getLocation().getY() + (Math.sin(Math.toRadians(90 - getHeading())) * (1 + getSpeed() * (double)ticker/10))));

    	if (this.getLocation().getX() + this.size > gw.getMapWidth() + gw.getMapX()) {
            this.setLocation(gw.getMapX(), this.getLocation().getY());
        } else if (this.getLocation().getX() < gw.getMapX()) {
            this.setLocation(gw.getMapWidth() + gw.getMapX() - this.getSize(), this.getLocation().getY());
        }
        if (this.getLocation().getY() + this.size > gw.getMapHeight() + gw.getMapY()) {
            this.setLocation(this.getLocation().getX(), gw.getMapY());
        } else if(this.getLocation().getY() < gw.getMapY()) {
            this.setLocation(this.getLocation().getX(), gw.getMapHeight() + gw.getMapY() - this.getSize());
        }
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
    
	
	public void handleCollision(ICollider gameObj) {
		GameObject gameObj1 = (GameObject)gameObj;
		this.setIsCollided(true);
		gameObj1.setIsCollided(true);
	}


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void shootMissles(){
        if(this.getAmtMissiles() > 0){
            this.setAmtMissiles(this.getAmtMissiles() - 1);
            System.out.println("Missile Fired");
        }else{
            System.out.println("NPS out of missiles");
        }
    }

    @Override
    public String toString() {
        String Desc = "Non-Player Ship: loc = " + this.getLocation() +
                " color: [" + ColorUtil.red(getColor()) + ", " + ColorUtil.green(getColor()) +
        ", " + ColorUtil.blue(getColor()) + "]" + " speed = " + getSpeed() +
                " dir = " + getHeading() + " size = " + getSize();
        return Desc;
    }
    
    public void draw(Graphics g, Point pCmpRelPrnt) {
    	tri.draw(g, new Point((int) this.getLocation().getX(), 
    			  (int) this.getLocation().getY()));
    }
}	
