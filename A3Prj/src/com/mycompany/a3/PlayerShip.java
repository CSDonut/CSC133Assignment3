package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;


public class PlayerShip extends Ship implements ISteerable, ICollider{
    private int maxMissiles = 10;
    private MissileLauncher missleLauncher;
    private Triangle tri;
    private GameWorldProxy gw;
    private int triHeight = 100;
    private int triBase = 100;
    
    public PlayerShip(GameWorldProxy gw){
        super(gw, 0);
        this.gw = gw;
        this.setLocation(gw.getMapX()+ gw.getMapWidth()/2, gw.getMapY()+ gw.getMapHeight()/2);
        this.setSpeed(0);
        this.setHeading(0);
        missleLauncher = new MissileLauncher(gw);
        tri = new Triangle(triBase, triHeight, ColorUtil.rgb(255, 0, 0));
        
    }

    public void move(int ticker){
        //Sorry to whoever has to read this
    	setLocation(Math.round(this.getLocation().getX() + (Math.cos(90 - getHeading()) * (1 + getSpeed() * (double)ticker/10))),
                Math.round(this.getLocation().getY() + (Math.sin(90 - getHeading()) * (1 + getSpeed() * (double)ticker/10))));
    
    	if (this.getLocation().getX() + this.triBase/2 > gw.getMapWidth() + gw.getMapX()) {
            this.setLocation(gw.getMapX(), this.getLocation().getY());
        } else if (this.getLocation().getX() < gw.getMapX()) {
            this.setLocation(gw.getMapWidth() + gw.getMapX() - this.triBase/2, this.getLocation().getY());
        }
        if (this.getLocation().getY() + this.triHeight/2 > gw.getMapHeight() + gw.getMapY()) {
            this.setLocation(this.getLocation().getX(), gw.getMapY());
        } else if(this.getLocation().getY() < gw.getMapY()) {
            this.setLocation(this.getLocation().getX(), gw.getMapHeight() + gw.getMapY() - this.triHeight/2);
        }
    }



    public void shootMissles(){
        if(this.getAmtMissiles() > 0){
            this.setAmtMissiles(this.getAmtMissiles() - 1);
            System.out.println("Missile Fired");
        }else{
            System.out.println("Player out of missiles");
        }
    }

    public void refillMissiles(){
        this.setAmtMissiles(maxMissiles);
    }

    public int getLauncherHeading(){
        return this.missleLauncher.getHeading();
    }

    public void rotateLauncher(){
        this.missleLauncher.setHeading(this.getHeading() + 1);

    }

    public void steerLeft(){
        this.setHeading(this.getHeading() + 1);
        this.missleLauncher.setHeading(this.getHeading());
    }

    public void steerRight(){
        this.setHeading(this.getHeading() - 1);
        this.missleLauncher.setHeading(this.getHeading());
    }

    public void speedUp(){
        if(this.getSpeed() < 10){
            this.setSpeed(this.getSpeed() + 1);
            System.out.println("Debug: Ship speed increased");
        }
    }

    public void speedDown(){
        if(this.getSpeed() > 0){
            this.setSpeed(this.getSpeed() - 1);
            System.out.println("Debug: Ship speed decreased");
        }
    }
    
    public boolean collideWith(ICollider gameObject) {
        GameObject obj = (GameObject) gameObject;
        boolean result = false;
        int thisCenterX = (int) (this.getLocation().getX() + (triBase/2)); // find centers
        int thisCenterY = (int) (this.getLocation().getY() + (triBase/2));
        int otherCenterX = (int) (obj.getLocation().getX() + (obj.getSize()/2));
        int otherCenterY = (int) (obj.getLocation().getY() + (obj.getSize()/2));
        // find dist between centers (use square, to avoid taking roots)
        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;
        int distBetweenCentersSqr = (dx*dx + dy*dy);
        // find square of sum of radii
        int thisRadius = triBase/2;
        int otherRadius = obj.getSize()/2;
        int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
        + otherRadius*otherRadius);
        if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
        return result ;
    }
    
    public void handleCollision(ICollider gameObject) {
    	GameObject temp = (GameObject)gameObject;
    	if(temp instanceof PlayerMissile) {
    		this.setIsCollided(false);
    		((GameObject)gameObject).setIsCollided(false);
    	}
    	else {
    		this.setIsCollided(true);
    		((GameObject)gameObject).setIsCollided(true);
    	}
    		
    	
    }
    

    public String toString(){
        String Desc = "Player Ship: loc = " + this .getLocation() +
                " color: [" + ColorUtil.red(getColor()) + ", " + ColorUtil.green(getColor()) +
                ", " + ColorUtil.blue(getColor()) + "]" + " speed = " + this.getSpeed() +
                " dir = " + this.getHeading() + " missiles = " + this.getAmtMissiles() +
                " missile launcher dir = " + missleLauncher.getHeading();
        return Desc;
    }
    
    public void draw(Graphics g, Point pCmpRelPrnt) {
    	tri.draw(g, new Point((int) this.getLocation().getX(), 
    			  (int) this.getLocation().getY()));
    }
    
    
}
