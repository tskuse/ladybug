package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BrakeCommand extends Command {
    private GameWorld gw;
    private static BrakeCommand command;
    
    private BrakeCommand(GameWorld gw) {
        super("Brake");
        this.gw = gw;
    }
    
    public static BrakeCommand getCommand(GameWorld gw) {
    	if (command == null) {
    		command = new BrakeCommand(gw);
    	}
    	return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.brakePlayer();
        gw.notifyObservers();
    }
}