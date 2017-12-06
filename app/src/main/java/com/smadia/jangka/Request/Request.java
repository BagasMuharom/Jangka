package com.smadia.jangka.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;
import java.util.HashMap;
import java.lang.String;

public class Request extends StringRequest {

    private Map<String, String> params;

    public Request(String url) {
        super(Method.GET, url, null, null);

        this.params = new HashMap<>();
    }

    public Request(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);

        this.params = new HashMap<>();
    }

    public void setHashMap(HashMap<String, String> hashMap) {
        this.params = hashMap;
    }

    public Request addParams(String key, String value) {
        this.params.put(key, value);

        return this;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return this.params;
    }

}
