package com.example.udeys.instantresume;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

/**
 * Created by Siddharth Malhotra on 4/18/2016.
 */
public class StartPdfAct extends AppCompatActivity implements View.OnClickListener{

    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, PdfViewAct.class);
        intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, Environment.getExternalStorageDirectory().toString() + "/PDF/" + "Name.pdf");
        startActivity(intent);
        setContentView(R.layout.pdfchooser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_b1);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        b1 = (Button) findViewById(R.id.cont);
        b2 = (Button) findViewById(R.id.main);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.cont:
                onContinue(view);
                break;
            case R.id.main:
                onMain(view);
                break;
        }

    }


    void onContinue(View view){
        startActivity(new Intent(this,Generator.class));
        finish();
    }

    void onMain(View view){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
