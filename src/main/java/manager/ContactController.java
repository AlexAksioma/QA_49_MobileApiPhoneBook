package manager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Contact;
import models.ResponseMessageDto;
import models.TokenDto;
import models.User;
import utils.BaseApi;
import utils.ContactFactory;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static utils.PropertiesReader.getProperty;

public class ContactController implements BaseApi {

    static private RequestSpecification getRequestSpecification(){
        return new RequestSpecBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build();
    }


    public static Response requestAddNewContact(Contact contact, String url, String token){
        return given()
                .body(contact)
                .baseUri(BASE_URL)
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                //.spec(getRequestSpecification())
                .when()
                .post(url)
                .thenReturn()
                ;
    }

    public static Response getAllUserContacts(String url, String token){
        return given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get(ADD_NEW_CONTACT)
                .thenReturn()
                ;
    }

    public static Response requestEditContact(Contact contact, String url, String token){
        return given()
                .body(contact)
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .put(url)
                .thenReturn()
                ;
    }

    public static Response requestDeleteContactById(String contactId, String url, String token){
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .delete(url + "/" + contactId)
                .thenReturn()
                ;
    }

    /**
     *
     * @return   List[0] - token, List[1] - contactId
     */
    public List<String> loginWithValidCredentials() {
        List<String> result = new ArrayList<>(2);
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        Response responseLogin = AuthenticationController.requestRegLogin(user, LOGIN);
        if (responseLogin.getStatusCode() == 200)
            result.add(responseLogin.body().as(TokenDto.class).getToken());
        else
            throw new IllegalArgumentException("Login status code =" + responseLogin.getStatusCode());
        Contact contact = ContactFactory.positiveContact();
        Response responseAdd = requestAddNewContact(contact, ADD_NEW_CONTACT, result.get(0));
        if (responseAdd.getStatusCode() == 200) {
            ResponseMessageDto responseMessageDto = responseAdd.body().as(ResponseMessageDto.class);
            //System.out.println(responseMessageDto.getMessage());
            result.add(responseMessageDto.getMessage().split("ID: ")[1]);
        } else
            throw new IllegalArgumentException("Add contact status code =" + responseAdd.getStatusCode());
        return result;
    }
}
