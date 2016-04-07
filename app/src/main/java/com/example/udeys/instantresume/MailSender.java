/*
* Camera Activiy
* After: Form and database entries are done.
* Before: the final generation
* Opens the camera and take the picture
* sends the picture to the Generator_Activity
* */

package com.example.udeys.instantresume;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MailSender extends AppCompatActivity {

    Button b1;
    EditText t1;
    int i = 0;
    Uri URI = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mailsender);

        Button startBtn = (Button) findViewById(R.id.sendEmail);
        t1 = (EditText) findViewById(R.id.email);


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
                }
            }
        });



    }

    protected int sendEmail() {

        try {
            File externalStorage = Environment.getExternalStorageDirectory();
            URI = Uri.fromFile(new File(externalStorage.getAbsolutePath() + "/PDF/" + "Name.pdf"));
        }
        catch (Exception e)
        {
            Toast.makeText(this , "attach "+e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        String subject = "Resume->"+"udey singh";
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
