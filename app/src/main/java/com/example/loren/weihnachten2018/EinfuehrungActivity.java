package com.example.loren.weihnachten2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EinfuehrungActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einfuehrung);

        if(MainActivity.getSHowRätselOnStart(getApplicationContext())!=MainActivity.Start.SHOWRÄTSEL){
            Intent intent = new Intent(EinfuehrungActivity.this,MainActivity.class);
            startActivity(intent);
        }

    }

    public void onClick(View view){
        Intent intent = new Intent(EinfuehrungActivity.this,RaetselActivity.class);
        startActivity(intent);
    }
}
