package com.mycompany.a3.myCommands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AboutCmd extends Command {

	private GameWorld gw;
	
	public AboutCmd(GameWorld gw) {
		super("About");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		Dialog.show("About", "CSC 133 Asteroid Game", "Proceed", "Cancel");
	}
}