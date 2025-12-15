package tests_mobile;

import io.restassured.response.Response;
import manager.AuthenticationController;
import models.ContactsDto;
import models.TokenDto;
import models.User;
import org.asynchttpclient.Request;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.AuthenticationScreen;
import screens.BaseScreen;
import screens.ContactListScreen;
import utils.BaseApi;
import utils.android_utils.SwipeUtils;
import utils.enums.Direction;

import static manager.ContactController.getAllUserContacts;
import static utils.BaseApi.ADD_NEW_CONTACT;

public class TestsDeleteContact extends TestBase {
    AuthenticationScreen authenticationScreen;
    ContactListScreen contactListScreen;
    ContactsDto contactsBeforeDelete, contactsAfterDelete;
    TokenDto tokenDto;

    @BeforeMethod
    public void login() {
        User user = new User("a@mail.ru", "Password123!");
        tokenDto = AuthenticationController.requestRegLogin(user, BaseApi.LOGIN)
                .as(TokenDto.class);
        Response response = getAllUserContacts(ADD_NEW_CONTACT, tokenDto.getToken());
        System.out.println(response.getStatusLine());
        if(response.getStatusCode() == 200)
            contactsBeforeDelete = response.as(ContactsDto.class);
        authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeAuthForm(user);
        authenticationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
    }

    @Test
    public void deleteContactTest() {
        contactListScreen.deleteContactMiddle();
        int sizeBefore = contactsBeforeDelete.getContacts().size();
        int sizeAfter =  getAllUserContacts(ADD_NEW_CONTACT, tokenDto.getToken())
                .as(ContactsDto.class).getContacts().size();
        System.out.println(sizeBefore +"----"+sizeAfter);
        Assert.assertEquals(sizeAfter, sizeBefore-1);
    }

    @Test
    public void deleteFirstContactTest() {
        contactListScreen.deleteFirstContact();
        //validate RestAssured
    }
}
