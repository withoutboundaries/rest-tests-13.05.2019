package weather;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(SerenityRunner.class)
public class WeatherTest {

    @Test
    public void getWeatherPerCityTest() {
        String cityName="Lviv";
        RestAssured.baseURI = "https://pinformer.sinoptik.ua/";
        // RestAssured.basePath= "search.php";
        ValidatableResponse response = SerenityRest.given()
                .basePath("search.php")
                .param("lang", "ua")
                .param("return_id", 1)
                .param("q", cityName)
                //.log().uri()
                .get()
                .then()
                //.log().all()
                .statusCode(200);

        String responseString = response.extract().asString();
        String cityId = responseString.substring(responseString.lastIndexOf("|") + 1);
        System.out.println(cityId);

        //System.out.println(cityId.substring(47));
        // RestAssured.basePath= "pinformer4.php";

        ValidatableResponse response1 = SerenityRest.given()
                .param("type", "js")
                .param("lang", "en")
                .param("id", cityId)
                .log().uri()
                .get("pinformer4.php")
                .then()
                .log().all()
                .statusCode(200)
                .body("any {it.key=='{pcity}' }", is(true))//Grrovy path with hamcrest matchers
                .body("'{pcity}'", is(not(1)));//JSON path with hamcrest matchers
       // System.out.println(response1.extract().path("'{pcity}'"))
        ;

    }
}
