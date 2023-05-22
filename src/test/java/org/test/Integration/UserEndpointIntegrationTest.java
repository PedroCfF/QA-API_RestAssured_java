package org.test.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.test.Domain.Model.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class UserEndpointIntegrationTest {
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:3000";
    }

    @Test
    void testGetUsers() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 2));
    }

    @Test
    void testGetUserById() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    void testCreateUser() {
        User newUser = new User(null,"Alice", "alice@example.com");

        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post("/api/users")
                .then()
                .statusCode(200)
                .body("name", equalTo("Alice"));
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User(null, "Updated", "jane.smith@example.com");

        given()
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated"));
    }

    @Test
    void testDeleteUser() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/users/3")
                .then()
                .statusCode(200);
    }
}
