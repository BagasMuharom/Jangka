package com.smadia.jangka.JSON;

import org.json.JSONTokener;

public interface AsyncTaskListener {

    public void onPreExecuteListener();

    public void onPostExecuteListener(JsonFetcher jsonFetcher);

    public void onProgressUpdateListener(Integer progress);

}
