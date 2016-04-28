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
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by udeys on 3/31/2016.
 */
public class Generator extends AppCompatActivity {

    ProgressBar spinner;
    int i = 0;
   // Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generator);

        spinner = (ProgressBar) findViewById(R.id.progressBar);

        //we've the image here
        //Bitmap bp = (Bitmap) intent.getParcelableExtra("BitmapImage");


        //send the image to the service
        Intent serviceIntent = new Intent(getApplicationContext() , Engine.class);
        //serviceIntent.putExtra("UserID", "123456");
        //serviceIntent.putExtra("BitmapImage", bp);
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
                    sleep(3000);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext() , "Error!" + e.getMessage() , Toast.LENGTH_SHORT).show();
                }

                finally {
                    Intent i = new Intent(getBaseContext() , MailSender.class);
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