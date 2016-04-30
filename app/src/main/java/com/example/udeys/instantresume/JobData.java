package com.example.udeys.instantresume;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Siddharth Malhotra on 4/29/2016.
 */

public class JobData extends AppCompatActivity implements View.OnClickListener{

    EditText e1,e2,e3,e4,e5,e6;
    Button b1;
    ArrayList<String> info;
    Intent in = null;
    String obj,ks1,ks2,ks3,ks4,ks5,res = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobform);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_job);
        setSupportActionBar(toolbar);
        Drawable upArrow = ContextCompat.getDrawable(getApplicationContext(), R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);

        e1 = (EditText) findViewById(R.id.obj);
        e2 = (EditText) findViewById(R.id.ks1);
        e3 = (EditText) findViewById(R.id.ks2);
        e4 = (EditText) findViewById(R.id.ks3);
        e5 = (EditText) findViewById(R.id.ks4);
        e6 = (EditText) findViewById(R.id.ks5);
        res = "false";

        try{
            in = getIntent();
            res = in.getStringExtra("values");
            //Toast.makeText(getApplicationContext() , res , Toast.LENGTH_SHORT).show();
            if(res.equals("true")){
                setData(in);
            }
            else{
               // Toast.makeText(getApplicationContext() , "else" , Toast.LENGTH_SHORT).show();
                res = "false";
            }
        }catch (Exception e){
            //Toast.makeText(getApplicationContext() , "Error:"+e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        b1 = (Button) findViewById(R.id.next);
        b1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        try{
            if(res.equals("false")){
               // Toast.makeText(getApplicationContext() , res , Toast.LENGTH_SHORT).show();
                nextForm(view);
            }
            else{
                //Toast.makeText(getApplicationContext() , "Intent" , Toast.LENGTH_SHORT).show();
                ArrayList<String> info = in.getStringArrayListExtra("DATA");
                Intent in1 = new Intent(getApplicationContext(),EduData.class);
                in1.putExtra("values","true");
                in1.putStringArrayListExtra("DATA",info);
                startActivity(in1);
            }

        }catch (Exception e){
            //Toast.makeText(getApplicationContext() , "Error1:"+e.getMessage()+res , Toast.LENGTH_SHORT).show();
        }
        //nextForm(view);

    }

    public void setData( Intent in){
        ArrayList<String> info = in.getStringArrayListExtra("DATA");
        e1.setText(info.get(7));
        e2.setText(info.get(8));
        e3.setText(info.get(9));
        e4.setText(info.get(10));
        e5.setText(info.get(11));
        e6.setText(info.get(12));
    }


    public boolean validateData() {
        obj = e1.getText().toString();
        ks1 = e2.getText().toString();
        ks2 = e3.getText().toString();
        ks3 = e4.getText().toString();
        ks4 = e5.getText().toString();
        ks5 = e6.getText().toString();
        boolean res = true;
        if (obj.equals("")) {
            res = false;
            e1.setError("Field can't be empty");
        }
        if (ks1.equals("")) {
            res = false;
            e2.setError("Field can't be empty");
        }
        if (ks2.equals("")) {
            res = false;
            e3.setError("Field can't be empty");
        }
        if (ks3.equals("")) {
            res = false;
            e4.setError("Field can't be empty");
        }
        if(ks4.equals("")){
            res = false;
            e5.setError("Field can't be empty");
        }if(ks5.equals("")){
            res = false;
            e6.setError("Field can't be empty");
        }

        return res;
    }

    public void nextForm(View view){
        if(validateData() == true){
            Intent prev = getIntent();
            info = new ArrayList<>();
            info = prev.getStringArrayListExtra("Personal");
            info.add(obj);
            info.add(ks1);
            info.add(ks2);
            info.add(ks3);
            info.add(ks4);
            info.add(ks5);
            Intent in = new Intent(getApplicationContext(),EduData.class);
            in.putStringArrayListExtra("Job",info);
            in.putExtra("values","false");
            startActivity(in);
        }
    }
}
