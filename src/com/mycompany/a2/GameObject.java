package com.mycompany.a2;

import java.util.Random;

import com.codename1.ui.geom.Point2D;

public abstract class GameObject {
    
    private static final int MINIMUM_SIZE = 10;
    private static final int MAXIMUM_SIZE = 50;

    public static final double LOCATION_MAX_X = 1024.0;
    public static final double LOCATION_MAX_Y = 768.0;

    private Point2D location;
    private int size;
    private int color;
    
    /**
     * @param color
     */
    public GameObject(int color) {
        Random random = new Random();
        this.location = new Point2D(random.nextDouble() * LOCATION_MAX_X,
                                    random.nextDouble() * LOCATION_MAX_Y);
        this.size = random.nextInt(MAXIMUM_SIZE - MINIMUM_SIZE + 1) + MINIMUM_SIZE;
        this.color = color;
    }

    /**
     * @param size
     */
    public GameObject(int size, int color) {
        Random random = new Random();
        this.location = new Point2D(random.nextDouble() * LOCATION_MAX_X,
                                    random.nextDouble() * LOCATION_MAX_Y);
        this.size = size;
        this.color = color;
    }
    
    /**
     * @param location
     * @param size
     */
    public GameObject(Point2D location, int size, int color) {
        super();
        this.location = location;
        this.size = size;
        this.color = color;
    }
    
    /**
     * @return the location
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point2D location) {

        // ensure object edges do not fall outside the max area
        if (location.getX() > LOCATION_MAX_X - (this.size + 1)/2) {
            this.location.setX(LOCATION_MAX_X - (this.size + 1)/2);
        } else if (location.getX() < (this.size + 1)/2) {
            this.location.setX((this.size + 1)/2);
        } else {
            this.location.setX(location.getX());
        }

        if (location.getY() > LOCATION_MAX_Y - (this.size + 1) / 2) {
            this.location.setY(LOCATION_MAX_Y - (this.size + 1) / 2);
        } else if (location.getY() < (this.size + 1) / 2) {
            this.location.setY((this.size + 1) / 2);
        } else {
            this.location.setY(location.getY());
        }
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }
    
    /**
     * @param color the color to set
     */
    public void setColor(int color) {
        this.color = color;
    }
    
    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

}
