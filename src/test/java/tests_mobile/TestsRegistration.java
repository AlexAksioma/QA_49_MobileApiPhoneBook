package tests_mobile;

import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
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
    }
}
