package com.example.loren.weihnachten2018;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DialogFragment extends android.support.v4.app.DialogFragment {

    public static final String VERSUCHEKEY ="versuche" ;
    int numberofcheckboxes;
    String correctcheckbox;
    String[] checkboxestitles;
    String title;
    private View.OnClickListener onClickListener;
    private ArrayList<CheckBox> checkBoxes;
    private String solution;
    private int i ;
    private TextView versuche;


    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static DialogFragment newInstance(int numberofcheckboxes, String[] checkboxestitles,String correctcheckbox,String title) {
        DialogFragment f = new DialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num",numberofcheckboxes);
        args.putStringArray("checkboxes",checkboxestitles);
        args.putString("title",title);
        args.putString("solution",correctcheckbox);
        f.setArguments(args);

        return f;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            numberofcheckboxes = getArguments().getInt("num");
            correctcheckbox = getArguments().getString("solution");
            checkboxestitles = getArguments().getStringArray("checkboxes");
            title = getArguments().getString("title");
        }

        i=getVersuche(title);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog, container, false);
        checkBoxes = new ArrayList<>();
        TextView textView = (TextView)v.findViewById(R.id.title);
        versuche = (TextView)v.findViewById(R.id.versuche);
        versuche.setText(getResources().getString(R.string.versuche,getVersuche(title)));
        textView.setText(title);
        LinearLayout linearLayout = (LinearLayout)v.findViewById(R.id.layoutcheckboxes);

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CheckBox checkBox:checkBoxes) {
                    checkBox.setChecked(false);
                }
                CheckBox box = (CheckBox)view;
                box.setChecked(true);
                solution = box.getText().toString();


            }
        };

        for (int i=0;i<numberofcheckboxes;i++){
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(checkboxestitles[i]);
            linearLayout.addView(checkBox);
            checkBoxes.add(checkBox);
            checkBox.setOnClickListener(onClickListener);

        }

        Button redeem = (Button)v.findViewById(R.id.checksolution);
        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getVersuche(title)>=2){
                    Toast.makeText(getContext(),"Leider alle Versuche aufgebraucht.",Toast.LENGTH_LONG).show();
                }else{
                    if(solution.equals(correctcheckbox)){
                        //richtige Lösung
                        Toast.makeText(getContext(),"Richtig",Toast.LENGTH_LONG).show();
                        dismiss();
                    }else{
                        //Falsche Lösung, Versuche erhöhen
                        Toast.makeText(getContext(),"Leider Falsch",Toast.LENGTH_LONG).show();
                        i++;
                        setVersuche(i,title);
                        versuche.setText(getResources().getString(R.string.versuche,getVersuche(title)));
                    }
                }


            }
        });

        return v;
    }

    public void setVersuche(int versuch,String titleofrätsel){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(VERSUCHEKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(titleofrätsel,versuch);
        editor.commit();
    }

    public int getVersuche(String titleofrätsel){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(VERSUCHEKEY, Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(titleofrätsel,0);
    }

    public static void deleteVersuche(ArrayList<String>titles,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(VERSUCHEKEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (String title:titles) {
            if (sharedPreferences.getInt(title,0)>=2){
                editor.putInt(title,0);
                editor.commit();
            }
        }
    }
}
