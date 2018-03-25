package com.mycompany.a2;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import java.util.Observable;
import java.util.Observer;

public class ScoreView extends Container implements Observer {
	private Label timeValue;
	private Label livesValue;
	private Label foodValue;
	private Label healthValue;
	private Label soundValue;
	
	public ScoreView() {
		Label timeLabel = new Label("Time:");
		timeValue = new Label("0");
		Label livesLabel = new Label("Lives Remaining:");
		livesValue = new Label("0");
		Label foodLabel = new Label("Food Level:");
		foodValue = new Label("0");
		Label healthLabel = new Label("Health Level:");
		healthValue = new Label("0");
		Label soundLabel = new Label("Sound:");
		soundValue = new Label("OFF");
		
		this.setLayout(new FlowLayout(CENTER));
		
		this.add(timeLabel)
			.add(timeValue)
			.add(livesLabel)
			.add(livesValue)
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
    	timeValue.setText(Integer.toString(gw.getClockTime()));
    	livesValue.setText(Integer.toString(gw.getLivesRemaining()));
    	foodValue.setText(Integer.toString(player.getFoodLevel()));
    	healthValue.setText(Integer.toString(player.getHealthLevel()));
    	soundValue.setText((gw.isSoundEnabled()) ? "ON" : "OFF");
    	this.revalidate();    	
    }
	
}