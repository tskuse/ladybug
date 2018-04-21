package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickClockCommand extends Command {
    private GameWorld gw;
    private static TickClockCommand command;
    private int tickRate;

    private TickClockCommand(GameWorld gw, int tickRate) {
        super("Tick");
        this.gw = gw;
        this.tickRate = tickRate;
    }
    
    public static TickClockCommand getCommand(GameWorld gw, int tickRate) {
        if (command == null) {
            command = new TickClockCommand(gw, tickRate);
        }
        return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.tickClock(tickRate);
        gw.notifyObservers();
    }
}