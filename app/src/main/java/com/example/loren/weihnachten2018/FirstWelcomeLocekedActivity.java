package com.example.loren.weihnachten2018;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstWelcomeLocekedActivity extends AppCompatActivity {

    EditText Code;
    Button EntsperrenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_welcome_loceked);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Frohe Weihnachten!");

        Code = (EditText)findViewById(R.id.code);
        EntsperrenBtn = (Button)findViewById(R.id.entsperren);

        EntsperrenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Code.getText().toString().equals("")){
                    if(Code.getText().toString().equals("1369")){
                        //code richtig
                        Intent intent = new Intent(FirstWelcomeLocekedActivity.this,WelcomeTextActivity.class);
                        startActivity(intent);
                    }else{
                        Code.setError("Falscher Code!");
                    }
                }else{
                    Code.setError("Bitte Code eingeben!");
                }
            }
        });


    }
}
