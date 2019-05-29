package petstore;

import io.restassured.RestAssured;
import org.junit.Test;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;


public class PetStoreTest {
    static {
        RestAssured.baseURI = Config.BASE_URI;
    }

    private enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    @Test
    public void getPetByIdTest() {
        int petId = 26;

        RestAssured.given()
                .log().uri()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getPetByStatusTest() {

        for (Status status : Status.values()) {
            RestAssured.given()
                    .param("status", status)
                    .log().uri()
                    .get(Config.GET_PET_BY_STATUS)
                    .then()
                    .log().all()
                    .statusCode(200);

        }
    }

    @Test
    public void createPetTest() {
        PetModel petModel = new PetModel(
                98,
               new CategoryModel(),
                "Lilushechka",
                new String[]{"www.zoo.com"},
                new TagModel[] {new TagModel()},
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


    @Test
    public void deletePetByIdTest(){

       int petId =25;

        RestAssured.given()
                //.log().uri()
                .get(Config.DELETE_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);

    }

}
