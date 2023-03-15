package booking.api;

import booking.Constants;
import booking.Variable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ActionBooking {
    public Response createBooking() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("src/test/java/booking/test_data/CreateBooking.json");
        JSONObject jsonObject = new JSONObject(new JSONTokener(file));
        Response response = RestAssured.given()
                .baseUri(Constants.URI)
                .contentType("application/json")
                .body(jsonObject.toString())
                .when()
                .post("/booking");
        Variable.bookingId = response.jsonPath().getString("bookingid");
        return response;
    }
    public Response updateBooking() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("src/test/java/booking/test_data/UpdateBooking.json");
        JSONObject jsonObject = new JSONObject(new JSONTokener(file));
        Response response = RestAssured.given()
                .baseUri(Constants.URI)
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie","token="+Variable.getToken)
                .body(jsonObject.toString())
                .when()
                .put("/booking/"+Variable.bookingId);
        return response;
    }
    public Response partialUpdateBooking(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname" , "James");
        jsonObject.put("lastname" , "Brown");
        Response response = RestAssured.given()
                .baseUri(Constants.URI)
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie","token="+Variable.getToken)
                .body(jsonObject.toString())
                .when()
                .put("/booking/"+Variable.bookingId);
        return response;
    }
    public Response deleteBooking(){
        Response response = RestAssured.given()
                .baseUri(Constants.URI)
                .contentType("application/json")
                .header("Cookie","token="+Variable.getToken)
                .when()
                .delete("/booking/"+Variable.bookingId);
        return response;
    }
}
