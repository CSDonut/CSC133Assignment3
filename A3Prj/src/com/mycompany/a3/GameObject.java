package com.mycompany.a3;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;
import com.codename1.ui.Graphics;

public class GameObject{
    private Point2D location = new Point2D(0, 0); //Codename1's method for coordinates
    private int color = new ColorUtil().rgb(255, 255, 255);
    private int size;
    private boolean isCollided;
    Random randomNumGen = new Random();
    
    

    GameObject(GameWorldProxy gw) {
    	isCollided = false;
    	this.location.setX(new Random().nextInt((gw.getMapWidth()-gw.getMapX()) + 1) + gw.getMapX());
		this.location.setY(new Random().nextInt((gw.getMapHeight()-gw.getMapY()) + 1) + gw.getMapY());
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(double x, double y) {
        this.location.setX(x);
        this.location.setY(y);
    }

    public void setColor(int r, int g, int b) {
        this.color = ColorUtil.rgb(r, g, b);
    }

    public int getColor() {
        return color;
    }
    
	public void draw(Graphics g, Point pCmpRelPrnt) {	
		
	}
	
	public boolean collideWith(ICollider gameObj) {
		return false;
	}
	
	public void handleCollision(ICollider gameObj) {
		
	}
	
	public boolean getIsCollided() {
		return isCollided;
	}
	
	public void setIsCollided(boolean yesNo) {
		this.isCollided = yesNo;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

}
