package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    WebElement textNoContacts;

    @AndroidFindBy(accessibility = "add")
    WebElement btnPlus;

    public boolean validateContactListScreenOpenAfterRegistration(String text, int time){
        return textInElementPresent(textNoContacts, text, time);
    }

    public boolean btnPlusIsPresent(int time){
        return isElementPresent(btnPlus, time);
    }

}
