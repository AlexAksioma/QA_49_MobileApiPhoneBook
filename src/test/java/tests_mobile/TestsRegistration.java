package tests_mobile;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.ErrorScreen;
import screens.SplashScreen;

import static utils.UserFactory.*;

public class TestsRegistration extends TestBase{
    AuthenticationScreen authenticationScreen;

    @BeforeMethod
    public void goToAuthScreen(){
        //new SplashScreen(driver);
        authenticationScreen = new AuthenticationScreen(driver);
    }

    @Test
    public void testRegistrationPositive(){
        User user = positiveUser();
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ContactListScreen(driver)
                .validateContactListScreenOpenAfterRegistration("No Contacts. Add One more!", 10));
    }

    @Test
    public void testRegistrationNegative_emptyEmail(){
        User user = positiveUser();
        user.setUsername("");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorText("username=must not be blank", 10));
    }
}
