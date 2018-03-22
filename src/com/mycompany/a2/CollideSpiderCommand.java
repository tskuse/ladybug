package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideSpiderCommand extends Command {
    private GameWorld gw;
    private static CollideSpiderCommand command;

    private CollideSpiderCommand(GameWorld gw) {
        super("Collide With Spider");
        this.gw = gw;
    }
    
    public static CollideSpiderCommand getCommand(GameWorld gw) {
    	if (command == null) {
    		command = new CollideSpiderCommand(gw);
    	}
    	return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.handleSpiderCollision();
        gw.notifyObservers();
    }
}