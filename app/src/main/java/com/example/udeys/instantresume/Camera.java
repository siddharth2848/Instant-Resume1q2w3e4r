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
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Camera extends AppCompatActivity {

    Button b1, b2, b3;
    ImageView iv , cm;
    Bitmap bp;
    String name,date;
    ArrayList<String> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        b1=(Button)findViewById(R.id.sendEmail);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);


        iv =(ImageView)findViewById(R.id.imageView15);
        cm = (ImageView) findViewById(R.id.camera_logo);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent in = getIntent();
                    info = new ArrayList<>();
                    info = in.getStringArrayListExtra("Final");
                    name = info.get(0);
                    Intent i = new Intent(getApplicationContext(), Generator.class);
                    i.putExtra("NAME",name);
                    i.putExtra("BitmapImage" , bp);
                    i.putExtra("Data",info);
                    startActivity(i);
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext() , "Error:"+e.getMessage() , Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        bp = (Bitmap) data.getExtras().get("data");
        //bp = convertColorIntoBlackAndWhiteImage(bp);
        iv.setVisibility(View.VISIBLE);
        iv.setImageBitmap(bp);
        cm.setVisibility(View.GONE);

        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        b1.setVisibility(View.GONE);


    }

    public void compressImage(Bitmap bp){

        int h=bp.getHeight();
        int w=bp.getWidth();

//depends on compression rate/file size/pixels (dynamic)
//like inSampleSize in ur code
        if( h > 2000)   h /= 2;
        if( w > 2000)   w /= 2;

        h=h/2;
        w=w/2;

        bp=Bitmap.createScaledBitmap(bp, w, h, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private Bitmap convertColorIntoBlackAndWhiteImage(Bitmap orginalBitmap) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);

        Bitmap blackAndWhiteBitmap = orginalBitmap.copy(
                Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        paint.setColorFilter(colorMatrixFilter);

        Canvas canvas = new Canvas(blackAndWhiteBitmap);
        canvas.drawBitmap(blackAndWhiteBitmap, 0, 0, paint);

        return blackAndWhiteBitmap;
    }


}
