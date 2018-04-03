package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command {
    private GameWorld gw;
    private static AccelerateCommand command;

    private AccelerateCommand(GameWorld gw) {
        super("Accelerate");
        this.gw = gw;
    }
    
    public static AccelerateCommand getCommand(GameWorld gw) {
    	if (command == null) {
    		command = new AccelerateCommand(gw);
    	}
    	return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.acceleratePlayer();
        gw.notifyObservers();
    }
}