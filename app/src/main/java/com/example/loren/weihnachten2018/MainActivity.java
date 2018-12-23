package com.example.loren.weihnachten2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    Adapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this,2);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);



        ArrayList<Item> items = new ArrayList<>();

        Item item = new Item(getResources().getDrawable(R.drawable.massage),getString(R.string.massage_beschreibung),5,"Massage");
        items.add(item);
        Item kino = new Item(getResources().getDrawable(R.drawable.kino),getString(R.string.kino_message),1,"Kinobesuch");
        items.add(kino);
        madapter = new Adapter(items);
        madapter.setMitemClickListener(new Adapter.ItemClickListener() {
            @Override
            public void onItemClicked(Item item) {
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(madapter);



    }
}
