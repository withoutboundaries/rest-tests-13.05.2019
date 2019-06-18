package petstore.tests;

import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import petstore.endpoints.PetEndPoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

import java.util.Arrays;
import java.util.Collection;

public class VerifyPetNameTest {
    @Steps

    private PetEndPoint petEndPoint;

    @TestData
    public static Collection <Object[]> testData () {
        return Arrays.asList(new Object[][][]{
                        {"Lucky", 98, 200},
                        {"@@@", 350, 400},
                        {"", 23,200}
                }

                );
    }

    private  final  String petName;
    private final int petId;
    private final int statusCode;

    public VerifyPetNameTest (String petName, int petId, int statusCode) {
        this.petName=petName;
        this.petId=petId;
        this.statusCode=statusCode;
    }

    @Test
    public void createPetTest (){
        PetModel petModel=new PetModel(
                this.petId,
                new CategoryModel(),
                this.petName,
                new String[] {"wwww.zoo.com"},
                new TagModel[] {new TagModel()},
                "AVAILABLE"
        );

        petEndPoint
                .createPet(petModel)
                .statusCode(this.statusCode);
    }
}
