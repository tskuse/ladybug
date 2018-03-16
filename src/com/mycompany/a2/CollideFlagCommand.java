package com.mycompany.a2;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;

public class CollideFlagCommand extends Command {
    private GameWorld gw;
    private static CollideFlagCommand command;

    private CollideFlagCommand(GameWorld gw) {
        super("Collide With Flag");
        this.gw = gw;
    }
    
    public static CollideFlagCommand getCommand(GameWorld gw) {
    	if (command == null) {
    		command = new CollideFlagCommand(gw);
    	}
    	return command;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    	TextField flag = new TextField();
    	Button okButton = new Button("Ok");
    	Button cancelButton = new Button("Cancel");
    	Dialog dialog = new Dialog("Enter a flag:");
    	dialog.setLayout(new BoxLayout(BoxLayout.X_AXIS));
    	dialog.add(flag)
    		  .add(okButton)
    		  .add(cancelButton);
    	dialog.show();
        gw.handleFlagCollision(Integer.parseInt(flag.getText())); // TODO fix me
    }
}