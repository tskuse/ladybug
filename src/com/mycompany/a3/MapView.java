package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

import java.util.Observable;
import java.util.Observer;

public class MapView extends Container implements Observer {

    private GameWorld gw;

    public MapView() {
        this.getAllStyles().setBorder(Border.createInsetBorder(5, ColorUtil.rgb(255, 0, 0)));
        this.getAllStyles().setBgColor(ColorUtil.WHITE);
        this.getAllStyles().setBgTransparency(255);
        this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        this.add(BorderLayout.CENTER, new Label("MapView"));
    }
    
    public void update(Observable observable, Object data) {
        gw = (GameWorld) observable;
        gw.displayMap();
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        IIterator<GameObject> it = gw.getObjects().getIterator();
        while (it.hasNext()) {
            GameObject object = it.getNext();
            object.draw(g, new Point2D(getX(), getY()));
        }
    }

    @Override
    public void pointerReleased(int x, int y) {
        Point2D pointer = new Point2D(x - getAbsoluteX(), y - getAbsoluteY());
        IIterator<GameObject> it = gw.getObjects().getIterator();
        while (it.hasNext()) {
            GameObject object = it.getNext();
            if (object instanceof ISelectable) {
                if (isWithinObject(pointer, object) && ((ISelectable) object).isSelectable()) {
                    ((ISelectable) object).setSelected(!((ISelectable) object).isSelected());
                    ((Game) getComponentForm()).setLastSelectedObject(((ISelectable) object));
                } else if (((Game) getComponentForm()).isMoveSelectedObject() && ((ISelectable) object).isSelected()) {
                    Point2D oldLocation = object.getLocation();
                    object.setLocation(pointer);
                    if (object.collidesWithAny()) {
                        object.setLocation(oldLocation);
                    }
                    ((ISelectable) object).setSelected(false);
                    ((Game) getComponentForm()).setMoveSelectedObject(false);
                } else {
                    ((ISelectable) object).setSelected(false);
                }
                repaint();
            }
        }
    }

    private boolean isWithinObject(Point2D pointer, GameObject object) {
        Point2D origin = object.getLocation();
        int edgeDistance = (object.getSize() + 1) / 2;
        return (pointer.getX() >= (origin.getX() - edgeDistance) && pointer.getX() <= (origin.getX() + edgeDistance))
                && (pointer.getY() >= (origin.getY() - edgeDistance) && pointer.getY() <= (origin.getY() + edgeDistance));
    }

}