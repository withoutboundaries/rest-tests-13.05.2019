package petstore;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.Test;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

public class PetUpdateTest {
    static {
        RestAssured.baseURI = Config.BASE_URI;
    }


    @BeforeClass
    public void beforeTest() {
        PetModel petModel = new PetModel(
                77,
                new CategoryModel(),
                "Lilushka",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        RestAssured.given()
                .log().uri()
                //.header("Content-Type","application/xml")
                .contentType("application/json")
                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
                .log().all()
                .statusCode(200);

    }

    @AfterClass
    public void afterMethod() {
        int petId = 77;

        RestAssured.given()
                //.log().uri()
                .get(Config.DELETE_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void updatePetTest() {
        PetModel petModel = new PetModel(
                77,
                new CategoryModel(),
                "Liluha",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        RestAssured.given()
                .log().uri()
                //.header("Content-Type","application/xml")
                .contentType("application/json")
                .body(petModel)
                .put(Config.UPDATE_PET)
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void getPetAfterUpdateTest() {
        int petId = 77;

        RestAssured.given()
                .log().uri()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);
    }
}