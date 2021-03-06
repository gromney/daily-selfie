package com.pseudolab.coursera_daily_selfie;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    private static final int TAKE_PHOTO = 0;
    private static final long TWO_MINUTES = 2 * 60 * 1000;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView grid = (GridView) findViewById(R.id.gridview);
        GridAdapter adapter = new GridAdapter(this);

        grid.setAdapter(adapter);

        createPendingIntent();
        createSelfieReminders();
    }

    private void createPendingIntent() {
        Intent notificationIntent = new Intent(MainActivity.this,NotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,notificationIntent,0);
    }
    private void createSelfieReminders() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() +TWO_MINUTES,
                TWO_MINUTES,
                pendingIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case (R.id.action_settings):
                return true;
            case (R.id.action_camera):
                takeNewPhoto();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void takeNewPhoto(){
        String timeTaken = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        String photoFileName = "SELFIE_"+timeTaken;
        File folder = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "dailySelfie/");

        File photo = new File(folder.getAbsolutePath()+photoFileName+".jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        startActivityForResult(intent,TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == TAKE_PHOTO){

        }
    }
}
