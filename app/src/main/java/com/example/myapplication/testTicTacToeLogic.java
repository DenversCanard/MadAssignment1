public class testTicTacToeLogic {

    public static void main(String[] args)
    {
        // test 3x3
        System.out.println("Testing board size of 3x3");
        TicTacToeLogic logic3 = new TicTacToeLogic(3);
        logic3.printBoard();

        // column win
        logic3.placePiece(0, 0, 1);
        logic3.placePiece(1, 0, 1);
        logic3.placePiece(2, 0, 1);
        logic3.printBoard();

        // row win
        logic3.clearBoard();
        logic3.placePiece(0, 0, 1);
        logic3.placePiece(0, 1, 1);
        logic3.placePiece(0, 2, 1);
        logic3.printBoard();

        // down right win
        logic3.clearBoard();
        logic3.placePiece(0, 0, 1);
        logic3.placePiece(1, 1, 1);
        logic3.placePiece(2, 2, 1);
        logic3.printBoard();

        // down left win
        logic3.clearBoard();
        logic3.placePiece(2, 0, 1);
        logic3.placePiece(1, 1, 1);
        logic3.placePiece(0, 2, 1);
        logic3.printBoard();

        // test 4x4
        System.out.println("Testing board size of 4x4");
        TicTacToeLogic logic4 = new TicTacToeLogic(4);
        logic4.printBoard();

        // test 5x5
        System.out.println("Testing board size of 5x5");
        TicTacToeLogic logic5 = new TicTacToeLogic(5);
        logic5.printBoard();

        System.out.println("done");


    }
}
