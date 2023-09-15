package com.example.myapplication;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


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

    // Necessary Timer logic, might be a bit bloated
    private long player1Time;
    private CountDownTimer p1Timer;
    private TextView p1TimeText;

    private long player2Time;
    private CountDownTimer p2Timer;
    private TextView p2TimeText;

    private MutableLiveData<Integer> totalMoves;
    private MutableLiveData<Integer> freeMoves;


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

        // Timer setup // probably retrieve this from the data view model since it will probably be a setting
        player1Time = 30*1000;  // 1000 millisec in a sec
        player2Time = 30*1000;  // 1000 millisec in a sec


        // Saved Instance Logic
        boolean reapply = false;
        if (savedInstanceState != null) {

            // apply old time
            player1Time = savedInstanceState.getLong("p1Time");
            player2Time = savedInstanceState.getLong("p2Time");

            boardSize = savedInstanceState.getInt("boardSize");
            playersTurn = savedInstanceState.getInt("playersTurn");
            Log.d("playersTurnLoad", playersTurn+"");
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

        // Make Win message Invisible
        TextView winMessage = playView.findViewById(R.id.winMessage);
        winMessage.setVisibility(View.GONE);


        // Initial Timer set up for current Player
        p1TimeText = playView.findViewById(R.id.Player1Timer);
        String p1FormattedTime = Float.toString(player1Time / 1000).replace(".", ":");
        p1TimeText.setText(p1FormattedTime);
        p2TimeText = playView.findViewById(R.id.Player2Timer);
        String p2FormattedTime = Float.toString(player2Time / 1000).replace(".", ":");
        p2TimeText.setText(p2FormattedTime);
        setUpInitialTimer();

        // Set up the win counters // important for screen resets
        TextView score = playView.findViewById(R.id.score);
        String newScore = mainActivityDataViewModel.getP1wins() + " : " + mainActivityDataViewModel.getP2wins();
        score.setText(newScore);

        // Check board info (total/free)
        totalMoves = new MediatorLiveData<Integer>();
        totalMoves.setValue(boardLogic.getTotalPlacedPieces());
         totalMoves.observe(getViewLifecycleOwner(), new Observer<Integer>() {
             @Override
             public void onChanged(Integer integer) {
                 TextView totalMovesText = playView.findViewById(R.id.totalMoves);
                 totalMovesText.setText("T : "+totalMoves.getValue());
             }
         });

        freeMoves = new MediatorLiveData<Integer>();
        freeMoves.setValue(boardLogic.getFreeSpotAmount());
        freeMoves.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                TextView freeMovesText = playView.findViewById(R.id.freeMoves);
                freeMovesText.setText("F : "+freeMoves.getValue());
            }
        });

        return playView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("saving", "true");
        super.onSaveInstanceState(outState);

        Log.d("playersTurnStartSave", playersTurn+"");
        outState.putInt("playersTurn", playersTurn);

        // save board states
        outState.putString("reapplyBoard", "yes"); // this is so the app know to reapply the board
        outState.putInt("boardSize", boardSize);

        // save timers
        outState.putLong("p1Time", player1Time);
        outState.putLong("p2Time", player2Time);


        // remember to save the timer here if used
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
            TextView winMessage = playView.findViewById(R.id.winMessage);
            String message = "Player "+playersTurn+" Wins";
            winMessage.setVisibility(View.VISIBLE);
            winMessage.setText(message);

            TextView score = playView.findViewById(R.id.score);
            String newScore = activityData.getP1wins() + " : " + activityData.getP2wins();
            score.setText(newScore);
            switchPlayerTurn();
        }
        else if (result == 4) {
            ticTacToeButtons[row][col].setBackgroundResource(getPlayerMarker(playersTurn, activityData));
            activityData.addDraw();
            switchPlayerTurn();
            TextView winMessage = playView.findViewById(R.id.winMessage);
            String message = "Its a Draw!";
            winMessage.setVisibility(View.VISIBLE);
            winMessage.setText(message);
        }

        if (AI && result != 2) // bug occurs if the buttons is already hit,  this prevents it
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
                else if (result == 4)
                {
                    switchPlayerTurn();
                    TextView winMessage = playView.findViewById(R.id.winMessage);
                    String message = "Its a Draw!";
                    winMessage.setVisibility(View.VISIBLE);
                    winMessage.setText(message);
                }
            }
            else
            {
                Log.d("No free", "spots");
            }
        }
        updateTurnCounters();
    }

    private void switchPlayerTurn() {
        if (playersTurn == 1) {
            playersTurn = 2;

            // Timer Logic here
            p1Timer.cancel(); // cancel the p1Timer

            p2Timer = new CountDownTimer(player2Time, 1000) {
                @Override
                public void onTick(long l) {
                    String formattedTime = Float.toString(player2Time / 1000).replace(".", ":");
                    p2TimeText.setText(formattedTime);
                    player2Time = player2Time-1000;
                }

                @Override
                public void onFinish() {
                    p2TimeText.setText("NO MORE TIME");
                }
            }.start();
        } else {
            playersTurn = 1;

            // Timer Logic here
            p2Timer.cancel(); // cancel the p1Timer
            p1Timer = new CountDownTimer(player1Time, 1000) {
                @Override
                public void onTick(long l) {
                    String formattedTime = Float.toString(player1Time / 1000).replace(".", ":");
                    p1TimeText.setText(formattedTime);
                    player1Time = player1Time-1000;
                }

                @Override
                public void onFinish() {
                    p1TimeText.setText("NO MORE TIME");
                }
            }.start();
        }
        Log.d("playersTurn", playersTurn+"");

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
        // clear the win text if it is present
        TextView winMessage = getActivity().findViewById(R.id.winMessage);
        winMessage.setVisibility(View.GONE);
        // update the turn counters to be reset
        updateTurnCounters();
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

    public void setUpInitialTimer()
    {
        if(playersTurn == 1)
        {
            // Timer Logic here
            p1Timer = new CountDownTimer(player1Time, 1000) {
                @Override
                public void onTick(long l) {
                    String formattedTime = Float.toString(player1Time / 1000).replace(".", ":");
                    p1TimeText.setText(formattedTime);
                    player1Time = player1Time-1000;
                }

                @Override
                public void onFinish() {
                    p1TimeText.setText("NO MORE TIME");
                }
            }.start();
        } else
        {
            p2Timer = new CountDownTimer(player2Time, 1000) {
                @Override
                public void onTick(long l) {
                    String formattedTime = Float.toString(player2Time / 1000).replace(".", ":");
                    p2TimeText.setText(formattedTime);
                    player2Time = player2Time-1000;
                }

                @Override
                public void onFinish() {
                    p2TimeText.setText("NO MORE TIME");
                }
            }.start();
        }
    }



    private void updateTurnCounters()
    {
        if(totalMoves.getValue() != boardLogic.getTotalPlacedPieces())
        {
            totalMoves.setValue(boardLogic.getTotalPlacedPieces());
        }

        if(freeMoves.getValue() != boardLogic.getFreeSpotAmount())
        {
            freeMoves.setValue(boardLogic.getFreeSpotAmount());
        }
    }
}