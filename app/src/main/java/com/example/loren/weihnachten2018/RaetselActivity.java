package com.example.loren.weihnachten2018;

import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

import java.util.ArrayList;

public class RaetselActivity extends AppIntro implements RaetselFragment.OnFragmentInteractionListener {


    private Resources ressources;
    //Lösungen

    private String[] einsteinposs = {"Der Norweger", "Der Ukrainer", "Der Engländer", "Der Japaner", "Der Spanier"};
    private String[] geldwunsche = {"7590€", "10652€", "2308€", "22560€", "8422€"};
    private String[] gangsterposs = {"ca. 8%", "ca. 12%", "ca. 0,4%", "ca. 31%", "ca. 55%"};
    private String[] ziegenposs = {"Chancen erhöhen sich","Chancen bleiben gleich","Chancen verringern sich"};
    private String[] söhneposs = {"6 Söhne","3 Söhne","5 Söhne","4 Söhne","7 Söhne"};
    private Rätsel einstein;
    private Rätsel geldwunsch;
    private Rätsel gangster;
    private Rätsel ziegen;
    private Rätsel golderbe;


    @Override
    public void onFragmentInteraction(String title) {
        switch (title) {
            case "Einstein Rätsel":
                showDialog(einstein);
                break;

            case "Kryptischer Geldwunsch":
                showDialog(geldwunsch);
                break;

            case "Die findigen Gangster":
                showDialog(gangster);
                break;

            case "Das Ziegenproblem":
                showDialog(ziegen);
                break;

            case "Das Golderbe":
                showDialog(golderbe);
                break;


        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ressources = getResources();

        ArrayList<String>titel=new ArrayList<>();
        titel.add("Einstein Rätsel");
        titel.add("Kryptischer Geldwunsch");

        titel.add("Das Ziegenproblem");
        titel.add("Das Golderbe");
        //todo unccomment
        DialogFragment.deleteVersuche(titel,getApplicationContext());

        //Rätsel Seiten
        RaetselFragment rätsel1 = RaetselFragment.newInstance(ressources.getString(R.string.Einstein_title), String.format(ressources.getString(R.string.Einstein_intro)), ressources.getString(R.string.Einstein_Main), ressources.getColor(R.color.Raetsel_1), R.drawable.hauser);
        RaetselFragment rätsel2 = RaetselFragment.newInstance(ressources.getString(R.string.Geldwunsch_titel), ressources.getString(R.string.Geldwunsch_intro), ressources.getString(R.string.Geldwunsch_main), ressources.getColor(R.color.Raetsel_2), R.drawable.geldwunschimage);
        RaetselFragment raetsel3 = RaetselFragment.newInstance(ressources.getString(R.string.Gangster_title), ressources.getString(R.string.Gangster_intro), ressources.getString(R.string.Gangster_main), ressources.getColor(R.color.GangsterRätsel), R.drawable.gangster);
        RaetselFragment raetsel4 = RaetselFragment.newInstance(ressources.getString(R.string.Ziegen_title), ressources.getString(R.string.Ziegen_intro), ressources.getString(R.string.Ziegen_main), ressources.getColor(R.color.Ziegenproblem), R.drawable.ziegen);
        RaetselFragment raetsel5 = RaetselFragment.newInstance(ressources.getString(R.string.Golderbe_title), ressources.getString(R.string.Golderbe_Intro), ressources.getString(R.string.Golderbe_Main), ressources.getColor(R.color.Goldraetsel), R.drawable.chestgold);

        //Rätsel für die Lösungen
        einstein = new Rätsel("Der Japaner", einsteinposs, getResources().getString(R.string.Einstein_title));
        geldwunsch = new Rätsel("10652€", geldwunsche, getResources().getString(R.string.Geldwunsch_titel));
        gangster = new Rätsel("ca. 31%", gangsterposs, getResources().getString(R.string.Gangster_title));
        ziegen = new Rätsel("Chancen erhöhen sich",ziegenposs,ressources.getString(R.string.Ziegen_title));
        golderbe = new Rätsel("6 Söhne",söhneposs,ressources.getString(R.string.Golderbe_title));

        addSlide(rätsel1);
        addSlide(rätsel2);
        addSlide(raetsel3);
        addSlide(raetsel4);
        addSlide(raetsel5);

        showSkipButton(false);
        setSeparatorColor(ressources.getColor(R.color.separtpr));

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    void showDialog(Rätsel rätsel) {


        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = DialogFragment.newInstance(rätsel.size(), rätsel.getPossibilites(), rätsel.getCorrect(), rätsel.getTitle());
        newFragment.show(ft, "dialog");
    }
}
