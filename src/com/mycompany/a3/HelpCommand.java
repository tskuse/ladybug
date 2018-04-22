package com.mycompany.a3;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;

public class HelpCommand extends Command {
    private static HelpCommand command;
    private final String HELP_TEXT = "The following key commands are available for use:\n" +
                                     "'a' - Accelerate\n" +
                                     "'b' - Brake\n" +
                                     "'l' - Left turn\n" +
                                     "'r' - Right turn\n" +
                                     "'x' - Exit";
    
    private HelpCommand() {
        super("Help");
    }
    
    public static HelpCommand getCommand() {
        if (command == null) {
            command = new HelpCommand();
        }
        return command;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        Dialog dialog = new Dialog(super.getCommandName(), new BorderLayout());
        dialog.add(BorderLayout.CENTER, new SpanLabel(HELP_TEXT));
        dialog.add(BorderLayout.SOUTH, new Button(new Command("Ok")));
        dialog.showDialog();
    }
}
