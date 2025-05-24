package stepDefinitions;

import io.cucumber.java.en.Given;
import pojo.AuthBody;
import resources.TestContext;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static resources.Utils.requestSpecification;

public class AuthSteps {
    private final TestContext context;

    public AuthSteps(TestContext context) {
        this.context = context;
    }
    String baseUsername = "admin";
    String basePassword = "password123";

    @Given("the user has the valid auth payload")
    public void the_user_has_the_valid_auth_payload() throws IOException {
        context.request = given().log().all().spec(requestSpecification()).body(context.data.auth(baseUsername, basePassword));
    }

    @Given("the user has auth request with the {string} field set to {string} in the request body")
    public void the_user_has_auth_request_with_the_field_set_to_in_the_request_body(String field, String value) throws IOException {
        AuthBody authBody;
        authBody = new AuthBody();
        switch (field.toLowerCase()) {
            case "username":
                authBody.setUsername(value);
                break;
            case "password":
                authBody.setPassword(value);
                break;
            default:
                throw new IllegalArgumentException("Unsupported field: " + field);
        }
        if (authBody.getPassword() == null) {
            authBody.setPassword(basePassword);
            context.request = given().log().all().spec(requestSpecification()).body(authBody);
        } else if (authBody.getUsername() == null) {
            authBody.setUsername(baseUsername);
            context.request = given().log().all().spec(requestSpecification()).body(authBody);
        } else {
            context.request = given().log().all().spec(requestSpecification()).body(authBody);
        }
    }
}
