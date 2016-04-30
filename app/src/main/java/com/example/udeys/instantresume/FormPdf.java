package com.example.udeys.instantresume;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FormPdf extends AppCompatActivity implements View.OnClickListener{
    Document document = new Document();
    com.itextpdf.text.Rectangle one = new com.itextpdf.text.Rectangle(210, 297);
    EditText e1, e2, e3, e4,e5,e6,e7;
    RadioGroup rg1;
    Button b1;
    View v;
    String res = "";
    String name, fname, addr, email, flat,city,state,country,mno,date;
    SQLiteDatabase db;
    ArrayList<String> info;
    String create_tb = " CREATE TABLE IF NOT EXISTS PersonalInfo (Id Integer PRIMARY KEY AUTOINCREMENT,Name text,F_Name text,Email text,Address text,Mobile number)";
    //String create_tb = " CREATE TABLE IF NOT EXISTS PersonalInfo (Name text,F_Name text,Email text,Address text,Mobile number)";
    Intent in = null;
    ContentValues values;
    boolean nxt = true;
    int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);


        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.email);
        e3 = (EditText) findViewById(R.id.mno);
        e4 = (EditText) findViewById(R.id.flat);
        e5 = (EditText) findViewById(R.id.city);
        e6 = (EditText) findViewById(R.id.state);
        e7 = (EditText) findViewById(R.id.country);

        try{
            in = getIntent();
            res = in.getStringExtra("values");

            if(res.equals("true")){
                setData(in);
            }
            else{

            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext() , "Error:"+e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        b1 = (Button) findViewById(R.id.next);
        b1.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        Drawable upArrow = ContextCompat.getDrawable(getApplicationContext(), R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);

        /**/
    }
    public void setData( Intent in){

        ArrayList<String> info = in.getStringArrayListExtra("DATA");

        e1.setText(info.get(0));
        e2.setText(info.get(1));
        e3.setText(info.get(2));
        e4.setText(info.get(3));
        e5.setText(info.get(4));
        e6.setText(info.get(5));
        e7.setText(info.get(6));

        res = "true";
    }

    @Override
    public void onClick(View v) {
        try {
            if(res.equals("true")){
                ArrayList<String> info = in.getStringArrayListExtra("DATA");
                Intent in1 = new Intent(getApplicationContext(),JobData.class);
                in1.putExtra("values" , "true");
                in1.putStringArrayListExtra("DATA",info);
                startActivity(in1);
            }
            else
                saveData(v);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public boolean validateData() {
        name = e1.getText().toString();
        email = e2.getText().toString();
        mno = e3.getText().toString();
        flat = e4.getText().toString();
        city = e5.getText().toString();
        state = e6.getText().toString();
        country = e7.getText().toString();
        boolean res = true;
        if (name.equals("")) {
            res = false;
            e1.setError("Field can't be empty");
        }
        if (email.equals("")) {
            res = false;
            e2.setError("Field can't be empty");
        }
        if (flat.equals("")) {
            res = false;
            e4.setError("Field can't be empty");
        }
        if (city.equals("")) {
            res = false;
            e5.setError("Field can't be empty");
        }
        if(mno.length() != 10){
            res = false;
            e3.setError("Enter valid Number");
        }if(state.equals("")){
            res = false;
            e6.setError("Field can't be empty");
        }
        if(country.equals("")){
            res = false;
            e7.setError("Field can't be empty");
        }

        return res;
    }

    public void saveData(View view) {
        try {
            if (validateData() == true) {
                //Toast.makeText(this, "new intent",Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getApplicationContext(),JobData.class);
                info = new ArrayList<>();
                info.add(name);
                info.add(email);
                info.add(mno);
                info.add(flat);
                info.add(city);
                info.add(state);
                info.add(country);
                in.putStringArrayListExtra("Personal",info);
                /*
                in.putExtra("NAME",name);
                in.putExtra("EMAIL",email);
                in.putExtra("MNO",mno);
                in.putExtra("FLAT",flat);
                in.putExtra("CITY",city);
                in.putExtra("STATE",state);
                in.putExtra("COUNTRY",country);*/
                in.putExtra("values","false");
                startActivity(in);
                //finish();
                /*Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                date = String.valueOf(formattedDate).toString();
                //Toast.makeText(this, formattedDate,Toast.LENGTH_SHORT).show();
                //db = openOrCreateDatabase("PersonalInfo", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                //db.execSQL("insert into PersonalInfo (Name,F_Name,Email,Address,Mobile)" + "values("+name+","+fname+","+email+","+addr+","+mno+"+);") ;

                values = new ContentValues();
               // values.put("Id","rowid");
                values.put("Name", name);
                values.put("F_Name", fname);
                values.put("Email", email);
                values.put("Address", addr);
                values.put("Mobile",mno);
                db.insert("PersonalInfo",null,values);
                Toast.makeText(getApplicationContext(), "Inserted",Toast.LENGTH_SHORT).show();
                res = true;*/
            }
        }catch (Exception e){
            //Toast.makeText(this, "catch",Toast.LENGTH_SHORT).show();
        }

  /*      try {
            if(res == true)
   //             Toast.makeText(this, "call pdf",Toast.LENGTH_SHORT).show();
                createPdf();
        } catch (Exception e) {
*/
       // }
    }

    public void createPdf(){
        //File myDir = getDir("PDF",MODE_PRIVATE);
       // File file = new File(myDir,"Name.pdf");
        //String file2 = myDir + "/PDF/" + "Name.pdf";
        //myDir.mkdir();
        String file = Environment.getExternalStorageDirectory().toString() + "/PDF/" + name+date+".pdf";
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PDF");
        myDir.mkdirs();

        try{
     //       Toast.makeText(this, "try",Toast.LENGTH_SHORT).show();
            PdfReader reader = new PdfReader(getResources().openRawResource(R.raw.final_temp));
            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(file));
            //stamp.setFormFlattening(true);
            document.setPageSize(one);
            document.open();
            //stamp.setFormFlattening(true);
            //Toast.makeText(this, name,Toast.LENGTH_SHORT).show();
            //Image img = Image.getInstance()
            AcroFields form = stamp.getAcroFields();
            form.setField("Text Box 1", name);
            form.setField("Text Box 2", fname);
            form.setField("Text Box 3", addr);
            form.setField("Text Box 4", email);

            stamp.close();
            document.close();
            reader.close();
            stamp.setFormFlattening(true);
            nxt = true;
            //Toast.makeText(this, "try end",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            //Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show();
        }
        if(nxt == true){
            Intent in1 = new Intent(this, Camera.class);
            in1.putExtra("NAME",name);
            in1.putExtra("DATE",date);
            startActivity(in1);
            finish();
        }

    }





}
