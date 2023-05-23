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
    void GetUsers_ExistingUsers() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 2));
    }

    @Test
    void GetUsers_noUsers() {
        // TODO: Clear users before this test

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body(is(empty()));
    }

    @Test
    void GetUserById_existingId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    void GetUserById_nonExistingId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/999")
                .then()
                .statusCode(500);
    }

    @Test
    void CreateUser_validValues() {
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
    void CreateUser_missingName() {
        User newUser = new User(null, null, "email@example.com");

        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post("/api/users")
                .then()
                .statusCode(500);
    }

    @Test
    void CreateUser_missingEmail() {
        User newUser = new User(null, "Name", null);

        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post("/api/users")
                .then()
                .statusCode(500);
    }

    @Test
    void UpdateUser_validValues() {
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
    void UpdateUser_nonExistingId() {
        User updatedUser = new User(null, "Updated", "updated@example.com");

        given()
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .put("/api/users/999")
                .then()
                .statusCode(500);
    }

    @Test
    void UpdateUser_missingName() {
        User updatedUser = new User(null, null, "updated@example.com");

        given()
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .put("/api/users/1")
                .then()
                .statusCode(500);
    }

    @Test
    void UpdateUser_missingEmail() {
        User updatedUser = new User(null, "Updated", null);

        given()
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .put("/api/users/1")
                .then()
                .statusCode(500);
    }

    @Test
    void DeleteUser_existingId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/users/3")
                .then()
                .statusCode(200);
    }

    @Test
    void DeleteUser_nonExistingId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/users/999")
                .then()
                .statusCode(500);
    }
}
