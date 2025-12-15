package tests_api;

import io.restassured.response.Response;
import manager.AuthenticationController;
import manager.ContactController;
import models.TokenDto;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utils.PropertiesReader.*;

public class TestAddNewContact extends ContactController {

    TokenDto tokenDto;

    @BeforeClass
    public void login() {
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        Response response = AuthenticationController.requestRegLogin(user, LOGIN);
        if(response.getStatusCode() == 200)
            tokenDto = response.body().as(TokenDto.class);
        else
            throw new IllegalArgumentException("Login status code =" + response.getStatusCode());
    }

    @Test
    public void addNewContactPositiveTest(){
        System.out.println(tokenDto.toString());
    }
}
