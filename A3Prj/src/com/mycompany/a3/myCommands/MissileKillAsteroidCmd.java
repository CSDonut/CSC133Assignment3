package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MissileKillAsteroidCmd extends Command {
    private GameWorld gw;

    public MissileKillAsteroidCmd(GameWorld gw){
        super("Missile destroys asteroid");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
        gw.missileKillAsteroid();
    } 
}
