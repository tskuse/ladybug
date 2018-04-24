package com.mycompany.a3;

import java.util.Vector;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.events.ActionEvent;

public class PlayPauseCommand extends Command {

    private static PlayPauseCommand command;

    private GameWorld gw;
    private Game game;
    private Vector<Component> controlComponents;

    private PlayPauseCommand(GameWorld gw, Game game, Vector<Component> controlComponents) {
        super("Pause");
        this.gw = gw;
        this.game = game;
        this.controlComponents = controlComponents;
    }

    public static PlayPauseCommand getCommand(GameWorld gw, Game game, Vector<Component> controlComponents) {
        if (command == null) {
            command = new PlayPauseCommand(gw, game, controlComponents);
        }
        return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (getCommandName().equals("Pause")) {
            System.out.println("PAUSE");
            this.setCommandName("Play");

            game.setGamePaused(true);
            game.getTimer().cancel();
            if (game.getBgSound().isPlaying()) {
                game.getBgSound().pause();
            }

            AccelerateCommand.getCommand(gw).setEnabled(false);
            BrakeCommand.getCommand(gw).setEnabled(false);
            PositionCommand.getCommand().setEnabled(true);
            TurnLeftCommand.getCommand(gw).setEnabled(false);
            TurnRightCommand.getCommand(gw).setEnabled(false);

            for (Component cmp : controlComponents) {
                cmp.setEnabled(!cmp.isEnabled());
            }

            IIterator<GameObject> it = gw.getObjects().getIterator();
            while (it.hasNext()) {
                GameObject object = it.getNext();
                if (object instanceof ISelectable) {
                    ((ISelectable) object).setSelectable(true);
                }
            }
        } else {
            System.out.println("PLAY");
            this.setCommandName("Pause");

            AccelerateCommand.getCommand(gw).setEnabled(true);
            BrakeCommand.getCommand(gw).setEnabled(true);
            PositionCommand.getCommand().setEnabled(false);
            TurnLeftCommand.getCommand(gw).setEnabled(true);
            TurnRightCommand.getCommand(gw).setEnabled(true);

            for (Component cmp : controlComponents) {
                cmp.setEnabled(!cmp.isEnabled());
            }

            IIterator<GameObject> it = gw.getObjects().getIterator();
            while (it.hasNext()) {
                GameObject object = it.getNext();
                if (object instanceof ISelectable) {
                    ((ISelectable) object).setSelectable(false);
                    ((ISelectable) object).setSelected(false);
                }
            }
            game.repaint();

            if (gw.isSoundEnabled()) {
                game.getBgSound().play();
            }
            
            game.setGamePaused(false);
            game.getTimer().schedule(game.TICK_RATE, true, game);
        }
        ((Button) evt.getActualComponent()).setText(getCommandName());
    }
}