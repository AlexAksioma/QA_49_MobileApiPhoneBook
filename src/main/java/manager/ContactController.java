package manager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Contact;
import utils.BaseApi;

import static io.restassured.RestAssured.given;

public class ContactController implements BaseApi {

    public static Response requestAddNewContact(Contact contact, String url, String token){
        return given()
                .body(contact)
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
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
}
