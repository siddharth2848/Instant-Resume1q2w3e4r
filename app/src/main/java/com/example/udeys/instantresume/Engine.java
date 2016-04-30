package com.example.udeys.instantresume;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by udeys on 4/7/2016.
 */

public class Engine extends Service {
    Document document = new Document();
    com.itextpdf.text.Rectangle one = new com.itextpdf.text.Rectangle(210, 297);
    private MyDataBase mdb=null;
    private SQLiteDatabase db=null,db1 = null;
    private Cursor c=null;
    private byte[] img=null;
    private static final String DATABASE_NAME = "ImageDb.db";
    public static final int DATABASE_VERSION = 1;
    Bitmap bp = null;
    ArrayList<String> info;
    String date,name;
    Bitmap photo = null;
    Bitmap b1;

    String create_tb = " CREATE TABLE IF NOT EXISTS PersonalInfo (Id Integer PRIMARY KEY AUTOINCREMENT,Name text,Email text," +
            "Mobile number,Flat text,City text,State text,Country text,Objective text,Strength_1 text,Strength_2 text,Strength_3 text," +
            "Strength_4 text,Strength_5 text,First text,Last text,Inst text,Branch text,Training_1 text,Training_2 text,Training_3 text)";
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.

        info = intent.getStringArrayListExtra("Data");
        bp = (Bitmap) intent.getParcelableExtra("BitmapImage");


        /*
        * functionalities:
        * 1.    Fetching from DataBase
        * 2.    Getting Picture from the intent
        * 3.    Creating Directory in external storage
        * 4.    Creating PDF File
        * 5.    Filling Information
        * 6.    inserting picture into the PDf document
        * 7.    Closing and writing the Document to External memory
        * */
        //fetch_db();
        //Toast.makeText(this,"Engine",Toast.LENGTH_SHORT).show();

        //get_pic(intent);
        pdf_gen();
        ins_db(info);

        //create_dir();



        stopSelf();

        //intent goes here

        return START_STICKY;
    }

    public void ins_db(ArrayList<String> info){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        date = String.valueOf(formattedDate).toString();


        try {
            db = openOrCreateDatabase("InfoData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL(create_tb);

            ContentValues values = new ContentValues();

            name = info.get(0);

            values.put("Name", info.get(0));
            values.put("Email", info.get(1));
            values.put("Mobile", info.get(2));
            values.put("Flat", info.get(3));
            values.put("City", info.get(4));
            values.put("State", info.get(5));
            values.put("Country", info.get(6));
            values.put("Objective", info.get(7));
            values.put("Strength_1", info.get(8));
            values.put("Strength_2", info.get(9));
            values.put("Strength_3", info.get(10));
            values.put("Strength_4", info.get(11));
            values.put("Strength_5", info.get(12));
            values.put("First", info.get(13));
            values.put("Last", info.get(14));
            values.put("Inst", info.get(15));
            values.put("Branch", info.get(16));
            values.put("Training_1", info.get(17));
            values.put("Training_2", info.get(18));
            values.put("Training_3", info.get(19));

            db.insert("PersonalInfo", null, values);
            //Toast.makeText(this, "try db",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        create_pdf();


    }

    public void create_pdf() {
        String file = Environment.getExternalStorageDirectory().toString() + "/PDF/" + name +" - "+ date + ".pdf";
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PDF");
        myDir.mkdirs();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] im = bos.toByteArray();

        try {
            PdfReader reader = new PdfReader(getResources().openRawResource(R.raw.final_temp));
            //PdfWriter.getInstance(document, new FileOutputStream(file));
            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(file));
            document.setPageSize(one);
            document.open();
            AcroFields form = stamp.getAcroFields();
            // Toast.makeText(this, "try startt", Toast.LENGTH_SHORT).show();


            //  Address
            form.setField("Text Box 1", info.get(3));
            form.setField("Text Box 2", info.get(4));
            form.setField("Text Box 3", info.get(5));
            form.setField("Text Box 4", info.get(6));


            // Contact
            form.setField("Text Box 5", info.get(2));
            form.setField("Text Box 6", info.get(1));

            //  Name
            form.setField("Text Box 7", name);

            // Objective
            form.setField("Text Box 8", info.get(7));

            //  Strengths
            form.setField("Text Box 9", info.get(8));
            form.setField("Text Box 10", info.get(9));
            form.setField("Text Box 11", info.get(10));
            form.setField("Text Box 12", info.get(11));
            form.setField("Text Box 13", info.get(12));

            //  Graduation
            form.setField("Text Box 14", info.get(13));
            form.setField("Text Box 15", info.get(14));
            form.setField("Text Box 16", info.get(15));
            form.setField("Text Box 17", info.get(16));

            //  Training
            form.setField("Text Box 18", info.get(17));
            form.setField("Text Box 19", info.get(18));
            form.setField("Text Box 20", info.get(19));

            stamp.close();
            reader.close();
            document.close();

            //document.open();


        } catch (Exception e) {
        }
    }


    /*public void get_pic(Intent intent){
        bp = (Bitmap) intent.getParcelableExtra("BitmapImage");
    }*/

    //public void ins_pic() {


        /*try {
            db = openOrCreateDatabase("Resume", SQLiteDatabase.CREATE_IF_NECESSARY, null);

            db.execSQL("Create table if not exists image(img blob)");

            String sql = "INSERT INTO image VALUES(" + bArray + ")";
            db.execSQL(sql);
            Toast.makeText(getApplicationContext(), "Created tb", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }*/
    public void pdf_gen(){
        //Toast.makeText(getApplicationContext() , "In fn" , Toast.LENGTH_SHORT).show();

        //byte[] bost = null;



        mdb=new MyDataBase(getApplicationContext(), DATABASE_NAME,null, DATABASE_VERSION);


       /* Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.y);
        ByteArrayOutputStream bost=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bost);
        img=bost.toByteArray();
        db=mdb.getWritableDatabase();*/

        try {

        photo = bp;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
            //byte[] bos = null;
            //FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory().toString() + "/PDF/" + "na.jpg");
            //bos = new byte[fis.available()];
            //fis.read(bos);
            db1 = openOrCreateDatabase("Resume", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db1.execSQL("Create table if not exists image(Id Integer PRIMARY KEY AUTOINCREMENT,img blob)");
            //Toast.makeText(getApplicationContext() , "db" , Toast.LENGTH_SHORT).show();
            ContentValues values = new ContentValues();
            values.put("img",bArray);
            db1.insert("image",null,values);
           // Toast.makeText(getApplicationContext() , "Inserted" , Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            //Toast.makeText(getApplicationContext() , "Not Inserted" , Toast.LENGTH_SHORT).show();
        }
        readFromDB();

        //we fetched the picture here to use.
        //photo = BitmapFactory.decodeByteArray(bos , 0 , bos.length);
    }

    void readFromDB() {
        String[] col={"img"};
        try {
            c = db1.query("image", col, null, null, null, null, null);

            if (c != null) {
                c.moveToFirst();
                do {
                    img = c.getBlob(c.getColumnIndex("img"));
                } while (c.moveToNext());
            }

             b1 = BitmapFactory.decodeByteArray(img, 0, img.length);
            //name = info.get(0);
            //imageview.setImageBitmap(b1);
           // Toast.makeText(this, "Retrive successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
           // Toast.makeText(this, "Retrive failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
