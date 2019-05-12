package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PMissileKillNPSCmd extends Command {
    private GameWorld gw;

    public PMissileKillNPSCmd(GameWorld gw){
        super("Missile Hits Flying Saucer");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
        gw.missileKillNPS();
    }
}
