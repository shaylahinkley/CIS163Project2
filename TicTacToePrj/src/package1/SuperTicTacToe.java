package package1;

import javax.swing.*;
import java.awt.*;
/***********************************************************************************************************************
 * CIS 162 Project 2
 * Implement methods for the game board GUI
 *
 * @author Keilani Bailey and Shayla Hinkley
 * @version Project 2: October 2nd, 2019
 **********************************************************************************************************************/
public class SuperTicTacToe {

    public static void  main(String[] args) {
        JFrame frame = new JFrame("GeoCountDownTimer");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        SuperTicTacToePanel gamePanel = new SuperTicTacToePanel();

        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);

        frame.setTitle("Super TicTacToe");

        frame.setVisible(true);

        frame.pack();
    }
}
