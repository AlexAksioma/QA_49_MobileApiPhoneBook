package tests_api;

import io.restassured.response.Response;
import manager.AuthenticationController;
import manager.ContactController;
import models.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ContactFactory;

import java.util.List;

import static utils.PropertiesReader.getProperty;

public class TestEditContact extends ContactController {
    List<String> resultLogin;

    @BeforeClass
    public void login(){
        resultLogin = loginWithValidCredentials();
    }

    @Test
    public void editContactPositiveTest() {
        System.out.println(resultLogin.get(1));
        Contact newContact = ContactFactory.positiveContact();
        newContact.setId(resultLogin.get(1));
        Response response = requestEditContact(newContact, ADD_NEW_CONTACT, resultLogin.get(0));
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
        Response response = getAllUserContacts(ADD_NEW_CONTACT, resultLogin.get(0));
        if (response.getStatusCode() == 200) {
            return response.as(ContactsDto.class);
        }
        throw new IllegalArgumentException("Get all contacts status code =" + response.getStatusCode());
    }
}
