package com.mycompany.a3;

import java.util.HashSet;
import java.util.Random;

import com.codename1.ui.geom.Point2D;

public abstract class GameObject implements ICollider, IDrawable {
    
    private static final int MINIMUM_SIZE = 50;
    private static final int MAXIMUM_SIZE = 150;

    private IGameWorld gw;
    private Point2D location;
    private int size;
    private int color;
    private HashSet<GameObject> collisionSet;
    
    /**
     * @param color
     */
    public GameObject(IGameWorld gw, int color) {
        this.gw = gw;
        Random random = new Random();
        this.size = random.nextInt(MAXIMUM_SIZE - MINIMUM_SIZE + 1) + MINIMUM_SIZE;
        do {
            this.location = validateLocation(new Point2D(random.nextDouble() * gw.getMaxWidth(),
                                                         random.nextDouble() * gw.getMaxHeight()), size);
        } while (collidesWithAny());
        this.color = color;
        this.collisionSet = new HashSet<GameObject>();
    }

    /**
     * @param size
     */
    public GameObject(IGameWorld gw, int size, int color) {
        this.gw = gw;
        Random random = new Random();
        this.size = size;
        do {
            this.location = validateLocation(new Point2D(random.nextDouble() * gw.getMaxWidth(),
                                                         random.nextDouble() * gw.getMaxHeight()), size);
        } while (collidesWithAny());
        this.color = color;
        this.collisionSet = new HashSet<GameObject>();
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
        this.collisionSet = new HashSet<GameObject>();
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

    /**
     * @param otherObject the other GameObject
     * @return true if it collides with the other GameObject
     */
    public boolean collidesWith(GameObject otherObject) {
        int edgeDistance1 = (getSize() + 1) / 2;
        double l1 = getLocation().getX() - edgeDistance1;
        double r1 = getLocation().getX() + edgeDistance1;
        double t1 = getLocation().getY() + edgeDistance1;
        double b1 = getLocation().getY() - edgeDistance1;
        int edgeDistance2 = (otherObject.getSize() + 1) / 2;
        double l2 = otherObject.getLocation().getX() - edgeDistance2;
        double r2 = otherObject.getLocation().getX() + edgeDistance2;
        double t2 = otherObject.getLocation().getY() + edgeDistance2;
        double b2 = otherObject.getLocation().getY() - edgeDistance2;
        return !(((r1 < l2) || (l1 > r2)) || ((t2 < b1) || (t1 < b2)));
    }

    /**
     * Calls the appropriate collision handler in GameWorld
     * @param otherObject the other GameObject in the collision
     */
    public void handleCollision(GameObject otherObject) {
        if (this instanceof Ladybug) {
            if (otherObject instanceof Flag) {
                getGw().handleFlagCollision((Flag) otherObject);
            } else if (otherObject instanceof Spider) {
                getGw().handleSpiderCollision();
            } else if (otherObject instanceof FoodStation) {
                getGw().handleFoodCollision((FoodStation) otherObject);
            }
        } else if (this instanceof Flag && otherObject instanceof Ladybug) {
            getGw().handleFlagCollision((Flag) this);
        } else if (this instanceof Spider && otherObject instanceof Ladybug) {
            getGw().handleSpiderCollision();
        } else if (this instanceof FoodStation && otherObject instanceof Ladybug) {
            getGw().handleFoodCollision((FoodStation) this);
        }
    }

    /**
     * @return true if this GameObject collides with any other in the GameWorld
     */
    public boolean collidesWithAny() {
        IIterator<GameObject> it = getGw().getObjects().getIterator();
        while (it.hasNext()) {
            GameObject object = it.getNext();
            if (this != object && collidesWith(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the collisionSet
     */
    public HashSet<GameObject> getCollisionSet() {
        return collisionSet;
    }

}
