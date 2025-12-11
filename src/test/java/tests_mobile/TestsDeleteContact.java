package tests_mobile;

import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.AuthenticationScreen;
import screens.BaseScreen;
import screens.ContactListScreen;
import utils.android_utils.SwipeUtils;
import utils.enums.Direction;

public class TestsDeleteContact extends TestBase {
    AuthenticationScreen authenticationScreen;
    ContactListScreen contactListScreen;


    @BeforeMethod
    public void login() {
        authenticationScreen = new AuthenticationScreen(driver);
        User user = new User("a@mail.ru", "Password123!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
    }

    @Test
    public void deleteContactTest() {
        contactListScreen.deleteContactMiddle();
        //validate RestAssured
    }

    @Test
    public void deleteFirstContactTest() {
        contactListScreen.deleteFirstContact();
        //validate RestAssured
    }
}
