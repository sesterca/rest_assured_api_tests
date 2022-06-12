import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiTests extends BaseTest{

    @Test
    public void registrationTest(){

        String credentials = "{\"email\": \"janet.weaver@reqres.in\",\n" +
                "\"password\": \"pistol\"\n}";

        given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .log().body()
                .when()
                .post(BASEURL + Endpoints.REGISTER.endpoint)
                .then()
                .log().body()
                .statusCode(200)
                .body("id",is(2))
                .body("token", is("QpwL5tke4Pnpja7X2"));
    }

    @Test
    public void loginTest(){

        String credentials = "{\"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"}";

        given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .when()
                .post(BASEURL + Endpoints.LOGIN.endpoint)
                .then()
                .statusCode(200);
    }

    @Test
    public void getUserProfileData(){

        int id = 2;

        given()
                .when()
                .log().uri()
                .get(BASEURL + Endpoints.USERS.endpoint + "/" + id)
                .then()
                .log().body()
                .statusCode(200)
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    public void createPlayer(){

        String character = "{\"name\": \"morpheus\",\n" +
                "\"job\": \"leader\"}";

        given()
                .body(character)
                .contentType(ContentType.JSON)
                .when()
                .post(BASEURL + Endpoints.USERS.endpoint)
                .then()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    public void deleteUser(){
        int id = 2;

        given()
                .when()
                .delete(BASEURL + Endpoints.USERS.endpoint + id)
                .then()
                .statusCode(204);
    }
}
