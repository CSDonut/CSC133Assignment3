package com.mycompany.a3;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.mycompany.a3.GameCollection.VectorIterator;



public class GameWorld extends Observable implements IGameWorld, IObservable{
    Random random = new Random();
    private Vector<IObserver> myObserverList = new Vector<IObserver>();
    private int shipLife, points, gameTime, asteroidAmt, nonPlayerShipsAmt, NPSCounter, AsteroidCounter;
    private boolean shipExist, soundOn, pauseBool;
    private GameCollection collection;
    private int mapWidth;
	private int mapHeight;
	private int mapX;
	private int mapY;
	private GameWorldProxy gwp;
    private SoundBG bgSound;  
    private SoundGameOver gameOverSound;
    private SoundHit soundHit;
    private SoundMissile soundMissile;
    
    GameWorld(){
    	gwp = new GameWorldProxy(this);
    	init();
    }

    public void init() {
        shipLife = 3;
        points = 0;
        asteroidAmt = 0;
        NPSCounter = 0;
        shipExist = false;
        collection = new GameCollection();
        soundOn = false;
        bgSound = new SoundBG("BackgroundMusic.mp3");
        gameOverSound = new SoundGameOver("GameOver.mp3");
        soundHit = new SoundHit("Collision.wav");
        soundMissile = new SoundMissile("Missile.wav");
        bgSound.play();
        notifyObservers();
    }
    //addtional methods here to manipulate world objects
    //and related game state data

    public void notifyObservers() {
        this.setChanged();
        this.notifyObservers(new GameWorldProxy(this));
    }

    public void addObservers(IObserver observer) {
        myObserverList.add(observer);
    }

    public void addNewStation(){
    	collection.add(new SpaceStation(gwp));
        notifyObservers();
        //System.out.println("A new Space Station has been created");

    }
    public void addNonPlayerShips(){
        NonPlayerShips nps = new NonPlayerShips(gwp);
        nonPlayerShipsAmt++;
        NPSCounter++;
        collection.add(nps);
        //System.out.println("A new NPS has been created");
        notifyObservers();

    }
    public void setNPSCounter(int counter) {
    	NPSCounter = counter;
    }
    
    public int getNPSCounter() {
    	return NPSCounter;
    }

    public void addNewAsteroid() {
        //create asteroid object
        Asteroid asteroid = new Asteroid(gwp);
        asteroidAmt++;
        AsteroidCounter++;
        //Add asteroid to storage vector
        collection.add(asteroid);
        //Tell user you created an asteroid
       // System.out.println("A new ASTEROID has been created");
        notifyObservers();

    }
    
    public void setAsteroidCounter(int counter) {
    	AsteroidCounter = counter;
    }
    
    public int getAsteroidCounter() {
    	return AsteroidCounter;
    }

    public void addPShip(){
        if(!shipExist){
            PlayerShip pShip = new PlayerShip(gwp);
            collection.add(pShip);
            //System.out.println("A new SHIP has been created");
            pShip.setAmtMissiles(10);
            shipExist = true;

        }
        else
           // System.out.println("Ship already created");
        
        notifyObservers();

    }

    public void eliminate(){
        boolean didRemove = false;
//        collection.removeElementAt(0);
        notifyObservers();

    }

    private void isDead() {
        if (shipLife > 0) {
        	shipLife--;
        	collection.remove((GameObject)getShip());
        	shipExist = false;
            this.addPShip();
            shipExist = true;
        }

        else if (shipLife == 0){
        	collection.remove((GameObject)getShip());
            //System.out.print("GAME OVER");
            shipExist = false;
            bgSound.pause();
            gameOverSound.play();
            Boolean bOK = Dialog.show("GAME OVER", "Start over?", "GO", "END IT");
            if(bOK) {
            	gameOverSound.pause();
            	newGame();
            }
            else {
            	Display.getInstance().exitApplication();
            }
        }
   
        notifyObservers();
    }


