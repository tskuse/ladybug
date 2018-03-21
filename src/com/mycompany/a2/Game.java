package com.mycompany.a2;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;

public class Game extends Form {
    
    private final String COMMAND_PROMPT = "Enter a Command:";
    private final String INVALID_INPUT = "Invalid input.";

    private GameWorld gw;
    private MapView mv;
    private ScoreView sv;

    public Game() {
        gw = new GameWorld();
        mv = new MapView();
        sv = new ScoreView();

        gw.init();
        
        this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_SCALE));

        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.setTitle("Ladybug Game");
        toolbar.addCommandToSideMenu(AccelerateCommand.getCommand(gw));
        toolbar.addCommandToSideMenu(AboutCommand.getCommand());
        toolbar.addCommandToSideMenu(ExitCommand.getCommand(gw));
        
        final Label myLabel = new Label(COMMAND_PROMPT);
        final TextField myTextField = new TextField();

        this.add(BorderLayout.NORTH,
                 BoxLayout.encloseY(sv, FlowLayout.encloseCenter(myLabel, myTextField)));

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
        
        myTextField.addActionListener(new ActionListener<ActionEvent>() {
            public void actionPerformed(ActionEvent event) {
                processInput(myTextField, myLabel);
            }
        });
    }
    
    private void processInput(TextField myTextField, Label myLabel) {
        if (myTextField.getText().length() < 1) {
            return;
        } else if (myTextField.getText().length() > 1) {
            System.out.println(INVALID_INPUT);
            myTextField.clear();
            return;
        }
        
        char command = myTextField.getText().toString().charAt(0);
        myTextField.clear();
        
        switch (command) {
            case 'd':
                gw.displayState();
                break;
            case 'm':
                gw.displayMap();
                break;
            default:
                System.out.println(INVALID_INPUT);
        }
    }

}
