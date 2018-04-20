package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.geom.Point2D;

public abstract class GameObject {
    
    private static final int MINIMUM_SIZE = 50;
    private static final int MAXIMUM_SIZE = 100;

    private IGameWorld gw;
    private Point2D location;
    private int size;
    private int color;
    
    /**
     * @param color
     */
    public GameObject(IGameWorld gw, int color) {
    	this.gw = gw;
        Random random = new Random();
        this.size = random.nextInt(MAXIMUM_SIZE - MINIMUM_SIZE + 1) + MINIMUM_SIZE;
        this.location = validateLocation(new Point2D(random.nextDouble() * gw.getMaxWidth(),
                                                     random.nextDouble() * gw.getMaxHeight()), size);
        this.color = color;
    }

    /**
     * @param size
     */
    public GameObject(IGameWorld gw, int size, int color) {
    	this.gw = gw;
        Random random = new Random();
        this.size = size;
        this.location = validateLocation(new Point2D(random.nextDouble() * gw.getMaxWidth(),
                                                     random.nextDouble() * gw.getMaxHeight()), size);
        this.color = color;
    }
    
    /**
     * @param location
     * @param size
     */
    public GameObject(IGameWorld gw, Point2D location, int size, int color) {
        this.gw = gw;
        this.size = size;
        this.location = validateLocation(location, size);
        this.color = color;
    }
    
    /**
     * @return the location
     */
    public Point2D getLocation() {
        return location;
    }

    public Point2D validateLocation(Point2D location, int size) {
        if (location.getX() > getGw().getMaxWidth() - (size + 1)/2) {
            location.setX(getGw().getMaxWidth() - (size + 1)/2);
        } else if (location.getX() < (size + 1)/2) {
            location.setX((size + 1)/2);
        } else {
            location.setX(location.getX());
        }

        if (location.getY() > getGw().getMaxHeight() - (size + 1) / 2) {
            location.setY(getGw().getMaxHeight() - (size + 1) / 2);
        } else if (location.getY() < (size + 1) / 2) {
            location.setY((size + 1) / 2);
        } else {
            location.setY(location.getY());
        }
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point2D location) {
        this.location = validateLocation(location, this.size);
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

    /**
     * @return the GameWorld
     */
	public IGameWorld getGw() {
		return gw;
	}

}
