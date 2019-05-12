package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PlayerShipRightTurnCmd extends Command {
    private GameWorld gw;

    public PlayerShipRightTurnCmd(GameWorld gw){
        super("Player ship turn right");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
        gw.PSRightTurn();
    }
}
