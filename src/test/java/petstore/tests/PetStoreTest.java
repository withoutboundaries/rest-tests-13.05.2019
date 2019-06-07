package petstore.tests;

import net.serenitybdd.junit.runners.SerenityRunner;

import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoints.PetEndPoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;


@RunWith(SerenityRunner.class)
public class PetStoreTest {
    @Steps
    private PetEndPoint petEndPoint;

    public PetStoreTest() {
        petEndPoint = new PetEndPoint();
    }


    @Test
    public void getPetByIdTest() {
        int petId = 2;
        petEndPoint
                .getPetById(petId)
                .statusCode(200);
    }

    @Test
    public void getPetByStatusTest() {

        for (PetEndPoint.Status status : PetEndPoint.Status.values()) {
            petEndPoint
                    .getPetByStatus(status)
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

        petEndPoint
                .createPet(petModel)
                .statusCode(200);
    }





    @Test
    public void deletePetByIdTest(){
       int petId =25;

        petEndPoint
                .deletePet(petId)
                .statusCode(200);
    }

}
