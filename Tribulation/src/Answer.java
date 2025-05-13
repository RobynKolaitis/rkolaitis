import javax.swing.*;
import java.awt.*;

public class Answer {
    int[][] board;
    int currentNumber;
    int[][] key=new int[7][7];
    JPanel answerBoard;

    public Answer(int[][] board, int currentNumber) {
        this.board = board;
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                key[i][j]=0;
            }
        }
        // Check rows (left to right AND right to left)
        // Left to Right:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                int a = board[i][j];
                int b = board[i][j + 1];
                int c = board[i][j + 2];
                if (a == 6 && c != 6 && ((9 * b) - c == currentNumber || (9 * b) + c == currentNumber)) a = 9;
                if (a == 6 && b == 6 && currentNumber > 45) a = 9;
                if (c == 6 && ((a * b) - 9 == currentNumber || (a * b) + 9 == currentNumber)) c = 9;
                if (a * b - c == currentNumber || a * c - b == currentNumber) {
                    key[i][j]=board[i][j];
                    key[i][j+1]=board[i][j+1];
                    key[i][j+2]=board[i][j+2];

                }
            }

            // Right to left
            for (int j = cols - 1; j >= 2; j--) {
                int a = board[i][j];
                int b = board[i][j - 1];
                int c = board[i][j - 2];
                if (a == 6 && c != 6 && ((9 * b) - c == currentNumber || (9 * b) + c == currentNumber)) a = 9;
                if (a == 6 && b == 6 && currentNumber > 45) a = 9;
                if (c == 6 && ((a * b) - 9 == currentNumber || (a * b) + 9 == currentNumber)) c = 9;
                if (a * b - c == currentNumber || a * c - b == currentNumber) {
                    key[i][j]=board[i][j];
                    key[i][j-1]=board[i][j-1];
                    key[i][j-2]=board[i][j-2];

                }
            }
        }
        // Check columns (top to bottom)
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i <= rows - 3; i++) {
                int a = board[i][j];
                int b = board[i + 1][j];
                int c = board[i + 2][j];
                if (a == 6 && c != 6 && ((9 * b) - c == currentNumber || (9 * b) + c == currentNumber)) a = 9;
                if (a == 6 && b == 6 && currentNumber > 45) a = 9;
                if (c == 6 && ((a * b) - 9 == currentNumber || (a * b) + 9 == currentNumber)) c = 9;
                if (a * b - c == currentNumber || a * c - b == currentNumber) {
                    key[i][j]=board[i][j];
                    key[i+1][j]=board[i+1][j];
                    key[i+2][j]=board[i+2][j];
                }
            }
        }
        // Check columns (bottom to top)
        for (int j = 0; j < cols; j++) {
            for (int i = rows - 1; i >= 2; i--) {
                int a = board[i][j];
                int b = board[i - 1][j];
                int c = board[i - 2][j];
                if (a == 6 && c != 6 && ((9 * b) - c == currentNumber || (9 * b) + c == currentNumber)) a = 9;
                if (a == 6 && b == 6 && currentNumber > 45) a = 9;
                if (c == 6 && ((a * b) - 9 == currentNumber || (a * b) + 9 == currentNumber)) c = 9;
                if (a * b - c == currentNumber || a * c - b == currentNumber) {
                    key[i][j]=board[i][j];
                    key[i-1][j]=board[i-1][j];
                    key[i-2][j]=board[i-2][j];
                }
            }
        }
        // Check diagonals (down-right)
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                int a = board[i][j];
                int b = board[i + 1][j + 1];
                int c = board[i + 2][j + 2];
                if (a == 6 && c != 6 && ((9 * b) - c == currentNumber || (9 * b) + c == currentNumber)) a = 9;
                if (a == 6 && b == 6 && currentNumber > 45) a = 9;
                if (c == 6 && ((a * b) - 9 == currentNumber || (a * b) + 9 == currentNumber)) c = 9;
                if (a * b - c == currentNumber || a * c - b == currentNumber) {
                    key[i][j]=board[i][j];
                    key[i+1][j+1]=board[i+1][j+1];
                    key[i+2][j+2]=board[i+2][j+2];
                }
            }
        }
        // Check diagonals down-left
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 2; j < cols; j++) {
                int a = board[i][j];
                int b = board[i + 1][j - 1];
                int c = board[i + 2][j - 2];
                if (a == 6 && c != 6 && ((9 * b) - c == currentNumber || (9 * b) + c == currentNumber)) a = 9;
                if (a == 6 && b == 6 && currentNumber > 45) a = 9;
                if (c == 6 && ((a * b) - 9 == currentNumber || (a * b) + 9 == currentNumber)) c = 9;
                if (a * b - c == currentNumber || a * c - b == currentNumber) {
                   key[i][j]=board[i][j];
                   key[i+1][j-1]=board[i+1][j-1];
                   key[i+2][j-2]=board[i+2][j-2];
                }
            }
        }
        // Check diagonals up-right
        for (int i = 2; i < rows; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                int a = board[i][j];
                int b = board[i - 1][j + 1];
                int c = board[i - 2][j + 2];
                if (a == 6 && c != 6 && ((9 * b) - c == currentNumber || (9 * b) + c == currentNumber)) a = 9;
                if (a == 6 && b == 6 && currentNumber > 45) a = 9;
                if (c == 6 && ((a * b) - 9 == currentNumber || (a * b) + 9 == currentNumber)) c = 9;
                if (a * b - c == currentNumber || a * c - b == currentNumber) {
                    key[i][j]=board[i][j];
                    key[i-1][j+1]=board[i-1][j+1];
                    key[i-2][j+2]=board[i-2][j+2];
                }
            }
        }
        // Check diagonals up-left
        for (int i = 2; i < rows; i++) {
            for (int j = 2; j < cols; j++) {
                int a = board[i][j];
                int b = board[i - 1][j - 1];
                int c = board[i - 2][j - 2];
                if (a == 6 && c != 6 && ((9 * b) - c == currentNumber || (9 * b) + c == currentNumber)) a = 9;
                if (a == 6 && b == 6 && currentNumber > 45) a = 9;
                if (c == 6 && ((a * b) - 9 == currentNumber || (a * b) + 9 == currentNumber)) c = 9;
                if (a * b - c == currentNumber || a * c - b == currentNumber) {
                    key[i][j]=board[i][j];
                    key[i-1][j-1]=board[i-1][j-1];
                    key[i-2][j-2]=board[i-2][j-2];
                }
            }
        }
        createAnswerBoardDisplay();
        displayAnswer(key);
    }


    public void displayAnswer(int[][] key) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%6d", key[i][j]);

            }
            System.out.println();

        }
    }
    private static class RotatedLabelA extends JLabel {
        private int rotation; // 0, 90, 180, or 270 degrees

        public RotatedLabelA(String text, int rotation) {
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

    private void createAnswerBoardDisplay() {
        JFrame frameA = new JFrame("Answer Board");
        frameA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameA.setResizable(true);
        frameA.setIconImage(Toolkit.getDefaultToolkit().getImage("tribredone.jpeg"));

        answerBoard = new JPanel(new GridLayout(7, 7, 2, 2));
        answerBoard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Different rotations randomly determined on the board
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                int rotation;
                int[] degrees = {0, 90, 180, 270};
                rotation=degrees[(int)(Math.random()*3)];
               /* // Determine rotation based on position
                if (i < 3) {
                    rotation = 90; // Top section rotated 90 degrees
                } else if (i >= 4) {
                    rotation = 180; // Bottom section upside down
                } else {
                    rotation = 270; // Middle section rotated 270 degrees
                }*/

                Answer.RotatedLabelA label = new Answer.RotatedLabelA(String.valueOf(key[i][j]), rotation);
                label.setPreferredSize(new Dimension(60, 60));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setFont(new Font("Impact", Font.BOLD, 20));
                answerBoard.add(label);
            }
        }

        frameA.add(answerBoard);
        frameA.pack();
        frameA.setLocationRelativeTo(null);
        frameA.setVisible(true);
    }
}


