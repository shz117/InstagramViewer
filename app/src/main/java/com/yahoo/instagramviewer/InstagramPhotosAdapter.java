package com.yahoo.instagramviewer;

import android.content.Context;
import android.graphics.Typeface;
import android.speech.tts.TextToSpeech;
import android.text.Layout;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.util.ArrayList;
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
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
        Typeface font = Typeface.createFromAsset( getContext().getAssets(), "fontawesome-webfont.ttf" );
        TextView tvLikeIcon = (TextView) convertView.findViewById(R.id.tvLikeIcon);
        TextView tvClockIcon = (TextView) convertView.findViewById(R.id.tvClockIcon);
        tvLikeIcon.setTypeface(font);
        tvClockIcon.setTypeface(font);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvRelTime = (TextView) convertView.findViewById(R.id.tvRelTime);
        ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);
        ImageView imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
        tvLikes.setText(String.valueOf(photo.likes_count) + " likes");
        tvUsername.setText(photo.username);
        tvRelTime.setText(DateUtils.getRelativeTimeSpanString(photo.creationTime * 1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));
        float ratio = photo.imageHeight / photo.imageWidth;
        DisplayMetrics displayMetrics;
        displayMetrics = getContext().getResources().getDisplayMetrics();
        int viewHeight = Math.round(ratio * displayMetrics.widthPixels);

        // http://stackoverflow.com/questions/3135112/android-nested-listview
        LinearLayout list = (LinearLayout) convertView.findViewById(R.id.llComments);
        list.removeAllViews();
        for (PhotoComments comment : photo.comments) {
            View line = LayoutInflater.from(getContext()).inflate(R.layout.comment_item, null);
            TextView commentUser = (TextView) line.findViewById(R.id.tvUsername);
            commentUser.setText(comment.userName);
            TextView commentContent = (TextView) line.findViewById(R.id.tvContent);
            commentContent.setText(comment.text);
            list.addView(line);
        }

        imgPhoto.setMinimumWidth(displayMetrics.widthPixels);
        imgPhoto.setMinimumHeight(viewHeight);
        imgPhoto.setImageResource(0);
        imgAvatar.setImageResource(0);
        Picasso.with(getContext()).load(photo.avatarUrl).resize(100, 100).centerCrop().into(imgAvatar);
        Picasso.with(getContext()).load(photo.imageUrl).placeholder(R.drawable.placeholder).into(imgPhoto);
        return convertView;
    }
}
