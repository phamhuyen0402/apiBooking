package booking.api;

import booking.Constants;
import booking.Variable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class CreateToken {
    public Response authToken(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username" , "admin");
        jsonObject.put("password" , "password123");
        Response response = RestAssured.given()
                .baseUri(Constants.URI)
                .header("Content-type", "application/json")
                .body(jsonObject.toString())
                .when()
                .post("/auth");
        Variable.getToken = response.jsonPath().getString("token");
        return response;
    }
}
