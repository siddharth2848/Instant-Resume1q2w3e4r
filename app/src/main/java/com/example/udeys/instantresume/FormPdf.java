package com.example.udeys.instantresume;

import android.app.Activity;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Siddharth Malhotra on 04/09/16.
 */
public class FormPdf extends Activity implements View.OnClickListener{
    Document document = new Document();
    com.itextpdf.text.Rectangle one = new com.itextpdf.text.Rectangle(210,297);
    EditText e1,e2,e3,e4;
    Button b1;
    String name,fname,addr,email;
    SQLiteDatabase db;
    String create_tb = " CREATE TABLE IF NOT EXISTS PersonalInfo (Name text,F_Name text,Email text,Address text)";
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);
        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.fname);
        e3 = (EditText) findViewById(R.id.addr);
        e4 = (EditText) findViewById(R.id.email);

        b1 = (Button) findViewById(R.id.next);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            db = openOrCreateDatabase("PersonalInfo",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            db.execSQL(create_tb);
            Toast.makeText(this,"Table Created",Toast.LENGTH_SHORT).show();
            saveData(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateData(){
        name = e1.getText().toString();
        fname = e2.getText().toString();
        addr = e3.getText().toString();
        email = e4.getText().toString();
        boolean res = true;
        if(name.equals("")){
            res = false;
            e1.setError("Field can't be empty");
        }
        if(fname.equals("")){
            res = false;
            e2.setError("Field can't be empty");
        }
        if(addr.equals("")){
            res = false;
            e3.setError("Field can't be empty");
        }
        if(email.equals("")){
            res = false;
            e4.setError("Field can't be empty");
        }return res;
    }

    public void saveData(View view){
        try{
            if(validateData() == true) {
                values = new ContentValues();
                values.put("Name", name);
                values.put("F_Name", fname);
                values.put("Email", email);
                values.put("Address", addr);
                Toast.makeText(this, "Inserted in DB", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try{
            createPdf();
        }catch (Exception e){

        }

    }
    public void createPdf(){
        String file = Environment.getExternalStorageDirectory().toString() + "/PDF/" + "Name.pdf";
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PDF");
        myDir.mkdirs();

        try{
            PdfReader reader = new PdfReader(getResources().openRawResource(R.raw.udey_basic));
            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(file));
            stamp.setFormFlattening(false);
            document.setPageSize(one);
            document.open();
            Toast.makeText(this, name,Toast.LENGTH_SHORT).show();
            AcroFields form = stamp.getAcroFields();
            form.setField("Text Box 1", name);
            form.setField("Text Box 2", fname);
            form.setField("Text Box 3", addr);
            form.setField("Text Box 4", email);
            stamp.setFreeTextFlattening(true);
            stamp.setFullCompression();

            stamp.close();

            document.close();

            Toast.makeText(this,"Saved to Name.pdf",Toast.LENGTH_SHORT).show();
        }catch(Exception e){

        }
    }
}
