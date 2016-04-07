package com.example.udeys.instantresume;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by udeys on 4/7/2016.
 */
public class Engine extends Service {
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Engine started", Toast.LENGTH_SHORT).show();
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

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
