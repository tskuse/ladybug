package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideFoodCommand extends Command {
    private GameWorld gw;
    private static CollideFoodCommand command;

    private CollideFoodCommand(GameWorld gw) {
        super("Collide With Food");
        this.gw = gw;
    }
    
    public static CollideFoodCommand getCommand(GameWorld gw) {
    	if (command == null) {
    		command = new CollideFoodCommand(gw);
    	}
    	return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.handleFoodCollision();
        gw.notifyObservers();
    }
}