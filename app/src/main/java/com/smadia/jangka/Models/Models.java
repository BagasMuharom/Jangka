package com.smadia.jangka.Models;

import android.os.AsyncTask;

import com.smadia.jangka.JSON.AsyncTaskListener;
import com.smadia.jangka.JSON.JsonFetcher;
import com.smadia.jangka.JSON.JsonFetcherAsyncTask;
import com.smadia.jangka.JSON.JsonParser;
import com.smadia.jangka.Util.App;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

abstract public class Models {

    private AsyncTaskListener asyncTaskListener = null;

    private String lastStringResult;

    protected JSONArray getJsonArray(String dir, String get) {
        JsonFetcherAsyncTask asyncTask = new JsonFetcherAsyncTask(this.asyncTaskListener);
        JsonFetcher jsonFetcher;

        jsonFetcher =  new JsonFetcher(App.generateUrl(dir + get));

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

    public void setAsyncTaskListener(AsyncTaskListener asyncTaskListener) {
        this.asyncTaskListener = asyncTaskListener;
    }
}
