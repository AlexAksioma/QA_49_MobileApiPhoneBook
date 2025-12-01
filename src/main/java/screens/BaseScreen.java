package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BaseScreen {
    protected static AppiumDriver driver;

    public BaseScreen(AppiumDriver driver){
        BaseScreen.driver = driver;
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(20)), this);
    }
}
