package com.smadia.jangka.Util;

public class App {

    public static final String rootUrl = "http://jangka.herokuapp.com";

    public static String generateUrl(String url) {
        return App.rootUrl + "/" + url;
    }

}
