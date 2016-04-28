package com.example.udeys.instantresume;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by udeys on 4/7/2016.
 */
public class Engine extends Service {
    Document document = new Document();
    public SQLiteDatabase db;
    Bitmap bp;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.


        String userID = intent.getStringExtra("UserID");



        Toast.makeText(this, userID, Toast.LENGTH_SHORT).show();
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
Toast.makeText(this,"Engine",Toast.LENGTH_SHORT).show();
        //get_pic(intent);

        //ins_pic();

        //create_dir();

        pdf_gen();

        stopSelf();

        //intent goes here

        return START_STICKY;
    }

    public void fetch_db(){
        int roll ;
        String name ;
        String cls ;
        String section ;
        try{
            db = openOrCreateDatabase("School" , SQLiteDatabase.CREATE_IF_NECESSARY , null);

            db.execSQL("Create table if not exists student(rollno integer , name text , class text , section text)");

            Toast.makeText(getApplicationContext() , "DB ready" , Toast.LENGTH_SHORT);
        }
        catch ( Exception e){
            Toast.makeText(getApplicationContext() , "Creation failed" , Toast.LENGTH_SHORT).show();
        }

        Cursor cur = db.rawQuery("Select rollno , name , class , section from student" , null);
        cur.moveToFirst();

        while (!cur.isAfterLast()){
            roll = cur.getInt(0);
            name = cur.getString(1);
            cls = cur.getString(2);
            section = cur.getString(3);
            Toast.makeText(getApplicationContext() , "roll_no"+roll, Toast.LENGTH_SHORT).show();
            cur.moveToNext();
        }
        cur.close();
    }

    public void create_dir(){

    }

    public void get_pic(Intent intent){
        bp = (Bitmap) intent.getParcelableExtra("BitmapImage");
    }

    //public void ins_pic() {

     /*
        Bitmap photo = bp;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();*/

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

        String file = Environment.getExternalStorageDirectory().toString() + "/PDF/" + "Name.pdf";
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PDF");
        myDir.mkdirs();


        Bitmap photo = null;
        //byte[] bost = null;
        byte[] bos = null;


        try {
            Toast.makeText(getApplicationContext() , "In try one" , Toast.LENGTH_SHORT).show();
            FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory().toString() + "/PDF/" + "na.jpg");
            bos = new byte[fis.available()];
            fis.read(bos);
            db = openOrCreateDatabase("Resume", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL("Create table if not exists image(img blob)");
            Toast.makeText(getApplicationContext() , "db" , Toast.LENGTH_SHORT).show();
            ContentValues values = new ContentValues();
            values.put("img",bos);
            db.insert("Resume",null,values);
            Toast.makeText(getApplicationContext() , "Inserted" , Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext() , "Not Inserted" , Toast.LENGTH_SHORT).show();
        }
        //db = openOrCreateDatabase("Resume", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        /*try {
            Cursor cur = db.rawQuery("Select * from Resume", null);
            cur.moveToFirst();
            while (!cur.isAfterLast()){
                bos = cur.getBlob(0);
                cur.moveToNext();
            }
            // cur.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext() , e.toString() , Toast.LENGTH_LONG).show();
        }*/
        //c
        /*try{
            file = Environment.getExternalStorageDirectory().toString() + "/PDF/" + "Name.pdf";
            PdfReader reader = new PdfReader(file);
            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(file));
            Toast.makeText(getApplicationContext() , "in pdf try" , Toast.LENGTH_LONG).show();
            PdfContentByte pcb = stamp.getUnderContent(1);
            document.open();
            Image image = Image.getInstance(bos);
            image.setAbsolutePosition(450f, 650f);
            image.scaleAbsolute(105f, 85f);
            pcb.addImage(image);

            document.close();
            Toast.makeText(getApplicationContext() , "out pdf try" , Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        readFromDB();




        //we fetched the picture here to use.
        //photo = BitmapFactory.decodeByteArray(bos , 0 , bos.length);



    }

    void readFromDB() {
        byte[] byteImage2 = null;
        SQLiteDatabase myDb;
        myDb = openOrCreateDatabase("Resume", Context.MODE_PRIVATE, null);
        Toast.makeText(this,"jg",Toast.LENGTH_SHORT).show();
        /*Cursor cur = myDb.query("image", null, null, null, null, null, null);
        cur.moveToFirst();
        while (cur.isAfterLast() == false) {
            Toast.makeText(this,"jg",Toast.LENGTH_SHORT).show();
            cur.moveToNext();
        }

        // /////Read data from blob field////////////////////
        cur.moveToFirst();
        byteImage2 = cur.getBlob(cur.getColumnIndex("img"));
        //setImage(byteImage2);
        /*cur.close();
        myDb.close();
        Toast.makeText(this.getBaseContext(),
                "Image read from DB successfully.", Toast.LENGTH_SHORT).show();
        Toast.makeText(this.getBaseContext(),
                "If your image is big, please scrolldown to see the result.",
                Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
