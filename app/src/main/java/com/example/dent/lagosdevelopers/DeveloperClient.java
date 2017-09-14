package com.example.dent.lagosdevelopers;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by dent4 on 9/10/2017.
 */

public class DeveloperClient {
    private static final String API_BASE_URL = "https://api.github.com/search/users?q=location:lagos";
    private AsyncHttpClient client;

    public DeveloperClient() {
        this.client = new AsyncHttpClient();
    }

    // Method for accessing the github API
    public void getDevelopers(JsonHttpResponseHandler handler) {
        try {
            String url = API_BASE_URL;
            client.setUserAgent("UBrowser/7.0.6.1042");
            client.get(url, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
