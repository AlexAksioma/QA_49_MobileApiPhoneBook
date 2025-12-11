package tests_mobile;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.EditContactScreen;
import screens.ErrorScreen;
import utils.ContactFactory;

public class TestsEditContact extends TestBase{
    AuthenticationScreen authenticationScreen;
    ContactListScreen contactListScreen;
    EditContactScreen editContactScreen;


    @BeforeMethod
    public void login() {
        authenticationScreen = new AuthenticationScreen(driver);
        User user = new User("a@mail.ru", "Password123!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
    }

    @Test
    public void editContactPositiveTest() {
        contactListScreen.openContactMiddle();
        editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(ContactFactory.positiveContact());
        Assert.assertTrue(contactListScreen.validatePopUpMessage("Contact was updated!", 10));
    }

    @Test
    public void editContactNegativeTest_wrongLastName() {
        Contact contact = ContactFactory.positiveContact();
        contact.setLastName("");
        contactListScreen.openContactMiddle();
        editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(contact);
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorText("lastName=must not be blank", 10));
    }
}
