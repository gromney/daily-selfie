package com.pseudolab.coursera_daily_selfie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geronimo on 5/5/2015.
 */
public class GridAdapter extends BaseAdapter {
    private final Context mContext;
    private LayoutInflater mInflater;

    private List<Selfie> selfies = new ArrayList<Selfie>();

    public GridAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(context);

        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/dailySelfie/");
        folder.mkdirs();
        File[] files = folder.listFiles();

        for (File f : files) {
            selfies.add(new Selfie(f.lastModified(),Uri.fromFile(f.getAbsoluteFile())));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return selfies.size();
    }

    @Override
    public Object getItem(int position) {
        return selfies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ImageView photo;
        TextView dateTaken;

        if (convertView == null){
            view = mInflater.inflate(R.layout.grid_item, parent,false);
            view.setTag(R.id.photo,view.findViewById(R.id.photo));
            view.setTag(R.id.date_taken,view.findViewById(R.id.date_taken));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,GridItem.class);

                    intent.putExtra("IMAGE_URL",selfies.get(position).getImageUrl().toString());

                    mContext.startActivity(intent);
                }
            });
        }
        photo = (ImageView) view.getTag(R.id.photo);
        dateTaken = (TextView) view.getTag(R.id.date_taken);

        final Selfie item = selfies.get(position);

        photo.setImageURI(item.getImageUrl());
        dateTaken.setText(item.getDateTaken());

        return view;
    }

    public void getSelfies(){
    }
}
