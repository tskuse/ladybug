package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnRightCommand extends Command {
    private GameWorld gw;
    private static TurnRightCommand command;

    private TurnRightCommand(GameWorld gw) {
        super("Turn Right");
        this.gw = gw;
    }
    
    public static TurnRightCommand getCommand(GameWorld gw) {
        if (command == null) {
            command = new TurnRightCommand(gw);
        }
        return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (isEnabled()) {
            gw.turnPlayerRight();
            gw.notifyObservers();
        }
    }
}