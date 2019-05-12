package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ShootNPSMissilesCmd extends Command {
    private GameWorld gw;

    public ShootNPSMissilesCmd(GameWorld gw){
        super("Shoot NPS Missiles");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
        gw.shootNPSMissiles();
    }
}
