package tests_api;

import io.restassured.response.Response;
import manager.AuthenticationController;
import manager.ContactController;
import models.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ContactFactory;

import static utils.PropertiesReader.getProperty;

public class TestEditContact extends ContactController {
    TokenDto tokenDto;
    String contactId;

    @BeforeClass
    public void login() {
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        Response responseLogin = AuthenticationController.requestRegLogin(user, LOGIN);
        if (responseLogin.getStatusCode() == 200)
            tokenDto = responseLogin.body().as(TokenDto.class);
        else
            throw new IllegalArgumentException("Login status code =" + responseLogin.getStatusCode());
        Contact contact = ContactFactory.positiveContact();
        Response responseAdd = requestAddNewContact(contact, ADD_NEW_CONTACT, tokenDto.getToken());
        if (responseAdd.getStatusCode() == 200) {
            ResponseMessageDto responseMessageDto = responseAdd.body().as(ResponseMessageDto.class);
            System.out.println(responseMessageDto.getMessage());
            contactId = responseMessageDto.getMessage().split("ID: ")[1];
        } else
            throw new IllegalArgumentException("Add contact status code =" + responseAdd.getStatusCode());
    }

    @Test
    public void editContactPositiveTest() {
        System.out.println(contactId);
        Contact newContact = ContactFactory.positiveContact();
        newContact.setId(contactId);
        Response response = requestEditContact(newContact, ADD_NEW_CONTACT, tokenDto.getToken());
        if (response.getStatusCode() == 200) {
            boolean flag = false;
            ContactsDto contactsDto = getAllUserContacts();
            for (Contact contact : contactsDto.getContacts()) {
                if (contact.equals(newContact)) {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!" + contact);
                    flag = true;
                }
            }
            Assert.assertTrue(flag, "Contact is not contained in contact list");
        } else
            Assert.fail("status code " + response.getStatusCode());
    }

    private ContactsDto getAllUserContacts() {
        Response response = getAllUserContacts(ADD_NEW_CONTACT, tokenDto.getToken());
        if (response.getStatusCode() == 200) {
            return response.as(ContactsDto.class);
        }
        throw new IllegalArgumentException("Get all contacts status code =" + response.getStatusCode());
    }
}
