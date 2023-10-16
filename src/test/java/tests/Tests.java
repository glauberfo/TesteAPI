package tests;

import DTO.LoginDTO;
import DTO.ProdutoDTO;
import core.BaseTest;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Tests extends BaseTest {

    private String PATH_USERS = "users/";

    @Test(priority = 0, description = "Buscar os usuarios da aplicacao")
    public void ValidarStatusAplicacao() {

        JsonPath expectedJson = new JsonPath(new File("/path/to/expected.json"));

        given()
                .log().all()
                .when()
                .get(PATH_USERS)
                .then()
                .log().all()
                .statusCode(200)
                .body("id",hasItems(instanceOf(Integer.class)))
                .body("name",hasItems(instanceOf(String.class)))
                .body("username",hasItems(instanceOf(String.class)))
                .body("email",hasItems(instanceOf(String.class)))
                .body("address.street",hasItems(instanceOf(String.class)))
                .body("address.suite",hasItems(instanceOf(String.class)))
                .body("address.city",hasItems(instanceOf(String.class)))
                .body("address.zipcode",hasItems(instanceOf(String.class)))
                .body("address.geo.lat",hasItems(instanceOf(String.class)))
                .body("address.geo.lng",hasItems(instanceOf(String.class)))
                .body("phone",hasItems(instanceOf(String.class)))
                .body("website",hasItems(instanceOf(String.class)))
                .body("company.name",hasItems(instanceOf(String.class)))
                .body("company.catchPhrase",hasItems(instanceOf(String.class)))
                .body("company.bs",hasItems(instanceOf(String.class)))

        ;
    }

    @Test(priority = 0, description = "Cadastra um novo usuario na aplicacao")
    public void LoginCredenciaisIncorretas() {

        given()
                .log().all()
                .contentType("application/json")
                .body(gerarUsuario())
                .when()
                .post(PATH_USERS)
                .then()
                .log().all()
                .statusCode(201)
                .body("name", containsString(gerarUsuario().getName()))
                .body("username", containsString(gerarUsuario().getUsername()))
                .body("email", containsString(gerarUsuario().getEmail()))
                .body("phone", containsString(gerarUsuario().getPhone()))
                .body("website", containsString(gerarUsuario().getWebsite()));
    }

    @Test(priority = 0, description = "Deleta um usuario da aplicacao")
    public void LoginSenhaVazia() {
        Integer id = Integer.valueOf("11");

        given()
                .log().all()
                .contentType("application/json")
                .when()
                .delete(PATH_USERS+id)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test(priority = 0, description = "Altera um usuario da aplicacao")
    public void PutUsuario() {
        Integer id = Integer.valueOf("10");

        given()
                .log().all()
                .contentType("application/json")
                .body(gerarUsuario())
                .when()
                .put(PATH_USERS+id)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", containsString(gerarUsuario().getName()))
                .body("username", containsString(gerarUsuario().getUsername()))
                .body("email", containsString(gerarUsuario().getEmail()))
                .body("phone", containsString(gerarUsuario().getPhone()))
                .body("website", containsString(gerarUsuario().getWebsite()));
    }

    public ProdutoDTO gerarUsuario (){
        return new ProdutoDTO().builder()

                .name("Test Glauber")
                .username("test.glauber")
                .email("glauber@teste.com.br")
                .street("Kattie Turnpike")
                .phone("024-648-3804")
                .website("ambrose.net")
                .build();
    }
}
