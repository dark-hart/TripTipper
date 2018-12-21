package com.example.rache.triptipper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JSONParser {
    private static List<Item> items;

    public static List<Item> getData (String data) throws JSONException {

        items = new ArrayList<>();
        //  Create  JSONObject from the data
        try {
            JSONArray jArr = new JSONArray(data);
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject jObj = jArr.getJSONObject(i);
                Item item = new Item();
                item.setStation(getString("station", jObj));
                item.setLat(Double.parseDouble(getString("lat", jObj)));
                item.setLng(Double.parseDouble(getString("lng", jObj)));
                items.add(item);
            }
            return items;
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

}
