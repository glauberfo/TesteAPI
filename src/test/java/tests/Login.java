package tests;

import DTO.LoginDTO;
import core.Constants;
import static io.restassured.RestAssured.given;

public class Login implements Constants{
    String PATH_TEST = "auth/login";

    public String retornaTokenLogin(String url) {
        LoginDTO dadosLogin = new LoginDTO("","");
        return given()
                .contentType("application/json")
                .body(dadosLogin)
                .when()
                .post(url+PATH_TEST)
                .then()
                .extract().path("token");
    }
}
