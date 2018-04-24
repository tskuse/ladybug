package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class Ladybug extends Movable implements ISteerable {

    private static Ladybug ladybug;

    private static final int DEFAULT_SIZE = 80;
    private static final int DEFAULT_COLOR = ColorUtil.rgb(255, 0, 0);
    private static final int COLOR_MODIFIER = ColorUtil.rgb(0, 20, 20);
    private static final int DEFAULT_HEADING = 0;
    private static final int DEFAULT_SPEED = 200;
    private static final int DEFAULT_MAX_SPEED = 400;
    private static final int DEFAULT_FOOD_LEVEL = 20;
    private static final int FOOD_CONSUMPTION_RATE = 50;
    private static final int DEFAULT_HEALTH = 10;
    private static final int DEFAULT_LAST_FLAG = 1;

    private int maximumSpeed;
    private int foodLevel;
    private int foodConsumption;
    private int healthLevel;
    private int lastFlagReached;

    public static Ladybug getLadybug(IGameWorld gw, Point2D location) {
        if (ladybug == null) {
            ladybug = new Ladybug(gw, location);
        }
        return ladybug;
    }
    
    /**
     * @param location
     * @param maximumSpeed
     * @param foodLevel
     * @param foodConsumptionRate
     */
    private Ladybug(IGameWorld gw, Point2D location) {
        super(gw, location, DEFAULT_SIZE, DEFAULT_COLOR, DEFAULT_HEADING, DEFAULT_SPEED);
        reset();
    }

    /**
     * Reset the player
     */
    public void reset() {
        this.maximumSpeed = DEFAULT_MAX_SPEED;
        this.foodLevel = DEFAULT_FOOD_LEVEL;
        this.healthLevel = DEFAULT_HEALTH;
        this.lastFlagReached = DEFAULT_LAST_FLAG;
        this.foodConsumption = 0;
        updateColor();
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
     * @param healthLevel the healthLevel to set
     */
    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    /**
     * @param speed the speed to set
     */
    @Override
    public void setSpeed(int speed) {
        if (speed > maximumSpeed) {
            speed = maximumSpeed;
        }
        super.setSpeed(speed);
    }

    /**
     * Decrease the the health of the ladybug
     */
    public void decreaseHealth() {
        if (--healthLevel < 0) {
            healthLevel = 0;
        }
        maximumSpeed = (int) (maximumSpeed / 4 * ((double) healthLevel / 10) + maximumSpeed / 4 * 3);
        setSpeed(getSpeed());
        updateColor();
    }

    /**
     * Updates the ladybug's color based on current healthLevel
     */
    private void updateColor() {
        int r, g, b;
        int healthLevel = getHealthLevel();

        r = ColorUtil.red(DEFAULT_COLOR) + ColorUtil.red(COLOR_MODIFIER) * (DEFAULT_HEALTH - healthLevel);
        g = ColorUtil.green(DEFAULT_COLOR) + ColorUtil.green(COLOR_MODIFIER) * (DEFAULT_HEALTH - healthLevel);
        b = ColorUtil.blue(DEFAULT_COLOR) + ColorUtil.blue(COLOR_MODIFIER) * (DEFAULT_HEALTH - healthLevel);

        setColor(ColorUtil.rgb(r, g, b));
    }

    /**
     * Reduce the ladybug's foodLevel based on foodConsumptionRate
     */
    public void reduceFood() {
        if (++foodConsumption == FOOD_CONSUMPTION_RATE) {
            if (--foodLevel < 0) {
                foodLevel = 0;
            }
            foodConsumption = 0;
        }
    }

    @Override
    public String toString() {
        return "Ladybug: loc=" + Math.round(getLocation().getX()*10.0)/10.0 + ","
                + Math.round(getLocation().getY()*10.0)/10.0 + " color=["
                + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + ","
                + ColorUtil.blue(getColor()) + "] heading=" + getHeading()
                + " speed=" + getSpeed() + " size=" + getSize() + " maxSpeed="
                + maximumSpeed + " foodConsumption=" + foodConsumption;
    }

    public void draw(Graphics g, Point2D pCmpRelPrnt) {
        Point2D origin = new Point2D(pCmpRelPrnt.getX() + getLocation().getX(), pCmpRelPrnt.getY() + getLocation().getY());
        g.setColor(getColor());
        g.fillArc((int) origin.getX() - (getSize() + 1) / 2,
                  (int) origin.getY() - (getSize() + 1) / 2,
                  getSize(), getSize(),
                  0, 360);
    }

}
