package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

    Ocean ocean;
    Ship ship;

    @BeforeEach
    void setUp() throws Exception {
        ocean = new Ocean();
    }

    @Test
    void testGetLength() {
        ship = new Battleship();
        assertEquals(4, ship.getLength());


        //More tests
        //Test for get length of cruiser
        ship = new Cruiser();
        assertEquals(3, ship.getLength());

        //Test for submarine
        ship = new Submarine();
        assertEquals(1, ship.getLength());

        ship = new Destroyer();
        assertEquals(2, ship.getLength());

        ship = new EmptySea();
        assertEquals(1, ship.getLength());


    }

    @Test
    void testGetBowRow() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, battleship.getBowRow());


        //More tests
        //Cruiser bow row in row 9
        Cruiser cruiser = new Cruiser();
        int rowA = 9;
        int columnA = 4;
        boolean horizontalA = true;
        cruiser.placeShipAt(rowA, columnA, horizontalA, ocean);
        assertEquals(rowA, cruiser.getBowRow());

        //Submarine bow row in row 5
        Ship submarine = new Submarine();
        int rowB = 5;
        int columnB = 7;
        boolean horizontalB = false;
        submarine.placeShipAt(rowB, columnB, horizontalB, ocean);
        assertEquals(rowB, submarine.getBowRow());
    }

    @Test
    void testGetBowColumn() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        battleship.setBowColumn(column);
        assertEquals(column, battleship.getBowColumn());


        //More tests
        //Cruiser bow row in row 9
        Cruiser cruiser = new Cruiser();
        int rowA = 9;
        int columnA = 4;
        boolean horizontalA = true;
        cruiser.placeShipAt(rowA, columnA, horizontalA, ocean);
        assertEquals(rowA, cruiser.getBowRow());

        //Submarine bow row in row 5
        Ship submarine = new Submarine();
        int rowB = 5;
        int columnB = 7;
        boolean horizontalB = false;
        submarine.placeShipAt(rowB, columnB, horizontalB, ocean);
        assertEquals(rowB, submarine.getBowRow());
    }

    @Test
    void testGetHit() {
        ship = new Battleship();
        boolean[] hits = new boolean[4];
        assertArrayEquals(hits, ship.getHit());
        assertFalse(ship.getHit()[0]);
        assertFalse(ship.getHit()[1]);



        //More tests
        ship = new Cruiser();
        boolean[] hitsA = new boolean[3];
        assertArrayEquals(hitsA, ship.getHit());
        assertFalse(ship.getHit()[0]);
        assertFalse(ship.getHit()[1]);
        assertEquals(hitsA[0], ship.getHit()[0]);
        assertEquals(hitsA[1], ship.getHit()[1]);
        assertEquals(hitsA[2], ship.getHit()[2]);

        //More tests
        ship = new Destroyer();
        boolean[] hitsB = new boolean[2];
        assertArrayEquals(hitsB, ship.getHit());
        assertFalse(ship.getHit()[0]);
        assertFalse(ship.getHit()[1]);
        assertEquals(hitsB[0], ship.getHit()[0]);
        assertEquals(hitsB[1], ship.getHit()[1]);

    }
    @Test
    void testGetShipType() {
        ship = new Battleship();
        assertEquals("battleship", ship.getShipType());

        //More tests
        ship = new Cruiser();
        assertEquals("cruiser", ship.getShipType());

        ship = new Destroyer();
        assertEquals("destroyer", ship.getShipType());

        ship = new Submarine();
        assertEquals("submarine", ship.getShipType());

        ship = new EmptySea();
        assertEquals("empty", ship.getShipType());


    }

    @Test
    void testIsHorizontal() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertTrue(battleship.isHorizontal());


        //More tests
        //Place a horizontal cruiser with the bow at 9,9
        Ship cruiser = new Cruiser();
        int rowA = 9;
        int columnA = 9;
        boolean horizontalA = true;
        cruiser.placeShipAt(rowA, columnA, horizontalA, ocean);
        assertTrue(cruiser.isHorizontal());

        //Place a vertical destroyer with the bow at 5,4
        Ship destroyer = new Destroyer();
        int rowB = 5;
        int columnB = 4;
        boolean horizontalB = false;
        destroyer.placeShipAt(rowB, columnB, horizontalB, ocean);
        assertFalse(destroyer.isHorizontal());
    }

    @Test
    void testSetBowRow() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.setBowRow(row);
        assertEquals(row, battleship.getBowRow());

        //More tests
        //Vertical cruiser at 3,1
        Ship cruiser = new Cruiser();
        int rowA = 3;
        int columnA = 1;
        boolean horizontalA = false;
        cruiser.setBowRow(rowA);
        assertEquals(rowA, cruiser.getBowRow());

        //vertical destroyer at 9,7
        Ship destroyer = new Destroyer();
        int rowB = 9;
        int columnB = 7;
        boolean horizontalB = false;
        destroyer.setBowRow(rowB);
        assertEquals(rowB, destroyer.getBowRow());
    }

    @Test
    void testSetBowColumn() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.setBowColumn(column);
        assertEquals(column, battleship.getBowColumn());

        //More tests
        //Vertical cruiser at 3,1
        Ship cruiser = new Cruiser();
        int rowA = 3;
        int columnA = 1;
        boolean horizontalA = false;
        cruiser.setBowColumn(rowA);
        assertEquals(rowA, cruiser.getBowColumn());

        //vertical destroyer at 9,7
        Ship destroyer = new Destroyer();
        int rowB = 9;
        int columnB = 7;
        boolean horizontalB = false;
        destroyer.setBowColumn(columnB);
        assertEquals(columnB, destroyer.getBowColumn());
    }

    @Test
    void testSetHorizontal() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.setHorizontal(horizontal);
        assertTrue(battleship.isHorizontal());

        //More tests
        //Cruiser test with vertical form

        Ship cruiser = new Cruiser();
        int rowA = 2;
        int columnA = 3;
        boolean horizontalA = false;
        cruiser.setHorizontal(horizontalA);
        assertFalse(cruiser.isHorizontal());

        //Destroyer with horizontal form
        Ship destroyer = new Destroyer();
        int rowB = 7;
        int columnB = 3;
        boolean horizontalB = true;
        destroyer.setHorizontal(horizontalB);
        assertTrue(destroyer.isHorizontal());
    }

    @Test
    void testOkToPlaceShipAt() {

        //test when other ships are not in the ocean
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok, "OK to place ship here.");
        //out of the ocean array
        assertFalse(battleship.okToPlaceShipAt(-1, 0, true, ocean));
        //part of battleship out of array, is illegal
        assertFalse(battleship.okToPlaceShipAt(1, 0, true, ocean));
        assertFalse(battleship.okToPlaceShipAt(0, 10, true, ocean));

        //More tests
        //Cruiser
        Ship cruiser = new Cruiser();
        int rowA = 2;
        int columnA = 3;
        boolean horizontalA = true;

        boolean okA = cruiser.okToPlaceShipAt(rowA, columnA, horizontalA, ocean);
        assertTrue(okA, "OK to place ship here.");
        //place ship legally
        cruiser.placeShipAt(rowA, columnA, horizontalA, ocean);

        Ship cruiser2 = new Cruiser();
        int rowC = 2;
        int columnC = 4;
        boolean horizontalC = true;
        //illegal overlapping arrangement
        assertFalse(cruiser2.okToPlaceShipAt(rowC, columnC, horizontalC, ocean));


        //Submarine test
        Ship submarine = new Submarine();
        int rowB = 0;
        int columnB = 3;
        boolean horizontalB = false;
        boolean okB = submarine.okToPlaceShipAt(rowB, columnB, horizontalB, ocean);
        assertTrue(okB, "OK to place ship here.");
        //illegal diagonal arrangement
        assertFalse(submarine.okToPlaceShipAt(1, 2, true, ocean));
        assertFalse(submarine.okToPlaceShipAt(1, 4, true, ocean));
        //illegal vertical arrangement
        assertFalse(submarine.okToPlaceShipAt(3, 3, true, ocean));
    }

    @Test
    void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {

        //test when other ships are in the ocean

        //place first ship
        Battleship battleship1 = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok1, "OK to place ship here.");
        battleship1.placeShipAt(row, column, horizontal, ocean);

        //test second ship
        Battleship battleship2 = new Battleship();
        row = 1;
        column = 4;
        horizontal = true;
        boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
        assertFalse(ok2, "Not OK to place ship vertically adjacent below.");

        //More tests
        //Cruiser test with illegal horizontal arrangement
        Ship cruiser = new Cruiser();
        row = 0;
        column = 7;
        horizontal = true;
        boolean ok3 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
        assertFalse(ok3, "Not OK to place ship vertically adjacent below.");


        //Test for overlapping ships
        //Cruiser test with illegal horizontal arrangement
        Ship destroyer = new Destroyer();
        row = 0;
        column = 3;
        horizontal = false;
        boolean ok4 = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
        assertFalse(ok4, "Not OK to place ship vertically adjacent below.");
    }

    @Test
    void testPlaceShipAt() {

        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, battleship.getBowRow());
        assertEquals(column, battleship.getBowColumn());
        assertTrue(battleship.isHorizontal());

        assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
        assertEquals(battleship, ocean.getShipArray()[0][1]);

        //More tests
        assertFalse(ocean.isOccupied(5, 5));
        assertFalse(ocean.isOccupied(0, 5));
        assertTrue(ocean.isOccupied(0, 4));
        assertTrue(ocean.isOccupied(0, 2));
        assertTrue(ocean.isOccupied(0, 1));

        //Cruiser test in vertical form in different part of ocean
        Ship cruiser = new Cruiser();
        int rowB = 9;
        int columnB = 8;
        boolean horizontalB = false;
        cruiser.placeShipAt(rowB, columnB, horizontalB, ocean);
        assertEquals(rowB, cruiser.getBowRow());
        assertEquals(columnB, cruiser.getBowColumn());
        assertFalse(cruiser.isHorizontal());

        assertTrue(ocean.isOccupied(9, 8));
        assertTrue(ocean.isOccupied(8, 8));
        assertTrue(ocean.isOccupied(7, 8));
        assertFalse(ocean.isOccupied(9, 9));
        assertFalse(ocean.isOccupied(8, 7));
    }

    @Test
    void testShootAt() {

        Ship battleshipOne = new Battleship();
        int row = 0;
        int column = 9;
        boolean horizontal = true;
        battleshipOne.placeShipAt(row, column, horizontal, ocean);

        assertTrue(battleshipOne.shootAt(0, 9));
        boolean[] hitArray0 = {true, false, false, false};
        assertArrayEquals(hitArray0, battleshipOne.getHit());

        //More tests
        assertFalse(battleshipOne.shootAt(5, 8));
        assertFalse(battleshipOne.shootAt(5, 2));
        assertTrue(battleshipOne.shootAt(0, 9));
        assertTrue(battleshipOne.shootAt(0, 8));
        assertTrue(battleshipOne.shootAt(0, 7));
        assertTrue(battleshipOne.shootAt(0, 6));
        //test after the battleship has already been sunk
        //should return false
        assertFalse(battleshipOne.shootAt(8, 0));

        //emptySea test
        Ship emptySea = new EmptySea();
        int rowA = 0;
        int columnA = 0;
        boolean horizontalA = true;
        emptySea.placeShipAt(rowA, columnA, horizontalA, ocean);
        //should be false when shot at empty sea
        assertFalse(emptySea.shootAt(0, 0));


        //Destroyer
        Ship destroyer = new Destroyer();
        int rowB = 4;
        int columnB = 9;
        boolean horizontalB = false;
        destroyer.placeShipAt(rowB, columnB, horizontalB, ocean);

        assertFalse(destroyer.shootAt(1, 9));
        boolean[] hitArray1 = {false, false};
        assertArrayEquals(hitArray1, destroyer.getHit());

        //More tests
        assertFalse(destroyer.shootAt(5, 9));
        assertFalse(destroyer.shootAt(2, 9));
        assertTrue(destroyer.shootAt(4, 9));
        assertTrue(destroyer.shootAt(3, 9));

        //test after the destroyer has already been sunk
        assertFalse(destroyer.shootAt(3, 9));
    }

    @Test
    void testIsSunk() {

        Ship submarine = new Submarine();
        int row = 3;
        int column = 3;
        boolean horizontal = true;
        submarine.placeShipAt(row, column, horizontal, ocean);

        assertFalse(submarine.isSunk());
        assertFalse(submarine.shootAt(5, 2));
        assertFalse(submarine.isSunk());
        assertTrue(submarine.shootAt(3, 3));
        assertTrue(submarine.isSunk());


        //More tests

        //emptySea
        Ship emptySea = new EmptySea();
        assertFalse(emptySea.isSunk());

        //Destroyer
        Ship destroyer = new Destroyer();
        int rowA = 6;
        int columnA = 7;
        boolean horizontalA = true;
        destroyer.placeShipAt(rowA, columnA, horizontalA, ocean);

        assertFalse(destroyer.isSunk());
        //shot adjacent to the bow location
        assertFalse(destroyer.shootAt(6, 8));
        assertFalse(destroyer.isSunk());
        assertTrue(destroyer.shootAt(6, 6));
        assertFalse(destroyer.isSunk());

        //sunk after the second hit
        assertTrue(destroyer.shootAt(6, 7));
        assertTrue(destroyer.isSunk());
    }

    @Test
    void testToString() {

        Ship battleship = new Battleship();
        assertEquals("x", battleship.toString());

        int row = 9;
        int column = 1;
        boolean horizontal = false;
        battleship.placeShipAt(row, column, horizontal, ocean);
        battleship.shootAt(9, 1);
        assertEquals("x", battleship.toString());

        //ship sunk test
        battleship.shootAt(8, 1);
        assertEquals("x", battleship.toString());
        battleship.shootAt(7, 1);
        assertEquals("x", battleship.toString());
        battleship.shootAt(6, 1);
        assertEquals("s", battleship.toString());

        //Submarine hit/sunk test
        Ship submarine = new Submarine();

        int rowA = 1;
        int columnA = 1;
        boolean horizontalA = false;
        submarine.placeShipAt(rowA, columnA, horizontalA, ocean);
        submarine.shootAt(1, 1);
        assertEquals("s", submarine.toString());

        //More tests
        //Empty Sea test

        //not hit yet
        Ship emptySea = new EmptySea();
        int rowB = 9;
        int columnB = 9;
        boolean horizontalB = false;
        emptySea.placeShipAt(rowB, columnB, horizontalB, ocean);
        assertEquals(" .", emptySea.toString());

        //empty sea hit
        emptySea.shootAt(9, 9);
        assertEquals(" -", emptySea.toString());


    }

}