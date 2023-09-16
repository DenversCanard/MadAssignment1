package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    private static final DecimalFormat df = new DecimalFormat("0.0");

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

        ImageView crownP1 = view.findViewById(R.id.crownP1);        //crown. indication of leading player
        ImageView crownP2 = view.findViewById(R.id.crownP2);

        crownP1.setAlpha(0f);   //set both to transparent
        crownP2.setAlpha(0f);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        //total rounds played = p1 wins + p2 wins + ties
        int totalRounds;
        if (mainActivityDataViewModel.getP1wins() >0 || mainActivityDataViewModel.getP2wins() > 0 ||
                mainActivityDataViewModel.getDraw() >0) {
            totalRounds = mainActivityDataViewModel.getP1wins() + mainActivityDataViewModel.getP2wins()
                    + mainActivityDataViewModel.getDraw();
        }else {
            totalRounds=0;
        }


                //setting player icons
        ImageView iconP1 = view.findViewById(R.id.iconP1);          //player icons
        ImageView iconP2 = view.findViewById(R.id.iconP2);
        iconP1.setImageResource(mainActivityDataViewModel.getP1Icon());
        iconP2.setImageResource(mainActivityDataViewModel.getP2Icon());

            //setting player names
        TextView nameP1 = view.findViewById(R.id.Player1Name);      //player names
        TextView nameP2 = view.findViewById(R.id.Player2Name);
        nameP1.setText(mainActivityDataViewModel.getP1Name());
        nameP2.setText(mainActivityDataViewModel.getP2Name());

            //setting player score
        TextView scoreP1 = view.findViewById(R.id.P1Score);        //player scores
        TextView scoreP2 = view.findViewById(R.id.P2Score);
        scoreP1.setText(mainActivityDataViewModel.getP1wins().toString());
        scoreP2.setText(mainActivityDataViewModel.getP2wins().toString());

            //extract score as int
        int scoreP1int = Integer.valueOf(scoreP1.getText().toString());
        int scoreP2int = Integer.valueOf(scoreP2.getText().toString());

            //losses
        TextView lossesP1 = view.findViewById(R.id.lossesP1);
        TextView lossesP2 = view.findViewById(R.id.lossesP2);
        lossesP1.setText(mainActivityDataViewModel.getP2wins().toString()); //player losses = other player wins
        lossesP2.setText(mainActivityDataViewModel.getP1wins().toString());

            //ties
        TextView tiesP1 = view.findViewById(R.id.tiesP1);               //player ties
        TextView tiesP2 = view.findViewById(R.id.tiesP2);
        // INSERT TIES
        tiesP1.setText(mainActivityDataViewModel.getDraw()+"");
        tiesP2.setText(mainActivityDataViewModel.getDraw()+"");

            // out of
        TextView winTotalP1 = view.findViewById(R.id.winsTotal1);
        TextView winTotalP2 = view.findViewById(R.id.winsTotal2);
        winTotalP1.setText("wins out of " + Integer.toString(totalRounds));
        winTotalP2.setText("wins out of " + Integer.toString(totalRounds));


            //win percentage
        TextView winPercentP1 = view.findViewById(R.id.winPercentP1);
        TextView winPercentP2 = view.findViewById(R.id.winPercentP2);
        if (totalRounds==0){            //if no games played yet
            winPercentP1.setText("0%");
            winPercentP2.setText("0%");
        }else{                          //else, calculate the percentage
            float percentP1 = (float)scoreP1int/(float)totalRounds*100;
            float percentP2 = (float)scoreP2int/(float)totalRounds*100;
            winPercentP1.setText(df.format(percentP1)+ "%");
            winPercentP2.setText(df.format(percentP2)+ "%");
        }



        //tiesP1.setText(Integer.toString(totalRounds));

        //setting leader (crown)
        if (scoreP1int > scoreP2int){           //if PI in lead
            crownP1.setAlpha(1f);
            crownP2.setAlpha(0f);
        } else if (scoreP2int > scoreP1int){    //if P2 in lead
            crownP2.setAlpha(1f);
            crownP1.setAlpha(0f);
        }
        //else they are tied. transparent crowns remain same


        //press back button goes to PLAY page
        Button backbutton = view.findViewById(R.id.scoreBackButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mainActivityDataViewModel.setDisplayScreen("Play");}
        });





        return view;
    }


}