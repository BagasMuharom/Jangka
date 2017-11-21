package com.smadia.jangka.JSON;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonFetcher {

    public enum METHOD {
        GET, POST
    };

    private String url;

    private String method = "get";

    private JSONArray jsonArray;

    private JSONObject jsonObject;

    private String responseText = "";

    public JsonFetcher(String url) {
        this.url = url;
        this.initRequire();
    }

    public JsonFetcher(String url, METHOD method) {
        this.url = url;
        this.method = method == METHOD.GET ? "get" : method == METHOD.POST ? "post" : "unknown";
        this.initRequire();
    }

    public void setMethod(METHOD method) {
        this.method = method == METHOD.GET ? "get" : method == METHOD.POST ? "post" : "unknown";
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponseText() {
        return this.responseText;
    }

    public JsonFetcher fetch() {
        this.fetchProcess();
        return this;
    }

    public JSONArray getJsonArray() {
        return this.jsonArray;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    protected void fetchProcess() {
        try {
            URL url = new URL(this.url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedResponse = new BufferedReader(new InputStreamReader(inputStream));

            this.responseText = "";
            String tempResponseText = bufferedResponse.readLine();

            while(tempResponseText != null) {
                this.responseText += tempResponseText;
                tempResponseText = bufferedResponse.readLine();
            }

            this.jsonArray = new JSONArray(this.responseText);

            if(jsonArray.length() == 0) {
                this.jsonObject = new JSONObject(this.responseText);
            }

        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void initRequire() {
        //
    }

}
