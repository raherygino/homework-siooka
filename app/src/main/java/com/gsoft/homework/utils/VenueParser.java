package com.gsoft.homework.utils;

import static com.gsoft.homework.constants.data.KeysModelConstants.COUNTRY;
import static com.gsoft.homework.constants.data.KeysModelConstants.FORMATTED_ADDRESS;
import static com.gsoft.homework.constants.data.KeysModelConstants.FSQ_ID;
import static com.gsoft.homework.constants.data.KeysModelConstants.LOCALITY;
import static com.gsoft.homework.constants.data.KeysModelConstants.LOCATION;
import static com.gsoft.homework.constants.data.KeysModelConstants.NAME;

import com.gsoft.homework.models.Location;
import com.gsoft.homework.models.Venue;

import org.json.JSONException;
import org.json.JSONObject;

public class VenueParser {

    public Venue Parse(JSONObject venueObject) {
        Venue venue = null;
        String country = null;
        String formattedAdress = null;
        String locality = null;

        try {
            String fsqId = venueObject.getString(FSQ_ID);
            String name = venueObject.getString(NAME);

            JSONObject locationObject = venueObject.getJSONObject(LOCATION);
            if (locationObject.has(COUNTRY)) {
                country = locationObject.getString(COUNTRY);
            }

            if (locationObject.has(FORMATTED_ADDRESS)) {
                formattedAdress = locationObject.getString(FORMATTED_ADDRESS);
            }

            if (locationObject.has(LOCALITY)) {
                locality = locationObject.getString(LOCALITY);
            }

            Location location = new Location(country,formattedAdress,locality);
            venue = new Venue(fsqId,name,location);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return venue;
    }
}
