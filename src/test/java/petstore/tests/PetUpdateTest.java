package petstore.tests;

import io.restassured.RestAssured;
import org.testng.annotations.*;
import petstore.endpoints.Config;
import petstore.endpoints.PetEndpoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

import static org.hamcrest.CoreMatchers.is;

public class PetUpdateTest {
    private PetEndpoint petEndpoint = new PetEndpoint();
    private PetModel petModel;

    @BeforeMethod
    public void beforeTest() {
        petModel = new PetModel(
                137,
                new CategoryModel(),
                "Lilushka",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndpoint
                .createPet(petModel)
                .statusCode(200);
    }

    @AfterMethod
    public void afterMethod() {
        int petId = 137;
        petEndpoint
                .deletePet(petModel.getId())
                .statusCode(200);
    }

    @Test
    public void updatePetTest() {
        petModel.setName("tiger");
        petModel.setStatus("SOLD");
        petEndpoint
                .updatePet(petModel)
                .statusCode(200);

        petEndpoint
                .getPetById(petModel.getId())
                .statusCode(200);

    }

}