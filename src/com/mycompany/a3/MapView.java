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
    private GameObjectCollection<GameObject> objects;

    public MapView() {
        this.getAllStyles().setBorder(
                Border.createInsetBorder(5, ColorUtil.rgb(255, 0, 0)));
        this.getAllStyles().setBgColor(ColorUtil.WHITE);
        this.getAllStyles().setBgTransparency(255);
        this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        this.add(BorderLayout.CENTER, new Label("MapView"));
    }
    
    public void update(Observable observable, Object data) {
        objects = ((IGameWorld) observable).getObjects();
        ((IGameWorld) observable).displayMap();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        IIterator<GameObject> it = objects.getIterator();
        while (it.hasNext()) {
            GameObject object = it.getNext();
            if (object instanceof IDrawable) {
                ((IDrawable) object).draw(g, new Point2D(getX(), getY()));
            }
        }
    }
}