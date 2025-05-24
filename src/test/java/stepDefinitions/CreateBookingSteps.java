package stepDefinitions;

import io.cucumber.java.en.Given;
import resources.TestContext;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static resources.Utils.requestSpecification;

public class CreateBookingSteps {
    private final TestContext context;

    public CreateBookingSteps(TestContext context) {
        this.context = context;
    }

    @Given("User prepares a valid booking payload with {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void user_prepares_a_valid_booking_payload_with(String firstname, String lastname, String totalpriceStr, String depositpaidStr, String checkin, String checkout, String additionalneeds) throws IOException {
        boolean depositpaid = Boolean.parseBoolean(depositpaidStr);
        int totalprice = Integer.parseInt(totalpriceStr);

        context.request = given().log().all().spec(requestSpecification()).body(context.data.createBooking(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds));
    }
}