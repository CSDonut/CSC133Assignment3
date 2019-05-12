package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AddNewStationCmd extends Command {
    private GameWorld gw;

    public AddNewStationCmd(GameWorld gw){
        super("Add New Station");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
        if (e.getKeyEvent() != -1){
            gw.addNewStation();

        }

    }
}
