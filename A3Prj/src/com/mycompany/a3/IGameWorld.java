package com.mycompany.a3;

public interface IGameWorld {

    public void addPShip();
    public void addNonPlayerShips();
    public void addNewAsteroid();
    public void addNewStation();
    public void turnLauncher();
    public void missileKillNPS();
    public void npsMissileKillPShip();
    public void shootNPSMissiles();
    public void shootMissiles();
    public void reloadMissiles();
    public void shipCrash();
    public void ShipAndNonPShipCrash();
    public void npsAsteroidCrash();
    public void missileKillAsteroid();
    public void twoAsteroidCrash();
    public void PSLeftTurn();
    public void PSRightTurn();
    public void jump();
    public void decSpeed();
    public void incSpeed();
    public void printScore();
    public void timeTick(int ticker);
    public void map();
    public int getPlayerScore();
    public int getGameTime();
    public int getShipLife();
    public int getAmtMissiles();
	public GameCollection getCollection();
    }
