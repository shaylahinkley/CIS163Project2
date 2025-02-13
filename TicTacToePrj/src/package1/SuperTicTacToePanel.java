package package1;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/***********************************************************************************************************************
 * CIS 162 Project 2
 * GUI interface for the game
 *
 * @author Keilani Bailey and Shayla Hinkley
 * @version Project 2: October 2nd, 2019
 **********************************************************************************************************************/

public class SuperTicTacToePanel extends JPanel{

    /**instance variable board of type JButton that makes every box on the board a button */
    private JButton[][] board;

    /**instance variable iBoard of type Cell that creates the physical cells on the board */
    private Cell[][] iBoard;

    /**instance variable xIcon of type ImageIcon creates the x symbol for the board turn*/
    private ImageIcon xIcon;

    /**instance variable oIcon of type ImageIcon creates the o symbol for the board turn */
    private ImageIcon oIcon;

    /**instance variable emptyIcon of type ImagineIcon that creates the empty box for the board turn */
    private ImageIcon emptyIcon;

    /**instance variable object of class SuperTicTacToeGame */
    private SuperTicTacToeGame game;

    /**instance variable of type int for the size of the board*/
    private int boardSize;

    /**private JButton for the undo*/
    private JButton undoBtn;

    /*******************************************************************************************************************
     *Constructor that sets the size of the board, sets up
     *
     *******************************************************************************************************************/
    public SuperTicTacToePanel() {

        int size = 0;

        //checking if format is an integer
        try {
            size = Integer.parseInt(JOptionPane.showInputDialog("Enter board size"));
            while (size < 3 || size > 15) {
                try {
                    throw new IllegalArgumentException("Input for board size not valid");

                } catch (IllegalArgumentException te) {
                    JOptionPane.showMessageDialog(null, "Input for board size not valid. Must be 3-15.", "Alert", JOptionPane.ERROR_MESSAGE);
                } finally {

                    //always executing allowing for user to try entering in new size
                    size = Integer.parseInt(JOptionPane.showInputDialog("Enter board size"));
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Input an integer only between 3-15.", "Alert", JOptionPane.ERROR_MESSAGE);
            size = Integer.parseInt(JOptionPane.showInputDialog("Enter board size"));
        }

        //sets the valid game board size to boardSize
        game = new SuperTicTacToeGame(size);
        boardSize = size;

        //changes String into integer
        int numCon = Integer.parseInt(JOptionPane.showInputDialog("Enter number of connections"));
        game.setConnections(numCon);

        //checks if the number of connections is valid and allows for user to enter new connection number if invalid
        while (numCon < 0 || boardSize == 3 && numCon != 3 || boardSize > 3 && numCon <= 3 || numCon > boardSize) {

            //throws errors
            try {
                throw new IllegalArgumentException();

                //can change the size of the board
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input integer only.", "Alert", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException te) {
                JOptionPane.showMessageDialog(null, "Input invalid.", "Alert", JOptionPane.ERROR_MESSAGE);
            } finally {

                //always executes and allows user to try again
                numCon = Integer.parseInt(JOptionPane.showInputDialog("Enter number of connection"));
                game.setConnections(numCon);
            }
        }

        //creating string
        String first = JOptionPane.showInputDialog("Who goes first? X or O");
        if (first.equals("X")) {
            game.setTurnX();
        } else if (first.equals("O")) {
            game.setTurnO();
        } else {
            JOptionPane.showMessageDialog(null, "Input invalid. Default X used.", "Alert", JOptionPane.ERROR_MESSAGE);
            game.setTurnX();
        }

        //create Image Icons
        xIcon = new ImageIcon ("./src/x.jpg");
        oIcon = new ImageIcon ("./src/o.jpg");
        emptyIcon = new ImageIcon ("./src/empty.jpg");

        //F19Project2
        //creates JPanel
        JPanel bottom = new JPanel();
        JPanel center = new JPanel();

        // create game, listeners
        ButtonListener listener = new ButtonListener();

        undoBtn = new JButton("Undo");
        undoBtn.addActionListener(listener);
        add(undoBtn, BorderLayout.SOUTH);

        //sets the layout of the game board
        //check if it works when it resize board game.getSize()
        center.setLayout(new GridLayout(game.getSize(),game.getSize(),3,2));

        //sets the dimension of each individual box on the board
        Dimension temp = new Dimension(60,60);
        board = new JButton[game.getSize()][game.getSize()];

        //establishes the size of board
        //goes through the rows
        for (int row = 0; row < game.getSize(); row++) {

            //goes through the columns
            for (int col = 0; col < game.getSize(); col++) {

                Border thickBorder = new LineBorder(Color.blue, 2);

                //sets JButtons of each square to emptyIcon
                board[row][col] = new JButton("", emptyIcon);
                board[row][col].setPreferredSize(temp);
                board[row][col].setBorder(thickBorder);

                //adds action listeners to each box on the grid
                board[row][col].addActionListener(listener);
                center.add(board[row][col]);
            }
        }
        displayBoard();

        // add all to contentPane
        add (new JLabel("!!!!!!  Super TicTacToe  !!!!"), BorderLayout.NORTH);
        add (center, BorderLayout.CENTER);
        add (bottom, BorderLayout.SOUTH);

    }

    /*******************************************************************************************************************
     *Method that puts the X and O's in the cells depending on what player turn it is
     *
     *******************************************************************************************************************/
    private void displayBoard() {
        iBoard = game.getBoard();

        for (int r = 0; r < game.getSize(); r++) {
            for (int c = 0; c < game.getSize(); c++) {

                //sets X icon
                board[r][c].setIcon(emptyIcon);
                if (iBoard[r][c] == Cell.O) {
                    board[r][c].setIcon(oIcon);
                }

                //sets O icon
                if (iBoard[r][c] == Cell.X) {
                    board[r][c].setIcon(xIcon);
                }
            }
        }
    }

    /*******************************************************************************************************************
     *ButtonListener class that implements ActionListener
     * Displays message if game is won by player and resets the game
     *
     *******************************************************************************************************************/
    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for (int r = 0; r < game.getSize(); r++) {
                for (int c = 0; c < game.getSize(); c++) {
                    if (board[r][c] == e.getSource()) {
                        game.select(r, c);
                    }
                }
            }
            displayBoard();

            if(undoBtn == e.getSource()) {
                game.undo();
                displayBoard();
                return;
            }

            //if X won, resets the board and displays it
            if (game.getGameStatus() == GameStatus.X_WON) {
                JOptionPane.showMessageDialog(null, "X won and O lost!\n The game will reset");
                game.reset();
                displayBoard();
            }

            //if O, resets the board and displays it
            if (game.getGameStatus() == GameStatus.O_WON) {
                JOptionPane.showMessageDialog(null, "O won and X lost!\n The game will reset");
                game.reset();
                displayBoard();
            }

            //if it is a cats game, resets the board and displays it
            if (game.getGameStatus() == GameStatus.CATS) {
                JOptionPane.showMessageDialog(null, "Draw!\n The game will reset");
                game.reset();
                displayBoard();
            }
        }
    }
}
