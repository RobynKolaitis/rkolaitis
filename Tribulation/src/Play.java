import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Play {
    public int[][] board;
    private int[] randomNums;
    private Integer numRounds;
    private Object[][] players;
    private Integer score;
    private Integer timeUsed;
    private Integer level;
    private int count;
    private int playerGuess;
    private int currentNumber;
    private Answer answer;


    //Mechanics of Game Play and win
    public Play(Object[][] players, Integer level, int[][] board, int[] randomNums) {

        this.board = board;
        this.randomNums = randomNums;
        this.numRounds = (Integer) players[0][3];
        this.level = level;
        this.players = players;
        count = 0;
        System.out.println("Enter three consecutive digits where the first two digits multiplied together\n" +
                "and the third digit is either subtracted or added from the first two digits to get the current number." +
                        "if you can not find the answer enter 0");
        PlayersTurn(players, level,  board, randomNums);
    }

    public void PlayersTurn(Object[][] players, int level,int[][] board, int[] randomNums) {
        score = 0;
        count = 0;
        Scanner playerin = new Scanner(System.in);
        String again;
        while (count < numRounds * players.length) {
            for (int i = 0; i < players.length; i++) {
                drawNumber(count);
                System.out.println(players[i][0] + " enter your guess");
                //this.roundTimer.startTimer(() -> System.out.println("Time's up!"));//start timer
                String input = playerin.next();
                playerGuess = Integer.parseInt(input);
                if (playerGuess == 0) {

                    answer = new Answer(this.board, this.currentNumber);}

                else if (playerGuess >999 ||playerGuess <100 ){System.out.println("Invalid input");
                    input =playerin.next();}
                else if (checkMath(playerGuess, this.currentNumber)) {
                    players[i][1] = ((Integer) players[i][1]) + 1;
                    // players[i][2] = ((Integer) players[i][2]) + roundTimer.getTimeUsed();

                    //players[i][2] = timeUsed + roundTimer.getSecondsRemaining();
                }
                // when last number is drawn
                //player did great,ok,or could use some practice
                score++;
                count++;
                displayBoard(this.board);
            }
        }
        // when last - number is drawn  -  //player did great,ok,or could use some practice
        for (int i = 0; i < players.length; i++){
            System.out.println(players[i][0] + " You scored " + players[i][1] + " out of " + numRounds + " rounds");}
        Scanner playeragain=new Scanner(System.in);
        System.out.println("did you want to play again? Y for yes");
        again = playeragain.nextLine();
        if (again.contains("Y") || again.contains("y")) {
            Trio game=new Trio();
        }
        else System.exit(0);
    }

    public void displayBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%6d", board[i][j]);

            }
            System.out.println();
        }
    }

    public boolean checkLeftRight(int a, int b, int c) {
        int rows = board.length;
        int cols = board[0].length;

        if (a == 9) a = 6;
        if (b == 9) b = 6;
        if (c == 9) c = 6;

        // Check rows left to right
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                if ((board[i][j] == a && board[i][j + 1] == b && board[i][j + 2] == c) ||
                        (board[i][j] == c && board[i][j + 1] == b && board[i][j + 2] == a)) {
                    return true;
                }
            }
        }

        // Check rows right to left
        for (int i = 0; i < rows; i++) {
            for (int j = cols - 1; j >= 2; j--) {
                if ((board[i][j] == a && board[i][j - 1] == b && board[i][j - 2] == c) ||
                        (board[i][j] == c && board[i][j - 1] == b && board[i][j - 2] == a)) {
                    return true;
                }
            }
        }

        // Check columns top to bottom
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i <= rows - 3; i++) {
                if ((board[i][j] == a && board[i + 1][j] == b && board[i + 2][j] == c) ||
                        (board[i][j] == c && board[i + 1][j] == b && board[i + 2][j] == a)) {
                    return true;
                }
            }
        }

        // Check columns bottom to top
        for (int j = 0; j < cols; j++) {
            for (int i = rows - 1; i >= 2; i--) {
                if ((board[i][j] == a && board[i - 1][j] == b && board[i - 2][j] == c) ||
                        (board[i][j] == c && board[i - 1][j] == b && board[i - 2][j] == a)) {
                    return true;
                }
            }
        }

        // Check diagonals down-right
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                if ((board[i][j] == a && board[i + 1][j + 1] == b && board[i + 2][j + 2] == c) ||
                        (board[i][j] == c && board[i + 1][j + 1] == b && board[i + 2][j + 2] == a)) {
                    return true;
                }
            }
        }

        // Check diagonals down-left
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 2; j < cols; j++) {
                if ((board[i][j] == a && board[i + 1][j - 1] == b && board[i + 2][j - 2] == c) ||
                        (board[i][j] == c && board[i + 1][j - 1] == b && board[i + 2][j - 2] == a)) {
                    return true;
                }
            }
        }

        // Check diagonals up-right
        for (int i = 2; i < rows; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                if ((board[i][j] == a && board[i - 1][j + 1] == b && board[i - 2][j + 2] == c) ||
                        (board[i][j] == c && board[i - 1][j + 1] == b && board[i - 2][j + 2] == a)) {
                    return true;
                }
            }
        }

        // Check diagonals up-left
        for (int i = 2; i < rows; i++) {
            for (int j = 2; j < cols; j++) {
                if ((board[i][j] == a && board[i - 1][j - 1] == b && board[i - 2][j - 2] == c) ||
                        (board[i][j] == c && board[i - 1][j - 1] == b && board[i - 2][j - 2] == a)) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean checkMath(int playerGuess, int currentNumber) {
        this.currentNumber = currentNumber;
        int a = playerGuess / 100;
        int b = (playerGuess / 10) % 10;
        int c = playerGuess % 10;  // Fixed: take modulo of playerGuess, not b

        if (!checkLeftRight(a, b, c)) {
            System.out.println("Wrong - pattern not found on board");
            return false;
        }

        if (a == 6 && c != 6 && ((9 * b) - c == currentNumber || (9 * b) + c == currentNumber)) a = 9;
        if (a == 6 && b == 6 && currentNumber > 45) a = 9;
        if (c == 6 && ((a * b) - 9 == currentNumber || (a * b) + 9 == currentNumber)) c = 9;

        boolean mathCorrect = ((a * b) + c == currentNumber) || ((a * b) - c == currentNumber);
        System.out.println(mathCorrect ? "Correct" : "Incorrect Math");
        return mathCorrect;
    }
    public int[][] getBoard() {
        return board;
    }
    public void drawNumber(int count) {
        System.out.println();
        System.out.println("The number is " + randomNums[count]);
        this.currentNumber = randomNums[count];
    }
}
