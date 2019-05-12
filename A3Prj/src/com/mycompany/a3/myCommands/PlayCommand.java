package com.mycompany.a3.myCommands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class PlayCommand extends Command {
	private Game gw;
	
	public PlayCommand(Game g) {
		super("Play");
		this.gw = g;
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getKeyEvent() != -1){
        	gw.play();
        }
	}
	
}
