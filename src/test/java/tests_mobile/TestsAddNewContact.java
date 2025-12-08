package tests_mobile;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import utils.ContactFactory;

public class TestsAddNewContact extends TestBase{

    AuthenticationScreen authenticationScreen;
    ContactListScreen contactListScreen;
    AddNewContactScreen addNewContactScreen;

    @BeforeMethod
    public void login(){
        authenticationScreen = new AuthenticationScreen(driver);
        User user = new User("a@mail.ru", "Password123!");
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
        contactListScreen.clickBtnAdd();
        addNewContactScreen = new AddNewContactScreen(driver);
    }

    @Test
    public void addNewContactPositiveTest(){
        Contact contact = ContactFactory.positiveContact();
        addNewContactScreen.typeContact(contact);
        addNewContactScreen.clickBtnCreateContact();
        Assert.assertTrue(contactListScreen.validatePopUpMessage("Contact was added!", 10));
    }

}
