package com.example.loren.weihnachten2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private final Context context;
    ItemClickListener mitemClickListener;

    public interface ItemClickListener{
        void onItemClicked(Item item);
    }

    public void setMitemClickListener(ItemClickListener mitemClickListener) {
        this.mitemClickListener = mitemClickListener;
    }

    private ArrayList<Item> items;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView beschreibung;
        public TextView anzahl;
        public ImageView backgroundimage;
        public TextView title;
        public Button einlösen;
        public CardView layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            beschreibung = (TextView) itemView.findViewById(R.id.item_message);
            title = (TextView) itemView.findViewById(R.id.item_title);
            anzahl = (TextView) itemView.findViewById(R.id.item_anzahl);
            backgroundimage = (ImageView) itemView.findViewById(R.id.background_image);
            einlösen = (Button)itemView.findViewById(R.id.item_einlösen);
            layout = (CardView) itemView.findViewById(R.id.item_layout);

        }

        public void onBind(final Item item, final ItemClickListener clickListener){
            einlösen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(item);
                }
            });
        }




    }

    public Adapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder item, int i) {
        item.anzahl.setText(Integer.toString(items.get(i).getAnzahl())+"x");
        item.beschreibung.setText(items.get(i).getBeschreibung());
        item.title.setText(items.get(i).getTitle());
        item.backgroundimage.setImageDrawable(items.get(i).getBackgroundimage());
        if(items.get(i).getAnzahl()==0){

            item.layout.setBackgroundColor(context.getResources().getColor(R.color.grey));
            item.einlösen.setTextColor(context.getResources().getColor(R.color.greydunkler));
            item.anzahl.setTextColor(context.getResources().getColor(R.color.greydunkler));
            item.beschreibung.setTextColor(context.getResources().getColor(R.color.greydunkler));
            item.title.setTextColor(context.getResources().getColor(R.color.greydunkler));
        }
        item.onBind(items.get(i),mitemClickListener);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
