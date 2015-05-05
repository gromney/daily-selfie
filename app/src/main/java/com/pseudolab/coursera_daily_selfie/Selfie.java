package com.pseudolab.coursera_daily_selfie;

import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Geronimo on 5/5/2015.
 */
public class Selfie {
    private final long dateTaken;
    private final Uri mImageUrl;

    public Selfie(long time, Uri imageUrl){
        dateTaken=time;
        mImageUrl=imageUrl;
    }

    public Uri getImageUrl(){
        return mImageUrl;
    }
    public String getDateTaken(){
        return new SimpleDateFormat("MM/dd/yyyy").format(new Date(dateTaken));
    }
}
