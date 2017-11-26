package com.smadia.jangka.Models;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonFetcherAsyncTask;
import com.smadia.jangka.JSON.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

abstract public class Models {

    private String lastStringResult;

    protected String host = "http://192.168.55.2";

    protected String root = "jangka/public";

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
        JsonParser jsonParser = new JsonParser(jsonArray);
        JSONObject jsonObject;

            try {
                jsonObject = jsonParser.getJsonObjects().get(index);
                return jsonObject;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        return new JSONObject();
    }

    public static Models find(int id) {
        return null;
    }

    abstract public void setPropetyFromJsonObject(JSONObject jsonObject);

    @Override
    public String toString() {
        return this.lastStringResult;
    }

}
