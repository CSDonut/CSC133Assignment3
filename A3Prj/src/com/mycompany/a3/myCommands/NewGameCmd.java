package com.mycompany.a3.myCommands;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.*;

public class NewGameCmd extends Command {

	private GameWorld gw;
	
	public NewGameCmd(GameWorld gw) {
		super("New Game");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.newGame();

	}
}
