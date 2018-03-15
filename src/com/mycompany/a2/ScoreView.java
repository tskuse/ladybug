package com.mycompany.a2;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import java.util.Observable;
import java.util.Observer;

public class ScoreView extends Container implements Observer {
	
	public ScoreView() {
		Label timeLabel = new Label("Time:");
		Label timeValue = new Label("0");
		Label livesLabel = new Label("Lives Remaining:");
		Label livesValue = new Label("0");
		Label foodLabel = new Label("Food Level:");
		Label foodValue = new Label("0");
		Label healthLabel = new Label("Health Level:");
		Label healthValue = new Label("0");
		Label soundLabel = new Label("Sound:");
		Label soundValue = new Label("0");
		
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

    }
	
}