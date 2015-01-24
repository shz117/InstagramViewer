package com.yahoo.instagramviewer;

import java.util.ArrayList;

/**
 * Created by jeremyshi on 1/20/15.
 */
public class InstagramPhoto {
    public String username;
    public String caption;
    public String imageUrl;
    public String avatarUrl;
    public int imageHeight;
    public int imageWidth;
    public int likes_count;
    public long creationTime;
    public ArrayList<PhotoComments> comments;
}
