package com.example.dltjr.baseapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1;
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent minent = new Intent(Intent.ACTION_VIEW,Uri.parse("http:///m.nate.com"));
                startActivity(minent);
            }
        });
        button1.setBackgroundColor(Color.GRAY);

        Button button2;
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent minent = new Intent(Intent.ACTION_VIEW,Uri.parse("content://media/internal/images/media"));
                startActivity(minent);
            }
        });
        button2.setBackgroundColor(Color.GREEN);

        Button button3;
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent minent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:/911"));
                startActivity(minent);
            }
        });
        button3.setBackgroundColor(Color.RED);

        Button button4;
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
        button4.setBackgroundColor(Color.YELLOW);
    }


}
