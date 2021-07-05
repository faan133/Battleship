//Name: Angela Fan
//ID: 48779009

package battleship;

import java.util.Random;

public class Ocean {

    //instance variables
    //Indicates size of ocean and has the number of ships in a fleet
    static final int OCEAN_SIZE = 10;

    //Used to determine which ship is in any given location
    //Stores the different kinds of ship in a fleet
    private Ship[][]ships = new Ship[Ocean.OCEAN_SIZE][Ocean.OCEAN_SIZE];


    //Numbers of each type of ship in a fleet
    static final int NUM_BATTLESHIPS = 1;
    static final int NUM_CRUISERS = 2;
    static final int NUM_DESTROYERS = 3;
    static final int NUM_SUBMARINES = 4;
    //The total number of shots fired by the user
    private int shotsFired;

    //Number of times a shot hit a ship
    //If the user shoots the same part of a ship more than once, every hit is counted even though
    //additional hits don't do the user any good
    private int hitCount;


    //number of ships sunk (total of 10 ships)
    private int shipsSunk;
    private boolean[] shipsSunkArray;

    private Ship[] fleetOfShips;



    /**
     * Constructor for the Ocean. Creates an empty ocean (and fills the ship array
     * with EmptySea objects
     * Initializes variables
     */
    public Ocean() {
        //initialize the game variables eg how many shots have been fired

        this.ships = new Ship[10][10];
        this.shipsSunkArray = new boolean[10];
        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;


        //creates an empty ocean and fills ocean with emptySea objects
        for (int x = 0; x < this.ships.length; x++) {
            for (int y = 0; y < this.ships[x].length; y++) {
                //create an empty sea for each location in the array
                this.ships[x][y] = new EmptySea();

                // and puts it in the location
                ships[x][y].placeShipAt(x, y, true, this);

            }
        }

        //Place all ten ships randomly on empty ocean; place larger ships before smaller ones
        Battleship battleship = new Battleship();
        Cruiser cruiserOne = new Cruiser();
        Cruiser cruiserTwo = new Cruiser();
        Destroyer destroyerOne = new Destroyer();
        Destroyer destroyerTwo = new Destroyer();
        Destroyer destroyerThree = new Destroyer();

        Submarine submarineOne = new Submarine();
        Submarine submarineTwo = new Submarine();
        Submarine submarineThree = new Submarine();
        Submarine submarineFour = new Submarine();

        this.fleetOfShips = new Ship[10];
        this.fleetOfShips[0] = battleship;
        this.fleetOfShips[1] = cruiserOne;
        this.fleetOfShips[2] = cruiserTwo;
        this.fleetOfShips[3] = destroyerOne;
        this.fleetOfShips[4] = destroyerTwo;
        this.fleetOfShips[5] = destroyerThree;
        this.fleetOfShips[6] = submarineOne;
        this.fleetOfShips[7] = submarineTwo;
        this.fleetOfShips[8] = submarineThree;
        this.fleetOfShips[9] = submarineFour;

    }

    //Methods
    /**
     * Puts the 10 ships in a random formation. Checks that the positions are legal
     * before placed in the ocean
     */
    void placeAllShipsRandomly() {

        Random rd = new Random();

        // for each of the ships in the myShips array, place ship in a
        //random location
        for (int index = 0; index < Ocean.OCEAN_SIZE; index++) {
            Ship ship = fleetOfShips[index];
            //random int between 0 and 9 for row and column for the ship
            int r = rd.nextInt(10);
            int c = rd.nextInt(10);

            //determine whether ship is horizontal or vertical

            boolean horizontal = rd.nextInt(2) == 0 ? false : true;

            if (ship.okToPlaceShipAt(r, c, horizontal, this)) {
                ship.placeShipAt(r, c, horizontal, this);
                // when not ok to play ship at the location,
            } else {
                while (!ship.okToPlaceShipAt(r, c, horizontal, this)) {
                    r = rd.nextInt(10);
                    c = rd.nextInt(10);
                    horizontal = rd.nextInt(2) == 0 ? false : true;
                    if (ship.okToPlaceShipAt(r, c, horizontal, this)) {
                        ship.placeShipAt(r, c, horizontal, this);
                        break;
                    }
                }
            }

        }
    }

