package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayerShipCrashCmd extends Command {
    private GameWorld gw;

    public PlayerShipCrashCmd(GameWorld gw){
        super("Crash Player Ship");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
        gw.shipCrash();
    }
}
