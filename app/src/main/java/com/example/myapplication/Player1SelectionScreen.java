package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.imageview.ShapeableImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Player1SelectionScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Player1SelectionScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Player1SelectionScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerSelectionScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static Player1SelectionScreen newInstance(String param1, String param2) {
        Player1SelectionScreen fragment = new Player1SelectionScreen();
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
        View view = inflater.inflate(R.layout.fragment_player_selection_screen, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);

        ShapeableImageView PlayerIconAButton = view.findViewById(R.id.PlayerIconAButton);
        ShapeableImageView PlayerIconBButton = view.findViewById(R.id.PlayerIconBButton);
        ShapeableImageView PlayerIconCButton = view.findViewById(R.id.PlayerIconCButton);

        AppCompatImageButton PlayerMarkerAButton = view.findViewById(R.id.PlayerMarkerAButton);
        AppCompatImageButton PlayerMarkerBButton = view.findViewById(R.id.PlayerMarkerBButton);
        AppCompatImageButton PlayerMarkerCButton = view.findViewById(R.id.PlayerMarkerCButton);

        EditText PlayerName  = view.findViewById(R.id.PlayerName);
        PlayerName.setText(mainActivityDataViewModel.getP1Name());

        Button BackButton = view.findViewById(R.id.BackButton);
        Button NextButton = view.findViewById(R.id.NextButton);

        PlayerIconAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP1Icon(R.drawable.icon1);
            }
        });

        PlayerIconBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP1Icon(R.drawable.icon2);
            }
        });

        PlayerIconCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP1Icon(R.drawable.icon3);
            }
        });


        PlayerMarkerAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP1Marker(R.drawable.marker1);
            }
        });

        PlayerMarkerBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP1Marker(R.drawable.marker2);
            }
        });

        PlayerMarkerCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP1Marker(R.drawable.marker3);
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP1Name(PlayerName.getText().toString());
                mainActivityDataViewModel.setDisplayScreen("PlayerOptionMenu");

            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP1Name(PlayerName.getText().toString());
                mainActivityDataViewModel.setDisplayScreen("PVPOrPVESelectionScreen");

            }
        });

        //this is how to get the resID using the file name as a string

        //int resID = getResources().getIdentifier("icon5", "drawable", getActivity().getPackageName());
        //        PlayerIconAButton.setBackgroundResource(resID);

        return view;
    }
}
