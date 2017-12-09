package com.smadia.jangka.Models;

import android.os.AsyncTask;

import com.smadia.jangka.JSON.JsonFetcher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

abstract public class Models<T> {

    abstract public void setPropetyFromJsonObject(JSONObject jsonObject);

    abstract public ArrayList<T> all(JSONArray jsonArray);

    public ArrayList<T> all(JsonFetcher jsonFetcher) {
        return this.all(jsonFetcher.getJsonArray());
    }

}
