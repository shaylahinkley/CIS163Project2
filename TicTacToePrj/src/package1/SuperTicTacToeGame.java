package package1;

public class SuperTicTacToeGame {
    private Cell[][] board;
    private GameStatus status;
    private int size;
    private int win;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public SuperTicTacToeGame(int size) {
        this.size = size;
        status = GameStatus.IN_PROGRESS;
        board = new Cell[size][size];

        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                board[row][col] = Cell.EMPTY;
    }

    public void select(int row, int col) {

    }

    public void reset() {
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                board[row][col] = Cell.EMPTY;

    }

    public GameStatus getGameStatus() {
        return null;
    }

    public Cell[][] getBoard() {
        return board;
    }
}
//=======
//import java.awt.*;
//import java.util.ArrayList;
//
//public class SuperTicTacToeGame {
//    private Cell[][] board;
//    private GameStatus status;
//    private Cell turn;
//    private int connections;
//
//    private static final boolean AI = true;
//
//    private ArrayList<Point> backup = new ArrayList<Point>();
//
//    public SuperTicTacToeGame() {
//        status = GameStatus.IN_PROGRESS;
//        board = new Cell[3][3];
//        reset();
//    }
//
//    public Cell[][] getBoard() {
//        return board;
//    }
//
//    public void select(int row, int col) {
//        if (board[row][col] != Cell.EMPTY)
//            return;
//
//        board[row][col] = turn;
//
//        turn = (turn == Cell.O) ? Cell.X : Cell.O;
//        status = isWinner();
//    }
//
//    private GameStatus isWinner() {
//        for (int r = 0; r < 3; r++)
//            for (int c = 0; c < 3 - 2; c++)
//                if (board[r][c] == Cell.X &&
//                        board[r][c+1] == Cell.X &&
//                        board[r][c+2] == Cell.X)
//                    return GameStatus.X_WON;
//
//        return GameStatus.IN_PROGRESS;
//    }
//
//    public GameStatus getGameStatus() {
//        return status;
//    }
//
//    public void reset() {
//        for (int r = 0; r < 3; r++)
//            for (int c = 0; c < 3; c++)
//                board[r][c] = Cell.EMPTY;
//
//        turn = Cell.X;
//    }
//}
