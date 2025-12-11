package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.android_utils.SwipeUtils;

import java.time.Duration;

public class BaseScreen implements SwipeUtils {
    protected static AppiumDriver driver;

    public BaseScreen(AppiumDriver driver){
        BaseScreen.driver = driver;
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(20)), this);
    }

    public boolean textInElementPresent(WebElement element, String text, int time){
        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public boolean isElementPresent(WebElement element, int time){
        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }


}
