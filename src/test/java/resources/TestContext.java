package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {
    public RequestSpecification request;
    public Response response;
    public JsonPath responseJson;
    public TestDataBuild data = new TestDataBuild();
    public JsonPath jsonPath;
    public int lastCreatedBookingId;
}