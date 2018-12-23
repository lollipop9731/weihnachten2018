package com.example.loren.weihnachten2018;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

import java.util.ArrayList;

import static com.example.loren.weihnachten2018.DialogFragment.VERSUCHEKEY;

public class RaetselActivity extends AppIntro implements RaetselFragment.OnFragmentInteractionListener, DialogFragment.RaetselSolved {


    private Resources ressources;
    //Lösungen

    private String[] einsteinposs = {"Der Norweger", "Der Ukrainer", "Der Engländer", "Der Japaner", "Der Spanier"};
    private String[] geldwunsche = {"7590€", "10652€", "2308€", "22560€", "8422€"};
    private String[] gangsterposs = {"ca. 8%", "ca. 12%", "ca. 0,4%", "ca. 31%", "ca. 55%"};
    private String[] ziegenposs = {"Chancen erhöhen sich", "Chancen bleiben gleich", "Chancen verringern sich"};
    private String[] söhneposs = {"6 Söhne", "3 Söhne", "5 Söhne", "4 Söhne", "7 Söhne"};
    private Rätsel einstein;
    private Rätsel geldwunsch;
    private Rätsel gangster;
    private Rätsel ziegen;
    private Rätsel golderbe;
    private ArrayList<RaetselFragment> alleRätselFragments;
    private ArrayList<Rätsel> alleRätsel;


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
    public void SolvedClicked(String title) {
        for (Rätsel rätsel : alleRätsel) {
            if (rätsel.getTitle().equals(title)) {
                rätsel.setSolved(true);
            }
        }

        for (RaetselFragment fragment : alleRätselFragments) {
            if (fragment.getÜberschrift() != null && fragment.getÜberschrift().equals(title)) {
                fragment.updateButton();
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alleRätselFragments = new ArrayList<>();
        alleRätsel = new ArrayList<>();

        ressources = getResources();

        ArrayList<String> titel = new ArrayList<>();
        titel.add("Einstein Rätsel");
        titel.add("Kryptischer Geldwunsch");

        titel.add("Das Ziegenproblem");
        titel.add("Das Golderbe");
        //todo unccomment
        //DialogFragment.deleteVersuche(titel,getApplicationContext());

        //Rätsel Seiten
        RaetselFragment rätsel1 = RaetselFragment.newInstance(ressources.getString(R.string.Einstein_title), String.format(ressources.getString(R.string.Einstein_intro)), ressources.getString(R.string.Einstein_Main), ressources.getColor(R.color.Raetsel_1), R.drawable.hauser);
        RaetselFragment rätsel2 = RaetselFragment.newInstance(ressources.getString(R.string.Geldwunsch_titel), ressources.getString(R.string.Geldwunsch_intro), ressources.getString(R.string.Geldwunsch_main), ressources.getColor(R.color.Raetsel_2), R.drawable.geldwunschimage);
        RaetselFragment raetsel3 = RaetselFragment.newInstance(ressources.getString(R.string.Gangster_title), ressources.getString(R.string.Gangster_intro), ressources.getString(R.string.Gangster_main), ressources.getColor(R.color.GangsterRätsel), R.drawable.gangster);
        RaetselFragment raetsel4 = RaetselFragment.newInstance(ressources.getString(R.string.Ziegen_title), ressources.getString(R.string.Ziegen_intro), ressources.getString(R.string.Ziegen_main), ressources.getColor(R.color.Ziegenproblem), R.drawable.ziegen);
        RaetselFragment raetsel5 = RaetselFragment.newInstance(ressources.getString(R.string.Golderbe_title), ressources.getString(R.string.Golderbe_Intro), ressources.getString(R.string.Golderbe_Main), ressources.getColor(R.color.Goldraetsel), R.drawable.chestgold);

        //Add to Arraylist
        alleRätselFragments.add(rätsel1);
        alleRätselFragments.add(rätsel2);
        alleRätselFragments.add(raetsel3);
        alleRätselFragments.add(raetsel4);
        alleRätselFragments.add(raetsel5);


        //Rätsel für die Lösungen
        einstein = new Rätsel("Der Japaner", einsteinposs, getResources().getString(R.string.Einstein_title));
        alleRätsel.add(einstein);
        geldwunsch = new Rätsel("10652€", geldwunsche, getResources().getString(R.string.Geldwunsch_titel));
        alleRätsel.add(geldwunsch);
        gangster = new Rätsel("ca. 31%", gangsterposs, getResources().getString(R.string.Gangster_title));
        alleRätsel.add(gangster);
        ziegen = new Rätsel("Chancen erhöhen sich", ziegenposs, ressources.getString(R.string.Ziegen_title));
        alleRätsel.add(ziegen);
        golderbe = new Rätsel("6 Söhne", söhneposs, ressources.getString(R.string.Golderbe_title));
        alleRätsel.add(golderbe);

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
        Boolean allsolved = true;

        for (Rätsel rätsel : alleRätsel) {
            int current = getVersuche(rätsel.getTitle());
            if (current!=-1) {
                //mindestens Ein Rätsel ungelöst!
                allsolved = false;
            }
        }

        if (allsolved) {
            //ALle erledigt
            Toast.makeText(getApplicationContext(), "Geht los", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RaetselActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Noch nicht alle erledigt!", Toast.LENGTH_LONG).show();
        }
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

    public int getVersuche(String titleofrätsel){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(VERSUCHEKEY, Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(titleofrätsel,0);
    }


}
