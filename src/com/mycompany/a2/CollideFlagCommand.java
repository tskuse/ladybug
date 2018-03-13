package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideFlagCommand extends Command {
    private GameWorld gw;

    public CollideFlagCommand(GameWorld gw) {
        super("Collide With Flag");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.handleFlagCollision(1); // TODO fix me
    }
}