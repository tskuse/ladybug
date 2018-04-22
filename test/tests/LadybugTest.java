package tests;

import com.codename1.testing.AbstractTest;

import com.codename1.ui.Display;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Ladybug;
import com.codename1.charts.util.ColorUtil;

public class LadybugTest extends AbstractTest {
	
    @Override
    public boolean runTest() throws Exception {
    	testMaximumSpeed();
        return true;
    }
    
    public void testMaximumSpeed() {
        GameWorld gw = new GameWorld();
        gw.init();
        Ladybug ladybug = Ladybug.getLadybug(gw, new Point2D(0, 0));
        ladybug.setSpeed(2000);
        assertEqual(300, ladybug.getSpeed());
    }

    public void testReduceHealthColorUpdate() {
        GameWorld gw = new GameWorld();
        gw.init();
        Ladybug ladybug = Ladybug.getLadybug(gw, new Point2D(0,0));
        assertEqual(ColorUtil.rgb(255,0,0), ladybug.getColor());
        ladybug.decreaseHealth();
        assertEqual(ColorUtil.rgb(255,20,20), ladybug.getColor());
        ladybug.decreaseHealth();
        assertEqual(ColorUtil.rgb(255,40,40), ladybug.getColor());
        ladybug.decreaseHealth();
        assertEqual(ColorUtil.rgb(255,60,60), ladybug.getColor());
        ladybug.decreaseHealth();
        assertEqual(ColorUtil.rgb(255,80,80), ladybug.getColor());
        ladybug.decreaseHealth();
        assertEqual(ColorUtil.rgb(255,100,100), ladybug.getColor());
        ladybug.decreaseHealth();
        assertEqual(ColorUtil.rgb(255,120,120), ladybug.getColor());
        ladybug.decreaseHealth();
        assertEqual(ColorUtil.rgb(255,140,140), ladybug.getColor());
    }
}
