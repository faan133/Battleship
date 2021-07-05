package battleship;
import java.util.Arrays;
import java.util.Scanner;

/**
 * BattleshipGame class which sets up the game, accepts shots
 * from user, display the results, print final scores, and asks the user if he/she want to play again
 * All of the input is done here
 * @author angela
 *
 */

public class BattleshipGame {

    /**
     * main method that launches the game
     * Creates single instance of the Ocean
     * @param args
     */
    public static void main(String[] args) {
        BattleshipGame battleshipGame = new BattleshipGame();
        battleshipGame.battleshipGame();


    }

    /**
     * Battleship game which accepts user input for the locations
     * of shots, shows the results, scores
     *
     * @return
     */
    public void battleshipGame() {
        //Create instance of Ocean
        Ocean ocean = new Ocean();

        //Call method to place ten ships
        ocean.placeAllShipsRandomly();

        //ocean.print();

        //Variable for game continue mode
        boolean gameEnd = ocean.isGameOver();

        //Call printWithShips for debugging only
        ocean.printWithShips();

        Scanner scanner = new Scanner(System.in);
        //Welcome to game message
        System.out.println(" ");
        System.out.println("Welcome to Battleship! ");
        System.out.println(" ");
        System.out.println("Rules for the game: ");
        System.out.println("-------------------");
        System.out.println("Computer has placed 10 ships either in horizontal or vertical form \n"
                + "without being overlapping or adjacent in a 10x10 ocean \n"
                + "there are 4 submarines, 2 cruisers, 3 destroyers, and 1 battleship \n"
                + "You should try to sink all of the ships in the ocean \n"
                + "'x' indicates location in which you hit a ship, 's' indicates \n "
                + "the location in which the ship has sunk \n"
                + "Best possible score is 20. \n"
                +"  \n"
                + "--------------- \n"
                + "USER PLAY: \n"
                + "--------------- \n"
                + "Rows and Columns of the ocean are numbered from 0 to 9.\n"
                + "To shoot at a specific location please enter input in the form of 'row, column' \n"
                + "You can also end the game by entering 'end'");

        //print the initial ocean
        System.out.println("  ");
        ocean.print();
        while (gameEnd == false) {
            System.out.println(" ");
            System.out.println("Hit Count: " + ocean.getHitCount());
            System.out.println("Ships sunk: " + ocean.getShipsSunk());
            System.out.println("Enter row, column: ");
            String rowColumn = scanner.nextLine();
            //end or terminate the game if the user enters 'end'
            if (rowColumn.equals("end")) {
                gameEnd = true;
                //exits the program
                System.exit(0);
            } else {
                int[] locationToShoot = stringToInteger(rowColumn);
                if (locationToShoot[0] == -1) {
                    String playAgain = scanner.nextLine();
                } else {
                    //get the row and column
                    int row = locationToShoot[0];
                    int column = locationToShoot[1];


                    boolean toShoot = ocean.shootAt(row, column);
                    if (!toShoot) {

                    }

                    //print the updated the ocean
                    System.out.println(" ");
                    ocean.print();

                    if (gameEnd == true) {
                        //print final scores
                        System.out.println("You sank all ten ships. You win! \n"
                                + "You made a total of " + ocean.getShotsFired() + "shots. \n");


                        //ask user if they want to play again
                        System.out.println("Do you want to play again? Y or N");
                        String playAgain = scanner.nextLine();
                        if (playAgain.toLowerCase().equals("y")) {
                            System.out.println("You have selected to start a new game");
                            gameEnd = true;
                            //Create new instance of Ocean, place ships randomly, and print ocean
                            Ocean oceanTwo = new Ocean();
                            oceanTwo.placeAllShipsRandomly();
                            ocean.print();



                        } else  if (playAgain.toLowerCase().equals("n")) {
                            System.out.println("You have selected to not start new game.");
                            //exits the program
                            System.exit(0);
                        } else {
                            System.out.println("user selection is not Y or N.");
                            System.out.println("Game will end now");
                            //exits the program
                            System.exit(0);
                        }

                    }
                }
            }

        }
    }



    /**
     * Converts the given string into an array with integers
     * @param userGivenString
     * @return array with integers from the user's input
     */
    public int[] stringToInteger(String userGivenString) {
        String[] userString = {};
        int[] integerArray = new int[2];
        if (userGivenString.length() == 3) {
            for (int index = 0; index < 2; index++) {
                try {

                    userString = userGivenString.split(",");
                    //return the integer from element of the String array
                    int integer = Integer.parseInt(userString[index]);
                    integerArray[index] = integer;
                    if (integer < 0 || integer > 9) {
                        System.out.println("Both row and column have to be an integer from 0 to 9.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("This is not a valid row, column.");
                    integerArray[0] = -1;


                } catch (Exception e) {
                    System.out.println("Enter input in form: row, column");
                    integerArray[0] = -1;
                }
            }
        } else {
            integerArray[0] = -1;

        }

        return integerArray;
    }



}
