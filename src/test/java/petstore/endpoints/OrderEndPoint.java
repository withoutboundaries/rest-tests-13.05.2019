package petstore.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderEndPoint {
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private RequestSpecification given(){

        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json")
                .log().uri();
    }

    @Step
    public ValidatableResponse createOrder(petstore.model.OrderModel orderModel){

        return  given()
                .body(orderModel)
                .post(Config.CREATE_ORDER)
                .then()
                .log().all();
    }


    @Step
    public ValidatableResponse getOrderById(int orderId) {
        return given()
                .get(Config.GET_ORDER_BY_ID, orderId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step
    public ValidatableResponse delOrderById(int orderId){

        return given()
                .delete(Config.DEL_ORDER_BY_ID,orderId)
                .then()
                .log().all();
    }
}
