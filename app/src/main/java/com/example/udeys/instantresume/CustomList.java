package com.example.udeys.instantresume;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        ArrayList<String> dates= in.getStringArrayListExtra("DATE");

        ListAdapter lad = new CustomAdapter(getApplicationContext(),names,dates);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(lad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String naam = String.valueOf(parent.getItemAtPosition(position));

                SQLiteDatabase db = openOrCreateDatabase("PersonalInfo", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                Cursor cur;
                try {

                    cur = db.rawQuery("Select Name,F_Name,Email,Address,Mobile number from PersonalInfo", null);
                    cur.moveToFirst();
                      /*
                    while (!cur.isAfterLast()){
                        //Toast.makeText(getApplicationContext(),"fhfhg",Toast.LENGTH_SHORT).show();
                        String name = cur.getString(0);
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();

                        cur.moveToNext();
                    }*/
                    String fname = cur.getString(1);
                    String email = cur.getString(2);
                    String addr = cur.getString(3);
                    int mob = cur.getInt(4);
                    Toast.makeText(getApplicationContext(),fname,Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(getApplicationContext(),FormPdf.class);
                    in.putExtra("NAME",naam);
                    in.putExtra("FNAME",fname);
                    in.putExtra("EMAIL",email);
                    Toast.makeText(getApplicationContext(),naam,Toast.LENGTH_SHORT).show();
                    in.putExtra("ADDR",addr);
                    in.putExtra("MOB",mob);
                    in.putExtra("CUSTOM",150);
                    startActivity(in);
                    finish();

                } catch (Exception e){

                }
            }
        });
    }
}
