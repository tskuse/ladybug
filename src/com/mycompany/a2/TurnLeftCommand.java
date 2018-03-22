package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnLeftCommand extends Command {
    private GameWorld gw;
    private static TurnLeftCommand command;

    private TurnLeftCommand(GameWorld gw) {
        super("Turn Left");
        this.gw = gw;
    }
    
    public static TurnLeftCommand getCommand(GameWorld gw) {
    	if (command == null) {
    		command = new TurnLeftCommand(gw);
    	}
    	return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.turnPlayerLeft();
        gw.notifyObservers();
    }
}