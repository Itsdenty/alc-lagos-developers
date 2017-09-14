package com.example.dent.lagosdevelopers;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dent4 on 9/10/2017.
 */

public class DeveloperAdapter extends ArrayAdapter<Developer> {
    private static class ViewHolder {
        public ImageView ivAvatar;
        public TextView tvUsername;
    }
    public DeveloperAdapter(Context context, ArrayList<Developer> aDevelopers) {
        super(context, 0, aDevelopers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Developer developer = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.developer_detail, parent, false);
            viewHolder.ivAvatar = (ImageView)convertView.findViewById(R.id.ivAvatar);
            viewHolder.tvUsername = (TextView)convertView.findViewById(R.id.tvUsername);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.tvUsername.setText(developer.getLogin());
        Picasso.with(getContext()).load(Uri.parse(developer.getAvatar())).error(R.drawable.dev1).into(viewHolder.ivAvatar);
        // Return the completed view to render on screen
        return convertView;
    }
}
