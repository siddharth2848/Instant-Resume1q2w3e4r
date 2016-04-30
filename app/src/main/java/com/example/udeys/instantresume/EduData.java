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
public class EduData extends AppCompatActivity implements View.OnClickListener{

    EditText e1,e2,e3,e4,e5,e6,e7;
    String from,last,inst,branch,st1,st2,st3,res;
    Button b1;
    Intent in = null;
    ArrayList<String> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edu_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_edu);
        setSupportActionBar(toolbar);
        Drawable upArrow = ContextCompat.getDrawable(getApplicationContext(), R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);

        e1 = (EditText) findViewById(R.id.first);
        e2 = (EditText) findViewById(R.id.last);
        e3 = (EditText) findViewById(R.id.inst);
        e4 = (EditText) findViewById(R.id.branch);
        e5 = (EditText) findViewById(R.id.st1);
        e6 = (EditText) findViewById(R.id.st2);
        e7 = (EditText) findViewById(R.id.st3);

        try{
            in = getIntent();
            res = in.getStringExtra("values");

            if(res.equals("true")){
                setData(in);
            }
            else{
                //Toast.makeText(getApplicationContext() , "else" , Toast.LENGTH_SHORT).show();
                res = "false";
            }
        }catch (Exception e){
           // Toast.makeText(getApplicationContext() , "Error:"+e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        b1 = (Button) findViewById(R.id.submit);
        b1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        try{
            if(res.equals("true")){
                ArrayList<String> info = in.getStringArrayListExtra("DATA");
                Intent in1 = new Intent(getApplicationContext(),Camera.class);
                in1.putExtra("values" , "true");
                in1.putStringArrayListExtra("Final",info);
                startActivity(in1);
                finish();
            }
            else
                submitForm(view);
        }catch (Exception e){

        }

    }

    public void setData( Intent in){

        ArrayList<String> info = in.getStringArrayListExtra("DATA");
        e1.setText(info.get(13));
        e2.setText(info.get(14));
        e3.setText(info.get(15));
        e4.setText(info.get(16));
        e5.setText(info.get(17));
        e6.setText(info.get(18));
        e7.setText(info.get(19));

    }

    public boolean validateData() {
        from = e1.getText().toString();
        last = e2.getText().toString();
        inst = e3.getText().toString();
        branch = e4.getText().toString();
        st1 = e5.getText().toString();
        st2 = e6.getText().toString();
        st3 = e7.getText().toString();
        boolean res = true;
        if (from.equals("")) {
            res = false;
            e1.setError("Field can't be empty");
        }
        if (last.equals("")) {
            res = false;
            e2.setError("Field can't be empty");
        }
        if (inst.equals("")) {
            res = false;
            e3.setError("Field can't be empty");
        }
        if (branch.equals("")) {
            res = false;
            e4.setError("Field can't be empty");
        }
        if(st1.equals("")){
            res = false;
            e5.setError("Field can't be empty");
        }
        if(st2.equals("")){
            res = false;
            e6.setError("Field can't be empty");
        }
        if(st3.equals("")){
            res = false;
            e7.setError("Field can't be empty");
        }

        return res;
    }

    public void submitForm(View view){
        if(validateData() == true){
            Intent prev = getIntent();
            info = new ArrayList<>();
            info = prev.getStringArrayListExtra("Job");
            //Toast.makeText(this, info.get(0),Toast.LENGTH_SHORT).show();
            info.add(from);
            info.add(last);
            info.add(inst);
            info.add(branch);
            info.add(st1);
            info.add(st2);
            info.add(st3);


            Intent in = new Intent(getApplicationContext(),Camera.class);
            in.putStringArrayListExtra("Final",info);
            startActivity(in);
        }
    }
}
