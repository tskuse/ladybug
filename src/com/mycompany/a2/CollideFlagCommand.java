package com.mycompany.a2;

import com.codename1.ui.Command;
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
        gw.handleFlagCollision(1); // TODO fix me
    }
}