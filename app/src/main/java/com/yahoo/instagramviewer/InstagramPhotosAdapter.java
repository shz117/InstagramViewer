package com.yahoo.instagramviewer;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.text.Layout;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by jeremyshi on 1/20/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> resource) {
        super(context, android.R.layout.simple_list_item_1, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvRelTime = (TextView) convertView.findViewById(R.id.tvRelTime);
        ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);
        ImageView imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
        tvCaption.setText(photo.caption);
        tvLikes.setText(String.valueOf(photo.likes_count));
        tvUsername.setText(photo.username);
        tvRelTime.setText(DateUtils.getRelativeTimeSpanString(photo.creationTime * 1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));

        float ratio = photo.imageHeight / photo.imageWidth;
        DisplayMetrics displayMetrics;
        displayMetrics = getContext().getResources().getDisplayMetrics();
        int viewHeight = Math.round(ratio * displayMetrics.widthPixels);

        imgPhoto.setMinimumWidth(displayMetrics.widthPixels);
        imgPhoto.setMinimumHeight(viewHeight);
        imgPhoto.setImageResource(0);
        imgAvatar.setImageResource(0);
        Picasso.with(getContext()).load(photo.avatarUrl).into(imgAvatar);
        Picasso.with(getContext()).load(photo.imageUrl).into(imgPhoto);
        return convertView;
    }
}
