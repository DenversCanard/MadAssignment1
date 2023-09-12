package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Player2SelectionScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Player2SelectionScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Player2SelectionScreen() {
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
    public static Player2SelectionScreen newInstance(String param1, String param2) {
        Player2SelectionScreen fragment = new Player2SelectionScreen();
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

        ImageButton PlayerIconAButton = view.findViewById(R.id.PlayerIconAButton);
        ImageButton PlayerIconBButton = view.findViewById(R.id.PlayerIconBButton);
        ImageButton PlayerIconCButton = view.findViewById(R.id.PlayerIconCButton);

        ImageButton PlayerMarkerAButton = view.findViewById(R.id.PlayerMarkerAButton);
        ImageButton PlayerMarkerBButton = view.findViewById(R.id.PlayerMarkerBButton);
        ImageButton PlayerMarkerCButton = view.findViewById(R.id.PlayerMarkerCButton);

        EditText PlayerName  = view.findViewById(R.id.PlayerName);
        Button BackButton = view.findViewById(R.id.BackButton);
        Button NextButton = view.findViewById(R.id.NextButton);

        PlayerIconAButton.setBackgroundResource(getResources().getIdentifier("icon4", "drawable", getActivity().getPackageName()));
        PlayerIconBButton.setBackgroundResource(getResources().getIdentifier("icon5", "drawable", getActivity().getPackageName()));
        PlayerIconCButton.setBackgroundResource(getResources().getIdentifier("icon6", "drawable", getActivity().getPackageName()));

        PlayerMarkerAButton.setBackgroundResource(getResources().getIdentifier("marker4", "drawable", getActivity().getPackageName()));
        PlayerMarkerBButton.setBackgroundResource(getResources().getIdentifier("marker5", "drawable", getActivity().getPackageName()));
        PlayerMarkerCButton.setBackgroundResource(getResources().getIdentifier("marker6", "drawable", getActivity().getPackageName()));

        PlayerIconAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2Icon("icon4");
            }
        });

        PlayerIconBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2Icon("icon5");
            }
        });

        PlayerIconCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2Icon("icon6");
            }
        });


        PlayerMarkerAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2Marker("marker4");
            }
        });

        PlayerMarkerBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2Marker("marker5");
            }
        });

        PlayerMarkerCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2Marker("marker6");
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2Name(PlayerName.getText().toString());
                mainActivityDataViewModel.setDisplayScreen("PVPOrPVESelectionScreen");

            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setP2Name(PlayerName.getText().toString());
                mainActivityDataViewModel.setDisplayScreen("PlayerOptionMenu");

            }
        });


        //this is how to get the resID using the file name as a string

        //int resID = getResources().getIdentifier("icon5", "drawable", getActivity().getPackageName());
        //        PlayerIconAButton.setBackgroundResource(resID);

        return view;
    }
}