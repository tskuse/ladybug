package tests;

import com.codename1.testing.AbstractTest;

import com.codename1.ui.Display;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Spider;
import com.codename1.charts.util.ColorUtil;

public class SpiderTest extends AbstractTest {
	
    @Override
    public boolean runTest() throws Exception {
    	testSetColor();
        return true;
    }
    
    public void testSetColor() {
        GameWorld gw = new GameWorld();
        gw.init();
    	Spider spider = new Spider(gw);
    	spider.setColor(ColorUtil.BLUE);
    	assertEqual(ColorUtil.BLACK, spider.getColor());
    }
}