    /**
     * Determines if a location has a ship
     * @param row, integer from 0 to 9
     * @param column, integer from 0 to 9
     * @return true if the location has a ship and false if it doesn't have a ship
     */
    boolean isOccupied(int row, int column) {
        if (row < 0 || row > 9) {
            return false;
        }

        if (column < 0 || column > 9) {
            return false;
        }

        if (ships[row][column] instanceof EmptySea) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Returns true if given location has a ship that is still afloat
     * Updates the number of shots that have been fired and number of hits
     * @param row
     * @param column
     * @return true if the given location contains a ship and is still afloat
     * false if does not
     */
    public boolean shootAt(int row, int column) {
        shotsFired++;
        // if there is a ship occupied at the given location and it is afloat,
        if (isOccupied(row, column)) {

            String shipType = ships[row][column].getShipType();
            //if there is a ship and it's still afloat
            if (!ships[row][column].isSunk() && shipType != "empty") {

                //update hit array
                ships[row][column].shootAt(row, column);
                hitCount++;
                return true;
                //after updating hit count, if ship has

            }


            //else if the ship has been sunk,
        } if (ships[row][column].isSunk()) {
            return false;
            //else shoot at the empty sea
        } else {
            ships[row][column].shootAt(row, column);
            return false;
        }

    }

    /**
     *
     * @returns the number of shots fired
     */
    int getShotsFired() {
        return shotsFired;

    }

    /**
     *
     * @returns the number of shots fired in the game
     */
    int getHitCount() {
        return hitCount;
    }

    /**
     * Method that gets the number of ships sunk
     * @returns the number of ships sunk in the game
     */
    int getShipsSunk() {
        //number of hits on different parts of a ship
        for (int index = 0; index < 10; index++) {

            boolean isSunk = fleetOfShips[index].isSunk();
            //System.out.println(fleetOfShips[index].isSunk());
            //set corresponding element in array to boolean value
            shipsSunkArray[index] = isSunk;
            //System.out.print(shipsSunkArray[index]);
        }

        //counter for ships sunk
        shipsSunk = 0;
        //count number of ships sunk from array, and increment shipsSunk
        for (int index = 0; index < 10; index++ ) {
            if (fleetOfShips[index].isSunk()) {
                shipsSunk++;

            }
        }
        return shipsSunk;
    }

    /**
     * Determines to see if the Battle ship game is over
     * @returns true if all ships have been sunk, otherwise false
     */
    boolean isGameOver() {
        //count the number of ships sunk (updated number)
        shipsSunk = this.getShipsSunk();
        if (shipsSunk == 10) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return the 10x10 array of Ships
     */
    Ship[][] getShipArray() {
        return ships;
    }

    /**
     * prints the Ocean
     * Row numbers should be displayed on the left of the array
     * Numbers are 0 to 9
     * Column numbers should be displayed on the top
     */

    void print() {

        for (int x = 0; x < 10; x++) {
            if (x == 0) {
                System.out.println("  0 1 2 3 4 5 6 7 8 9");
                System.out.print(x);
            } else {
                System.out.println(" ");
                System.out.print(x);
            }
            for (int y = 0; y < 10; y++) {

                //if empty sea and no ship at the spot, mark with "-"
                if (ships[x][y].getShipType() == "empty") {

                    System.out.print(ships[x][y]);

                    // when there is a ship at the location
                } else {

                    boolean[] hitArray = ships[x][y].getHit();

                    int bowRow = ships[x][y].getBowRow();
                    int bowColumn = ships[x][y].getBowColumn();


                    if (!ships[x][y].isHorizontal()) {
                        // if whole ship not hit, print "."

                        //if hitArray has 'true' for any part of array update to x or s
                        //if (ships[x][y].isSunk()) {
                        //	System.out.print(ships[x][y]);

                        //}

                        if (hitArray[bowRow - x]) {

                            System.out.print(" " + ships[x][y]);


                        } else {
                            System.out.print(" .");
                        }
                    } else {
                        // if whole ship not hit, print "."

                        //if hitArray has 'true' for any part of array update to x or s
                        //if (ships[x][y].isSunk()) {
                        //	System.out.println("sunk");
                        //	System.out.print(ships[x][y]);

                        //}

                        if (hitArray[bowColumn - y] || ships[x][y].isSunk()) {
                            System.out.print(" " + ships[x][y]);


                        } else {
                            System.out.print(" .");
                        }
                    }

                }
            }

        }

    }

    /**
     * Method that prints Ocean with row numbers and column numbers
     * In addition, shows the location of the ships
     */
    void printWithShips() {
        for (int x = 0; x < 10; x++) {
            if (x == 0) {
                System.out.println(" ");
                System.out.println("  0 1 2 3 4 5 6 7 8 9");
                System.out.print(x);
            } else {
                System.out.println(" ");
                System.out.print(x);
            }
            for (int y = 0; y < 10; y++) {
                // if there has not been any fire at location, mark with "."


                if (ships[x][y].getShipType() == "battleship") {
                    System.out.print(" b");

                }
                else if (ships[x][y].getShipType() == "cruiser") {
                    System.out.print(" c");
                } else if (ships[x][y].getShipType() == "submarine") {
                    System.out.print(" s");
                    //'d' to indicate destroyer
                } else if (ships[x][y].getShipType() == "destroyer"){
                    System.out.print(" d");
                } else {
                    System.out.print("  ");

                }


            }

        }

    }

}
