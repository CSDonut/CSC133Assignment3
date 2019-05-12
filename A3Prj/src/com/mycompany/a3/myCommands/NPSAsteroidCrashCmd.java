package com.mycompany.a3.myCommands;
import com.mycompany.a3.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NPSAsteroidCrashCmd extends Command {
    private GameWorld gw;

    public NPSAsteroidCrashCmd(GameWorld gw){
        super("Asteroid Wacks Saucer");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent e){
        gw.npsAsteroidCrash();
    }
}
