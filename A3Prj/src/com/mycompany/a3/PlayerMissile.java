package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerMissile extends Missile implements ICollider, ISelectable{
	private boolean selected;
	private int size;
	private int color;
	
	public PlayerMissile(GameWorldProxy gw) {
		super(gw);
		this.size = 100;
		this.color = (ColorUtil.rgb(255, 0, 0));
		selected = false;
	}

	public String toString() {
		String Desc = "PS Missile loc = " + this.getLocation() + " color: " + "[" + ColorUtil.red(getColor()) + ", "
				+ ColorUtil.green(getColor()) + ", " + ColorUtil.blue(getColor()) + "]" + " speed = " + this.getSpeed()
				+ " dir = " + this.getHeading() + " fuel = " + this.getFuelLevel();
		return Desc;
	}
	
	

	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.color);
		g.fillRect((int) this.getLocation().getX(), (int) this.getLocation().getY(), size/2, size, (byte) 100);
	}

	public boolean collideWith(ICollider gameObject) {
		GameObject obj = (GameObject) gameObject;
		boolean result = false;
		int thisCenterX = (int) (this.getLocation().getX() + (this.size / 2)); // find centers
		int thisCenterY = (int) (this.getLocation().getY() + (this.size / 2));
		int otherCenterX = (int) (obj.getLocation().getX() + (obj.getSize() / 2));
		int otherCenterY = (int) (obj.getLocation().getY() + (obj.getSize() / 2));
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx * dx + dy * dy);
		// find square of sum of radii
		int thisRadius = 50 / 2;
		int otherRadius = obj.getSize() / 2;
		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		return result;
	}

	public void handleCollision(ICollider gameObject) {
		GameObject temp = (GameObject) gameObject;
		if (temp instanceof PlayerMissile) {
			this.setIsCollided(false);
			((GameObject) gameObject).setIsCollided(false);
		} else {
			this.setIsCollided(true);
			((GameObject) gameObject).setIsCollided(true);
		}

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

		if ((ptrX <= (locX + this.size/2)) && (ptrX >= locX) && (ptrY <= (locY + this.size)) && (ptrY >= locY)) {
			isContained = true;
			this.color = (ColorUtil.rgb(0, 0, 0));
		} else {
			this.color = (ColorUtil.rgb(255, 0, 0));
		}
		return isContained;
	}
}
