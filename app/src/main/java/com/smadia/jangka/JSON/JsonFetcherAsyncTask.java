package com.smadia.jangka.JSON;

import android.os.AsyncTask;

public class JsonFetcherAsyncTask extends AsyncTask<JsonFetcher, Integer, JsonFetcher> {

    protected AsyncTaskListener asyncTaskListener;

    static Integer progress = 0;

    public JsonFetcherAsyncTask() {}

    public JsonFetcherAsyncTask(AsyncTaskListener asyncTaskListener) {
        this.asyncTaskListener = asyncTaskListener;
    }

    public void setAsyncTaskListener(AsyncTaskListener asyncTaskListener) {
        this.asyncTaskListener = asyncTaskListener;
    }

    @Override
    protected JsonFetcher doInBackground(JsonFetcher... jsonFetchers) {
        progress++;
        this.publishProgress(progress);
        return jsonFetchers[0].fetch();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(this.asyncTaskListener != null)
            this.asyncTaskListener.onPreExecuteListener();
    }

    @Override
    protected void onPostExecute(JsonFetcher jsonFetcher) {
        super.onPostExecute(jsonFetcher);

        if(this.asyncTaskListener != null)
            this.asyncTaskListener.onPostExecuteListener(jsonFetcher);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        if(this.asyncTaskListener != null) {
            this.asyncTaskListener.onProgressUpdateListener(values[0]);
        }
    }

}
