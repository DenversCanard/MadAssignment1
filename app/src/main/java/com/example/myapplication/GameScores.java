package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameScores#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameScores extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameScores() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameScores.
     */
    // TODO: Rename and change types and number of parameters
    public static GameScores newInstance(String param1, String param2) {
        GameScores fragment = new GameScores();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_scores, container, false);

        Button backButton = view.findViewById(R.id.scoreBackButton);//back button
        ImageView iconP1 = view.findViewById(R.id.iconP1);          //player icons
        ImageView iconP2 = view.findViewById(R.id.iconP2);
        TextView nameP1 = view.findViewById(R.id.Player1Name);      //player names
        TextView nameP2 = view.findViewById(R.id.Player2Name);
        TextView scoreP1 = view.findViewById(R.id.P1Score);        //player scores
        TextView scoreP2 = view.findViewById(R.id.P2Score);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        //setting player icons
        String chosenIconP1 = mainActivityDataViewModel.getP1Icon();    //get chosen icon
        String chosenIconP2 = mainActivityDataViewModel.getP2Icon();
        if (chosenIconP1.equals("icon1")){
            iconP1.setImageResource(R.drawable.icon1);
        } else if (chosenIconP1.equals("icon2")){
            iconP1.setImageResource(R.drawable.icon2);
        } else if (chosenIconP1.equals("icon3")){
            iconP1.setImageResource(R.drawable.icon3);
        }

        if (chosenIconP2.equals("icon4")){
            iconP1.setImageResource(R.drawable.icon1);
        } else if (chosenIconP2.equals("icon5")){
            iconP1.setImageResource(R.drawable.icon2);
        } else if (chosenIconP2.equals("icon6")){
            iconP1.setImageResource(R.drawable.icon3);
        }

        //setting player names
        nameP1.setText(mainActivityDataViewModel.getP1Name());
        nameP2.setText(mainActivityDataViewModel.getP2Name());

        //setting player score


        return view;
    }


}