package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class Spider extends Movable implements IUncolorable {

    private static final int DEFAULT_COLOR = ColorUtil.BLACK;

    public Spider(IGameWorld gw) {
        super(gw, DEFAULT_COLOR);
    }
    
    @Override
    public void setColor(int color) {
        return;
    }
    
    @Override
    public void move() {
        // Spiders randomly select new headings +/- 5 degrees from their previous heading
        Random random = new Random();
        setHeading(getHeading() + (10 - random.nextInt(21)));
        Point2D location = new Point2D(getLocation().getX() + Math.cos(Math.toRadians(90 - getHeading())) * getSpeed(),
                                       getLocation().getY() + Math.sin(Math.toRadians(90 - getHeading())) * getSpeed());

        // ensure Spider cannot move outside play area
        if (location.getX() > getGw().getMaxWidth() - (getSize() + 1) / 2
                || location.getY() > getGw().getMaxHeight() - (getSize() + 1) / 2
                || location.getX() < (getSize() + 1) / 2
                || location.getY() < (getSize() + 1) / 2) {
            setHeading(getHeading() + 180); // https://www.youtube.com/watch?v=lcOxhH8N3Bo
        }

        setLocation(location);
    }
    
    @Override
    public String toString() {
        return "Spider: loc=" + Math.round(getLocation().getX()*10.0)/10.0 + ","
                + Math.round(getLocation().getY()*10.0)/10.0 + " color=["
                + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor())
                + "," + ColorUtil.blue(getColor()) + "] heading=" + getHeading()
                + " speed=" + getSpeed() + " size=" + getSize();
    }

	public void draw(Graphics g, Point2D pCmpRelPrnt) {
		
	}

}
