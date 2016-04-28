package com.example.udeys.instantresume;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
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
import java.util.Calendar;

public class FormPdf extends AppCompatActivity implements View.OnClickListener{
    Document document = new Document();
    com.itextpdf.text.Rectangle one = new com.itextpdf.text.Rectangle(210, 297);
    EditText e1, e2, e3, e4,e5;
    RadioGroup rg1;
    Button b1;
    View v;
    String name, fname, addr, email, NAME,mno,str;
    SQLiteDatabase db;
    String create_tb = " CREATE TABLE IF NOT EXISTS PersonalInfo (Name text,F_Name text,Email text,Address text,Mobile number,Created text)";
    //String create_tb = " CREATE TABLE IF NOT EXISTS PersonalInfo (Name text,F_Name text,Email text,Address text,Mobile number)";

    ContentValues values;
    boolean nxt = true;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);

        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.fname);
        e3 = (EditText) findViewById(R.id.addr);
        e4 = (EditText) findViewById(R.id.email);
        e5 = (EditText) findViewById(R.id.mno);

        b1 = (Button) findViewById(R.id.next);
        b1.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        try {
            callIntent(v);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Catch",Toast.LENGTH_SHORT).show();
        }
        /**/
    }

    @Override
    public void onClick(View v) {
        try {
            i = 1;
            db = openOrCreateDatabase("PersonalInfo", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL(create_tb);
            //Toast.makeText(this,"Table Created",Toast.LENGTH_SHORT).show();
            saveData(v);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void callIntent(View view){
        Intent in = getIntent();
        int res = Integer.parseInt(in.getStringExtra("CUSTOM"));
        if(res == 150){
            //setData();
        }
    }

    public boolean validateData() {
        NAME = e1.getText().toString();
        name = e1.getText().toString();
        fname = e2.getText().toString();
        addr = e3.getText().toString();
        email = e4.getText().toString();
        mno = e5.getText().toString();
        boolean res = true;
        if (name.equals("")) {
            res = false;
            e1.setError("Field can't be empty");
        }
        if (fname.equals("")) {
            res = false;
            e2.setError("Field can't be empty");
        }
        if (addr.equals("")) {
            res = false;
            e3.setError("Field can't be empty");
        }
        if (email.equals("")) {
            res = false;
            e4.setError("Field can't be empty");
        }
        if(mno.length() != 10){
            res = false;
            e5.setError("Enter valid Number");
        }

        return res;
    }

    public void saveData(View view) {
        boolean res = true;
        try {
            if (validateData() == true) {
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                String date = String.valueOf(formattedDate).toString();
                Toast.makeText(this, formattedDate,Toast.LENGTH_SHORT).show();
                values = new ContentValues();
                values.put("Name", name);
                values.put("F_Name", fname);
                values.put("Email", email);
                values.put("Address", addr);
                values.put("Mobile",mno);
                values.put("Created",date);
                db.insert("PersonalInfo",null,values);
                res = true;
            }
        }catch (Exception e){

        }

        try {
            if(res == true)
   //             Toast.makeText(this, "call pdf",Toast.LENGTH_SHORT).show();
                createPdf();
        } catch (Exception e) {

        }
    }

    public void createPdf(){
        //File myDir = getDir("PDF",MODE_PRIVATE);
       // File file = new File(myDir,"Name.pdf");
        //String file2 = myDir + "/PDF/" + "Name.pdf";
        //myDir.mkdir();
        String file = Environment.getExternalStorageDirectory().toString() + "/PDF/" + "Name.pdf";
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PDF");
        myDir.mkdirs();

        try{
     //       Toast.makeText(this, "try",Toast.LENGTH_SHORT).show();
            PdfReader reader = new PdfReader(getResources().openRawResource(R.raw.udey_basic));
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
            Toast.makeText(this, "try end",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, e.toString(),Toast.LENGTH_SHORT).show();
        }/*
        if(nxt == true){
            Intent in1 = new Intent(this, StartPdfAct.class);
            startActivity(in1);
            finish();
        }*/

    }

    public void setData(){
        Intent in = getIntent();
        String name = in.getStringExtra("NAME").toString();
        String fname = in.getStringExtra("FNAME").toString();
        String email = in.getStringExtra("EMAIL").toString();
        String addr = in.getStringExtra("ADDR").toString();
        String mob = in.getStringExtra("MOB").toString();

        e1.setText(name);
        e2.setText(fname);
        e4.setText(email);
        e3.setText(addr);
        e5.setText(mob);

    }



}
