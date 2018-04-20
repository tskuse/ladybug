package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class FoodStation extends Fixed implements IDrawable {

    private static final double DEFAULT_CAPACITY_RATIO = 0.8;
    private static final int DEFAULT_COLOR = ColorUtil.rgb(0, 255, 0);
    private static final int DEPLETED_COLOR = ColorUtil.rgb(155, 255, 155);

    private int capacity;

    public FoodStation(IGameWorld gw) {
        super(gw, DEFAULT_COLOR);
        this.capacity = (int) (this.getSize() * DEFAULT_CAPACITY_RATIO);
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Deplete the food station
     */
    public int deplete() {
        int foodLevel = getCapacity();
        setColor(DEPLETED_COLOR);
        setCapacity(0);
        return foodLevel;
    }

    @Override
    public String toString() {
        return "FoodStation: loc=" + Math.round(getLocation().getX()*10.0)/10.0 + ","
                + Math.round(getLocation().getY()*10.0)/10.0 + " color=["
                + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + ","
                + ColorUtil.blue(getColor()) + "] capacity=" + capacity + " size=" + getSize();
    }

	public void draw(Graphics g, Point2D pCmpRelPrnt) {
        int localX = (int) (pCmpRelPrnt.getX() + getLocation().getX());
        int localY = (int) (pCmpRelPrnt.getY() + getLocation().getY());
        g.setColor(getColor());
        g.fillRect(localX - (getSize() + 1) / 2,
                   localY - (getSize() + 1) / 2,
                   getSize(),
                   getSize());
        g.setColor(ColorUtil.BLACK);
        g.drawString(Integer.toString(capacity),
                             localX,
                             localY);
	}

}
