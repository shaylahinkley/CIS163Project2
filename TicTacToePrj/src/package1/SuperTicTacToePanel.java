package package1;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SuperTicTacToePanel extends JPanel{
    private JButton[][] board;
    private Cell[][] iBoard;

    private ImageIcon xIcon;
    private ImageIcon oIcon;
    private ImageIcon emptyIcon;

    private SuperTicTacToeGame game;

    public SuperTicTacToePanel() {
        game = new SuperTicTacToeGame(4);

        xIcon = new ImageIcon ("./src/F19Project2/x.jpg");
        oIcon = new ImageIcon ("./src/F19Project2/o.jpg");
        emptyIcon = new ImageIcon ("./src/F19Project2/empty.jpg");

        JPanel bottom = new JPanel();
        JPanel center = new JPanel();

        // create game, listeners
        ButtonListener listener = new ButtonListener();

        center.setLayout(new GridLayout(3,3,3,2));
        Dimension temp = new Dimension(60,60);
        board = new JButton[3][3];

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {

                Border thickBorder = new LineBorder(Color.blue, 2);

                board[row][col] = new JButton ("", emptyIcon);
                board[row][col].setPreferredSize(temp);
                board[row][col].setBorder(thickBorder);

                board[row][col].addActionListener(listener);
                center.add(board[row][col]);
            }

        displayBoard();

        // add all to contentPane
        add (new JLabel("!!!!!!  Super TicTacToe  !!!!"), BorderLayout.NORTH);
        add (center, BorderLayout.CENTER);
        add (bottom, BorderLayout.SOUTH);


    }

    private void displayBoard() {
        iBoard = game.getBoard();

        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++) {

                board[r][c].setIcon(emptyIcon);
                if (iBoard[r][c] == Cell.O)
                    board[r][c].setIcon(oIcon);

                if (iBoard[r][c] == Cell.X)
                    board[r][c].setIcon(xIcon);
            }


    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for (int r = 0; r < 3; r++)
                for (int c = 0; c < 3; c++)
                    if (board[r][c] == e.getSource()) {
                        game.select(r, c);

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
