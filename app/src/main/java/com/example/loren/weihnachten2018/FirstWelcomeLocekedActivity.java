package com.example.loren.weihnachten2018;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class FirstWelcomeLocekedActivity extends AppCompatActivity {

    private static final String LOGGEDIN ="logged" ;
    private static final String LOGGEDINNOW = "loggedin";
    EditText Code;
    Button EntsperrenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_welcome_loceked);


        if(getLoggedIn()){
            Intent intent = new Intent(FirstWelcomeLocekedActivity.this,EinfuehrungActivity.class);
            startActivity(intent);
        }



        Code = (EditText)findViewById(R.id.code);
        EntsperrenBtn = (Button)findViewById(R.id.entsperren);

        EntsperrenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Code.getText().toString().equals("")){
                    if(Code.getText().toString().equals("1369")){
                        //code richtig
                        SetLoggedIn();
                        Intent intent = new Intent(FirstWelcomeLocekedActivity.this,EinfuehrungActivity.class);
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

    private void resetVersuche() {
        ArrayList<String> titel = new ArrayList<>();
        titel.add("Einstein Rätsel");
        titel.add("Kryptischer Geldwunsch");
        titel.add("Die findigen Gangster");
        titel.add("Das Ziegenproblem");
        titel.add("Das Golderbe");
        DialogFragment.deleteVersuche(titel,getApplicationContext());
    }


    public void SetLoggedIn(){
        SharedPreferences sharedPreferences = getSharedPreferences(LOGGEDIN,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGGEDINNOW,true);
        editor.commit();
    }

    public boolean getLoggedIn(){
        SharedPreferences sharedPreferences = getSharedPreferences(LOGGEDIN,MODE_PRIVATE);
        return sharedPreferences.getBoolean(LOGGEDINNOW,false);
    }
}
