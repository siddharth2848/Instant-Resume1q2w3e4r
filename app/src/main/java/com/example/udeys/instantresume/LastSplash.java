package com.example.udeys.instantresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by udeys on 3/31/2016.
 */

public class LastSplash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lastsplash);

        Thread t = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext() , "Error!" + e.getMessage() , Toast.LENGTH_SHORT).show();
                }

                finally {
                    Intent i = new Intent(getApplicationContext() , MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        t.start();

    }


}