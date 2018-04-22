package com.mycompany.a3;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import java.util.Observable;
import java.util.Observer;

public class ScoreView extends Container implements Observer {
    private Label timeValue;
    private Label livesValue;
    private Label flagValue;
    private Label foodValue;
    private Label healthValue;
    private Label soundValue;
    private int tickRate;
    
    public ScoreView(int tickRate) {
        this.tickRate = tickRate;

        Label timeLabel = new Label("Time:");
        timeValue = createValueLabel("0");
        Label livesLabel = new Label("Lives Remaining:");
        livesValue = createValueLabel("0");
        Label flagLabel = new Label("Last Flag Reached:");
        flagValue = createValueLabel("0");
        Label foodLabel = new Label("Food Level:");
        foodValue = createValueLabel("0");
        Label healthLabel = new Label("Health Level:");
        healthValue = createValueLabel("0");
        Label soundLabel = new Label("Sound:");
        soundValue = createValueLabel("OFF");
        
        this.setLayout(new FlowLayout(CENTER));
        
        this.add(timeLabel)
            .add(timeValue)
            .add(livesLabel)
            .add(livesValue)
            .add(flagLabel)
            .add(flagValue)
            .add(foodLabel)
            .add(foodValue)
            .add(healthLabel)
            .add(healthValue)
            .add(soundLabel)
            .add(soundValue);
    }
    
    public void update(Observable observable, Object data) {
        IGameWorld gw = ((IGameWorld) observable);
        Ladybug player = gw.getPlayer();
        timeValue.setText(Integer.toString(gw.getClockTime() / (1000 / tickRate)));
        livesValue.setText(Integer.toString(gw.getLivesRemaining()));
        flagValue.setText(Integer.toString(player.getLastFlagReached()));
        foodValue.setText(Integer.toString(player.getFoodLevel()));
        healthValue.setText(Integer.toString(player.getHealthLevel()));
        soundValue.setText((gw.isSoundEnabled()) ? "ON" : "OFF");
        this.revalidate();    	
    }
    
    private Label createValueLabel(String text) {
        Label label = new Label(text);
        label.getAllStyles().setPadding(Component.LEFT, 2);
        label.getAllStyles().setPadding(Component.RIGHT, 2);
        return label;
    }
    
}