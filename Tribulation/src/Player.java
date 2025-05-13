/******************************************************************************



 *******************************************************************************/
import java.util.Scanner;
public class Player{

    private Integer score;
    private Integer time;

    private Integer numRounds;
    private Integer gameLength;
    private Integer level;
    private int numPlayers;


    public Player() {
        Object[][] players=new Object[1][4];
        Integer score = 0;
        Integer level;
        Integer timeUsed=0;
        String name="";
        Scanner inplayer = new Scanner(System.in);
        System.out.println("Enter players name");
        name = inplayer.nextLine();
        players[0][0]=name;
        players[0][1]=0;
        players[0][2]=0;
        System.out.println("Enter degree of difficulty (1 for easy, 2 for moderate, 3 for difficult)");
        level = inplayer.nextInt();
        System.out.println("Enter game length (1 for short, 2 for medium, 3 for long)");
        gameLength = inplayer.nextInt();
        determineNumRounds(gameLength,1);
        players[0][3]=numRounds;
        new Board(players,level);
    }

    public Player(int numPlayers) {
        this.numPlayers=numPlayers;
        Object[][] players = new Object[numPlayers][4];
        Integer score = 0;
        Integer timeUsed = 0;
        Integer level;
        Scanner inplayers = new Scanner(System.in);
        System.out.println("Enter degree of difficulty (1 for easy, 2 for moderate, 3 for difficult)");
        level = inplayers.nextInt();
        System.out.println("Enter game length (1 for short, 2 for medium, 3 for long)");
        gameLength = inplayers.nextInt();
        determineNumRounds(gameLength, numPlayers);
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter players name");
            String name = inplayers.nextLine();
            players[i][0] = name;
            players[i][1] = 0;
            players[i][2] = 0;
            players[i][3] = numRounds;
        }
    }
        public int getnumPlayers(){
                return numPlayers;}

    public Integer determineNumRounds(int gameLength, int numPlayers){
        if(gameLength == 1){
            if (numPlayers == 1) numRounds = 5;
            else if (numPlayers == 2) numRounds = 10;
            else numRounds = (20/numPlayers);}
        else if (gameLength == 2){
            if (numPlayers == 1) numRounds = 10;
            else if (numPlayers == 2) numRounds = 20;
            else numRounds = (30/numPlayers);}
        else if (gameLength == 3){
            if (numPlayers == 1) numRounds = 15;
            else numRounds = (45/numPlayers);}
        return numRounds;
    }

}
