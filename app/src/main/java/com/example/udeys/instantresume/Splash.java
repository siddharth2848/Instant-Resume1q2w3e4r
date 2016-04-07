package com.example.udeys.instantresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by udeys on 3/31/2016.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread t = new Thread(){
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

    }


}