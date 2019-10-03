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

    /*******************************************************************************************************************
     *Constructor that sets the size of the board, sets up
     *
     *******************************************************************************************************************/
    public SuperTicTacToePanel() {

        //can change the size of the board
        game = new SuperTicTacToeGame(5);

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
     *
     *
     *******************************************************************************************************************/
    private void displayBoard() {
        iBoard = game.getBoard();

        for (int r = 0; r < game.getSize(); r++) {
            for (int c = 0; c < game.getSize(); c++) {

                board[r][c].setIcon(emptyIcon);
                if (iBoard[r][c] == Cell.O)
                    board[r][c].setIcon(oIcon);

                if (iBoard[r][c] == Cell.X)
                    board[r][c].setIcon(xIcon);
            }
        }
    }
    /*******************************************************************************************************************
     *
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

            if (game.getGameStatus() == GameStatus.X_WON) {
                JOptionPane.showMessageDialog(null, "X won and O lost!\n The game will reset");
                game.reset();
                displayBoard();
            }
        }
    }
}
