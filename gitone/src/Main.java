//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void maimport java.util.*;

    public class Battleship
    {
        // battleships public variables
        Scanner in = new Scanner(System.in);

        // player and computer boards
        Boards playerBoard = new Boards();
        Boards computerBoard = new Boards();

        // fixed ships for both player and computer
        Ship playerShips = new Ship();
        Ship[] pShips = playerShips.initializeShips();
        Ship computerShips = new Ship();
        Ship[] cShips = computerShips.initializeShips();

        // not used and idk where to have it
        Player computer = new Player("Computer", true);

        /*
         * TODO: the actual game play mechanics - guess, mark ships, hit, miss, etc
         *       - all provided on Boards, we just need to somehow make it work
         * FIXME: fix Board display, it displays ?'s for coordinates (letter, number)
         *
         */

        public void play()
        {
            System.out.println("Welcome to Battleship!");
            System.out.print("Enter player name: ");
            String playerName = in.nextLine();

            // initializes player and boards
            Player player = new Player(playerName, false);
            //Boards playerBoard = new Boards();

            fixedShips();

            System.out.println("\n" + player.getName() + ", here are your boards:");
            playerBoard.displayGameBoards();

        }

        public void fixedShips()
        {
            System.out.println("\n-- Provided ships -- ");

            // displays ships and their sizes in ships array
            for (Ship ship : pShips)
            {
                System.out.println(ship.getShipName() + " (Size: " + ship.getShipSize() + ")");
            }

            System.out.println("Computer ships have been initialized.");

        }

        public void playerDirectedShips()
        {
            for (Ship ship : pShips)
            {
                boolean placed = false;
                while (!placed)
                {
                    System.out.println("\nPlacing " + ship.getShipName() + " (Size: " + ship.getShipSize() + ")");
                    System.out.print("Enter starting coordinate (e.g., A1): ");
                    String start = in.nextLine().toUpperCase();

                    System.out.print("Enter direction (U, D, L, R): ");
                    char direction = in.nextLine().toUpperCase().charAt(0);

                    ArrayList<Integer> coordsList = playerBoard.decipherCoordinates(start);
                    int x = coordsList.get(0);
                    int y = coordsList.get(1);

                    if (validatePlacement(playerBoard.playersShips, x, y, direction, ship.getShipSize()))
                    {
                        char shipLetter = ship.getShipName().toUpperCase().charAt(0);
                        playerBoard.placeShip(ship.getShipSize(), x, y, direction, shipLetter);
                        playerBoard.displayGameBoards();
                        placed = true;
                    }
                    else
                    {
                        System.out.println("Invalid placement. Try again.");
                    }
                }
            }
        }

        public void computerDirectedShips()
        {
            Random placement = new Random();
            char[] directions = {'U', 'D', 'L', 'R'};

            for (Ship ship : cShips)
            {
                boolean comPlaced = false;
                while (!comPlaced)
                {
                    int x = placement.nextInt(11);
                    int y = placement.nextInt(11);
                    char randDirection = directions[placement.nextInt(directions.length)];
                    int size = ship.getShipSize();
                    char letter = ship.getShipName().toUpperCase().charAt(0);

                    if (validatePlacement(computerBoard.computersShips, x, y, randDirection, size))
                    {
                        computerBoard.placeShip(size, x, y, randDirection, letter);
                        comPlaced = true;
                    }
                }
            }
            System.out.println("\nComputer has placed its ships.");
            System.out.println("\nAll ships placed. Ready for battle!");
        }

        // created a validate placement helper method to check if the coordinates/moves
        // is valid for the ships to be placed
        public static boolean validatePlacement(char[][] board, int x, int y, char direction, int size)
        {
            try
            {
                if (direction == 'U')
                {
                    for (int i = 0; i < size; i++)
                    {
                        if (board[x - i][y] != 'W') return false;
                    }
                }
                else if (direction == 'D')
                {
                    for (int i = 0; i < size; i++)
                    {
                        if (board[x + i][y] != 'W') return false;
                    }
                }
                else if (direction == 'L')
                {
                    for (int i = 0; i < size; i++)
                    {
                        if (board[x][y - i] != 'W') return false;
                    }
                }
                else if (direction == 'R')
                {
                    for (int i = 0; i < size; i++)
                    {
                        if (board[x][y + i] != 'W') return false;
                    }
                }
                else
                {
                    // placement invalid
                    return false;
                }
                // placement valid
                return true;
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                // if index goes out of bounds, invalid
                return false;
            }
        }
    }in(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}