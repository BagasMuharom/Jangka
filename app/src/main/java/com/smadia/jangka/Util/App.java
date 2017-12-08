package com.smadia.jangka.Util;

public class App {

//    public static final String rootUrl = "http://192.168.55.2/jangka/public";

    public static final String rootUrl = "http://jangka.herokuapp.com";

    public static String generateUrl(String url) {
        return App.rootUrl + "/" + url;
    }

}
