package com.mycompany.a3;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {

    private GameWorld gw;

    public GameWorldProxy(GameWorld gw){
        this.gw = gw;
    }

    public int getPlayerScore(){
        return gw.getPlayerScore();
    }

    public void addNewAsteroid(){
        gw.addNewAsteroid();

    }
    public void addNewStation(){
        gw.addNewStation();
    }

    public void shipCrash(){
        gw.shipCrash();
    }

    public void decSpeed(){
        gw.decSpeed();
    }

    public void missileKillNPS(){
        gw.missileKillNPS();
    }

    public void npsMissileKillPShip(){
        gw.npsMissileKillPShip();
    }

    public void shootMissiles(){
        gw.shootMissiles();
    }

    public void ShipAndNonPShipCrash(){
        gw.ShipAndNonPShipCrash();
    }

    public void incSpeed(){
        gw.incSpeed();
    }

    public void npsAsteroidCrash(){
        gw.npsAsteroidCrash();
    }

    public void jump(){
        gw.jump();
    }

    public void missileKillAsteroid(){
        gw.missileKillAsteroid();
    }

    public void PSLeftTurn(){
        gw.PSLeftTurn();
    }

    public void shootNPSMissiles(){
        gw.shootNPSMissiles();
    }

    public void printScore(){
        gw.printScore();
    }

    public void addPShip(){
        gw.addPShip();
    }

    public void timeTick(int ticker){
        gw.timeTick(ticker);
    }

    public void map(){
        gw.map();
    }

    public void reloadMissiles(){
        gw.reloadMissiles();
    }

    public void PSRightTurn(){
        gw.PSRightTurn();
    }

    public void twoAsteroidCrash(){
        gw.twoAsteroidCrash();
    }

    public void addNonPlayerShips(){
        gw.addNonPlayerShips();
    }

    public void turnLauncher(){
        gw.turnLauncher();
    }


    public int getGameTime() {
        return gw.getGameTime();
    }

    @Override
    public int getShipLife() {
        return gw.getShipLife();
    }

    @Override
    public int getAmtMissiles() {
        return gw.getAmtMissiles();
    }

	@Override
	public GameCollection getCollection() {
		// TODO Auto-generated method stub
		return gw.getCollection();
	}
	
	public int getMapWidth(){
		return gw.getMapWidth();
	}
	
	public int getMapHeight(){
		return gw.getMapHeight();
	}
	
	public int getMapX() {
		return gw.getMapX();
	}
	
	public int getMapY() {
		return gw.getMapY();
	}
}
