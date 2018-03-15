package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickClockCommand extends Command {
    private GameWorld gw;
    private static TickClockCommand command;

    private TickClockCommand(GameWorld gw) {
        super("Tick");
        this.gw = gw;
    }
    
    public static TickClockCommand getCommand(GameWorld gw) {
    	if (command == null) {
    		command = new TickClockCommand(gw);
    	}
    	return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.tickClock();
    }
}