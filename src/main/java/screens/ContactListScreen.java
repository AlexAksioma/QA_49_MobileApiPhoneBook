package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.enums.Direction;

import java.time.Duration;
import java.util.List;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    WebElement textNoContacts;

    @AndroidFindBy(accessibility = "add")
    WebElement btnPlus;

    @AndroidFindBy(xpath = "//android.widget.Toast")
    WebElement popUpMessage;

    //==================================
    @AndroidFindBy(xpath = "//*[@text='YES']")
    WebElement btnYes;

    @AndroidFindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<WebElement> contactListOnScreen;

    public boolean validateContactListScreenOpenAfterRegistration(String text, int time){
        return textInElementPresent(textNoContacts, text, time);
    }

    public boolean btnPlusIsPresent(int time){
        return isElementPresent(btnPlus, time);
    }

    public void clickBtnAdd() {
        btnPlus.click();
    }

    public boolean validatePopUpMessage(String text, int time){
        return textInElementPresent(popUpMessage,text, time);
    }

    public void deleteContactMiddle(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(btnPlus));
        swipeScreen(driver, Direction.RIGHT);
        btnYes.click();
    }

    public void openContactMiddle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(btnPlus));
        swipeScreen(driver, Direction.LEFT);
    }

    public void deleteFirstContact(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(btnPlus));
        swipeInsideElement(driver, contactListOnScreen.get(0), Direction.RIGHT);
        btnYes.click();
    }
}
