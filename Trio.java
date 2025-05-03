import java.util.Scanner;
import java.util.ArrayList;
public class Trio {
    private int numPlayers;
    private Scanner in = new Scanner(System.in);

    public Trio() {
        System.out.println("Welcome! Please enter the number of players!");
        numPlayers = in.nextInt();
        if (numPlayers < 2) {
            Player game = new Player();
        } else {
            Player game = new Player(numPlayers);
        }
    }

    public int getNumplayers(){
        return numPlayers;
    }
}
