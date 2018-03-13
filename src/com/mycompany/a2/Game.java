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
    private final String EXIT_PROMPT = "Confirm exit (y/n):";
    private final String INVALID_INPUT = "Invalid input.";

    private GameWorld gw;
    private MapView mv;
    private ScoreView sv;

    private boolean exitPrompt;

    public Game() {
        this.exitPrompt = false;

        gw = new GameWorld();
        mv = new MapView();
        sv = new ScoreView();

        gw.init();

        this.setToolbar(new Toolbar());
        this.getToolbar().setTitle("Ladybug Game");
        this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        
        final Label myLabel = new Label(COMMAND_PROMPT);
        final TextField myTextField = new TextField();

        this.add(BorderLayout.NORTH,
                 BoxLayout.encloseY(sv, FlowLayout.encloseCenter(myLabel, myTextField)));

        this.add(BorderLayout.WEST,
                 BoxLayout.encloseY(new Button(new AccelerateCommand(gw)),
                                    new Button(new TurnLeftCommand(gw))));

        this.add(BorderLayout.CENTER, mv);
        
        this.add(BorderLayout.EAST,
                 BoxLayout.encloseY(new Button(new BrakeCommand(gw)),
                                    new Button(new TurnRightCommand(gw))));

        this.add(BorderLayout.SOUTH,
                 FlowLayout.encloseCenter(new Button(new CollideFlagCommand(gw)),
                                          new Button(new CollideSpiderCommand(gw)),
                                          new Button(new CollideFoodCommand(gw)),
                                          new Button(new TickClockCommand(gw))));        

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
        
        if (exitPrompt && command == 'y') {
            gw.exit();
        } else if (exitPrompt) {
            this.exitPrompt = false;
            myLabel.setText(COMMAND_PROMPT);
        }
        
        switch (command) {
            case 'x':
                myLabel.setText(EXIT_PROMPT);
                this.exitPrompt = true;
                break;
            case 'a':
                gw.acceleratePlayer();
                break;
            case 'b':
                gw.brakePlayer();
                break;
            case 'l':
                gw.turnPlayerLeft();
                break;
            case 'r':
                gw.turnPlayerRight();
                break;
            case '1':
                gw.handleFlagCollision(1);
                break;
            case '2':
                gw.handleFlagCollision(2);
                break;
            case '3':
                gw.handleFlagCollision(3);
                break;
            case '4':
                gw.handleFlagCollision(4);
                break;
            case '5':
                gw.handleFlagCollision(5);
                break;
            case '6':
                gw.handleFlagCollision(6);
                break;
            case '7':
                gw.handleFlagCollision(7);
                break;
            case '8':
                gw.handleFlagCollision(8);
                break;
            case '9':
                gw.handleFlagCollision(9);
                break;
            case 'f':
                gw.handleFoodCollision();
                break;
            case 'g':
                gw.handleSpiderCollision();
                break;
            case 't':
                gw.tickClock();
                break;
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
