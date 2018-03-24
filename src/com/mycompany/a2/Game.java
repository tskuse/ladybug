package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;

public class Game extends Form {
    
    private GameWorld gw;
    private MapView mv;
    private ScoreView sv;

    public Game() {
        gw = new GameWorld();

        this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_SCALE));

        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.setTitle("Ladybug Game");
        toolbar.addCommandToSideMenu(AccelerateCommand.getCommand(gw));
        toolbar.addCommandToSideMenu(AboutCommand.getCommand());
        toolbar.addCommandToSideMenu(ExitCommand.getCommand(gw));

        sv = new ScoreView();
        this.add(BorderLayout.NORTH, sv);
        gw.addObserver(sv);

        this.add(BorderLayout.WEST,
                 BoxLayout.encloseY(createCommandButton(AccelerateCommand.getCommand(gw)),
                		 			createCommandButton(TurnLeftCommand.getCommand(gw))));

        mv = new MapView();
        this.add(BorderLayout.CENTER, mv);
        gw.addObserver(mv);
        
        this.add(BorderLayout.EAST,
                 BoxLayout.encloseY(createCommandButton(BrakeCommand.getCommand(gw)),
                		 			createCommandButton(TurnRightCommand.getCommand(gw))));

        this.add(BorderLayout.SOUTH,
                 FlowLayout.encloseCenter(createCommandButton(CollideFlagCommand.getCommand(gw)),
                		 				  createCommandButton(CollideSpiderCommand.getCommand(gw)),
                		 				  createCommandButton(CollideFoodCommand.getCommand(gw)),
                		 				  createCommandButton(TickClockCommand.getCommand(gw))));        

        this.addKeyListener('a', AccelerateCommand.getCommand(gw));
        this.addKeyListener('b', BrakeCommand.getCommand(gw));
        this.addKeyListener('l', TurnLeftCommand.getCommand(gw));
        this.addKeyListener('r', TurnRightCommand.getCommand(gw));
        this.addKeyListener('f', CollideFoodCommand.getCommand(gw));
        this.addKeyListener('g', CollideSpiderCommand.getCommand(gw));
        this.addKeyListener('t', TickClockCommand.getCommand(gw));
        this.addKeyListener('x', ExitCommand.getCommand(gw));
        
        gw.init();
        gw.notifyObservers();
        this.show();
    }
    
    private Button createCommandButton(Command command) {
    	Button button = new Button(command);
    	button.getUnselectedStyle().setBgTransparency(255);
        button.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        button.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        button.getAllStyles().setMargin(2, 2, 2, 2);
        button.getAllStyles().setPadding(5, 5, 2, 2);
        return button;
    }

}
