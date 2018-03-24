package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;

public class CommandButton extends Button {

    public CommandButton(Command command) {
        super(command);
        this.getUnselectedStyle().setBgTransparency(255);
        this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        this.getAllStyles().setMargin(2, 2, 2, 2);
        this.getAllStyles().setPadding(5, 5, 2, 2);
    }

}