package com.smadia.jangka.Models;

import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonFetcherAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

abstract public class Models {

    private String lastStringResult;

    protected String host = "http://192.168.55.2";

    protected String root = "jangka/public";

    protected String table;

    public Models() {}

    public Models(int id) {
        JSONObject jsonObject = this.getJsonObject(this.table + '/' +id, "" , 0);

        this.setPropetyFromJsonObject(jsonObject);

        if (jsonObject.length() == 0)
            this.lastStringResult = "";
        else
            this.lastStringResult = jsonObject.toString();
    }

    protected JSONArray getJsonArray(String dir, String get) {
        JsonFetcherAsyncTask asyncTask = new JsonFetcherAsyncTask();
        JsonFetcher jsonFetcher = new JsonFetcher(this.host + '/' + this.root + '/' + dir + get);
        asyncTask.execute(jsonFetcher);

        try {
            return asyncTask.get().getJsonArray();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }

    protected JSONObject getJsonObject(String dir, String get, int index) {
        JSONArray jsonArray = this.getJsonArray(dir, get);
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(index);
            return jsonObject;
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    public static Models find(int id) {
        return null;
    }

    abstract public void setPropetyFromJsonObject(JSONObject jsonObject);

}
