package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Toolbar;
import com.mycompany.a3.myCommands.*;
import java.lang.String;
import java.util.Random;

public class Game extends Form implements Runnable {
    private GameWorld gw;
    private MapView mv;
    private PointsView pv;
    private UITimer timer; 
    private int ticker;
    int min, max;

    public class customButton extends Button{
      private customButton(String string) {
            super(string);
            getAllStyles().setBgTransparency(225);
            getAllStyles().setBgColor(ColorUtil.rgb(169,169,169));
            getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 0));
            getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
        }
    }

    public Game() {
        gw = new GameWorld();		// create "Observable"
        mv = new MapView(gw);			// creates an observer for PointsView
        pv = new PointsView(gw);	// creates an observer for MapView
        gw.addObserver(mv);			// registers a map observer
        gw.addObserver(pv);			// registers a point observer
        min = 0;
        max = 600;
        
        //Controls the movement of the game
        ticker  = 1;
        timer = new UITimer(this);
        timer.schedule(ticker, true, this);


        //Containers for GUI
        this.setLayout(new BorderLayout());
        
        
        Toolbar hamburgerMenu = new Toolbar();
        setToolbar(hamburgerMenu);
        hamburgerMenu.getAllStyles().setPadding(1, 0, 0, 0);
        Label label = new Label("Asteroids");
        hamburgerMenu.setTitleComponent(label);

        //Creating Top container for points view
        Container topContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        topContainer.getAllStyles().setBorder(Border.createLineBorder(4,
                ColorUtil.BLACK));
        add(BorderLayout.NORTH, topContainer);
        add(BorderLayout.NORTH, pv);

        //Creating side menu container
        Container SideMenuContainer = new Container(new GridLayout(21,1));
        SideMenuContainer.getAllStyles().setBorder(Border.createLineBorder(4,
                ColorUtil.BLACK));
        add(BorderLayout.WEST,SideMenuContainer);

        //Add map view into game 
        add(BorderLayout.CENTER, mv);
        
     
        /*Overflow Buttons*/
        /*======================================================================================================*/

		
        
        
		Button about = new Button("About");
		AboutCmd myAboutCmd = new AboutCmd(gw);
		hamburgerMenu.addCommandToOverflowMenu(myAboutCmd);

		Button newGame = new Button("New"); 
		NewGameCmd myNewGameCmd = new NewGameCmd(gw);
		hamburgerMenu.addCommandToOverflowMenu(myNewGameCmd);
		
		Button save = new Button("Save");
		SaveCmd mySaveCmd = new SaveCmd(gw);
		hamburgerMenu.addCommandToOverflowMenu(mySaveCmd);
		
		Button undo = new Button("Undo");
		UndoCmd myUndoCmd = new UndoCmd(gw);
		hamburgerMenu.addCommandToOverflowMenu(myUndoCmd);
		
        CheckBox sound = new CheckBox("Sound");
        sound.setSelected(gw.getSound()); 
        hamburgerMenu.setTitleComponent(label);
		

        /*Command Buttons*/
        /*======================================================================================================*/

        //Add Asteroid button
        customButton addAsteroidButton = new customButton("Add Asteroid.");
        //Set commands
        AddAsteroidCmd myAddAsteroid = new AddAsteroidCmd(gw);
        addAsteroidButton.setCommand(myAddAsteroid);
        addKeyListener('a', myAddAsteroid);
        SideMenuContainer.add(addAsteroidButton);
        hamburgerMenu.addCommandToSideMenu(myAddAsteroid);

        //Add Player Ship Button
        customButton addPlayerShipButton = new customButton("Add Player Ship");
        SideMenuContainer.add(addPlayerShipButton);
        //Set commands
        AddPShipCmd myAddPlayerShip = new AddPShipCmd(gw);
        addPlayerShipButton.setCommand(myAddPlayerShip);
        addKeyListener('s', myAddPlayerShip);
        hamburgerMenu.addCommandToSideMenu(myAddPlayerShip);

        //Add Space Station Button 
        customButton addSpaceStationButton = new customButton("Add Space Station");
        SideMenuContainer.add(addSpaceStationButton);
        //Set commands
        AddNewStationCmd myStation = new AddNewStationCmd(gw);
        addSpaceStationButton.setCommand(myStation);
        addKeyListener('b', myStation);
        hamburgerMenu.addCommandToSideMenu(myStation);


        //Add Non Player Ship Button
        customButton addNonPlayerShipButton = new customButton("Add Non-Player Ship");
        SideMenuContainer.add(addNonPlayerShipButton);
        //Set commands
        AddNonPlayerShipsCmd myNonPlayerShips = new AddNonPlayerShipsCmd(gw);
        addNonPlayerShipButton.setCommand(myNonPlayerShips);
        addKeyListener('y', myNonPlayerShips);
        hamburgerMenu.addCommandToSideMenu(myNonPlayerShips);

        //Accelerate Button
        //Binding up arrow key to increase ship speed (Up arrow key code = -91)
        customButton addIncreaseSpeedButton = new customButton("Accelerate Ship");
        //Set commands
        ShipIncreaseSpeedCmd myIncreaseSpeed = new ShipIncreaseSpeedCmd(gw);
        addIncreaseSpeedButton.setCommand(myIncreaseSpeed);
        addKeyListener(-91, myIncreaseSpeed);
        SideMenuContainer.add(addIncreaseSpeedButton);
        hamburgerMenu.addCommandToSideMenu(myIncreaseSpeed);

        //Decelerate Button
        customButton addDecreaseSpeedButton = new customButton("Decelerate Ship");
        //Set commands
        ShipDecreaseSpeedCmd myDecreaseSpeed = new ShipDecreaseSpeedCmd(gw);
        addDecreaseSpeedButton.setCommand(myDecreaseSpeed);
        addKeyListener(-92, myDecreaseSpeed);
        SideMenuContainer.add(addDecreaseSpeedButton);
        hamburgerMenu.addCommandToSideMenu(myDecreaseSpeed);

//        //Turn left Button
//        customButton addLeftTurnButton = new customButton("Turn Ship Left");
//        //Set commands
        PlayerShipLeftTurnCmd myTurnLeft = new PlayerShipLeftTurnCmd(gw);
//        addLeftTurnButton.setCommand(myTurnLeft);
        addKeyListener(-93, myTurnLeft);
//        SideMenuContainer.add(addLeftTurnButton);
//        hamburgerMenu.addCommandToSideMenu(myTurnLeft);
//
//        //Turn right Button
//        customButton addRightTurnButton = new customButton("Turn Ship Right");
//        //Set commands
        PlayerShipRightTurnCmd myTurnRight = new PlayerShipRightTurnCmd(gw);
//        addRightTurnButton.setCommand(myTurnRight);
        addKeyListener(-94, myTurnRight);
//        SideMenuContainer.add(addRightTurnButton);
//        hamburgerMenu.addCommandToSideMenu(myTurnRight);

        //Fire Missile Button
        customButton addFireMissileButton = new customButton("Shoot Missile");
        //set commands
        ShootPlayerMissilesCmd myShootMissiles = new ShootPlayerMissilesCmd(gw);
        addFireMissileButton.setCommand(myShootMissiles);
        addKeyListener(-90, myShootMissiles);
        SideMenuContainer.add(addFireMissileButton);
        hamburgerMenu.addCommandToSideMenu(myShootMissiles);

        //Jump Button
        customButton addJumpButton = new customButton("Jump");
        //set commands
        JumpCmd myJumpButton = new JumpCmd(gw);
        addJumpButton.setCommand(myJumpButton);
        addKeyListener('j', myJumpButton);
        SideMenuContainer.add(addJumpButton);
        hamburgerMenu.addCommandToSideMenu(myJumpButton);

        //Reload Missiles Button
        customButton addReloadMissileButton = new customButton("Reload Missiles");
        //set commands
        ReloadMissilesCmd myReloadMissiles = new ReloadMissilesCmd(gw);
        addReloadMissileButton.setCommand(myReloadMissiles);
        SideMenuContainer.add(addReloadMissileButton);
        hamburgerMenu.addCommandToSideMenu(myReloadMissiles);

//        //Missile Kills Asteroid button
//        customButton addMissileKillsAsteroid = new customButton("Missile Kills Asteroid");
//        //Set commands
//        MissileKillAsteroidCmd myMissileKillsAsteroid = new MissileKillAsteroidCmd(gw);
//        addMissileKillsAsteroid.setCommand(myMissileKillsAsteroid);
//        SideMenuContainer.add(addMissileKillsAsteroid);
//        hamburgerMenu.addCommandToSideMenu(myMissileKillsAsteroid);
//
//        //Missile Strikes Flying Saucer
//        customButton addMissileStrikesNPSButton = new customButton("Missile kills NPS");
//        PMissileKillNPSCmd myPMissileStrikesNPS = new PMissileKillNPSCmd(gw);
//        addMissileStrikesNPSButton.setCommand(myPMissileStrikesNPS);
//        SideMenuContainer.add(addMissileStrikesNPSButton);
//        hamburgerMenu.addCommandToSideMenu(myPMissileStrikesNPS);
//
//        //Ship crashes into Flying Saucer
//        customButton addShipAndNPSCrashButton = new customButton("Ship hits Flying Saucer");
//        ShipAndNPSCrashCmd myShipAndNPSCrash = new ShipAndNPSCrashCmd(gw);
//        addShipAndNPSCrashButton.setCommand(myShipAndNPSCrash);
//        SideMenuContainer.add(addShipAndNPSCrashButton);
//        hamburgerMenu.addCommandToSideMenu(myShipAndNPSCrash);
//
//        //Ship crashes into Asteroid
//        customButton addPlayerShipCrashButton = new customButton("Ship hits Asteroid");
//        PlayerShipCrashCmd myPlayerShipCrash = new PlayerShipCrashCmd(gw);
//        addPlayerShipCrashButton.setCommand(myPlayerShipCrash);
//        SideMenuContainer.add(addPlayerShipCrashButton);
//        hamburgerMenu.addCommandToSideMenu(myPlayerShipCrash);
//
//        //Asteroids Collide
//        customButton addTwoAsteroidCrashButton = new customButton("Two Asteroids Collide");
//        TwoAsteroidCrashCmd myTwoAsteroidCrash = new TwoAsteroidCrashCmd(gw);
//        addTwoAsteroidCrashButton.setCommand(myTwoAsteroidCrash);
//        SideMenuContainer.add(addTwoAsteroidCrashButton);
//        hamburgerMenu.addCommandToSideMenu(myTwoAsteroidCrash);
//
//        //Asteroid hits Flying Saucer
//        customButton addNPSAsteroidCrashButton = new customButton("Flying Saucer hits Asteroid");
//        NPSAsteroidCrashCmd myNPSAsteroidCrash = new NPSAsteroidCrashCmd(gw);
//        addNPSAsteroidCrashButton.setCommand(myNPSAsteroidCrash);
//        SideMenuContainer.add(addNPSAsteroidCrashButton);
//        hamburgerMenu.addCommandToSideMenu(myNPSAsteroidCrash);

        //Play Command
//        customButton addPlayButton = new customButton("Play");
//        SideMenuContainer.add(addPlayButton);
//        PlayCommand playCmd = new PlayCommand(this);
//        addPlayButton.setCommand(playCmd);
//        hamburgerMenu.addCommandToSideMenu(playCmd);
//        hamburgerMenu.addCommandToOverflowMenu(playCmd);
        
        
        //Pause Command
        customButton addPauseButton = new customButton("Pause/Play");
        SideMenuContainer.add(addPauseButton);
        PauseCommand pauseCmd = new PauseCommand(this);
        addPauseButton.setCommand(pauseCmd);
        hamburgerMenu.addCommandToSideMenu(pauseCmd);
        hamburgerMenu.addCommandToOverflowMenu(pauseCmd);
        
        //Add exit buttons
        customButton addExitButton = new customButton("Exit");
        SideMenuContainer.add(addExitButton);
        //Set commands
        ExitCmd exitCmd = new ExitCmd(gw);
        addExitButton.setCommand(exitCmd);
        hamburgerMenu.addCommandToSideMenu(exitCmd);
        hamburgerMenu.addCommandToOverflowMenu(exitCmd);

        
        
        
        

        /*End Command list*/
        /*=========================================================================================================*/
        this.show();
        
        gw.setMapHeightWidth(mv.getWidth(), mv.getHeight());
        gw.setMapXY(mv.getX(), mv.getY());
        
       
    }

	@Override
	public void run() {
		int numGen = randIntGenerator(min, max);
		
		if(numGen >= 80 && numGen <= 100) {
			if(gw.getNPSCounter() < 5) {
				gw.addNonPlayerShips();
				gw.shootNPSMissiles();
			}
			if(gw.getAsteroidCounter() < 10) {
				gw.addNewAsteroid();
			}
			
		}
		gw.timeTick(ticker);
		
	}
	
	public void pause() {
		timer.cancel();
		gw.pause();
		
	}	
	
	public void play() {
		timer.schedule(ticker, true, this);
		gw.play();
	}
	
	private static int randIntGenerator(int min, int max) {
		Random rand = new Random();
		return (rand.nextInt((max - min) - 1) + min);
	}

}
