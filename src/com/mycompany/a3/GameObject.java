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
        this.location = new Point2D(random.nextDouble() * gw.getMaxWidth(),
                                    random.nextDouble() * gw.getMaxHeight());
        this.size = random.nextInt(MAXIMUM_SIZE - MINIMUM_SIZE + 1) + MINIMUM_SIZE;
        this.color = color;
    }

    /**
     * @param size
     */
    public GameObject(IGameWorld gw, int size, int color) {
    	this.gw = gw;
        Random random = new Random();
        this.location = new Point2D(random.nextDouble() * gw.getMaxWidth(),
                                    random.nextDouble() * gw.getMaxHeight());
        this.size = size;
        this.color = color;
    }
    
    /**
     * @param location
     * @param size
     */
    public GameObject(IGameWorld gw, Point2D location, int size, int color) {
        this.gw = gw;
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
        if (location.getX() > getGw().getMaxWidth() - (this.size + 1)/2) {
            this.location.setX(getGw().getMaxWidth() - (this.size + 1)/2);
        } else if (location.getX() < (this.size + 1)/2) {
            this.location.setX((this.size + 1)/2);
        } else {
            this.location.setX(location.getX());
        }

        if (location.getY() > getGw().getMaxHeight() - (this.size + 1) / 2) {
            this.location.setY(getGw().getMaxHeight() - (this.size + 1) / 2);
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

    /**
     * @return the GameWorld
     */
	public IGameWorld getGw() {
		return gw;
	}

}
