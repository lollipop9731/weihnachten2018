package com.example.loren.weihnachten2018;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import in.codeshuffle.typewriterview.TypeWriterListener;
import in.codeshuffle.typewriterview.TypeWriterView;

public class WelcomeTextActivity extends AppCompatActivity implements TypeWriterListener {

    TypeWriterView typeWriterView;
    Button Rätselnstarten;

    @Override
    public void onTypingRemoved(String text) {

    }

    @Override
    public void onTypingStart(String text) {

    }

    @Override
    public void onTypingEnd(String text) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        Rätselnstarten.setVisibility(View.VISIBLE);
        Rätselnstarten.startAnimation(animation);
    }

    @Override
    public void onCharacterTyped(String text, int position) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_text);



        typeWriterView = (TypeWriterView)findViewById(R.id.typeWriterView);
        typeWriterView.setDelay(60);
        typeWriterView.setWithMusic(false);
        typeWriterView.animateText(getResources().getString(R.string.welcome_text));
        typeWriterView.setTypeWriterListener(this);

        Rätselnstarten =(Button)findViewById(R.id.start);

        Rätselnstarten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Rätselnstarten.getVisibility() == View.VISIBLE){
                    //nur wenn Button Sichtbar
                    Toast.makeText(getApplicationContext(),"Weiter zum Rätseln",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
