package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.User;
import org.openqa.selenium.WebElement;

public class AuthenticationScreen extends BaseScreen{
    public AuthenticationScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputEmail")
    WebElement inputEmail;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputPassword")
    WebElement inputPassword;
    @AndroidFindBy(id ="com.sheygam.contactapp:id/regBtn")
    WebElement btnRegistration;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/loginBtn")
    WebElement btnLogin;

    public void typeAuthForm(User user){
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnRegistration(){
        btnRegistration.click();
    }

    public void clickBtnLogin(){
        btnLogin.click();
    }
}
