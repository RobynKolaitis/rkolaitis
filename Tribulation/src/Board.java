/******************************************************************************



 *******************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {
    public int[][] board = new int[7][7];
    private int[] randomNums = new int[49];
    Object[][] players;
    Integer level;
    JPanel gameBoard;

    public Board(Object[][] players, Integer level) {
        initBoard(this.board);
        this.players = players;
        displayBoard(this.board);
        initrandom(this.randomNums);
        createBoardDisplay();
        this.level = level;
        new Play(this.players, level, this.board, this.randomNums);
    }

    public int[][] initBoard(int[][] board) {
        ArrayList list = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 2, 2, 2, 2,
                2, 2, 3, 3, 3, 3, 3, 3,
                4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5,
                6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7,
                8, 8, 8, 8, 8));
        Collections.shuffle(list);


        int index = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = (int) list.get(index++);

            }
        }
        return board;
    }
public void displayBoard(int[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%6d", board[i][j]);
            }System.out.println();
        }
}
    public int[] initrandom(int[] randomNums) {
        for (int i = 0; i < randomNums.length; i++) {
            randomNums[i] = (int) (Math.random() * 51) + 1;
            for (int j = 0; j < i; j++) {
                if (randomNums[i] == randomNums[j]) {
                    i--;
                }
            }
        }
        return randomNums;
    }
    private static class RotatedLabel extends JLabel {
        private int rotation; // 0, 90, 180, or 270 degrees

        public RotatedLabel(String text, int rotation) {
            super(text);
            this.rotation = rotation;
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            double centerX = getWidth() / 2.0;
            double centerY = getHeight() / 2.0;

            g2.rotate(Math.toRadians(rotation), centerX, centerY);
            super.paintComponent(g2);
            g2.dispose();
        }
    }

    private void createBoardDisplay() {
        JFrame frame = new JFrame("Game Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("tribredone.jpeg"));

        gameBoard = new JPanel(new GridLayout(7, 7, 2, 2));
        gameBoard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Different rotations for different positions on the board
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                int rotation;
                int[] degrees = {0, 90, 180, 270};
                rotation=degrees[(int)(Math.random()*3)];
                // Determine rotation based on position

                RotatedLabel label = new RotatedLabel(String.valueOf(board[i][j]), rotation);
                label.setPreferredSize(new Dimension(60, 60));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setFont(new Font("Impact", Font.BOLD, 20));
                gameBoard.add(label);
            }
        }

        frame.add(gameBoard);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
