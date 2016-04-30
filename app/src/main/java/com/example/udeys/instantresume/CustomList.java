package com.example.udeys.instantresume;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Siddharth Malhotra on 4/20/2016.
 */
public class CustomList extends AppCompatActivity {

    String select_query = "Select Name,F_Name,Email,Address,Mobile number from PersonalInfo where Name = ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customlist);
        Intent in = getIntent();
        ArrayList<String> names= in.getStringArrayListExtra("NAME");
        ArrayList<Bitmap> dates= in.getParcelableArrayListExtra("IMAGE");

        ListAdapter lad = new CustomAdapter(getApplicationContext(),names,dates);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(lad);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar5);
        setSupportActionBar(toolbar);
        Drawable upArrow = ContextCompat.getDrawable(getApplicationContext(), R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = String.valueOf(parent.getItemAtPosition(position));
                int pos = position + 1;
                SQLiteDatabase db = openOrCreateDatabase("InfoData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                ArrayList<String> data = new ArrayList<>();
                try {

                    Cursor cur = db.rawQuery("Select * from PersonalInfo where Id = "+pos+";" , null);
                    cur.moveToFirst();

                    String fname = "";
                    String email = "";
                    String addr = "";
                    int mob = 0;
                    data.add(name);

                    while (!cur.isAfterLast()){
                        data.add(cur.getString(1));
                        data.add(cur.getString(2));
                        data.add(String.valueOf(cur.getInt(3)));
                        data.add(cur.getString(4));
                        data.add(cur.getString(5));
                        data.add(cur.getString(6));
                        data.add(cur.getString(7));
                        data.add(cur.getString(8));
                        data.add(cur.getString(9));
                        data.add(cur.getString(10));
                        data.add(cur.getString(11));
                        data.add(cur.getString(12));
                        data.add(cur.getString(13));
                        data.add(cur.getString(14));
                        data.add(cur.getString(15));
                        data.add(cur.getString(16));
                        data.add(cur.getString(17));
                        data.add(cur.getString(18));
                        data.add(cur.getString(19));


                        /*fname = cur.getString(2);
                        email = cur.getString(3);
                        addr = cur.getString(4);
                        mob = cur.getInt(5);*/
                        cur.moveToNext();
                    }
                    cur.close();

                    try{
                        Toast.makeText(getApplicationContext(),"intent exc.",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(),FormPdf.class);
                        in.putExtra("values" , "true");
                        in.putStringArrayListExtra("DATA",data);
                        startActivity(in);
                        finish();
                    }catch (Exception e){
                       // Toast.makeText(getApplicationContext(),"intent exc.",Toast.LENGTH_SHORT).show();
                    }



                } catch (Exception e){
                   // Toast.makeText(getApplicationContext(),"intent not exc.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
