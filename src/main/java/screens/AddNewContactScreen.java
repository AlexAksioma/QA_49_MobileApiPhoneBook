package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.Contact;
import org.openqa.selenium.WebElement;

public class AddNewContactScreen extends BaseScreen {
    public AddNewContactScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputName")
    WebElement inputName;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputLastName")
    WebElement inputLastName;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputEmail")
    WebElement inputEmail;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputPhone")
    WebElement inputPhone;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputAddress")
    WebElement inputAddress;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputDesc")
    WebElement inputDesc;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/createBtn")
    WebElement btnCreateContact;

    public void typeContact(Contact contact) {
        inputName.sendKeys(contact.getName());
        inputLastName.sendKeys(contact.getLastName());
        inputEmail.sendKeys(contact.getEmail());
        inputPhone.sendKeys(contact.getPhone());
        inputAddress.sendKeys(contact.getAddress());
        inputDesc.sendKeys(contact.getDescription());
    }

    public void clickBtnCreateContact(){
        btnCreateContact.click();
    }
}
