package tests_mobile;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.ErrorScreen;

import static utils.UserFactory.positiveUser;

public class TestsLogin extends TestBase{
    AuthenticationScreen authenticationScreen;

    @BeforeMethod
    public void goToAuthScreen(){
        //new SplashScreen(driver);
        authenticationScreen = new AuthenticationScreen(driver);
    }

    @Test
    public void testLoginPositive(){
        User user = new User("a@mail.ru", "Password123!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        Assert.assertTrue(new ContactListScreen(driver)
                .btnPlusIsPresent(10));
    }

    @Test
    public void testLoginNegative_wrongEmail() {
        User user = new User("a123@mai.exe", "Password123!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorText("Login or Password incorrect", 10));
    }

    @Test
    public void testLoginNegative_wrongPassword() {
        User user = new User("a@mail.ru", "");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorText("Login or Password incorrect", 10));
    }
}
