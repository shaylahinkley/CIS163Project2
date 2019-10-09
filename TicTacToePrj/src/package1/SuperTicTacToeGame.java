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

    /**Instance variable of type int that is used for the number of connections to win*/
    private int connections;

    /**instance variable size of type int used to set the size of the board */
    private int size;

    /**instance variable win of type int to indicate the number of connections used to win*/
    private int win;

    /**instance variable for the amount of spaces taken up on the board */
    private int numSpaces;

    private int row;

    private int col;

    /** */
    private int countWin;

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
        this.row = row;
        this.col = col;

        if (board[row][col] != Cell.EMPTY) {
            return;
        }

        board[row][col] = turn;

        //if it is turn O, then switch to turn X, otherwise Turn O
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
        this.countWin = 0;
        turn = (turn == Cell.O) ? Cell.X : Cell.O;
        status = GameStatus.IN_PROGRESS;

    }

    /*******************************************************************************************************************
     *Method that checks the columns for connections
     *
     ******************************************************************************************************************/
//    private boolean isNCol(int row, int col, int connections) {
//
//        if(col + connections  < size) {
//            for(int r = 0; r < size; r++) {
//                if (board[row][col] == Cell.X) {
//                    return true;
//                }
//
//                if (board[row][col] == Cell.O) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    /*******************************************************************************************************************
     *Method that checks the rows for connections
     *
     ******************************************************************************************************************/
    private boolean isNRow(int row, int col, int connections) {

        int countX = 0;
        int countY = 0;

        for(int i = 0; i < connections; i++) {
            if(col + i < size) {
                if(board[row][col + i] == Cell.X) {
                    countX++;
                    if(countX == connections) {
                        return true;
                    }
                }
                if(board[row][col+i] == Cell.O) {
                    countY++;
                    if(countY == connections) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /*******************************************************************************************************************
     *Method that checks the columns for connections
     *
     ******************************************************************************************************************/
    private boolean isNCol(int row, int col, int connections) {

        int countX = 0;
        int countY = 0;

        for(int i = 0; i < connections; i++) {
            if(row + i < size) {
                if(board[row + i][col] == Cell.X) {
                    countX++;
                    if(countX == connections) {
                        return true;
                    }
                }
                if(board[row + i][col] == Cell.O) {
                    countY++;
                    if(countY == connections) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*******************************************************************************************************************
     *Method that determines if player has won the game
     *
     ******************************************************************************************************************/
    private GameStatus isWinner() {


        for(int c = 0; c < size; c++) {
            for(int r = 0; r < size; r++ ) {
                if(isNRow(r,c, this.connections) && board[r][c] == Cell.X) {
                    return GameStatus.X_WON;
                }
                if(isNRow(r,c, this.connections) && board[r][c] == Cell.O) {
                    return GameStatus.O_WON;
                }
                if(isNCol(r,c, this.connections) && board[r][c] == Cell.X) {
                    return GameStatus.X_WON;
                }
                if(isNCol(r,c, this.connections) && board[r][c] == Cell.O) {
                    return GameStatus.O_WON;
                }

            }
        }
        return GameStatus.CATS;



//        //number of empty cells on the board
//        this.numSpaces = 0;
//        for (int r = 0; r < size; r++) {
//            for (int c = 0; c < size; c++) {
//
//                //if cell is not empty check connection
//                if (board[r][c] == Cell.X || board[r][c] == Cell.O) {
//
//                    //temporary value being checked for connection
//                    int tCol = c;
//
//                    int i = 0;
//
//                    //checks for connections with the length of user input
//                    while (board[r][tCol] == board[r][c] && i <= this.connections) {
//
//                        //if you read the end of the board you go back to the beginning
//                        i++;
//                        if (tCol == (size - 1)) {
//                            tCol = 0;
//                        }
//
//                        //if you do not reach the bottom then you move down a column
//                        else {
//                            tCol++;
//                        }
//
//                        //if the connection criteria is met, then retrieve who the winner is
//                        if (i == this.connections) {
//                            if (board[r][c] == Cell.X) {
//                                return GameStatus.X_WON;
//                            } else if (board[r][c] == Cell.O) {
//                                return GameStatus.O_WON;
//                            }
//                        }
//                    }
//
//                    //temporary row that is checked or connection
//                    int tRow = r;
//
//                    int j = 0;
//
//                    //checks all the rows for connections of user input length
//                    while (board[tRow][c] == board[r][c] && j <= this.connections) {
//                        j++;
//
//                        //if you read the end of the board go back to the beginning
//                        if (tRow == (size - 1)) {
//                            tRow = 0;
//                        }
//
//                        //if you do not reach the bottom of the board move down a row
//                        else {
//                            tRow++;
//                        }
//
//                        //if win conditions are met retrieve the user winner
//                        if (j == this.connections) {
//                            if (board[r][c] == Cell.X) {
//                                return GameStatus.X_WON;
//                            } else if (board[r][c] == Cell.O) {
//                                return GameStatus.O_WON;
//                            }
//                        }
//                    }
//
//                    int x = 0;
//                    while(board[tRow][tCol] == board[r][c] && x <= this.connections) {
//                        x++;
//
//
//                    }
//
//                    //checks if cells are empty
//                } else if (board[r][c] == Cell.EMPTY) {
//                    this.numSpaces++;
//                }
//
//                //error if null is thrown
//                else if (board[r][c] == null) {
//                    throw new NullPointerException();
//                } else {
//                    throw new NullPointerException();
//                }
//
//            }
//        }
//
//        //if there are no empty cells then there is a cats game
//        if(this.numSpaces == 0) {
//            return GameStatus.CATS;
//        }
//
//        //game is still in progress
//        return GameStatus.IN_PROGRESS;
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

    /*******************************************************************************************************************
     *Method that sets the number of connections needed
     *
     * @param connections
     ******************************************************************************************************************/
    public void setConnections(int connections) {
        this.connections = connections;
    }

    /*******************************************************************************************************************
     *Method that sets the number of connections needed
     *
     * @param row
     ******************************************************************************************************************/
    public void setRow(int row) {
        this.row = row;
    }

    /*******************************************************************************************************************
     *Method that sets the number of connections needed
     *
     *
     ******************************************************************************************************************/
    public int getRow() {
        return row;
    }


    /*******************************************************************************************************************
     *Method that sets the number of connections needed
     *
     * @param col
     ******************************************************************************************************************/
    public void setCol(int col) {
        this.col= col;
    }

    /*******************************************************************************************************************
     *Method that sets the number of connections needed
     *
     *
     ******************************************************************************************************************/
    public int getCol() {
        return col;
    }

}
