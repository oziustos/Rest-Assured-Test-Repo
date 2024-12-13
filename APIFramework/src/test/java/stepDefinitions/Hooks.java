package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinition m = new StepDefinition();
        if (StepDefinition.place_id == null) {
            m.add_place_payload_with("Shetty", "French", "Asia");
            m.user_calls_with_http_request("addPlaceAPI", "POST");
            m.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
        }
    }
}
