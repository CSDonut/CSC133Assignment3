package com.mycompany.a3.myCommands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCmd extends Command {

	private GameWorld gw;
	
	public SoundCmd(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}
	

	public void actionPerformed(ActionEvent e) {
		gw.soundOffOn();
	}
	
}
	
	
