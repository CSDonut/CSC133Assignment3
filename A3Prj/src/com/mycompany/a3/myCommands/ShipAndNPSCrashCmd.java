package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ShipAndNPSCrashCmd extends Command {
    private GameWorld gw;

    public ShipAndNPSCrashCmd(GameWorld gw){
        super("Ship and Non Player Ship crashed");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
        gw.ShipAndNonPShipCrash();
    }
}
