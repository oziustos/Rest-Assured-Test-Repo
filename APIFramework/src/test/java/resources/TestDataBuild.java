package resources;

import pojo.AddPlaceBody;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlaceBody addPlacePayload(String name, String language, String address) {
        // Creating types list
        List<String> types = new ArrayList<String>();
        types.add("shoe park");
        types.add("shop");

        //Creating Locaiton class
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        // Creating body's java class
        AddPlaceBody bodyAddPlace = new AddPlaceBody();
        bodyAddPlace.setAccuracy(50);
        bodyAddPlace.setName(name);
        bodyAddPlace.setPhone_number("(+91) 983 893 3937");
        bodyAddPlace.setAddress(address);
        bodyAddPlace.setWebsite("http://google.com");
        bodyAddPlace.setLanguage(language);
        bodyAddPlace.setTypes(types);
        bodyAddPlace.setLocation(location);
        return bodyAddPlace;
    }

    public String deletePlacePayload(String place_id) {
        return "{\"place_id\": \""+place_id+"\"}";
    }

}
