package com.example.udeys.instantresume;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Siddharth Malhotra on 4/20/2016.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    ArrayList<String> names;
    ArrayList<Bitmap> images;
    Context context;
    public CustomAdapter(Context context, ArrayList<String> names, ArrayList<Bitmap> images){
        super(context,R.layout.custom_row,names);
        this.context = context;
        this.names = names;
        this.images = images;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater listInflater = LayoutInflater.from(getContext());
        View customView = listInflater.inflate(R.layout.custom_row,parent,false);

        String singleName = getItem(position);
        TextView tv = (TextView) customView.findViewById(R.id.custext);
        ImageView iv = (ImageView) customView.findViewById(R.id.cusimg);
        tv.setText(singleName);
        iv.setImageResource(R.drawable.classprofile);
        //iv.setImageBitmap(images.get(position));

        return customView;
    }

}
