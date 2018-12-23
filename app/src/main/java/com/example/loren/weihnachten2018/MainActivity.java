package com.example.loren.weihnachten2018;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.loren.weihnachten2018.MainActivity.Start.SHOWRÄTSEL;

public class MainActivity extends AppCompatActivity {

    private static final String ANZAHL = "anzahl";
    private static final String SHOWRÄTSELONSTART = "showrätse";
    public static final String STARTOPTIONS = "start";
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    Adapter madapter;
    private ArrayList<Item> items;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.getItem(0);
        if(getSHowRätselOnStart(getApplicationContext())==Start.SHOWRÄTSEL){
            menuItem.setChecked(true);
        }else{
            menuItem.setChecked(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.reset:
                if(!item.isChecked()){
                    item.setChecked(true);
                    setShowRätselOnStart(Start.SHOWRÄTSEL);
                }else{
                    item.setChecked(false);
                    setShowRätselOnStart(Start.DONTSHOWRÄTSEL);
                }

                break;
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Frohe Weihnachten Luisa!");


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        items = new ArrayList<>();
        addItems();

        //wenn schon geschrieben, dann anzahl von shared nehmen
        if (getSharedPreferences(ANZAHL, MODE_PRIVATE) != null) {
            for (Item item : items) {
                item.setAnzahl(getAnzahl(item));
            }
        }


        madapter = new Adapter(items, getApplicationContext());
        madapter.setMitemClickListener(new Adapter.ItemClickListener() {
            @Override
            public void onItemClicked(final Item item) {
                if (item.getAnzahl() == 0) {
                    Toast.makeText(getApplicationContext(), "Das wars leider schon.", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog));
                    builder.setTitle("Einlösen")
                            .setMessage("Willst du wirklich einlösen?")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    saveAnzahlMinus1(item);
                                    item.setAnzahl(item.getAnzahl() - 1);
                                    madapter.notifyDataSetChanged();
                                }
                            });

                    builder.create().show();


                }

            }
        });

        recyclerView.setAdapter(madapter);


    }

    public void saveAnzahlMinus1(Item item) {
        SharedPreferences sharedPreferences = getSharedPreferences(ANZAHL, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(item.getTitle(), getAnzahl(item) - 1);
        editor.commit();
    }

    public int getAnzahl(Item item) {
        SharedPreferences sharedPreferences = getSharedPreferences(ANZAHL, MODE_PRIVATE);
        return sharedPreferences.getInt(item.getTitle(), item.getAnzahl());
    }

    public void addItems() {


        Item item = new Item(getResources().getDrawable(R.drawable.massage), getString(R.string.massage_beschreibung), 5, "Massage");
        items.add(item);
        Item kino = new Item(getResources().getDrawable(R.drawable.kino), getString(R.string.kino_message), 1, "Kinobesuch");
        items.add(kino);
        Item essen = new Item(getResources().getDrawable(R.drawable.restaurant), getString(R.string.essen_message), 1, "Lecker essen");
        items.add(essen);
        Item übernachtung = new Item(getResources().getDrawable(R.drawable.schlafen), getString(R.string.uebernachtung_message), 2, "Übernachtung");
        items.add(übernachtung);


    }

    public enum Start {

        SHOWRÄTSEL,DONTSHOWRÄTSEL
    }


    public void setShowRätselOnStart(Start option){
        SharedPreferences sharedPreferences = getSharedPreferences(SHOWRÄTSELONSTART,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(option == SHOWRÄTSEL){
            editor.putBoolean(STARTOPTIONS,true);

            editor.commit();
        }else{
            editor.putBoolean(STARTOPTIONS,false);
            editor.commit();
        }
    }

    public static Start getSHowRätselOnStart(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHOWRÄTSELONSTART,MODE_PRIVATE);
        if(sharedPreferences.getBoolean(STARTOPTIONS,true)){
            return Start.SHOWRÄTSEL;
        }else{
            return Start.DONTSHOWRÄTSEL;
        }
    }
}
