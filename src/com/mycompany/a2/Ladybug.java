package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

public class Ladybug extends Movable implements ISteerable {

    private static final int DEFAULT_SIZE = 40;
    private static final int DEFAULT_COLOR = ColorUtil.rgb(255, 0, 0);
    private static final int COLOR_MODIFIER = ColorUtil.rgb(0, 20, 20);
    private static final int DEFAULT_HEADING = 0;
    private static final int DEFAULT_SPEED = 10;
    private static final int DEFAULT_HEALTH = 10;
    private static final int DEFAULT_LAST_FLAG = 1;

    private int maximumSpeed;
    private int foodLevel;
    private int foodConsumptionRate;
    private int healthLevel;
    private int lastFlagReached;
    
    /**
     * @param location
     * @param maximumSpeed
     * @param foodLevel
     * @param foodConsumptionRate
     */
    public Ladybug(Point2D location, int maximumSpeed, int foodLevel, int foodConsumptionRate) {
        super(location, DEFAULT_SIZE, DEFAULT_COLOR, DEFAULT_HEADING, DEFAULT_SPEED);
        this.maximumSpeed = maximumSpeed;
        this.foodLevel = foodLevel;
        this.foodConsumptionRate = foodConsumptionRate;
        this.healthLevel = DEFAULT_HEALTH;
        this.lastFlagReached = DEFAULT_LAST_FLAG;
    }

    /**
     * @param adjustment the adjustment to the heading
     */
    public void changeHeading(int adjustment) {
        setHeading(getHeading() + adjustment);
    }

    /**
     * @return the lastFlagReached
     */
    public int getLastFlagReached() {
        return lastFlagReached;
    }

    /**
     * @param lastFlagReached the lastFlagReached to set
     */
    public void setLastFlagReached(int lastFlagReached) {
        this.lastFlagReached = lastFlagReached;
    }

    /**
     * @return the foodLevel
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     * @param foodLevel the foodLevel to set
     */
    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    /**
     * @return the healthLevel
     */
    public int getHealthLevel() {
        return healthLevel;
    }

    /**
     * @param speed the speed to set
     */
    @Override
    public void setSpeed(int speed) {
        speed = speed * (healthLevel / 10);
        super.setSpeed(speed);
    }

    /**
     * Decrease the the health of the ladybug
     */
    public void decreaseHealth() {
        if (--healthLevel < 0) {
            healthLevel = 0;
        }
        updateColor();
    }

    /**
     * Updates the ladybug's color based on current healthLevel
     */
    private void updateColor() {
        int r, g, b;
        int color = getColor();
        int healthLevel = getHealthLevel();

        r = ColorUtil.red(color) + ColorUtil.red(COLOR_MODIFIER) * (DEFAULT_HEALTH - healthLevel);
        g = ColorUtil.green(color) + ColorUtil.green(COLOR_MODIFIER) * (DEFAULT_HEALTH - healthLevel);
        b = ColorUtil.blue(color) + ColorUtil.blue(COLOR_MODIFIER) * (DEFAULT_HEALTH - healthLevel);

        setColor(ColorUtil.rgb(r, g, b));
    }

    /**
     * Reduce the ladybug's foodLevel based on foodConsumptionRate
     */
    public void reduceFood() {
        if (foodLevel - foodConsumptionRate < 0) {
            foodLevel = 0;
        } else {
            foodLevel -= foodConsumptionRate;
        }
    }

    @Override
    public String toString() {
        return "Ladybug: loc=" + Math.round(getLocation().getX()*10.0)/10.0 + ","
                + Math.round(getLocation().getY()*10.0)/10.0 + " color=["
                + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + ","
                + ColorUtil.blue(getColor()) + "] heading=" + getHeading()
                + " speed=" + getSpeed() + " size=" + getSize() + " maxSpeed="
                + maximumSpeed + " foodConsumptionRate=" + foodConsumptionRate;
    }

}
