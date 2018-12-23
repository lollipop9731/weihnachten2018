import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loren.weihnachten2018.Item;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Item> {

    private ArrayList<Item> items;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView beschreibung;
        public ImageView anzahl;
        public TextView backgroundimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            beschreibung = (TextView)itemView;
            anzahl = (ImageView) itemView;
            backgroundimage= (TextView) itemView;
        }
    }

    public Adapter(ArrayList<Item>items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
