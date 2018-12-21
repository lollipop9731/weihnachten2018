package com.example.loren.weihnachten2018;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RaetselFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RaetselFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RaetselFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Überschrift = "param1";
    private static final String ARG_Introtext = "param2";
    private static final String ARG_Maintext="param3";
    private static final String ARG_Background_color="param4";

    // TODO: Rename and change types of parameters
    
    private String Überschrift;
    private String Introtext;
    private String Maintext;
    private int backgroundcolor;

    //Views from Layout
    private TextView TextÜberschrift;
    private TextView TextIntro;
    private TextView TextMain;
    private FrameLayout frameLayout;

    private OnFragmentInteractionListener mListener;

    public RaetselFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param Überschrift Thin and Light
     * @param Introtext introText
     * @param Maintext
     * @param backgroundcolor
     * @return
     */
    public static RaetselFragment newInstance(String Überschrift, String Introtext, String Maintext, int backgroundcolor) {
        RaetselFragment fragment = new RaetselFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Überschrift, Überschrift);
        args.putString(ARG_Introtext, Introtext);
        args.putString(ARG_Maintext, Maintext);
        args.putInt(ARG_Background_color, backgroundcolor);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Überschrift = getArguments().getString(ARG_Überschrift);
            Introtext = getArguments().getString(ARG_Introtext);
            Maintext = getArguments().getString(ARG_Maintext);
            backgroundcolor = getArguments().getInt(ARG_Background_color);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_raetsel, container, false);
        TextÜberschrift = (TextView)view.findViewById(R.id.text_title);
        TextIntro = (TextView)view.findViewById(R.id.text_intro);
        TextMain = (TextView)view.findViewById(R.id.text_main);
        frameLayout = (FrameLayout)view.findViewById(R.id.background_layout);

        TextÜberschrift.setText(Überschrift);
        TextIntro.setText(Introtext);
        TextMain.setText(Maintext);
        frameLayout.setBackgroundColor(backgroundcolor);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
