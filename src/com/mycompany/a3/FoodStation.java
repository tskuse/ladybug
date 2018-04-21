package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class FoodStation extends Fixed {

    private static final double DEFAULT_CAPACITY_RATIO = 2.5;
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
        Point2D origin = new Point2D(pCmpRelPrnt.getX() + getLocation().getX(), pCmpRelPrnt.getY() + getLocation().getY());
        g.setColor(getColor());
        g.fillRect((int) origin.getX() - (getSize() + 1) / 2,
                   (int) origin.getY() - (getSize() + 1) / 2,
                   getSize(), getSize());
        g.setColor(ColorUtil.BLACK);
        g.drawStringBaseline(Integer.toString(capacity),
                             (int) origin.getX() - (g.getFont().stringWidth(Integer.toString(capacity)) + 1) / 2,
                             (int) origin.getY() + (g.getFont().getHeight() + 1) / 4);
	}

}
