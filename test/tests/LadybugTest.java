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
        ladybug.setSpeed(200);
        assertEqual(50, ladybug.getSpeed());
    }
}
