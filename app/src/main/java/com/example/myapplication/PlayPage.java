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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // Game parameters
    private int boardSize;
    private TicTacToeLogic boardLogic;
    private ImageButton[][] ticTacToeButtons;
    private int playersTurn;
    private boolean AI;


    public PlayPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayPage.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayPage newInstance(String param1, String param2) {
        PlayPage fragment = new PlayPage();
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
        // Inflate the layout for this fragment
        View playView = inflater.inflate(R.layout.fragment_play_page, container, false);
        ViewGroup constraintLayout = playView.findViewById(R.id.playFragment);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        // Check Ai usage
        AI = mainActivityDataViewModel.getP2IsAi();

        // Saved Instance Logic
        boolean reapply = false;
        if (savedInstanceState != null) {

            boardSize = savedInstanceState.getInt("boardSize");
            playersTurn = savedInstanceState.getInt("playersTurn");
            int[][] loadedBoardInfo = new int[boardSize][boardSize];

            for (String key : savedInstanceState.keySet()) {
                Log.d("key", key);
                if (key.contains("row")) {
                    int pos = Character.getNumericValue(key.charAt(key.length() - 1));
                    loadedBoardInfo[pos] = savedInstanceState.getIntArray(key);


                }
            }
            boardLogic = new TicTacToeLogic();
            boardLogic.loadBoard(loadedBoardInfo);
            reapply = true;  // a tag to let the app know to reapply the info to the board ? unclean af
        } else {
            boardSize = mainActivityDataViewModel.getBoardSize(); // this is temporary until we have a way of fetching the board size from settings
            boardLogic = new TicTacToeLogic(boardSize);
            playersTurn = 1;
        }

        // Find board container
        LinearLayout boardContainer = constraintLayout.findViewById(R.id.boardContainer);
        boardContainer.setBackgroundColor(mainActivityDataViewModel.getBoardColour());

        // Setting up all the linear layouts which will act like rows to store the buttons
        LinearLayout[] rows = new LinearLayout[boardSize];
        for (int rowPos = 0; rowPos < boardSize; rowPos++) {
            rows[rowPos] = new LinearLayout(inflater.getContext()); // test this
            rows[rowPos].setId(R.id.boardContainer + rowPos + 1); // check this
            rows[rowPos].setOrientation(LinearLayout.HORIZONTAL);
            rows[rowPos].setBackgroundColor(getActivity().getColor(R.color.white));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1.0f
            );

            rows[rowPos].setLayoutParams(params);
            boardContainer.addView(rows[rowPos]);
        }


        // Create all the buttons for the game
        ticTacToeButtons = new ImageButton[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                ticTacToeButtons[row][col] = new ImageButton(getActivity());
                ticTacToeButtons[row][col].setBackgroundResource(getPlayerMarker(boardLogic.getPiece(row, col),mainActivityDataViewModel));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1.0f
                );

                int rowPos = row; // variables need to be final in onclicklistener
                int colPos = col;
                ticTacToeButtons[row][col].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        placeMarker(playView, ticTacToeButtons[rowPos][colPos], rowPos, colPos, mainActivityDataViewModel);
                    }
                });
                ticTacToeButtons[row][col].setLayoutParams(params);
                rows[row].addView(ticTacToeButtons[row][col]);

            }
        }



        // Button Logic
        Button backToHomePage = playView.findViewById(R.id.back);
        backToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mainActivityDataViewModel.setDisplayScreen("Home");}
        });

        Button settingsButton = playView.findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mainActivityDataViewModel.setDisplayScreen("Settings");}
        });

        Button statisticsButton = playView.findViewById(R.id.statsButton);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mainActivityDataViewModel.setDisplayScreen("Statistics");}
        });

        Button clearBoard = playView.findViewById(R.id.clearBoard);
        clearBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBoard();
            }
        });


        // Set the player icons
        ImageView player1Icon = playView.findViewById(R.id.Player1Icon);
        player1Icon.setImageResource(mainActivityDataViewModel.getP1Icon());

        ImageView player2Icon = playView.findViewById(R.id.Player2Icon);
        player2Icon.setImageResource(mainActivityDataViewModel.getP2Icon());

        // Set the player names
        TextView player1Name = playView.findViewById(R.id.Player1Name);
        player1Name.setText(mainActivityDataViewModel.getP1Name());

        TextView player2Name = playView.findViewById(R.id.Player2Name);
        player2Name.setText(mainActivityDataViewModel.getP2Name());



        return playView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("saving", "true");
        super.onSaveInstanceState(outState);
        outState.putString("reapplyBoard", "yes"); // this is so the app know to reapply the board
        outState.putInt("boardSize", boardSize);
        outState.putInt("playersTurn", boardSize);


        int[][] boardInfo = boardLogic.getBoard();
        for (int counter = 0; counter < boardSize; counter++) {
            outState.putIntArray("row" + counter, boardInfo[counter]);
        }
    }


    public void placeMarker(View playView, ImageButton button, int row, int col, MainActivityData activityData) {
        int result = boardLogic.placePiece(row, col, playersTurn);

        if (result == 1) {
            // change button image
            ticTacToeButtons[row][col].setBackgroundResource(getPlayerMarker(playersTurn, activityData));
            switchPlayerTurn();
        } else if (result == 3) {
            ticTacToeButtons[row][col].setBackgroundResource(getPlayerMarker(playersTurn, activityData));
            if (getPlayerTurn() == 1) {
                activityData.addP1Win();
            } else {
                activityData.addP2Win();
            }
            TextView score = playView.findViewById(R.id.score);
            String newScore = activityData.getP1wins() + " : " + activityData.getP2wins();
            score.setText(newScore);
            switchPlayerTurn();
        }

        if (AI && result != 2) // bug occurs if the buttons is already hit
        {
            int[] AIPos = boardLogic.getRandomFreeSpot();
            if(AIPos != null) // no free spots
            {
                result = boardLogic.placePiece(AIPos[0], AIPos[1], playersTurn);
                if (result == 1) {
                    // change button image
                    ticTacToeButtons[AIPos[0]][AIPos[1]].setBackgroundResource(getPlayerMarker(playersTurn, activityData));
                    switchPlayerTurn();
                } else if (result == 3) {
                    ticTacToeButtons[AIPos[0]][AIPos[1]].setBackgroundResource(getPlayerMarker(playersTurn, activityData));
                    if (getPlayerTurn() == 1) {
                        activityData.addP1Win();
                    } else {
                        activityData.addP2Win();
                    }
                    switchPlayerTurn();
                TextView score = playView.findViewById(R.id.score);
                String newScore = activityData.getP1wins() + " : " + activityData.getP2wins();
                score.setText(newScore);
                }
            }
            else
            {
                Log.d("No free", "spots");
            }
        }
    }

    private void switchPlayerTurn() {
        if (playersTurn == 1) {
            playersTurn = 2;
        } else {
            playersTurn = 1;
        }
    }

    private Integer getPlayerTurn() // slightly redundant though it makes the value final
    {
        return playersTurn;
    }

    private void clearBoard() // TODO
    {
        boardLogic.clearBoard();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                ticTacToeButtons[row][col].setBackgroundResource(R.drawable.square);
            }
        }
        playersTurn = 1;
    }

    private int getPlayerMarker(int player, MainActivityData activityData) // having arguement makes it more portable
    {
        int playerIcon = 0; // 0 is no image
        if (player == 1) {
            playerIcon = activityData.getP1Marker();
        } else if (player == 2) {
            playerIcon = activityData.getP2Marker();
        } else {
            playerIcon = R.drawable.square;
        }
        return playerIcon;
    }
}