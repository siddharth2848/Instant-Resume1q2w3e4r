package com.example.udeys.instantresume;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Siddharth Malhotra on 04/08/16.
 */
public class Details extends Activity implements View.OnClickListener{

    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        b1 = (Button) findViewById(R.id.bt1_det);
        b2 = (Button) findViewById(R.id.bt2_det);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id;
        id = v.getId();

        switch (id){
            case R.id.bt1_det:
                break;
            case R.id.bt2_det:
                break;
        }
    }
}
