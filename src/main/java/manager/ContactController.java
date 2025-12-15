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
}
