package com.example.udeys.instantresume;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Siddharth Malhotra on 04/08/16.
 */
public class Details extends AppCompatActivity implements View.OnClickListener{

    Button b1,b2;
    SQLiteDatabase db;
    ArrayList<String> names;
    ArrayList<String> dates;
    String select_query = "Select Name,Created from PersonalInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        b1 = (Button) findViewById(R.id.bt1_det);
        b2 = (Button) findViewById(R.id.bt2_det);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_b);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Fill Details");
    }

    @Override
    public void onClick(View v) {
        int id;
        id = v.getId();

        switch (id){
            case R.id.bt1_det:
                getDetails(v);
                break;
            case R.id.bt2_det:
                Intent in = new Intent(this, FormPdf.class);
                startActivity(in);
                break;
        }
    }

    private void getDetails(View view){
        names = new ArrayList<>();
        dates = new ArrayList<>();
        db = openOrCreateDatabase("PersonalInfo", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        Cursor cur;
        boolean nextPage = true;
        try {
            cur = db.rawQuery(select_query, null);
            cur.moveToFirst();
            while (!cur.isAfterLast()){
                //Toast.makeText(getApplicationContext(),"fhfhg",Toast.LENGTH_SHORT).show();
                String name = cur.getString(0);
                //Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                names.add(name);
                //String dt = cur.getColumnName(5);
               // Toast.makeText(getApplicationContext(),dt,Toast.LENGTH_SHORT).show();
                //dates.add(dt);
                cur.moveToNext();
            }
        }catch (Exception e){
            nextPage = false;
            Toast.makeText(getApplicationContext(),"No previous entry found",Toast.LENGTH_SHORT).show();
        }
        if(nextPage == true && !names.isEmpty()) {

            Intent in = new Intent(getApplicationContext(), CustomList.class);
            in.putStringArrayListExtra("NAME", names);
            in.putStringArrayListExtra("DATE", dates);
            startActivity(in);
        }

    }
}