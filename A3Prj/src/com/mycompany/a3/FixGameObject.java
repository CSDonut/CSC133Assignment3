package com.mycompany.a3;
import java.util.Random;


public class FixGameObject extends GameObject {
    //ID
    private static int id;

    public FixGameObject(GameWorldProxy gw) {
    	super(gw);
        this.id = new Random().nextInt(200);
    }

    public int getID() {
        return this.id;
    }

}
