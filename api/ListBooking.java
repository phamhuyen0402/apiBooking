package booking.apiBooking.api;

import booking.Constants;
import booking.Variable;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ListBooking {
    public Response getBooking(){
        Response response = RestAssured.given()
                .baseUri(Constants.URI)
                .when()
                .get("/booking");
        return response;
    }
    public Response getBookingIds(){
        Response response = RestAssured.given()
                .baseUri(Constants.URI)
                .when()
                .get("/booking/"+ Variable.bookingId);
        return response;
    }
}
