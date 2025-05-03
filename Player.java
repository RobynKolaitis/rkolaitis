import java.util.Scanner;
public class Player{

    private Integer score;
    private Double time;
    public Object[][] players;
    public int numRounds;
    private int gameLength;
    private Integer level;


    public Player() {
        Integer score = 0;
        Double time = 0.0;
        Integer level = 0;
        String name="";
        Scanner inplayer = new Scanner(System.in);

        System.out.println("Enter players name");
        name = inplayer.nextLine();
        System.out.println("Enter degree of difficulty (1 for easy, 2 for moderate, 3 for difficult)");
        level = inplayer.nextInt();
        System.out.println("Enter game length (1 for short, 2 for medium, 3 for long)");
        gameLength = inplayer.nextInt();
        determineNumRounds(gameLength, 1);
        new Play(name, numRounds, score, time, level);
    }

    public Player(int numPlayers) {
        Object[][] players= new Object[numPlayers][3];
        Integer score = 0;
        Double time = 0.0;
        Scanner inplayers = new Scanner(System.in);

        System.out.println("Enter degree of difficulty (1 for easy, 2 for moderate, 3 for difficult)");
        Integer level = inplayers.nextInt();
        System.out.println("Enter game length (1 for short, 2 for medium, 3 for long)");
        gameLength = inplayers.nextInt();
        createPlayerArray(numPlayers, players, level);
        new Play(numPlayers,players,score,time,level,numRounds);
        }

    public Object[][] createPlayerArray(int numPlayers, Object[][] players, Integer level) {
        this.players = players;
        this.score = 0;
        this.time = 0.0;
        this.level = level;

        Scanner array = new Scanner(System.in);
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter players name");
            String name = array.nextLine();
            players[i][0] = name;
        }
        for (int i = 0; i < numPlayers; i++) {
            players[i][1] = score;
            players[i][2] = time;
        }
        return players;
    }
//remove for testing only
    public void printArray(Object[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(array[i][j]);
            }
        }
    }
    public int determineNumRounds(int gameLength, int numPlayers){
        if(gameLength == 1){
            if (numPlayers == 1) numRounds = 10;
            else numRounds = (20/numPlayers);}
        else if (gameLength == 2){
            if (numPlayers == 1) numRounds = 15;
            else numRounds = (35/numPlayers);}
        else if (gameLength == 3){
            if (numPlayers == 1) numRounds = 20;
            else numRounds = (50/numPlayers);}
        return numRounds;
    }

}
