package com.mycompany.a3.myCommands;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.*;
//

public class UndoCmd extends Command{

	private GameWorld gw;
	
	public UndoCmd(GameWorld gw) {
		super("Undo");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		Dialog.show("Undo", "Undoes a portion of the game", "Confirm", "Cancel");

	}
	
}
