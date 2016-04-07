package com.example.udeys.instantresume;
/*
* generator activity
* calls engine to do all the work in back as service
* goes to mail activity when the service terminates
* engine performs all backend work
* loader is dummy.
* */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;

/**
 * Created by udeys on 3/31/2016.
 */
public class Generator extends Activity {

    ProgressBar spinner;
    int i = 0;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generator);

        spinner = (ProgressBar) findViewById(R.id.progressBar);

        //we've the image here
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");


        //send the image to the service
        startService();

        //change activity when service has finished
        //PDF Resume is in the place
        /*
        * Intent int = new Intent(this , mail.class);
        *
        * startActivity(int);
        *
        * */


        /*Thread t = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext() , "Error!" + e.getMessage() , Toast.LENGTH_SHORT).show();
                }

                finally {
                    Intent i = new Intent(getApplicationContext() , Tutorial1.class);
                    startActivity(i);
                }
            }
        };
        t.start();
*/
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