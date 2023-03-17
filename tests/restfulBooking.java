package booking.apiBooking.tests;
import booking.apiBooking.api.ActionBooking;
import booking.apiBooking.api.CreateToken;
import booking.apiBooking.api.ListBooking;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class restfulBooking {
    @BeforeTest
    public void login(){
        CreateToken createToken = new CreateToken();
        Assert.assertEquals(createToken.authToken().statusCode(),200);
    }
    @Test
    public void createBooking() throws FileNotFoundException {
        ActionBooking createBooking = new ActionBooking();
        Assert.assertEquals(createBooking.createBooking().statusCode(),200);
        String body = createBooking.createBooking().body().asString();
        JSONObject jsonObjectBody = new JSONObject(body);
        JSONObject jsonObject = jsonObjectBody.getJSONObject("booking");
        Assert.assertEquals(jsonObject.getString("firstname"),"Jim");
        Assert.assertEquals(jsonObject.getString("lastname"),"Brown");
        Assert.assertEquals(jsonObject.getInt("totalprice"),111);
        ListBooking booking = new ListBooking();
        booking.getBookingIds();
    }
    @Test
    public void updateBooking() throws FileNotFoundException {
        ActionBooking updateBooking = new ActionBooking();
        Assert.assertEquals(updateBooking.updateBooking().statusCode(),200);
        String body = updateBooking.updateBooking().body().asString();
        JSONObject jsonObject = new JSONObject(body);
        Assert.assertEquals(jsonObject.getString("firstname"),"James");
        Assert.assertEquals(jsonObject.getString("lastname"),"Brown");
        Assert.assertEquals(jsonObject.getInt("totalprice"),111);
    }
    @AfterTest
    public void deleteBooking(){
        ActionBooking deleteBooking = new ActionBooking();
        Assert.assertEquals(deleteBooking.deleteBooking().statusCode(),201);
    }
}
