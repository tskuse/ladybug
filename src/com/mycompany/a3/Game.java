package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable {
    
    private GameWorld gw;
    private MapView mv;
    private ScoreView sv;

    private UITimer timer;
    public final int TICK_RATE = 20;

    private BGSound bgSound;
    private boolean gamePaused;
    private ISelectable lastSelectedObject;
    private boolean moveSelectedObject;

    public Game() {
        gw = new GameWorld();

        this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_SCALE));

        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.setTitle("Ladybug Game");
        
        toolbar.addCommandToSideMenu(AccelerateCommand.getCommand(gw));
        
        CheckBox soundCheckBox = new CheckBox("Toggle Sound");
        soundCheckBox.getAllStyles().setBgTransparency(255);
        soundCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
        soundCheckBox.setSelected(gw.isSoundEnabled());

        bgSound = new BGSound("timer.wav");
        toolbar.addComponentToSideMenu(soundCheckBox, ToggleSoundCommand.getCommand(gw, this, soundCheckBox, bgSound));
        
        toolbar.addCommandToSideMenu(AboutCommand.getCommand());
        toolbar.addCommandToSideMenu(ExitCommand.getCommand(gw));
        
        toolbar.addCommandToRightBar(HelpCommand.getCommand());

        sv = new ScoreView(TICK_RATE);
        this.add(BorderLayout.NORTH, sv);
        gw.addObserver(sv);

        Container leftControlContainer = BoxLayout.encloseY(createCommandButton(AccelerateCommand.getCommand(gw)),
                                                            createCommandButton(TurnLeftCommand.getCommand(gw)));
        leftControlContainer.getAllStyles().setPadding(Container.TOP, 200);
        this.add(BorderLayout.WEST, leftControlContainer);

        mv = new MapView();
        this.add(BorderLayout.CENTER, mv);
        gw.addObserver(mv);
        
        Container rightControlContainer = BoxLayout.encloseY(createCommandButton(BrakeCommand.getCommand(gw)),
                                                             createCommandButton(TurnRightCommand.getCommand(gw)));
        rightControlContainer.getAllStyles().setPadding(Container.TOP, 200);
        this.add(BorderLayout.EAST, rightControlContainer);

        // add all play control components to a vector to pass to the pause command so they can be disabled
        Vector<Component> controlComponents = new Vector<Component>();
        for (Component cmp : leftControlContainer) {
            controlComponents.add(cmp);
        }
        for (Component cmp : rightControlContainer) {
            controlComponents.add(cmp);
        }

        Button positionButton = createCommandButton(PositionCommand.getCommand());
        PositionCommand.getCommand().setEnabled(false); 
        positionButton.setEnabled(false);
        controlComponents.add(positionButton);

        this.add(BorderLayout.SOUTH,
                 FlowLayout.encloseCenter(positionButton,
                                          createCommandButton(PlayPauseCommand.getCommand(gw, this, controlComponents))));
        
        this.addKeyListener('a', AccelerateCommand.getCommand(gw));
        this.addKeyListener('b', BrakeCommand.getCommand(gw));
        this.addKeyListener('l', TurnLeftCommand.getCommand(gw));
        this.addKeyListener('r', TurnRightCommand.getCommand(gw));
        this.addKeyListener('x', ExitCommand.getCommand(gw));
        
        this.show();
        gw.init(mv.getWidth(), mv.getHeight());
        gw.notifyObservers();

        gamePaused = false;
        if (gw.isSoundEnabled()) {
            bgSound.play();
        }
        timer = new UITimer(this);
        timer.schedule(TICK_RATE, true, this);
    }

	public void run() {
        gw.tickClock(TICK_RATE);
        gw.notifyObservers();
        repaint();
    }
  
    /**
     * @return A generated Button with specialized styling linked to the associated Command
     */
    private Button createCommandButton(Command command) {
        Button button = new Button(command);
        button.getUnselectedStyle().setBgTransparency(255);
        button.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        button.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        button.getAllStyles().setMargin(2, 2, 2, 2);
        button.getAllStyles().setPadding(5, 5, 2, 2);
        return button;
    }

    /**
     * @return the timer
     */
    public UITimer getTimer() {
        return timer;
    }

    /**
     * @return the bgSound
     */
    public BGSound getBgSound() {
        return bgSound;
    }

    /**
     * @return the gamePaused
     */
    public boolean isGamePaused() {
        return gamePaused;
    }

    /**
     * @param gamePaused the gamePaused to set
     */
    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    /**
     * @param lastSelectedObject the lastSelectedObject to set
     */
    public void setLastSelectedObject(ISelectable lastSelectedObject) {
        this.lastSelectedObject = lastSelectedObject;
    }

    /**
     * @return the lastSelectedObject
     */
    public ISelectable getLastSelectedObject() {
        return lastSelectedObject;
    }

    /**
     * @return the moveSelectedObject
     */
    public boolean isMoveSelectedObject() {
        return moveSelectedObject;
    }

    /**
     * @param moveSelectedObject the moveSelectedObject to set
     */
    public void setMoveSelectedObject(boolean moveSelectedObject) {
        this.moveSelectedObject = moveSelectedObject;
    }

}
