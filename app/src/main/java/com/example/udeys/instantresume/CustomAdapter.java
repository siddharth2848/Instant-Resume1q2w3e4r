package com.example.udeys.instantresume;

import android.content.Context;
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
    ArrayList<String> dates;
    Context context;
    public CustomAdapter(Context context, ArrayList<String> names, ArrayList<String> dates){
        super(context,R.layout.custom_row,names);
        this.context = context;
        this.names = names;
        this.dates = dates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater listInflater = LayoutInflater.from(getContext());
        View customView = listInflater.inflate(R.layout.custom_row,parent,false);

        String singleName = getItem(position);
        //String singleDate = getItem(dates.get(position));
        TextView tv = (TextView) customView.findViewById(R.id.custext);
        TextView tv1 = (TextView) customView.findViewById(R.id.cusdate);
        ImageView iv = (ImageView) customView.findViewById(R.id.cusimg);
        tv.setText(singleName);
        //tv1.setText(dates.get(position));
        iv.setImageResource(R.drawable.circle_em);

        return customView;
    }

}
