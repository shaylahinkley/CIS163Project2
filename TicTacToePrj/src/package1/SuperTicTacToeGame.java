package package1;

import java.awt.*;
import java.util.ArrayList;
/***********************************************************************************************************************
 * CIS 162 Project 2
 * Setting up the game board
 *
 * @author Keilani Bailey and Shayla Hinkley
 * @version Project 2: October 2nd, 2019
 **********************************************************************************************************************/

public class SuperTicTacToeGame {

    /**Instance variable board of type cell*/
    private Cell[][] board;

    /**Instance variable status of type GameStatus that sets the status of the game */
    private GameStatus status;


    /**Instance variable turn of type cell that sets what player turn it is */
    private Cell turn;

    /** */
    private int connections;

    /**instance variable size of type int used to set the size of the board */
    private int size;

    /**instance variable win of type int used to indicate a win has occurred */
    private int win;

    /**instance variable for the amount of spaces taken up on the board */
    private int numSpaces;
    /** */
    private static final boolean AI = true;

    /** */
    private ArrayList<Point> backup = new ArrayList<Point>();

    /*******************************************************************************************************************
    *Constructor that sets the size of the board
    *
    * @param size
    *******************************************************************************************************************/

    public SuperTicTacToeGame(int size) {
        this.size = size;
        status = GameStatus.IN_PROGRESS;
        board = new Cell[size][size];
        turn = Cell.X;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = Cell.EMPTY;
            }
        }
    }

    /*******************************************************************************************************************
    *Constructor that creates a new board, resets, and changes game status
    *
    *******************************************************************************************************************/

    public SuperTicTacToeGame() {
        status = GameStatus.IN_PROGRESS;
        board = new Cell[size][size];
        reset();

    }

    /*******************************************************************************************************************
    *Method  that selects a box on the board to place player turn
    *Checks if turn was a winning turn
    *******************************************************************************************************************/

    public void select(int row, int col) {
        if (board[row][col] != Cell.EMPTY) {
            return;
        }

        board[row][col] = turn;

        turn = (turn == Cell.O) ? Cell.X : Cell.O;
        status = isWinner();
    }

   /********************************************************************************************************************
    *Method that resets the size of the board and clears players turns
    *
    *******************************************************************************************************************/

    public void reset() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = Cell.EMPTY;
            }
        }
            turn = Cell.X;
    }

    /*******************************************************************************************************************
     *Method that determines if player has won the game
     *
     ******************************************************************************************************************/
     private GameStatus isWinner() {

         //checking for vertical rows
         for(int c = 0; c < size; c++) {
             for (int r = 0; r < (size - 2); r++) {
                 if (board[r][c] == Cell.X) {
                     if (board[r + 1][c] == Cell.X) {
                         if (board[r + 2][c] == Cell.X) {
                             return GameStatus.X_WON;
                         }
                     }
                 }
                 if (board[r][c] == Cell.O) {
                     if (board[r + 1][c] == Cell.O) {
                         if (board[r + 2][c] == Cell.O) {
                             return GameStatus.O_WON;
                         }
                     }
                 }
             }
         }


         //checking for horizontal rows
         for(int r = 0; r < size; r++) {
             for(int c = 0; c < (size -2); c++) {
                 if(board[r][c] == Cell.X) {
                     if(board[r][c+1] == Cell.X) {
                         if(board[r][c+2] == Cell.X) {
                             return GameStatus.X_WON;
                         }
                     }
                 }
                 if((board[r][c] == Cell.O)) {
                     if(board[r][c+1] == Cell.O) {
                         if(board[r][c+2] == Cell.O) {
                             return GameStatus.O_WON;
                         }
                     }
                 }
             }
         }

         //checking a diagonal from top left to bottom right
         for(int r = 0; r < (size - 2); r++) {
             for(int c = 0; c < (size - 2); c++){
                 if(board[r][c] == Cell.X) {
                     if(board[r+1][c+1] == Cell.X) {
                         if(board[r+2][c+2] == Cell.X) {
                             return GameStatus.X_WON;
                         }
                     }
                 }
                 if(board[r][c] == Cell.O) {
                     if(board[r+1][c+1] == Cell.O) {
                         if(board[r+2][c+2] == Cell.O) {
                             return GameStatus.O_WON;
                         }
                     }
                 }
             }
         }

         //checking from top right corner to the bottom left corner
         for(int r = 2; r < size; r++) {
             for(int c = 0; c < (size - 2); c++) {
                 if(board[r][c] == Cell.X) {
                     if(board[r-1][c+1] == Cell.X) {
                         if(board[r-2][c+2] == Cell.X) {
                             return GameStatus.X_WON;
                         }
                     }
                 }
                 if(board[r][c] == Cell.O) {
                     if(board[r-1][c+1] == Cell.O) {
                         if(board[r-2][c+2] == Cell.O) {
                             return GameStatus.O_WON;
                         }
                     }
                 }
             }
         }

         //checking to see how many cells are taken up on the board
         for(int r = 0; r < size; r++) {
             for(int c = 0; c < size; c++) {
                 if(board[r][c] == Cell.O || board[r][c] == Cell.X) {
                     numSpaces++;
                 }
             }
         }

         //deciding cats game
         if((size * size) <= numSpaces) {
             return GameStatus.CATS;
         }
         else {
             numSpaces = 0;
             return GameStatus.IN_PROGRESS;
         }
     }

    /*******************************************************************************************************************
     *getter Method for GameStatus
     *
     ******************************************************************************************************************/
     public GameStatus getGameStatus() {
        return status;
     }

    /*******************************************************************************************************************
     *getter Method for Board
     *
     ******************************************************************************************************************/
     public Cell[][] getBoard() {
        return board;
     }

    /*******************************************************************************************************************
     *getter Method for size of board
     *
     ******************************************************************************************************************/
     public int getSize() {
        return size;
     }


    /*******************************************************************************************************************
     *set Method for size of board
     *
     ******************************************************************************************************************/
     public void setSize(int size) {
        this.size = size;
     }

    /*******************************************************************************************************************
     *getter method for Win
     *
     ******************************************************************************************************************/
     public int getWin() {
        return win;
     }

    /*******************************************************************************************************************
     *setter Method for win. Sets instance variable to instance variable
     *
     ******************************************************************************************************************/
     public void setWin(int win) {
        this.win = win;
     }

    /*******************************************************************************************************************
     *Method that sets the turn to X player
     *
     ******************************************************************************************************************/
     public void setTurnX() {
         turn = Cell.X;
     }

    /*******************************************************************************************************************
     *Method that sets the turn to O player
     *
     ******************************************************************************************************************/
     public void setTurnO() {
         turn = Cell.O;
     }


}
