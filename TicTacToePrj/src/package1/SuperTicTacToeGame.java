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
     *
     * @param row int current row
     * @param col int current col
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

        //goes through all rows
        for (int row = 0; row < size; row++) {

            //goes through all columns
            for (int col = 0; col < size; col++) {

                //set each cell to empty
                board[row][col] = Cell.EMPTY;
            }
        }

        //reset count win to 0
        this.countWin = 0;

        //choose the turn
        turn = (turn == Cell.O) ? Cell.X : Cell.O;

        //change status to in progress
        status = GameStatus.IN_PROGRESS;

    }

    /*******************************************************************************************************************
     *Method that checks the rows for connections
     *
     * @param row int current row
     * @param col int current col
     * @param connections int number of connections
     * @return true if there are connections
     * @return false if there are not connections
     ******************************************************************************************************************/
    private boolean isNRow(int row, int col, int connections) {

        int countX = 0;
        int countY = 0;


        for(int i = 0; i < connections; i++) {

            //checks if the cells in a row counted are less than the size
            if(col + i < size) {

                //if the next one in the row is a X
                if(board[row][col + i] == Cell.X) {
                    countX++;
                    if(countX == connections) {
                        return true;
                    }
                }

                //if the next one in the row is a O
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
     * @param row int current row
     * @param col int current col
     * @param connections int number of connections needed to win
     * @return false if no connections
     * @return true if there are connections
     ******************************************************************************************************************/
    private boolean isNCol(int row, int col, int connections) {

        int countX = 0;
        int countY = 0;

        for(int i = 0; i < connections; i++) {

            //checks if the row is less than the size and that the connections is less than the size
            if(row + i < size) {

                //if the cell is an X
                if(board[row + i][col] == Cell.X) {
                    countX++;
                    if(countX == connections) {
                        return true;
                    }
                }

                //if the cell is an O
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
     *Method that checks the diagonal for connections from top left to bottom right
     *
     * @param row int current row
     * @param col int current col
     * @param connections int number of connections to win
     * @return true if there are connections
     * @return false if there are not connections
     ******************************************************************************************************************/
    private boolean isDiagonal1(int row, int col, int connections) {

        int countX = 0;
        int countY = 0;

        for(int i = 0; i < connections; i++) {
            if(board[row + i][col + i] == Cell.X) {
                countX++;
                if(countX == connections) {
                    return true;
                }
            }
            if(board[row + i][col + i] == Cell.O) {
                countY++;
                if(countY == connections) {
                    return true;
                }
            }
        }
        return false;
    }

    /*******************************************************************************************************************
     *Method that checks the diagonal for connections from bottom left to top right
     *
     * @param row int current row
     * @param col int current col
     * @param connections int number of connections to win
     * @return true if there is a winning connection
     * @return false if there is not a winning connection
     ******************************************************************************************************************/
    private boolean isDiagonal2(int row, int col, int connections) {

        int countX = 0;
        int countY = 0;

        for(int i = 0; i < connections; i++) {
                if(board[row + i][col - i] == Cell.X) {
                    countX++;
                    if(countX == connections) {
                        return true;
                    }
                }
                if(board[row + i][col - i] == Cell.O) {
                    countY++;
                    if(countY == connections) {
                        return true;
                    }
                }
        }
        return false;
    }

    /*******************************************************************************************************************
     *Method that determines if player has won the game
     *
     * @return GameStatus current game status
     ******************************************************************************************************************/
    private GameStatus isWinner() {

        this.numSpaces = 0;

        for(int c = 0; c < size; c++) {
            for(int r = 0; r < size; r++ ) {
                if(isNRow(r,c, this.connections) && board[r][c] == Cell.X) {
                    return GameStatus.X_WON;
                }
                else if(isNRow(r,c, this.connections) && board[r][c] == Cell.O) {
                    return GameStatus.O_WON;
                }
                else if(isNCol(r,c, this.connections) && board[r][c] == Cell.X) {
                    return GameStatus.X_WON;
                }
                else if(isNCol(r,c, this.connections) && board[r][c] == Cell.O) {
                    return GameStatus.O_WON;
                }
                else if(board[r][c] == Cell.EMPTY) {
                    this.numSpaces++;
                }

            }
        }

        //checks for cats game
        if(this.numSpaces == 0) {
            return GameStatus.CATS;
        } else {
            return GameStatus.IN_PROGRESS;
        }
    }

    /*******************************************************************************************************************
     *getter Method for GameStatus
     *
     *
     * @return status GameStatus of the game
     ******************************************************************************************************************/
    public GameStatus getGameStatus() {
        return status;
    }

    /*******************************************************************************************************************
     *getter Method for Board
     *
     *
     * @return board Cell[][]
     ******************************************************************************************************************/
    public Cell[][] getBoard() {
        return board;
    }

    /*******************************************************************************************************************
     *getter Method for size of board
     *
     * @return size int the size of the board
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
     * @param win int the number of wins
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
     * @return row int current row
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
     * @return col int current col
     ******************************************************************************************************************/
    public int getCol() {
        return col;
    }

}
