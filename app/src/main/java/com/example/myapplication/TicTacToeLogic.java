package com.example.myapplication;

public class TicTacToeLogic {

    int[][] board;
    int size;

    public TicTacToeLogic()
    {
        board = new int[3][3];
        size = 3;
    }


    public TicTacToeLogic(int size)
    {
        board = new int[size][size];
        this.size = size;
    }

    /// Result Codes
    /// 1 : Place OK
    /// 2 : Place Occupied
    /// 3 : Player has Won
    public int placePiece(int y, int x, int player)  // not done
    {
        if(x <= size-1 && y <= size-1)
        {
            if(board[y][x] == 0)
            {
                board[y][x] = player;

                if(checkWinning(y,x,player) == 1)
                {
                    return 3;
                }
            }
            else
            {
                return 2; // position is occupied
            }
        }
        return 1;
    }

    /// Result Codes
    /// 1 : win
    /// 2 : no win
    private int checkWinning(int y, int x, int player)
    {
        // check row winning
        for(int col_pos = 0; col_pos < size; col_pos++)
        {
            if(board[y][col_pos] != player)// if there is anything other than the player in the row, we assume he cant win. Due to how we have set our win conditions
            {
                break;
            }
            else if(col_pos == size-1)
            {
                System.out.println("row win");
                return 1;
            }
        }

        // check column winning
        for(int row_pos = 0; row_pos < size; row_pos++)
        {
            if(board[row_pos][x] != player)// if there is anything other than the player in the row, we assume he cant win. Due to how we have set our win conditions
            {
                break;
            }
            else if(row_pos == size-1)
            {
                System.out.println("Col win");
                return 1;
            }
        }

        // Both diagonals need testings #TODO

        // could be made easier by just checking all the cardinal diagonals not the positions diagonals
        // check down right winning // dr
        int max_jumps_to_highest_point_dr = Math.min(y,x); // needs testing

        int diagonal_counter_dr = 0;
        int y_pos_dr = y - max_jumps_to_highest_point_dr;
        int x_pos_dr = x - max_jumps_to_highest_point_dr;

        while(y_pos_dr < size && x_pos_dr < size)
        {
            if(board[y_pos_dr][x_pos_dr] == player)
            {
                diagonal_counter_dr++;
            }
            y_pos_dr++;
            x_pos_dr++;
        }

        if(diagonal_counter_dr == size)
        {
            System.out.println("DR win");
            return 1;
        }


        // check down left winning // dl

        int max_jumps_to_highest_point_dl = Math.min(y,size-1-x); // needs testing

        int diagonal_counter_dl = 0;
        int y_pos = y - max_jumps_to_highest_point_dl;
        int x_pos = x + max_jumps_to_highest_point_dl;

        // System.out.println(max_jumps_to_highest_point_dl);
        // System.out.println(y);
        // System.out.println(y_pos);
        // System.out.println(x);
        // System.out.println(x_pos);


        while(y_pos < size && x_pos >= 0)
        {
            if(board[y_pos][x_pos] == player)
            {
                diagonal_counter_dl++;
            }
            x_pos--;
            y_pos++;
        }

        if(diagonal_counter_dl == size)
        {
            System.out.println("DL win");
            return 1;
        }

        return 0;
    }

    public void printBoard()
    {
        for(int i = 0; i < size; i ++)
        {
            for(int j = 0; j < size; j++)
            {
                System.out.print(""+board[i][j]);
            }
            System.out.println("\n");
        }
    }

    public void clearBoard()
    {
        board = new int[size][size];
    }

}
