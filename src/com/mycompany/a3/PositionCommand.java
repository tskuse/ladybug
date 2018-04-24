package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command {

    private static PositionCommand command;

    private PositionCommand() {
        super("Position");
    }

    public static PositionCommand getCommand() {
        if (command == null) {
            command = new PositionCommand();
        }
        return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Game game = ((Game) evt.getComponent().getComponentForm());
        if (isEnabled() && game.getLastSelectedObject() != null && game.getLastSelectedObject().isSelected()) {
            game.setMoveSelectedObject(true);
        }
    }
}