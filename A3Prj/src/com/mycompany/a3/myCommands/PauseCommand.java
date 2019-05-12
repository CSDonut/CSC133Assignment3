package com.mycompany.a3.myCommands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.Game.customButton;

public class PauseCommand extends Command {
	private Game gw;
	boolean pause;
	
	public PauseCommand(Game g) {
		super("Play/Pause");
		this.gw = g;
		pause = false;
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getKeyEvent() != -1){
        	if(pause) {
        		gw.pause();
        		
        	}
        	else {
        		gw.play();

        	}
        	pause = (!pause);
        }
	}
	
}
