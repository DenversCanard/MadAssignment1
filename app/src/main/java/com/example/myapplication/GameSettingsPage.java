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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Button BackButton;
    MainActivityData mainActivityDataViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_settings_page, container, false);
        ViewGroup constraintLayout = view.findViewById(R.id.frameLayout);
        mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

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
        BackButton = view.findViewById(R.id.backButton);

        List<Button> colourList = new ArrayList<>();
        colourList.add(blueBoard);
        colourList.add(redBoard);
        colourList.add(greenBoard);
        colourList.add(yellowBoard);
        colourList.add(purpleBoard);
        colourList.add(blackBoard);

        List<Button> conditionList = new ArrayList<>();
        conditionList.add(winConThree);
        conditionList.add(winConFour);
        conditionList.add(winConFive);

        if (savedInstanceState != null) {
//            titleText.setText(savedInstanceState.getString("titleNote"));
//            textEditInput.setText(savedInstanceState.getString("noteTake"));
            mainActivityDataViewModel.setBoardColour(savedInstanceState.getInt("boardColour"));
            mainActivityDataViewModel.setBoardSize(savedInstanceState.getInt("boardSize"));
            mainActivityDataViewModel.setWinCondition(savedInstanceState.getInt("winCondition"));
        }
        else{
            boardSizeBar.setProgress(mainActivityDataViewModel.getBoardSize());
            int colour = mainActivityDataViewModel.getBoardColour();
            int winConNum = mainActivityDataViewModel.getWinCondition();

            if(colour == R.color.defaultBlue){
                blueBoard.setAlpha(0.3f);
            }
            else if(colour == R.color.red){
                redBoard.setAlpha(0.3f);
            }
            else if(colour == R.color.green){
                greenBoard.setAlpha(0.3f);
            }
            else if(colour == R.color.yellow){
                yellowBoard.setAlpha(0.3f);
            }
            else if(colour == R.color.purple){
                purpleBoard.setAlpha(0.3f);
            }
            else if(colour == R.color.black){
                blackBoard.setAlpha(0.3f);
            }

            if (winConNum == 3){
                winConThree.setAlpha(0.3f);
            }
            else if(winConNum == 4){
                winConFour.setAlpha(0.3f);
            } else if (winConNum ==5) {
                winConFive.setAlpha(0.3f);
            }

        }


        blueBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectButtons(colourList);
                blueBoard.setAlpha(0.3f);
                mainActivityDataViewModel.setBoardColour(R.color.defaultBlue);
            }
        });

        redBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectButtons(colourList);
                redBoard.setAlpha(0.3f);
                mainActivityDataViewModel.setBoardColour(R.color.red);
            }
        });
        greenBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectButtons(colourList);
                greenBoard.setAlpha(0.3f);
                mainActivityDataViewModel.setBoardColour(R.color.green);
            }
        });
        yellowBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectButtons(colourList);
                yellowBoard.setAlpha(0.3f);
                mainActivityDataViewModel.setBoardColour(R.color.yellow);
            }
        });
        greenBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectButtons(colourList);
                greenBoard.setAlpha(0.3f);
                mainActivityDataViewModel.setBoardColour(R.color.green);
            }
        });
        purpleBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectButtons(colourList);
                purpleBoard.setAlpha(0.3f);
                mainActivityDataViewModel.setBoardColour(R.color.purple);
            }
        });

        blackBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectButtons(colourList);
                blackBoard.setAlpha(0.3f);
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
                deselectButtons(conditionList);
                winConThree.setAlpha(0.3f);
                mainActivityDataViewModel.setWinCondition(Integer.parseInt(winConThree.getText().toString()));
            }
        });

        winConFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectButtons(conditionList);
                winConFour.setAlpha(0.3f);
                mainActivityDataViewModel.setWinCondition(Integer.parseInt(winConFour.getText().toString()));
            }
        });

        winConFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deselectButtons(conditionList);
                winConFive.setAlpha(0.3f);
                mainActivityDataViewModel.setWinCondition(Integer.parseInt(winConFive.getText().toString()));
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setDisplayScreen("Home");

            }
        });



        return view;
    }

    private void deselectButtons(List<Button> buttonList) {
        for (Button button : buttonList) {
            button.setAlpha(1f); // Set to "unselected" appearance
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int boardColour = mainActivityDataViewModel.getBoardColour();
        int boardSize = mainActivityDataViewModel.getBoardSize();
        int winCon = mainActivityDataViewModel.getWinCondition();

        outState.putInt("boardColour", boardColour);
        outState.putInt("boardSize", boardSize);
        outState.putInt("winCon", winCon);


    }
}