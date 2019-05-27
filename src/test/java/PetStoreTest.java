import io.restassured.RestAssured;
import org.junit.Test;


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

        RestAssured.given()
                .param("status", Status.AVAILABLE)
                .log().uri()
                .get(Config.GET_PET_BY_STATUS)
                .then()
                .log().all()
                .statusCode(200);


        String[] statuses = {"AVAILABLE", "PENDING", "SOLD"};
        for (String i : statuses) {
            System.out.println(i);
        }
    }

//    @Test
//    public void postPetTest() {
//        String newPet = "{}";
// //Newline is replaced with \n.
// //Double quote is replaced with \"
//
//        RestAssured.given()
//                .body(newPet)
//                .log().uri()
//                .get(Config.POST_PET)
//                .then()
//                .log().all()
//                .statusCode(200);
//    }
//

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
