package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AddPShipCmd extends Command {
    private GameWorld gw;

    public AddPShipCmd(GameWorld gw){
        super("Add Player Ship");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
    	if (e.getKeyEvent() != -1){
    		gw.addPShip();
    	}
    }
}
