package petstore.tests;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoints.PetEndPoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;


@RunWith(SerenityRunner.class)
public class PetUpdateTest {
    @Steps
    private PetEndPoint petEndPoint;
    private PetModel petModel;

    public PetUpdateTest() {
        petEndPoint = new PetEndPoint();
    }

    @Before
    public void beforeTest() {
        petModel = new PetModel(
                137,
                new CategoryModel(),
                "Lilushka",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndPoint
                .createPet(petModel)
                .statusCode(200);
    }

    @After
    public void afterMethod() {
        int petId = 137;
        petEndPoint
                .deletePet(petModel.getId())
                .statusCode(200);
    }

    @Test
    public void updatePetTest() {
        petModel.setName("tiger");
        petModel.setStatus("SOLD");
        petEndPoint
                .updatePet(petModel)
                .statusCode(200);

        petEndPoint
                .getPetById(petModel.getId())
                .statusCode(200);

    }

}