package com.example.udeys.instantresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Tutorial3 extends Activity implements View.OnClickListener {

    Button b1;
    ImageView im;
    TextView t1,t2;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial3);

        b1= (Button) findViewById(R.id.button);

        b1.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this , MainActivity.class);
        startActivity(i);
    }
}