package com.example.dent.lagosdevelopers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DeveloperDetailActivity extends AppCompatActivity {
    private ImageView ivAvatar;
    private TextView tvUsername;
    private TextView tvLocation;
    private TextView tvProfile;
    private TextView tvPageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_detail);
        // Fetch views
        ivAvatar = (ImageView) findViewById(R.id.ivAvatar);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvProfile = (TextView) findViewById(R.id.tvProfile);
        tvPageCount = (TextView) findViewById(R.id.tvPageCount);
        // Use the Developer object to populate the data into our views
        Developer developer = (Developer) getIntent().getSerializableExtra(DeveloperListActivity.DEVELOPER_DETAIL_KEY);
        loadDeveloper(developer);
    }

    // Populate data for the developer
    private void loadDeveloper(Developer developer) {
        //change activity title
        this.setTitle(developer.getLogin() + " " + "Profile");
        // Populate data
        Picasso.with(this).load(Uri.parse(developer.getAvatar())).error(R.drawable.dev1).into(ivAvatar);;
        tvUsername.setText(developer.getLogin());
        tvProfile.setText(developer.getHtml());
        tvLocation.setText("Lagos");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
    public void shareProfile(View view) {
        final TextView tvUsername = (TextView)findViewById(R.id.tvUsername);
        final TextView tvProfile = (TextView)findViewById(R.id.tvProfile);
        String shareText = "Check out this awesome developer @" + (String)tvUsername.getText() + "," + (String)tvProfile.getText();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("*/*");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Share Developer's profile"));
    }

}
