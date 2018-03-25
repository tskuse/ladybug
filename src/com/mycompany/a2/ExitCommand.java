package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {
	private GameWorld gw;
	private static ExitCommand command;
	
	private ExitCommand(GameWorld gw) {
		super("Exit");
		this.gw = gw;
	}
	
	public static ExitCommand getCommand(GameWorld gw) {
		if (command == null) {
			command = new ExitCommand(gw);
		}
		return command;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		boolean isExiting = Dialog.show("Exit", "Would you like to exit the game?", "Yes", "No");
		if (isExiting) {
			gw.exit();
		}
	}
}
