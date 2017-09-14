package com.example.dent.lagosdevelopers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class DeveloperListActivity extends AppCompatActivity {
    private DeveloperClient client;
    private ListView lvDevelopers;
    private DeveloperAdapter developerAdapter;
    private ProgressBar progress;
    public static final String DEVELOPER_DETAIL_KEY = "developer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_list);
        lvDevelopers = (ListView) findViewById(R.id.lvDevelopers);
        ArrayList<Developer> aDevelopers = new ArrayList<Developer>();
        developerAdapter = new DeveloperAdapter(this, aDevelopers);
        lvDevelopers.setAdapter(developerAdapter);
        progress = (ProgressBar) findViewById(R.id.progress);
        // Fetch the data remotely
        fetchDevelopers();
        setupDeveloperSelectedListener();
    }
    private void fetchDevelopers() {
        progress.setVisibility(ProgressBar.VISIBLE);
        client = new DeveloperClient();
        client.getDevelopers(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    progress.setVisibility(ProgressBar.GONE);
                    JSONArray devs = null;
                    if(response != null) {
                        // Get the docs json array
                        devs = response.getJSONArray("items");
                        Log.i("response", response.toString());
                        // Parse json array into array of model objects
                        final ArrayList<Developer> developers = Developer.fromJson(devs);
                        // Remove all developers from the adapter
                        developerAdapter.clear();
                        // Load model objects into the adapter
                        for (Developer developer : developers) {
                            developerAdapter.add(developer); // add developer through the adapter
                        }
                        developerAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    // Invalid JSON format, show appropriate error.
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progress.setVisibility(ProgressBar.GONE);
                Log.d("Failed: ", ""+statusCode);
                Log.d("Error : ", "" + throwable);
                log.d("failure:", "" + responseString);
            }
        });
    }
    public void setupDeveloperSelectedListener() {
        lvDevelopers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch the detail view passing developer as an extra
                Intent intent = new Intent(DeveloperListActivity.this, DeveloperDetailActivity.class);
                intent.putExtra(DEVELOPER_DETAIL_KEY, developerAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
