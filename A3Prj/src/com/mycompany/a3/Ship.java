package com.mycompany.a3;

public abstract class Ship extends MoveableGameObject implements IMoveable, ICollider {
    private int amtMissiles;
    

    public Ship(GameWorldProxy gw,int amtMissles){
    	super(gw);
        this.amtMissiles = amtMissles;
    }

    public int getAmtMissiles() {
        return amtMissiles;
    }

    public void setAmtMissiles(int amtMissiles) {
        this.amtMissiles = amtMissiles;
    }

}
