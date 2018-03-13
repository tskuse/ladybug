package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideFoodCommand extends Command {
    private GameWorld gw;

    public CollideFoodCommand(GameWorld gw) {
        super("Collide With Food");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.handleFoodCollision();
    }
}