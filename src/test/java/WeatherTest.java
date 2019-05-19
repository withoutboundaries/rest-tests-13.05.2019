import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;


public class WeatherTest {

    @Test
    public void getWeatherPerCityTest() {
        RestAssured.baseURI = "https://pinformer.sinoptik.ua/search.php";
        ValidatableResponse response = RestAssured.given()
                .param("lang", "ua")
                .param("return_id", 1)
                .param("q", "Lviv")
                //.log().uri()
                .get()
                .then()
                //.log().all()
                .statusCode(200);

        String cityId = response.extract().asString();
        System.out.println(cityId);

        System.out.println(cityId.substring(47));
        RestAssured.baseURI = "https://pinformer.sinoptik.ua/pinformer4.php";

        ValidatableResponse responseWeatherInCity = RestAssured.given()
                .param("lang", "ua")
                .param("id", 303014487)
                .log().uri()
                .get()
                .then()
                .log().all()
                .statusCode(200);
        String cityInfoWeather = responseWeatherInCity.extract().asString();
        System.out.println(responseWeatherInCity);


    }
}
