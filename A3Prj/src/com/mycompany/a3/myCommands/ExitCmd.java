package com.mycompany.a3.myCommands;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.*;

public class ExitCmd extends Command {
	private GameWorld gw;
	
	public ExitCmd(GameWorld gw) {
		super("Exit Game");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
    	if (e.getKeyEvent() != -1){
		Boolean bool = Dialog.show("Quit Game", "Are you sure you want to quit?", "Confirm", "Cancel");
		if (bool){
				Display.getInstance().exitApplication();
			}
    	}
	}
	
}
