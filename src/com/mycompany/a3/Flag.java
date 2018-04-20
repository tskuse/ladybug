package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

public class Flag extends Fixed implements IUncolorable {

    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_COLOR = ColorUtil.rgb(0, 0, 255);

    private int sequenceNumber;

    /**
     * @param location
     * @param sequenceNumber
     */
    public Flag(IGameWorld gw, Point2D location, int sequenceNumber) {
        super(gw, location, DEFAULT_SIZE, DEFAULT_COLOR);
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @return the sequenceNumber
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }
    
    @Override
    public void setColor(int color) {
        return;
    }

    @Override
    public String toString() {
        return "Flag: loc=" + Math.round(getLocation().getX()*10.0)/10.0 + ","
                + Math.round(getLocation().getY()*10.0)/10.0 + " color=["
                + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor())
                + "," + ColorUtil.blue(getColor()) + "]  size=" + getSize()
                + " seqNum=" + getSequenceNumber();
    }

}
