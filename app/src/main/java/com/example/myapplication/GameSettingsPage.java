package com.example.myapplication;

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
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameSettingsPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameSettingsPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameSettingsPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameSettingsPage.
     */
    // TODO: Rename and change types and number of parameters
    public static GameSettingsPage newInstance(String param1, String param2) {
        GameSettingsPage fragment = new GameSettingsPage();
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

    Button blueBoard;
    Button redBoard;
    Button greenBoard;
    Button yellowBoard;
    Button purpleBoard;
    Button blackBoard;
    SeekBar boardSizeBar;
    Button winConThree;
    Button winConFour;
    Button winConFive;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_settings_page, container, false);
        ViewGroup constraintLayout = view.findViewById(R.id.frameLayout);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        blueBoard = view.findViewById(R.id.colourBlueBut);
        redBoard = view.findViewById(R.id.colourRedBut);
        greenBoard = view.findViewById(R.id.colourGreenBut);
        yellowBoard = view.findViewById(R.id.colourYellowBut);
        purpleBoard = view.findViewById(R.id.colourPurpleBut);
        blackBoard = view.findViewById(R.id.colourBlackBut);
        boardSizeBar = view.findViewById(R.id.seekBar);
        winConThree = view.findViewById(R.id.winConThree);
        winConFour = view.findViewById(R.id.winConFour);
        winConFive = view.findViewById(R.id.winConFive);

        blueBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setBoardColour(R.color.defaultBlue);
            }
        });

        redBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setBoardColour(R.color.red);
            }
        });
        greenBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setBoardColour(R.color.green);
            }
        });
        yellowBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setBoardColour(R.color.yellow);
            }
        });
        greenBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setBoardColour(R.color.green);
            }
        });
        purpleBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setBoardColour(R.color.purple);
            }
        });

        blackBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setBoardColour(R.color.black);
            }
        });

        boardSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mainActivityDataViewModel.setBoardSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        winConThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setWinCondition(Integer.parseInt(winConThree.getText().toString()));
            }
        });

        winConFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setWinCondition(Integer.parseInt(winConFour.getText().toString()));
            }
        });

        winConFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setWinCondition(Integer.parseInt(winConFive.getText().toString()));
            }
        });



        return view;
    }
}