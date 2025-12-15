package manager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.User;
import utils.BaseApi;
import utils.UserFactory;

import static io.restassured.RestAssured.given;

public class AuthenticationController implements BaseApi {

    public static void main(String[] args) {
        System.out.println(requestRegLogin(UserFactory.positiveUser(), REGISTRATION).getStatusLine());
    }

    public static Response requestRegLogin(User user, String url){
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .post(url)
                .thenReturn()
                ;
    }
}
