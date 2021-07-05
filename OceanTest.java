package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

    Ocean ocean;
    Ship battleship;
    Ship cruiserA;
    Ship cruiserB;
    Ship destroyerC;
    Ship destroyerD;
    Ship destroyerE;
    Ship submarineF;
    Ship submarineG;
    Ship submarineH;
    Ship submarineI;

    static int NUM_BATTLESHIPS = 1;
    static int NUM_CRUISERS = 2;
    static int NUM_DESTROYERS = 3;
    static int NUM_SUBMARINES = 4;
    static int OCEAN_SIZE = 10;
    private int shipsSunk;
    private boolean[] shipsSunkArray;
    private Ship[] fleetOfShips;

    @BeforeEach
    void setUp() throws Exception {
        ocean = new Ocean();
        shipsSunkArray = new boolean[10];
        fleetOfShips = new Ship[10];
        fleetOfShips[0] = battleship;
        fleetOfShips[1] = cruiserA;
        fleetOfShips[2] = cruiserB;
        fleetOfShips[3] = destroyerC;
        fleetOfShips[4] = destroyerD;
        fleetOfShips[5] = destroyerE;
        fleetOfShips[6] = submarineF;
        fleetOfShips[7] = submarineG;
        fleetOfShips[8] = submarineH;
        fleetOfShips[9] = submarineI;


    }

    @Test
    void testOcean() {
        //testing that we get a ship array of empty seas initially
        assertEquals("empty", ocean.getShipArray()[0][7].getShipType());
        assertEquals("empty", ocean.getShipArray()[3][3].getShipType());
        assertEquals("empty", ocean.getShipArray()[7][0].getShipType());
    }
    @Test
    void testEmptyOcean() {

        //tests that all locations in the ocean are "empty"

        Ship[][] ships = ocean.getShipArray();

        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                Ship ship = ships[i][j];

                assertEquals("empty", ship.getShipType());
            }
        }

        assertEquals(0, ships[0][0].getBowRow());
        assertEquals(0, ships[0][0].getBowColumn());

        assertEquals(5, ships[5][5].getBowRow());
        assertEquals(5, ships[5][5].getBowColumn());

        assertEquals(9, ships[9][0].getBowRow());
        assertEquals(0, ships[9][0].getBowColumn());
    }

    @Test
    void testPlaceAllShipsRandomly() {

        //tests that the correct number of each ship type is placed in the ocean

        ocean.placeAllShipsRandomly();

        Ship[][] ships = ocean.getShipArray();
        ArrayList<Ship> shipsFound = new ArrayList<Ship>();

        int numBattleships = 0;
        int numCruisers = 0;
        int numDestroyers = 0;
        int numSubmarines = 0;
        int numEmptySeas = 0;

        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                Ship ship = ships[i][j];
                if (!shipsFound.contains(ship)) {
                    shipsFound.add(ship);
                }
            }
        }

        for (Ship ship : shipsFound) {
            if ("battleship".equals(ship.getShipType())) {
                numBattleships++;
            } else if ("cruiser".equals(ship.getShipType())) {
                numCruisers++;
            } else if ("destroyer".equals(ship.getShipType())) {
                numDestroyers++;
            } else if ("submarine".equals(ship.getShipType())) {
                numSubmarines++;
            } else if ("empty".equals(ship.getShipType())) {
                numEmptySeas++;
            }
        }

        assertEquals(NUM_BATTLESHIPS, numBattleships);
        assertEquals(NUM_CRUISERS, numCruisers);
        assertEquals(NUM_DESTROYERS, numDestroyers);
        assertEquals(NUM_SUBMARINES, numSubmarines);

        //calculate total number of available spaces and occupied spaces
        int totalSpaces = OCEAN_SIZE * OCEAN_SIZE;
        int occupiedSpaces = (NUM_BATTLESHIPS * 4)
                + (NUM_CRUISERS * 3)
                + (NUM_DESTROYERS * 2)
                + (NUM_SUBMARINES * 1);

        //test number of empty seas, each with length of 1
        assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
    }

    @Test
    void testIsOccupied() {

        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        Ship submarine = new Submarine();
        row = 0;
        column = 0;
        horizontal = false;
        submarine.placeShipAt(row, column, horizontal, ocean);

        assertTrue(ocean.isOccupied(1, 5));
        assertFalse(ocean.isOccupied(2, 4));
        assertTrue(ocean.isOccupied(0,0));

        // More tests with Cruiser ship
        //case with Destroyer at the end
        Destroyer cruiser = new Destroyer();
        int rowA = 7;
        int columnA = 5;
        boolean horizontalA = false;
        cruiser.placeShipAt(rowA, columnA, horizontalA, ocean);


        //More tests with the Battleship ship with horizontal form
        Ship battleship  = new Battleship();
        row = 6;
        column = 6;
        horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);

        assertTrue(ocean.isOccupied(1, 5));

        //Test with horizontal ship
        Destroyer destroyerB = new Destroyer();
        int rowB = 2;
        int columnB = 5;
        boolean horizontalB = true;
        destroyerB.placeShipAt(rowB, columnB, horizontalB, ocean);


        assertFalse(ocean.isOccupied(7,1));
        assertTrue(ocean.isOccupied(6,6));
        assertTrue(ocean.isOccupied(2, 5));

    }

    @Test
    void testShootAt() {

        assertFalse(ocean.shootAt(0, 1));

        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        assertTrue(ocean.shootAt(1, 5));
        assertFalse(destroyer.isSunk());
        assertTrue(ocean.shootAt(0, 5));

        //More tests
        //test with the cruiser at location 5,8
        assertFalse(ocean.shootAt(9, 9));

        Cruiser cruiser = new Cruiser();
        int rowA = 5;
        int columnA = 8;
        boolean horizontalA = false;
        cruiser.placeShipAt(rowA, columnA, horizontalA, ocean);

        assertTrue(ocean.shootAt(5, 8));
        assertTrue(destroyer.isSunk());
        assertTrue(ocean.shootAt(4, 8));

        //test with battleship
        assertFalse(ocean.shootAt(0, 3));

        Ship battleship = new Battleship();
        int rowB = 0;
        int columnB = 6;
        boolean horizontalB = true;
        battleship.placeShipAt(rowB, columnB, horizontalB, ocean);

        assertTrue(ocean.shootAt(0, 5));
        assertTrue(destroyer.isSunk());
        //test with location of an empty sea, no ship
        assertFalse(ocean.shootAt(1, 5));
    }

    @Test
    void testGetShotsFired() {

        //should be all false - no ships added yet
        assertFalse(ocean.shootAt(0, 1));
        assertFalse(ocean.shootAt(1, 0));
        assertFalse(ocean.shootAt(3, 3));
        assertFalse(ocean.shootAt(9, 9));
        assertEquals(4, ocean.getShotsFired());

        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        Ship submarine = new Submarine();
        row = 0;
        column = 0;
        horizontal = false;
        submarine.placeShipAt(row, column, horizontal, ocean);

        assertTrue(ocean.shootAt(1, 5));
        assertFalse(destroyer.isSunk());
        assertTrue(ocean.shootAt(0, 5));
        assertTrue(destroyer.isSunk());
        assertEquals(6, ocean.getShotsFired());


        //More tests
        //Test to see if battleship is sunk after 4 shots fired in all locations
        //with an extra shot fired
        Ship battleship = new Battleship();
        row = 6;
        column = 8;
        horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);

        //shoots five times, four at the battleship, one in emptysea
        assertFalse(ocean.shootAt(7, 7));
        assertFalse(battleship.isSunk());
        assertTrue(ocean.shootAt(6, 8));
        assertFalse(battleship.isSunk());
        assertTrue(ocean.shootAt(6, 5));
        assertFalse(battleship.isSunk());
        assertTrue(ocean.shootAt(6, 7));
        assertFalse(battleship.isSunk());
        assertTrue(ocean.shootAt(6, 6));
        assertTrue(battleship.isSunk());
        assertEquals(11, ocean.getShotsFired());

        //Test to see if the cruiser in vertical formation has been sunk
        //And that the battleship only shot twice at has not been sunk
        Cruiser cruiser = new Cruiser();
        int rowB = 6;
        int columnB = 8;
        boolean horizontalB = true;
        cruiser.placeShipAt(rowB, columnB, horizontalB, ocean);

        Ship submarineB = new Submarine();
        row = 4;
        column = 0;
        horizontal = false;
        submarineB.placeShipAt(row, column, horizontal, ocean);

        assertTrue(ocean.shootAt(6, 8));
        assertFalse(cruiser.isSunk());
        assertTrue(ocean.shootAt(6,7));
        assertFalse(cruiser.isSunk());
        assertTrue(ocean.shootAt(6, 6));
        assertTrue(cruiser.isSunk());

        //shoot twice for the submarine
        assertTrue(ocean.shootAt(4, 0));
        assertTrue(submarineB.isSunk());
        assertFalse(ocean.shootAt(3, 0));

        assertEquals(16, ocean.getShotsFired());

    }

    @Test
    void testGetHitCount() {

        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        assertTrue(ocean.shootAt(1, 5));
        assertFalse(destroyer.isSunk());
        assertEquals(1, ocean.getHitCount());


        //More tests
        //Test with battleship with two shots at the battleship and one shot elsewhere
        //more shots shots made than number of hits
        Ship battleship = new Battleship();
        int rowA = 3;
        int columnA = 4;
        boolean horizontalA = false;
        battleship.placeShipAt(rowA, columnA, horizontalA, ocean);

        assertTrue(ocean.shootAt(3, 4));
        assertTrue(ocean.shootAt(2, 4));
        assertFalse(ocean.shootAt(5, 5));

        assertFalse(battleship.isSunk());
        assertEquals(3, ocean.getHitCount());

        //Test with cruiser with 3 shots to sink the ship and 2 shots elsewhere
        //gets a hit count
        Cruiser cruiser = new Cruiser();
        int rowB = 3;
        int columnB = 4;
        boolean horizontalB = false;
        cruiser.placeShipAt(rowB, columnB, horizontalB, ocean);

        assertTrue(ocean.shootAt(3, 4));
        assertTrue(ocean.shootAt(2, 4));
        assertTrue(ocean.shootAt(1, 4));

        assertFalse(ocean.shootAt(8, 8));
        assertFalse(ocean.shootAt(6, 8));

        assertTrue(cruiser.isSunk());
        assertEquals(6, ocean.getHitCount());
    }


    @Test
    void testGetShipsSunk() {
        //Place all ten ships randomly on empty ocean; place larger ships before smaller ones

        Destroyer destroyerC = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyerC.placeShipAt(row, column, horizontal, ocean);
        fleetOfShips[3] = destroyerC;

        assertTrue(ocean.shootAt(1, 5));
        assertFalse(destroyerC.isSunk());
        assertEquals(1, ocean.getHitCount());
        assertEquals(0, ocean.getShipsSunk());

        //More tests
        //Cruiser with 2 of 3 spots hit but not sunk
        Cruiser cruiserA = new Cruiser();
        int rowA = 4;
        int columnA = 5;
        boolean horizontalA = true;
        cruiserA.placeShipAt(rowA, columnA, horizontalA, ocean);
        fleetOfShips[1] = cruiserA;


        assertTrue(ocean.shootAt(4, 5));
        assertFalse(cruiserA.isSunk());
        assertTrue(ocean.shootAt(4, 4));
        assertFalse(cruiserA.isSunk());
        assertEquals(3, ocean.getHitCount());
        assertEquals(0, ocean.getShipsSunk());

    }

    @Test
    void testGetShipArray() {

        Ship[][] shipArray = ocean.getShipArray();
        assertEquals(OCEAN_SIZE, shipArray.length);
        assertEquals(OCEAN_SIZE, shipArray[0].length);

        assertEquals("empty", shipArray[0][0].getShipType());


        //More tests
        //tests with different ships on the shipArray
        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);
        assertEquals("destroyer", shipArray[1][5].getShipType());
        assertEquals("destroyer", shipArray[0][5].getShipType());

        //assert Not Equals for several locations not containing destroyer
        assertNotEquals("destroyer", shipArray[2][2]);
        assertNotEquals("destroyer", shipArray[9][9]);

        //Battleship arranged horizontally
        Ship battleship = new Battleship();
        int rowB = 7;
        int columnB = 7;
        boolean horizontalB = true;
        battleship.placeShipAt(rowB, columnB, horizontalB, ocean);
        assertEquals("battleship", shipArray[7][5].getShipType());
        assertEquals("battleship", shipArray[7][6].getShipType());
        assertEquals("battleship", shipArray[7][7].getShipType());
        assertEquals("battleship", shipArray[7][4].getShipType());

        //assert Not Equals for several locations not containing destroyer
        assertNotEquals("battleship", shipArray[5][7]);
        assertNotEquals("battleship", shipArray[2][4]);
        assertNotEquals("battleship", shipArray[5][6]);

        Cruiser cruiser = new Cruiser();
        int rowA = 4;
        int columnA = 9;
        boolean horizontalA = true;
        cruiser.placeShipAt(rowA, columnA, horizontalA, ocean);
        assertEquals("cruiser", shipArray[4][7].getShipType());
        assertEquals("cruiser", shipArray[4][8].getShipType());
        assertEquals("cruiser", shipArray[4][9].getShipType());

    }

    //places the 10 ships from fleet
    private void placeShips() {
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

        fleetOfShips[0].placeShipAt(6, 8, true, ocean);
        fleetOfShips[1].placeShipAt(3, 5, false, ocean);
        fleetOfShips[2].placeShipAt(3, 7, false, ocean);

        fleetOfShips[3].placeShipAt(3, 2, true, ocean);
        fleetOfShips[4].placeShipAt(1, 2, false, ocean);
        fleetOfShips[5].placeShipAt(5, 2, false, ocean);
        fleetOfShips[6].placeShipAt(8, 1, false, ocean);
        fleetOfShips[7].placeShipAt(8, 3, false, ocean);
        fleetOfShips[8].placeShipAt(8, 5, false, ocean);
        fleetOfShips[9].placeShipAt(8, 7, false, ocean);
        shipsSunkArray = new boolean[10];

    }

    @Test
    void testIsGameOver() {
        //places the ships in the location in the ocean
        placeShips();
        //assert that the game is not over when none of ships have been shot
        assertFalse(ocean.isGameOver());


        //sink a submarine and make sure game is not over yet
        ocean.shootAt(8, 7);
        assertFalse(ocean.isGameOver());

        //shoot at every location in the 10x10 ocean to ensure all ships have sunk
        //game over
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                ocean.shootAt(x, y);
            }
        }

        assertTrue(ocean.isGameOver());
    }

}
