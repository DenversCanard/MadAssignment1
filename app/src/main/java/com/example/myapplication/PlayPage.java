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
import android.widget.TableLayout;
import android.widget.TableRow;
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
    int boardSize;
    TicTacToeLogic boardLogic;
    private ImageButton[][] ticTacToeButtons;
    private int playersTurn;


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
                             Bundle savedInstanceState)
    {

        boolean reapply = false;
        if(savedInstanceState != null)
        {
            boardSize = savedInstanceState.getInt("boardSize");
            playersTurn = savedInstanceState.getInt("playersTurn");
            int[][] loadedBoardInfo = new int[boardSize][boardSize];

            for(String key : savedInstanceState.keySet())
            {
                if(key.contains("row"))
                {
                    Log.d("key", key);
                    int pos = Character.getNumericValue(key.charAt(key.length()-1));
                    Log.d("pos", pos+"");
                    loadedBoardInfo[pos] = savedInstanceState.getIntArray(key);
                }
            }
            boardLogic = new TicTacToeLogic();
            boardLogic.loadBoard(loadedBoardInfo);
            reapply = true;
        }
        else
        {
            boardSize = 6; // this is temporary until we have a way of fetching the board size from settings
            boardLogic = new TicTacToeLogic(boardSize);
            playersTurn = 1;
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_page, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        // Setup player counter

        // create board
        LinearLayout boardContainer = view.findViewById(R.id.boardContainer);
        boardContainer.setBackgroundColor(getActivity().getColor(R.color.black));

        // Setting up all the linear layouts which will act like rows to store the buttons
        LinearLayout[] rows = new LinearLayout[boardSize];
        for(int rowPos = 0; rowPos < boardSize; rowPos++)
        {
            rows[rowPos] = new LinearLayout(inflater.getContext()); // test this
            rows[rowPos].setId(R.id.boardContainer+rowPos+1); // check this
            rows[rowPos].setOrientation(LinearLayout.HORIZONTAL);
            rows[rowPos].setBackgroundColor(getActivity().getColor(R.color.white));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1.0f
            );

            rows[rowPos].setLayoutParams(params);
            boardContainer.addView(rows[rowPos]);

            Log.d("Testing adding new linear layouts", rowPos+"");
        }

        // Create all the buttons for the game
        ticTacToeButtons = new ImageButton[boardSize][boardSize];
        for(int row = 0; row < boardSize; row++)
        {
            for(int col = 0; col < boardSize; col++)
            {
                ticTacToeButtons[row][col] = new ImageButton(getActivity());
                ticTacToeButtons[row][col].setBackgroundResource(R.drawable.square);
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

                        int result = boardLogic.placePiece(rowPos,colPos,playersTurn);

                        if(result == 1)
                        {
                            // change button image
                            ticTacToeButtons[rowPos][colPos].setBackgroundResource(getPlayerIcon(playersTurn));
                            switchPlayerTurn();
                        }
                        else if(result == 3)
                        {
                            ticTacToeButtons[rowPos][colPos].setBackgroundResource(getPlayerIcon(playersTurn));
                            Log.d("Win", "Yes");
                            // play win screen
                            // the player has won!!!
                        }
                    }
                });
                ticTacToeButtons[row][col].setLayoutParams(params);
                rows[row].addView(ticTacToeButtons[row][col]);

            }
        }

        if(reapply) // this makes sure the whole layout is setup properly before reapplying the board
        {
            reapplyBoard(); // TODO not working
        }

        return view;
    }


    @Override
    public void onSaveInstanceState( Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("reapplyBoard", "yes"); // this is so the app know to reapply the board
        outState.putInt("boardSize", boardSize);
        outState.putInt("playersTurn", boardSize);


        int[][] boardInfo = boardLogic.getBoard();
        for(int counter = 0; counter < boardSize; counter++)
        {
            outState.putIntArray("row"+counter, boardInfo[counter]);
        }
    }

    private int getPlayerIcon(int player) // having arguement makes it more portable
    {
        int playerIcon = 0; // 0 is no image
        if(player == 1)
        {
            playerIcon = R.drawable.red_square;
        } else if (player == 2)
        {
            playerIcon = R.drawable.blue_square;
        }
        return playerIcon;
    }

    private void switchPlayerTurn()
    {
        if(playersTurn == 1)
        {
            playersTurn = 2;
        }
        else
        {
            playersTurn = 1;
        }
    }

    private void clearBoard() // TODO
    {

    }

    private void reapplyBoard()
    {
        int[][] boardInfo = boardLogic.getBoard();

        for(int row = 0; row < boardSize; row++)
        {
            for(int col = 0; col < boardSize; col++)
            {
                if(boardInfo[row][col] != 0)
                {
                    ticTacToeButtons[row][col].setBackgroundResource(getPlayerIcon(boardInfo[row][col]));
                }

            }
        }
    }

}