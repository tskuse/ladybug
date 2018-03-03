package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed {

    private static final double DEFAULT_CAPACITY_RATIO = 0.8;
    private static final int DEFAULT_COLOR = ColorUtil.rgb(0, 255, 0);
    private static final int DEPLETED_COLOR = ColorUtil.rgb(155, 255, 155);

    private int capacity;

    public FoodStation() {
        super(DEFAULT_COLOR);
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

}