    public void map(){
        System.out.println("=========================================");
//        for(int i = 0; i < store.size(); i++){
//            System.out.println(store.elementAt(i).toString());
//        }
        System.out.println("=========================================");
    } 

    public PlayerShip getShip(){
    	PlayerShip pShip = new PlayerShip(gwp);
    	VectorIterator coll = collection.getIterator(); 
		while(coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof PlayerShip) {
				pShip = (PlayerShip) temp; 
			}	
		}
		return pShip;
    }


    public Asteroid getAsteroid() {

    	Asteroid asteroid = new Asteroid(gwp);
    	VectorIterator coll = collection.getIterator(); 
		while(coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof Asteroid) {
				asteroid = (Asteroid) temp;
			}	
		}
		return asteroid;
    }

    public NonPlayerShips getNonPlayerShips() {
    	NonPlayerShips NPS = null;
    	VectorIterator coll = collection.getIterator(); 
		while(coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof NonPlayerShips) {
				NPS = (NonPlayerShips) temp;
			}	
		}
		return NPS;
    }

    public PlayerMissile getPlayerMissile() {
    	PlayerMissile PMissile = null;
    	VectorIterator coll = collection.getIterator(); 
		while(coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof PlayerMissile) {
				PMissile = (PlayerMissile) temp;
			}	
		}
		return PMissile;
    }

    public NonPlayerMissile getNonPlayerMissile() {
    	NonPlayerMissile NPMissile = null;
    	VectorIterator coll = collection.getIterator(); 
		while(coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof NonPlayerMissile) {
				NPMissile = (NonPlayerMissile) temp;
			}	
		}
		return NPMissile;
    }

    public SpaceStation getSpaceStation() {
    	SpaceStation station = null;
    	VectorIterator coll = collection.getIterator(); 
		while(coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof SpaceStation) {
				station = (SpaceStation) temp;
			}	
		}
		return station;
    }

    public boolean doesShipExist(){
        boolean bool = false;
        VectorIterator coll = collection.getIterator(); 
        while(coll.hasNext()) {
			GameObject temp = coll.next();
			if (temp instanceof PlayerShip) {
				bool = true;
			}	
		}
        return bool;
    }

    public void shipCrash() {
        if (doesShipExist() && asteroidAmt >= 0) {
            if(shipLife >= 0){
            	collection.remove((GameObject)getShip());
            	collection.remove((GameObject)getAsteroid());
                shipLife--; 	
                asteroidAmt--;
            }
            shipExist = false;
            isDead();
        }
        else{
            //System.out.println("Cannot crash if there are no ships or asteroids");
        }
        notifyObservers();
    }

    public void npsAsteroidCrash() {
       
        if (getNonPlayerShips() != null && getAsteroid() != null) {
        	collection.remove((GameObject)getNonPlayerShips());
        	collection.remove((GameObject)getAsteroid());            
            nonPlayerShipsAmt--;
            asteroidAmt--;
            isDead();
        }
        else{
            //System.out.println("Cannot crash if there are no NPSships or asteroids");
        }
        notifyObservers();

    }

    public void twoAsteroidCrash(){
    	collection.remove((GameObject)getAsteroid());            
        notifyObservers();
    }

    public void ShipAndNonPShipCrash(){
        if(getNonPlayerShips() != null && getShip() != null){
            nonPlayerShipsAmt--;
            shipLife--;
            collection.remove((GameObject)getNonPlayerShips());            
            collection.remove((GameObject)getShip());            
        }
        else{
            //System.out.println("No ships to crash into");
        }
        shipExist = false;
        isDead();
        notifyObservers();

    }

    public void missileKillAsteroid(){

        if(getPlayerMissile() != null && getAsteroid() != null){
        	collection.remove((GameObject)getAsteroid());            
        	collection.remove((GameObject)getPlayerMissile());            
            points += 3;
           // System.out.println("test in missileKillAsteroid()");
        }
        notifyObservers();

    }

    public void missileKillNPS(){
        if(getPlayerMissile() != null && getNonPlayerShips() != null){
        	collection.remove((GameObject)getPlayerMissile());            
        	collection.remove((GameObject)getNonPlayerShips());            
            points += 10;
        }
        notifyObservers();
    }

    public void npsMissileKillPShip(){
		GameCollection coll = new GameCollection();
        if(getNonPlayerMissile() != null && getShip() != null){
            coll.remove((GameObject)getNonPlayerMissile());            
            coll.remove((GameObject)getShip());            
            shipLife--;
        }
        shipExist = false;
        isDead();
        notifyObservers();
    }

    public void incSpeed(){
        if(doesShipExist()) {
            if (getShip().getSpeed() < 10) {
                getShip().setSpeed(getShip().getSpeed() + 1);
                //System.out.println("Debug: Ship speed increased");
            } else {
                //System.out.println("Debug: Ship is already at max speed");
            }
        }
        else
            //System.out.println("No ship to speed up");
        notifyObservers();

    }

    public void decSpeed(){
        if(doesShipExist()){
            if(getShip().getSpeed() > 0){
                getShip().setSpeed(getShip().getSpeed() - 1);
                //System.out.println("Debug: Ship speed decreased");
            }
            else {
               // System.out.println("Debug: Ship speed at 0");
            }
        }
        else {
            //System.out.println("No ship to speed up");
        }
        notifyObservers();


    }

    public void PSLeftTurn(){
        if(doesShipExist()){
            getShip().steerLeft();
            //System.out.println("Ship turned left");
        }
        else
            //System.out.println("Ship does not exist");
        notifyObservers();

    }

    public void PSRightTurn(){
        if(doesShipExist()){
            getShip().steerRight();
            //System.out.println("Ship turned right");
        }
        else
            //System.out.println("Ship does not exist");
        notifyObservers();

    }

    public void reloadMissiles(){
        getShip().refillMissiles();
        //System.out.println("Missiles have been reloaded");
        notifyObservers();

    }

    public void turnLauncher(){
        if(doesShipExist()){
            getShip().rotateLauncher();
           // System.out.println("Launcher rotating");
        }
        else
           // System.out.println("No ship to turn launcher with");
        notifyObservers();

    }

    public void shootMissiles(){

        if(doesShipExist() && getShip().getAmtMissiles() > 0){
            PlayerMissile pMissile = new PlayerMissile(gwp);
            pMissile.setLocation(getShip().getLocation().getX(),
                    getShip().getLocation().getY());
            pMissile.setHeading(getShip().getLauncherHeading());
            pMissile.setSpeed(getShip().getSpeed() + 2);
            getShip().shootMissles();
            collection.add((GameObject)pMissile);
            points += 1;

        }
            //System.out.println("Player ship does not exist");
        soundMissile = new SoundMissile("Missile.wav");
        soundMissile.play();
        notifyObservers();


    }

    public void shootNPSMissiles(){
        if(getNonPlayerShips() != null){
            NonPlayerMissile npsMissile = new NonPlayerMissile(gwp);
            npsMissile.setLocation(getShip().getLocation().getX(),
                    getShip().getLocation().getY());
            npsMissile.setHeading(getShip().getLauncherHeading());
            npsMissile.setSpeed(getShip().getSpeed() + 2);
            getNonPlayerShips().shootMissles();
            collection.add((GameObject)npsMissile);
        }
        else
           // System.out.println("Player ship does not exist");
        soundMissile.play();
        notifyObservers();

    }

    public void jump(){
       // System.out.println("Hyperspace jump to center of map");
        getShip().setLocation(this.getMapX()+ this.getMapWidth()/2,
        		this.getMapY()+ this.getMapHeight()/2);
        notifyObservers();

    }

    public void timeTick(int ticker){
        gameTime++;
        VectorIterator iterator = collection.getIterator();
        VectorIterator iterator2 = collection.getIterator();
        PlayerMissile pMissile = getPlayerMissile();
        NonPlayerMissile nPMissile = getNonPlayerMissile();
        SpaceStation spaceStation = getSpaceStation();
        GameObject gameObj = new GameObject(gwp);
        
        if(spaceStation != null){
            int blinkRate = spaceStation.getBlinkRate();
            if(blinkRate % gameTime == 0)
            	spaceStation.setLightOn(false);
            else
            	spaceStation.setLightOn(true);
        }

        if(pMissile!= null){
            if(pMissile.getFuelLevel() > 0)
            	pMissile.decrementFuelLevel();
            else
            	collection.remove((GameObject)pMissile);
        }

        if(nPMissile != null){
            if(nPMissile.getFuelLevel() > 0)
            	nPMissile.decrementFuelLevel();
            else
            	collection.remove((GameObject)nPMissile);
        }

        while(iterator.hasNext()) {
			GameObject temp = iterator.next();
			if (temp instanceof IMoveable) {
				((IMoveable) temp).move(ticker);
			}	
		}
        
		iterator = collection.getIterator();
		while (iterator.hasNext()) {
			gameObj = iterator.next();
			iterator2 = collection.getIterator();
			while (iterator2.hasNext()) {
				GameObject temp2 = iterator2.next();
				if (temp2 instanceof ICollider) {
					ICollider otherObj = (ICollider) temp2;
					if (otherObj != gameObj) {
						if (gameObj.collideWith(otherObj)) {
							gameObj.handleCollision(otherObj);
//							 iterator = collection.getIterator();
//							 iterator2 = collection.getIterator();
						}
					}
				}
			}
		}

		iterator = collection.getIterator();
		while(iterator.hasNext()) {
			GameObject tempGameObject = iterator.next();
			if(tempGameObject.getIsCollided()) {
				if(tempGameObject instanceof PlayerShip) {
					isDead();
					iterator = collection.getIterator();
				}
				
				else {
					if(tempGameObject instanceof NonPlayerShips) 
						NPSCounter--;
					
					if(tempGameObject instanceof Asteroid)
						AsteroidCounter--;
					
					
					collection.remove(tempGameObject);
					iterator = collection.getIterator();
				}
			}
		}
//		soundHit = new SoundHit("Collision.wav");
		soundHit.play();		
		notifyObservers();
	}

    public void printScore() {
        System.out.println(
                "Score: " + points +
                        "\nAmount of missiles " + getShip().getAmtMissiles() +
                        "\nelapsed time: " + gameTime);
    }

    public int getPlayerScore() {
        return this.points;
    }

    public int getGameTime() {
        return this.gameTime;
    }

    public int getShipLife() {
        return this.shipLife;
    }

    public int getAmtMissiles(){
        return getShip().getAmtMissiles();
    }

	public GameCollection getCollection() {
		return this.collection;
	}
	
	public void soundOffOn() {
		if(soundOn) {
			soundOn = false; 
			bgSound.pause();
		}
		else {
			soundOn = true; 
			bgSound.play();
		}
	}
	
	public boolean getSound() {
		return soundOn;
	}

	public int getMapX() {
		return mapX;
	}
	
	public int getMapY() {
		return mapY;
	}
	
	public void setMapXY(int x, int y) {
		mapX = x;
		mapY = y;
	}
	
	public int getMapWidth(){
		return this.mapWidth;
	}
	
	public int getMapHeight(){
		return this.mapHeight;
	}
	
	public void setMapHeightWidth(int w, int h) {
		mapWidth = w;
		mapHeight = h;
	}

	public void pause() {
		pauseBool = true;
		bgSound.pause();
        notifyObservers();
	}
	
	public void play() {
		pauseBool = false;
		bgSound.play();
        notifyObservers();
	}
	
	public void newGame() {
		while(collection.getIterator().hasNext()) {
			collection.remove(collection.getIterator().next());
			bgSound.pause();
			notifyObservers();
		}
		AsteroidCounter = NPSCounter = 0;
		
		this.init();
	}
	public boolean getPauseBool() {
		return this.pauseBool;
	}
	
	
}
