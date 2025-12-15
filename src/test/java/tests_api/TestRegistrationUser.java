package tests_api;

import io.restassured.http.Header;
import io.restassured.response.Response;
import manager.AuthenticationController;
import models.ErrorMessageDto;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.UserFactory;

import java.time.LocalDate;

public class TestRegistrationUser extends AuthenticationController {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest(){
        User user = UserFactory.positiveUser();
        Response response = requestRegLogin(user, REGISTRATION);
        System.out.println(response.getStatusLine());
        System.out.println(response.getStatusCode());
        System.out.println(response.body().print());
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertTrue(response.getStatusLine().contains("OK"));
        softAssert.assertTrue(response.body().print().contains("token"));
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_wrongEmail(){
        User user = UserFactory.positiveUser();
        user.setUsername("wrong_mail");
        Response response = requestRegLogin(user, REGISTRATION);
        System.out.println(response.body().print());
        ErrorMessageDto errorMessageDto = response.body().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto.toString());
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(response.getStatusLine().contains("Bad Request"));
        softAssert.assertTrue(errorMessageDto.getMessage().toString().contains("must be a well-formed email address"));
        System.out.println(errorMessageDto.getTimestamp());
        System.out.println(LocalDate.now().toString());
        for (Header h : response.getHeaders().asList()){
            System.out.println(h);
        }
        softAssert.assertTrue(errorMessageDto.getTimestamp().contains(LocalDate.now().toString()));
        softAssert.assertAll();
    }
}
