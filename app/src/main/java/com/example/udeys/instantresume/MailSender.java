/*
* Camera Activiy
* After: Form and database entries are done.
* Before: the final generation
* Opens the camera and take the picture
* sends the picture to the Generator_Activity
* */

package com.example.udeys.instantresume;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MailSender extends AppCompatActivity {

    Button b1;
    EditText t1;
    int i = 0;
    Uri URI = null;
    //String name,date;
    ImageView iv;



    private MyDataBase mdb=null;
    private SQLiteDatabase db=null,db1 = null;
    private Cursor c=null;
    private byte[] img=null;
    private static final String DATABASE_NAME = "ImageDb.db";
    public static final int DATABASE_VERSION = 1;
    Bitmap bp = null;
    ArrayList<String> info;
    String date,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mailsender);

        Button startBtn = (Button) findViewById(R.id.sendEmail);
        t1 = (EditText) findViewById(R.id.email);

        iv = (ImageView) findViewById(R.id.ready);

        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(t1.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext() , "Where Do you want to send!" , Toast.LENGTH_SHORT).show();
                }

                else
                    i = sendEmail();

                if(i == 1 ){
                    Intent fin = new Intent(getApplicationContext() , LastSplash.class);
                    startActivity(fin);
                    finish();
                }
            }
        });



    }



    protected int sendEmail() {

        try {
            Intent in = getIntent();
            name = in.getStringExtra("NAME");
            Bitmap bp = in.getParcelableExtra("BitmapImage");
            iv.setImageBitmap(bp);
            //Toast.makeText(this , name , Toast.LENGTH_SHORT).show();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());
            date = String.valueOf(formattedDate).toString();
            File externalStorage = Environment.getExternalStorageDirectory();
            URI = Uri.fromFile(new File(externalStorage.getAbsolutePath() + "/PDF/" + name +" - "+ date+ ".pdf"));
        }
        catch (Exception e)
        {
            Toast.makeText(this , "attach "+e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        String subject = "Resume->"+name;
        String body = "Please find the Resume in attachments";
        String mail = "mailto:"+t1.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);       //userna
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_STREAM, URI);
        intent.setData(Uri.parse(mail)); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);

        return 1;
    }


}
