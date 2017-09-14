package com.example.dent.lagosdevelopers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dent4 on 9/10/2017.
 */

public class Developer implements Serializable {
        private String login;
        private String avatar_url;
        private String html_url;

        public String getLogin() {
            return login;
        }

        public String getAvatar() {
            return avatar_url;
        }

        public String getHtml() {
            return html_url;
        }

    // Returns a Developer given the expected JSON
    public static Developer fromJson(JSONObject jsonObject) {
        Developer developer = new Developer();
        try {
            // Deserialize json into object fields
            // Check if a cover edition is available
            developer.login = jsonObject.getString("login");
            developer.avatar_url = jsonObject.getString("avatar_url");
            developer.html_url = jsonObject.getString("html_url");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return developer;
    }
    public static ArrayList<Developer> fromJson(JSONArray jsonArray) {
        ArrayList<Developer> developers = new ArrayList<Developer>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject developerJson = null;
            try {
                developerJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Developer developer = Developer.fromJson(developerJson);
            if (developer != null) {
                developers.add(developer);
            }
        }
        return developers;
    }
    // Return comma separated author list when there is more than one author
}
