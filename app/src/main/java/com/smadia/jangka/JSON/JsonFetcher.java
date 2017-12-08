package com.smadia.jangka.JSON;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class JsonFetcher {

    public enum METHOD {
        GET, POST
    }

    private AppCompatActivity activity;

    private String url;

    private String method = "get";

    private JSONArray jsonArray = null;

    private JSONObject jsonObject = null;

    private String responseText = "";

    private boolean useVolley;

    private Map<String, String> params;

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

    public void setUseVolley(boolean useVolley) {
        this.useVolley = useVolley;
    }

    public JsonFetcher fetch() {
        this.fetchProcess();
        return this;
    }

    public void addParams(String key, String value) {
        this.params.put(key, value);
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

            if (this.method.equals("post")) {
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(10000);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
            }

            if (this.params.size() > 0) {
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(this.parseParams());
                writer.flush();
                writer.close();
                outputStream.close();
                connection.connect();
            }

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedResponse = new BufferedReader(new InputStreamReader(inputStream));

            this.responseText = "";
            String tempResponseText = bufferedResponse.readLine();

            while (tempResponseText != null) {
                this.responseText += tempResponseText;
                tempResponseText = bufferedResponse.readLine();
            }

            Object jsonTokener = new JSONTokener(this.responseText).nextValue();

            if(jsonTokener instanceof JSONObject) {
                this.jsonObject = new JSONObject(this.responseText);
            }

            if(jsonTokener instanceof JSONArray) {
                this.jsonArray = new JSONArray(this.responseText);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseParams() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<?, ?> entry : this.params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    entry.getKey().toString(),
                    entry.getValue().toString()
            ));
        }

        return sb.toString();
    }

    private void initRequire() {
        this.params = new HashMap<>();
    }

}
