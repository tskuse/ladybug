package com.mycompany.a3;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;

public class AboutCommand extends Command {
    private static AboutCommand command;
    private final String ABOUT_TEXT = "Tony Skuse\n" +
                               "CSC 133\n" +
                               "v0.0.2";
    
    private AboutCommand() {
        super("About");
    }
    
    public static AboutCommand getCommand() {
        if (command == null) {
            command = new AboutCommand();
        }
        return command;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        Dialog dialog = new Dialog(super.getCommandName(), new BorderLayout());
        dialog.add(BorderLayout.CENTER, new SpanLabel(ABOUT_TEXT));
        dialog.add(BorderLayout.SOUTH, new Button(new Command("Ok")));
        dialog.showDialog();
    }
}
