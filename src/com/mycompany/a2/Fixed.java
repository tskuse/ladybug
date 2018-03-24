package com.mycompany.a2;

import com.codename1.ui.geom.Point2D;

public abstract class Fixed extends GameObject {

    /**
     * @param color
     */
    public Fixed(IGameWorld gw, int color) {
        super(gw, color);
    }

    /**
     * @param size
     * @param color
     */
    public Fixed(IGameWorld gw, int size, int color) {
        super(gw, size, color);
    }

    /**
     * Returns a new Point2D object to prevent location from being changed through Point2D class methods
     */
    @Override
    public Point2D getLocation() {
        Point2D location = super.getLocation();
        return new Point2D(location.getX(), location.getY());
    }
    
    @Override
    public void setLocation(Point2D location) {
        return;
    }

}
