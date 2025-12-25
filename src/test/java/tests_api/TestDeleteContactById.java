package tests_api;

import io.restassured.response.Response;
import manager.AuthenticationController;
import manager.ContactController;
import models.Contact;
import models.ResponseMessageDto;
import models.TokenDto;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ContactFactory;

import java.util.List;

import static manager.ContactController.requestAddNewContact;
import static utils.PropertiesReader.getProperty;

public class TestDeleteContactById extends ContactController {

    List<String> resultLogin;

    @BeforeClass
    public void login(){
        resultLogin = loginWithValidCredentials();
    }

    @Test
    public void deleteContactByIdPositiveTest(){
        Response response = requestDeleteContactById(resultLogin.get(1), ADD_NEW_CONTACT, resultLogin.get(0));
        Assert.assertEquals(response.statusCode(), 200);
    }
}
