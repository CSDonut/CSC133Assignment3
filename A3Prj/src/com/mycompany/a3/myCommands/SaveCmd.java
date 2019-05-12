package com.mycompany.a3.myCommands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SaveCmd extends Command{

	private GameWorld gw;
	
	public SaveCmd(GameWorld gw) {
		super("Save");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		Dialog.show("Save", "Saves game at current location", "Save", "Cancel");
	}
}
