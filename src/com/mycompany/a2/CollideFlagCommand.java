package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CollideFlagCommand extends Command {
    private GameWorld gw;
    private static CollideFlagCommand command;

    private CollideFlagCommand(GameWorld gw) {
        super("Collide With Flag");
        this.gw = gw;
    }
    
    public static CollideFlagCommand getCommand(GameWorld gw) {
    	if (command == null) {
    		command = new CollideFlagCommand(gw);
    	}
    	return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    	TextField flagField = new TextField();
    	Command okCommand = new Command("Ok");
    	Command cancelCommand = new Command("Cancel");
    	Command responseCommand = Dialog.show("Enter a flag:", flagField, new Command[] {okCommand, cancelCommand});
        if (responseCommand == okCommand) {
        	try {
        		gw.handleFlagCollision(Integer.parseInt(flagField.getText()));
        		gw.notifyObservers();
        	} catch (NumberFormatException e) {
        		System.out.println("ERROR parsing flag number");
        	}
        }
    }
}