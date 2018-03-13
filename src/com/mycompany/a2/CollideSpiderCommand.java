package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideSpiderCommand extends Command {
    private GameWorld gw;

    public CollideSpiderCommand(GameWorld gw) {
        super("Collide With Spider");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.handleSpiderCollision();
    }
}