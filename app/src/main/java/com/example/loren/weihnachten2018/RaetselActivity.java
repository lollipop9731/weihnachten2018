package com.example.loren.weihnachten2018;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;

public class RaetselActivity extends AppIntro implements RaetselFragment.OnFragmentInteractionListener{


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        RaetselFragment rätsel1 = RaetselFragment.newInstance("Einstein Rätsel",String.format(getResources().getString(R.string.Einstein_intro)),String.format(getResources().getString(R.string.Einstein_Main)),getResources().getColor(R.color.Raetsel_1));
        RaetselFragment rätsel2 = RaetselFragment.newInstance("Ziegenproblem","Das hier ist das zweite Rätsel juhu","Main Text slkdfjslkfjsödlfkslöf",getResources().getColor(R.color.Raetsel_2));

        addSlide(rätsel1);
        addSlide(rätsel2);

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
