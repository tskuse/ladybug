package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class Flag extends Fixed implements ISelectable, IUncolorable {

    private static final int DEFAULT_SIZE = 100;
    private static final int DEFAULT_COLOR = ColorUtil.rgb(0, 0, 255);

    private int sequenceNumber;
    private boolean selected;
    private boolean selectable;

    /**
     * @param location
     * @param sequenceNumber
     */
    public Flag(IGameWorld gw, Point2D location, int sequenceNumber) {
        super(gw, location, DEFAULT_SIZE, DEFAULT_COLOR);
        this.sequenceNumber = sequenceNumber;
        this.selected = false;
        this.selectable = false;
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

    public void draw(Graphics g, Point2D pCmpRelPrnt) {
        Point2D origin = new Point2D(pCmpRelPrnt.getX() + getLocation().getX(), pCmpRelPrnt.getY() + getLocation().getY());
        Point2D v1 = new Point2D(origin.getX(), origin.getY() + (getSize() + 1) / 2);
        Point2D v2 = new Point2D(origin.getX() - (getSize() + 1) / 2, origin.getY() - (getSize() + 1) / 2);
        Point2D v3 = new Point2D(origin.getX() + (getSize() + 1) / 2, origin.getY() - (getSize() + 1) / 2);
        g.setColor(getColor());
        if (selected) {
            g.drawPolygon(new int[] {(int) v1.getX(), (int) v2.getX(), (int) v3.getX()},
                          new int[] {(int) v1.getY(), (int) v2.getY(), (int) v3.getY()}, 3);
        } else {
            g.fillTriangle((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY(), (int) v3.getX(), (int) v3.getY());
            g.setColor(ColorUtil.rgb(255, 255, 255));
        }
        g.drawStringBaseline(Integer.toString(sequenceNumber),
                             (int) origin.getX() - (g.getFont().stringWidth(Integer.toString(sequenceNumber)) + 1) / 2,
                             (int) origin.getY());
	}

	public void setSelected(boolean enabled) {
        this.selected = enabled;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelectable(boolean enabled) {
		this.selectable = enabled;
	}

	public boolean isSelectable() {
		return selectable;
	}

}
