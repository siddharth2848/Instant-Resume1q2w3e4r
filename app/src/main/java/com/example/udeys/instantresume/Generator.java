package com.example.udeys.instantresume;
/*
* generator activity
* calls engine to do all the work in back as service
* goes to mail activity when the service terminates
* engine performs all backend work
* loader is dummy.
* */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by udeys on 3/31/2016.
 */
public class Generator extends AppCompatActivity {

    ProgressBar spinner;
    int i = 0;
    String name,date;
    Bitmap bp = null;
    ArrayList<String> info;
    public ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generator);

        iv = (ImageView) findViewById(R.id.ivv);

        spinner = (ProgressBar) findViewById(R.id.progressBar);
        try {
            Intent intent = getIntent();
            info = intent.getStringArrayListExtra("Data");
            bp = (Bitmap) intent.getParcelableExtra("BitmapImage");
            name = info.get(0);
           // Toast.makeText(getApplicationContext() , "name:"+name , Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            //Toast.makeText(getApplicationContext() , "Error:"+e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        //send the image to the service
        Intent serviceIntent = new Intent(getApplicationContext() , Engine.class);
        //serviceIntent.putExtra("UserID", "123456");
        serviceIntent.putStringArrayListExtra("Data",info);
        serviceIntent.putExtra("BitmapImage", bp);
        getApplicationContext().startService(serviceIntent);



        //change activity when service has finished
        //PDF Resume is in the place
        /*
        * Intent int = new Intent(this , mail.class);
        *
        * startActivity(int);
        *
        * */


        Thread t = new Thread(){
            public void run(){
                try{
                    sleep(6000);
                }catch (Exception e){
                    //Toast.makeText(getApplicationContext() , "Error!" + e.getMessage() , Toast.LENGTH_SHORT).show();
                }

                finally {
                    Intent i = new Intent(getApplicationContext() , MailSender.class);
                    i.putExtra("NAME",name);
                    startActivity(i);
                    finish();
                }
            }
        };
        t.start();

    }

    // Method to start the service
    public void startService() {
        startService(new Intent(getBaseContext(), Engine.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), Engine.class));

    }


}