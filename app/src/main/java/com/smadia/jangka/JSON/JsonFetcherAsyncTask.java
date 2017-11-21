package com.smadia.jangka.JSON;

import android.os.AsyncTask;

public class JsonFetcherAsyncTask extends AsyncTask<JsonFetcher, Long, JsonFetcher> {

    @Override
    protected JsonFetcher doInBackground(JsonFetcher... jsonFetchers) {
        return jsonFetchers[0].fetch();
    }

}
