package com.mycompany.a2;

import com.codename1.ui.Button;
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
        mv = new MapView();
        sv = new ScoreView();

        gw.addObserver(mv);
        gw.addObserver(sv);
        gw.init();
        gw.notifyObservers();
        
        this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_SCALE));

        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.setTitle("Ladybug Game");
        toolbar.addCommandToSideMenu(AccelerateCommand.getCommand(gw));
        toolbar.addCommandToSideMenu(AboutCommand.getCommand());
        toolbar.addCommandToSideMenu(ExitCommand.getCommand(gw));

        this.add(BorderLayout.NORTH, sv);

        this.add(BorderLayout.WEST,
                 BoxLayout.encloseY(new Button(AccelerateCommand.getCommand(gw)),
                                    new Button(TurnLeftCommand.getCommand(gw))));

        this.add(BorderLayout.CENTER, mv);
        
        this.add(BorderLayout.EAST,
                 BoxLayout.encloseY(new Button(BrakeCommand.getCommand(gw)),
                                    new Button(TurnRightCommand.getCommand(gw))));

        this.add(BorderLayout.SOUTH,
                 FlowLayout.encloseCenter(new Button(CollideFlagCommand.getCommand(gw)),
                                          new Button(CollideSpiderCommand.getCommand(gw)),
                                          new Button(CollideFoodCommand.getCommand(gw)),
                                          new Button(TickClockCommand.getCommand(gw))));        

        this.addKeyListener('a', AccelerateCommand.getCommand(gw));
        this.addKeyListener('b', BrakeCommand.getCommand(gw));
        this.addKeyListener('l', TurnLeftCommand.getCommand(gw));
        this.addKeyListener('r', TurnRightCommand.getCommand(gw));
        this.addKeyListener('f', CollideFoodCommand.getCommand(gw));
        this.addKeyListener('g', CollideSpiderCommand.getCommand(gw));
        this.addKeyListener('t', TickClockCommand.getCommand(gw));
        this.addKeyListener('x', ExitCommand.getCommand(gw));
        
        this.show();
    }

}
