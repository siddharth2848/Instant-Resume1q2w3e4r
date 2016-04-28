package com.example.udeys.instantresume;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3;
    int tmplt_id;
    ImageButton im1,im2;
    RelativeLayout l1,l2;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Fill Details");

        im1 = (ImageButton) findViewById(R.id.form1);
        im2 = (ImageButton) findViewById(R.id.form2);

        l1 = (RelativeLayout) findViewById(R.id.grid);

        b1 = (Button) findViewById(R.id.sendEmail);

        im1.setOnClickListener(this);
        im2.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        int id =  v.getId();
        switch (id){
            case R.id.sendEmail:
                setContentView(R.layout.preview_main);
                b1.setVisibility(View.INVISIBLE);
                b2 = (Button) findViewById(R.id.cont);
                b3 = (Button) findViewById(R.id.back);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b2.setOnClickListener(this);
                b3.setOnClickListener(this);

                break;
            case R.id.cont:
                Intent in = new Intent(this, Details.class);
                startActivity(in);
                break;
            case R.id.back:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                break;


            default:
                tmplt_id = id;
                b1.setOnClickListener(this);
                b1.setText("Select");
                //b1.setBackground(Drawable.createFromPath("@colors/colorTheme"));
        }
    }
}
