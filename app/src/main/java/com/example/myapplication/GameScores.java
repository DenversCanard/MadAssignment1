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
        ImageView crownP1 = view.findViewById(R.id.crownP1);        //crown. indication of leading player
        ImageView crownP2 = view.findViewById(R.id.crownP2);

        crownP1.setAlpha(0f);   //set both to transparent
        crownP2.setAlpha(0f);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        //setting player icons
        iconP1.setImageResource(mainActivityDataViewModel.getP1Icon());
        iconP2.setImageResource(mainActivityDataViewModel.getP2Icon());

        //setting player names
        nameP1.setText(mainActivityDataViewModel.getP1Name());
        nameP2.setText(mainActivityDataViewModel.getP2Name());

        //setting player score
        scoreP1.setText(mainActivityDataViewModel.getP1wins());
        scoreP2.setText(mainActivityDataViewModel.getP2wins());

        //extract score as int
        int scoreP1int = Integer.valueOf(scoreP1.getText().toString());
        int scoreP2int = Integer.valueOf(scoreP2.getText().toString());

        //setting leader (crown)
        if (scoreP1int > scoreP2int){   //if PI in lead
            crownP1.setAlpha(1f);
            crownP2.setAlpha(0f);
        } else if (scoreP2int > scoreP1int){    //if P2 in lead
            crownP2.setAlpha(1f);
            crownP1.setAlpha(0f);
        }
        //else they are tied. transparent crowns remain same




        return view;
    }


}