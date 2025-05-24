package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import resources.TestContext;

import java.io.IOException;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static resources.Utils.requestSpecification;

public class CommonSteps {
    private final TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @Given("the user has the restful-booker URL")
    public void the_user_has_the_restful_booker_url() throws IOException {
        context.request = given().log().all().spec(requestSpecification());
    }

    @When("the user sends a {string} request to the {string} endpoint")
    public void the_user_sends_a_request_to_the_endpoint(String method, String endpoint) {
        if (endpoint.contains("{bookingId}")) {
            endpoint = endpoint.replace("{bookingId}", String.valueOf(context.lastCreatedBookingId));
        }

        context.response = switch (method.toUpperCase()) {
            case "POST" -> context.request.when().post(endpoint);
            case "GET" -> context.request.when().get(endpoint);
            case "PUT" -> context.request.when().put(endpoint);
            case "PATCH" -> context.request.when().patch(endpoint);
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        };
        context.response.then().log().all();
        context.responseJson = new JsonPath(context.response.asString());

        if (method.equals("POST") && endpoint.equals("booking")) {
            context.lastCreatedBookingId = context.responseJson.getInt("bookingid");
        }
    }

    @Then("the user should receive status code {int} in the response")
    public void the_user_should_receive_status_code_in_the_response(int statusCode) {
        Assert.assertEquals(statusCode, context.response.getStatusCode());
    }

    @Then("the user should receive a {int} OK response")
    public void the_user_should_receive_a_ok_response(Integer statusCode) {
        Assert.assertEquals(200, context.response.getStatusCode());
    }


    @Then("{string} in response body equals {string}")
    public void in_response_body_equals(String path, String expectedValueStr) {
        context.jsonPath = context.responseJson;
        Object actualValue = context.jsonPath.get(path);

        Object expectedValue;

        if (actualValue instanceof Integer) {
            expectedValue = Integer.parseInt(expectedValueStr);
        } else if (actualValue instanceof Boolean) {
            expectedValue = Boolean.parseBoolean(expectedValueStr);
        } else if (actualValue instanceof Float || actualValue instanceof Double) {
            expectedValue = Double.parseDouble(expectedValueStr);
        } else {
            expectedValue = expectedValueStr;
        }

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Then("the response body should has {string} item and it should a {string}")
    public void the_response_body_should_has_item_and_it_should_a(String key, String expectedType) {
        Object value = context.responseJson.get(key);

        Assert.assertNotNull("Response body does not contain key: " + key, value);

        switch (expectedType.toLowerCase()) {
            case "string":
                Assert.assertTrue("The value of '" + key + "' is not a String", value instanceof String);
                break;
            case "integer":
                Assert.assertTrue("The value of '" + key + "' is not an Integer", value instanceof Integer);
                break;
            case "boolean":
                Assert.assertTrue("The value of '" + key + "' is not a Boolean", value instanceof Boolean);
                break;
            default:
                Assert.fail("Unsupported type check: " + expectedType);
        }
    }
}
