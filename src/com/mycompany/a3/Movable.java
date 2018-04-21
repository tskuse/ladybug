package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.geom.Point2D;

public abstract class Movable extends GameObject {
    
    private final int HEADING_RANGE = 360;
    private final int MIN_SPEED = 100;
    private final int MAX_SPEED = 300;
    
    private int heading;
    private int speed;

    /**
     * @param gw
     * @param color
     */
    public Movable(IGameWorld gw, int color) {
        super(gw, color);
        Random random = new Random();
        this.heading = random.nextInt(HEADING_RANGE);
        this.speed = MIN_SPEED + random.nextInt(MAX_SPEED + 1);
    }

    /**
     * @param gw
     * @param location
     * @param size
     * @param color
     */
    public Movable(IGameWorld gw, Point2D location, int size, int color) {
        super(gw, location, size, color);
        Random random = new Random();
        this.heading = random.nextInt(HEADING_RANGE);
        this.speed = MIN_SPEED + random.nextInt(MAX_SPEED + 1);
    }

    /**
     * @param gw
     * @param location
     * @param size
     * @param color
     * @param heading
     * @param speed
     */
    public Movable(IGameWorld gw, Point2D location, int size, int color, int heading, int speed) {
        super(gw, location, size, color);
        this.heading = heading % HEADING_RANGE;
        this.speed = speed;
    }

    /**
     * @return the heading
     */
    public int getHeading() {
        return heading;
    }

    /**
     * @param heading the heading to set
     */
    public void setHeading(int heading) {
        this.heading = heading % HEADING_RANGE;
        // handle Java's funky % behavior
        if (this.heading < 0) {
            this.heading += HEADING_RANGE;
        }
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        if (speed < 0) {
            speed = 0;
        }
        this.speed = speed;
    }

    /**
     * update the object's location based on heading and speed
     * @param elapsedTime elapsed time in milliseconds
     */
    public void move(int elapsedTime) {
        Point2D location = new Point2D(getLocation().getX() + Math.cos(Math.toRadians(90 - heading)) * speed * ((double) elapsedTime / 1000),
                                       getLocation().getY() + Math.sin(Math.toRadians(90 - heading)) * speed * ((double) elapsedTime / 1000));
        setLocation(location);
    }
    
}
