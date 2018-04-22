package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ToggleSoundCommand extends Command {

    private static ToggleSoundCommand command;
    private GameWorld gw;
    private Game game;
    private CheckBox checkbox;
    private BGSound bgSound;
    
    private ToggleSoundCommand(GameWorld gw, Game game, CheckBox checkbox, BGSound bgSound) {
        super("Toggle Sound");
        this.gw = gw;
        this.game = game;
        this.checkbox = checkbox;
        this.bgSound = bgSound;
    }
    
    public static ToggleSoundCommand getCommand(GameWorld gw, Game game, CheckBox checkbox, BGSound bgSound) {
        if (command == null) {
            command = new ToggleSoundCommand(gw, game, checkbox, bgSound);
        }
        return command;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.setSoundEnabled(!gw.isSoundEnabled());
        checkbox.setSelected(gw.isSoundEnabled());
        if (gw.isSoundEnabled() && !game.isGamePaused()) {
            bgSound.play();
        } else {
            bgSound.pause();
        }
        gw.notifyObservers();
        evt.getActualComponent().getComponentForm().getToolbar().closeSideMenu();
    }
}
