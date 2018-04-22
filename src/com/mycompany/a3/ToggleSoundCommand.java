package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ToggleSoundCommand extends Command {
    private GameWorld gw;
    private static ToggleSoundCommand command;
    private CheckBox checkbox;
    private BGSound bgSound;
    
    private ToggleSoundCommand(GameWorld gw, CheckBox checkbox, BGSound bgSound) {
        super("Toggle Sound");
        this.gw = gw;
        this.checkbox = checkbox;
        this.bgSound = bgSound;
    }
    
    public static ToggleSoundCommand getCommand(GameWorld gw, CheckBox checkbox, BGSound bgSound) {
        if (command == null) {
            command = new ToggleSoundCommand(gw, checkbox, bgSound);
        }
        return command;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.setSoundEnabled(!gw.isSoundEnabled());
        checkbox.setSelected(gw.isSoundEnabled());
        if (gw.isSoundEnabled()) {
            bgSound.play();
        } else {
            bgSound.pause();
        }
        gw.notifyObservers();
        evt.getActualComponent().getComponentForm().getToolbar().closeSideMenu();
    }
}
