package booking.apiBooking.api;

import booking.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Ping {
    public Response healthCheck(){
        Response response = RestAssured.given()
                .baseUri(Constants.URI)
                .when()
                .get("/ping");
        return response;
    }

}
