package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ErrorScreen extends BaseScreen{
    public ErrorScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id="android:id/message")
    WebElement errorText;

    public boolean validateErrorText(String text, int time){
        return textInElementPresent(errorText, text, time);
    }
}
