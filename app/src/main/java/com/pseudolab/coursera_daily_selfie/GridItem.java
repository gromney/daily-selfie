package com.pseudolab.coursera_daily_selfie;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Geronimo on 5/5/2015.
 */
public class GridItem extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_item);

        Bundle bundle = getIntent().getExtras();
        Uri url = Uri.parse(bundle.getString("IMAGE_URL"));

        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageURI(url);
    }
}
