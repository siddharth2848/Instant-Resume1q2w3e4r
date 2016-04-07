package com.example.udeys.instantresume;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    int tmplt_id;
    ImageButton im1,im2;
    RelativeLayout l1,l2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("hello!");

        im1 = (ImageButton) findViewById(R.id.form1);
        im2 = (ImageButton) findViewById(R.id.form2);

        l1 = (RelativeLayout) findViewById(R.id.grid);

        b1 = (Button) findViewById(R.id.sendEmail);
        im1.setOnClickListener(this);
        im1.setOnClickListener(this);

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
                /*Intent in = new Intent(this , Splash.class);
                in.putExtra("form_id" , id);
                startActivity(in);
                */


                break;

            default:
                tmplt_id = id;
                b1.setOnClickListener(this);
                b1.setText("Select");
               //  b1.setBackground(Color.parseColor("#212338"));
        }
    }
}
