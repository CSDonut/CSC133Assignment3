package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayerShipLeftTurnCmd extends Command {
    private GameWorld gw;

    public PlayerShipLeftTurnCmd(GameWorld gw){
        super("Player ship turn left");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
        gw.PSLeftTurn();
    }
}
