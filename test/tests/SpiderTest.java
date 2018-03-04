package tests;

import com.codename1.testing.AbstractTest;

import com.codename1.ui.Display;
import com.mycompany.a2.Spider;
import com.codename1.charts.util.ColorUtil;

public class SpiderTest extends AbstractTest {
	
    @Override
    public boolean runTest() throws Exception {
    	testSetColor();
        return true;
    }
    
    public void testSetColor() {
    	Spider spider = new Spider();
    	spider.setColor(ColorUtil.BLUE);
    	assertEqual(ColorUtil.BLACK, spider.getColor());
    }
}
