package com.example.udeys.instantresume;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
    SQLiteDatabase db,db2;
    ArrayList<String> names;
    ArrayList<Bitmap> images;
    String select_query = "Select * from PersonalInfo";
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
        Drawable upArrow = ContextCompat.getDrawable(getApplicationContext(), R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
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
                in.putExtra("values" , "f");
                startActivity(in);
                break;
        }
    }

    private void getDetails(View view){
        names = new ArrayList<>();
        db = openOrCreateDatabase("InfoData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        SQLiteDatabase db2 = openOrCreateDatabase("Resume",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        boolean nextPage = true;
        try {
            Cursor cur = db.rawQuery("Select * from PersonalInfo", null);
            cur.moveToFirst();

            Cursor cur1 = db2.rawQuery("Select * from image",null);
            while (!cur.isAfterLast()){
                String name = cur.getString(1);
                names.add(name);
                cur.moveToNext();
            }
            cur.close();
            cur1.moveToFirst();
            byte [] db_img = null;
            /*while (!cur1.isAfterLast()){
                db_img = cur1.getBlob(cur1.getColumnIndex("img"));
                Bitmap bitmap = BitmapFactory.decodeByteArray(db_img , 0, db_img.length);
                Toast.makeText(getApplicationContext(),bitmap.toString(),Toast.LENGTH_SHORT).show();
                images.add(bitmap);
                cur1.moveToNext();
            }
            cur1.close();*/
        }catch (Exception e){
            nextPage = false;
            Toast.makeText(getApplicationContext(),"No previous entry found",Toast.LENGTH_SHORT).show();
        }
        if(nextPage == true && !names.isEmpty()) {

            Intent in = new Intent(getApplicationContext(), CustomList.class);
            in.putStringArrayListExtra("NAME", names);
            in.putParcelableArrayListExtra("IMAGE",images);
            startActivity(in);
        }

    }
}