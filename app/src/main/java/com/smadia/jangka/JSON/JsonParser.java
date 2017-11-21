package com.smadia.jangka.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    private JSONArray jsonArray;

    public JsonParser(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public ArrayList<JSONObject> getJsonObjects() {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObjects.add(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonObjects;
    }

}
