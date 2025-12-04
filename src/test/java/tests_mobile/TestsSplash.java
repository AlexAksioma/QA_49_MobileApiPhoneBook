package tests_mobile;

import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class TestsSplash extends TestBase{

    @Test
    public void testVersionApp(){
        SplashScreen splashScreen = new SplashScreen(driver);
        Assert.assertTrue(splashScreen.validateVersionApp("Version 1.0.0", 10));
    }
}
