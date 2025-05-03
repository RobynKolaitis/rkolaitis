import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Play {
    int[][] board;
    int[] randomNums;
    int numRounds;
    Object[][] players;
    Integer score;
    Double time;
    Integer level;
    int count;
    Scanner playerin;
    int playerGuess;
    String[] wrong;
    String[] correct;
    String[] timeOut;
    private int currentNumber;
    private boolean math;
    private ArrayList<Integer> list = new ArrayList<>();

    public Play(String name, int numRounds, Integer score, Double time, Integer level) {
        this.board = new int[7][7];
        this.randomNums = new int[49];
        this.numRounds = numRounds;
        this.score = score;
        this.time = time;
        this.level = level;
        count = 0;
        int currentNumber=0;
        boolean math;
        int a = 0;
        int b = 0;
        int c = 0;

        initBoard(board);
        displayboard(this.board);
        initrandom(this.randomNums);
        Scanner playerin = new Scanner(System.in);
        score = 0;
       drawNumber(count);
        System.out.println("Enter three consecutive digits where the first two digits multiplied together\n" +
                "and the third digit is either subtracted or added from the first two digits to get the current number.");
        while (count < numRounds) {
            playerGuess = playerin.nextInt();
            count++;
            if (checkMath(playerGuess, this.currentNumber)) {
                score++;
            }
            drawNumber(count);
        }
        System.out.println("You scored " + score + " out of " + numRounds + " rounds");
        //time used++
        //correct answers++
        // when last number is drawn
        //player did great,ok,or could use some practice
    }

    public Play(int numPlayers, Object[][] players, Integer score, Double time, int level, int numRounds) {
        this.players = players;
        this.board = new int[7][7];
        this.randomNums = new int[49];
        this.numRounds = numRounds;
        this.score = score;
        this.time = time;
        this.level = level;
        count = 0;
        int currentNumber = 0;
        boolean math;
        int a = 0;
        int b = 0;
        int c = 0;

        initBoard(board);
        displayboard(this.board);
        initrandom(this.randomNums);
        Scanner playerin = new Scanner(System.in);
        score = 0;

        for (int i = 0; i < numPlayers; i++) {
            drawNumber(count);
            System.out.println("Enter three consecutive digits where the first two digits multiplied together\n" +
                    "and the third digit is either subtracted or added from the first two digits to get the current number.");
            while (count < numRounds) {
                System.out.println(players[i][0] + " enter your guess");
                if (checkMath(playerGuess, this.currentNumber)) {
                    players[i][1] = score + 1;
                }
                count++;
            }
            drawNumber(count);
        }
        for (int i = 0; i < numPlayers; i++) {
            System.out.println(players[i][0] + " You scored " + players[i][1] + " out of " + numRounds/numPlayers + " rounds");
        }
    }
        //for each player
        //draw number
        //start timer
        //if player enters number and it checks out
        // score++
        //time used++
        //correct answer++
        //else next player
        // when last number is drawn
        //player did great,ok,or could use some practice


    //create boad with game specified numbers
    public void initBoard(int[][] board) {
        list = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3,
                4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8));
        Collections.shuffle(list);
        int index = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = list.get(index++);
            }
        }
    }

    public void drawNumber(int count) {
        this.currentNumber = randomNums[count];
        System.out.println(randomNums[count]);
    }

    public void initrandom(int[] randomNums) {
        for (int i = 0; i < randomNums.length; i++) {
            randomNums[i] = (int) (Math.random() * 51) + 1;
            for (int j = 0; j < i; j++) {
                if (randomNums[i] == randomNums[j]) {
                    i--;
                }
            }
        }
    }

    public void displayboard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%6d", board[i][j]);
            }
            System.out.println();
        }
    }


    private boolean checkLeftRight(int a, int b, int c) {

        int rows = board.length;
        int cols = board[0].length;

        if (a == 9) a = 6;
        if (b == 9) b = 6;
        if (c == 9) c = 6;

        // Check rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                if ((board[i][j] == a && board[i][j + 1] == b && board[i][j + 2] == c) ||
                        (board[i][j] == c && board[i][j + 1] == b && board[i][j + 2] == a)) {
                    return true;
                }
            }
        }

        // Check columns
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i <= rows - 3; i++) {
                if ((board[i][j] == a && board[i + 1][j] == b && board[i + 2][j] == c) ||
                        (board[i][j] == c && board[i + 1][j] == b && board[i + 2][j] == a)) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                if ((board[i][j] == a && board[i + 1][j + 1] == b && board[i + 2][j + 2] == c) ||
                        (board[i][j] == c && board[i + 1][j + 1] == b && board[i + 2][j + 2] == a)) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean checkMath(int playerGuess, int currentNumber) {
        this.currentNumber=currentNumber;
        int a = playerGuess / 100;
        int b = (playerGuess /10) %10;
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
}





