//Name: Angela Fan
//ID: 48779009

package battleship;

public abstract class Ship {

    /**
     * Row 0-9 which has the bow/front of the ship
     */
    private int bowRow;

    //Column which has the bow/front of the ship
    private int bowColumn;
    //length of the ship
    private int length;

    //shows whether ship is horizontal or not
    private boolean horizontal;

    //array of booleans which show the part of ship that has been hit
    private boolean[] hit;

    /**
     * Constructor for the Ship class
     * sets the length property of the ship and initializes the hit array based on that length
     * @param length
     */
    public Ship(int length) {
        //sets the length property of the ship
        this.length = length;

        //initializes the hit array based on length
        this.hit = new boolean[length];


    }
    /**
     *
     * @returns the ship length
     */
    public int getLength() {

        //return the ship length
        return this.length;
    }

    /**
     *
     * @returns the bow row location
     */
    public int getBowRow() {
        return this.bowRow;
    }


    /**
     * Gets the value of the bow column
     * @returns bow column location
     */
    public int getBowColumn() {
        return this.bowColumn;
    }


    /**
     * Gets the hit array
     * @returns the hit array
     */
    public boolean[] getHit() {
        return this.hit;
    }


    /**
     *
     * @returns whether the ship is horizontal or not
     * true if it is horizontal
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * Sets the value of bowRow
     * @param row (numbered 0-9)
     */
    public void setBowRow(int row) {
        this.bowRow = row;
    }


    /**
     * Sets the value of bowColumn
     * @param column (numbered 0-9)
     */
    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    /**
     * Sets the value of the instance variable horizontal
     * @param horizontal
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     *
     * @returns the type of ship as a String
     */
    public abstract String getShipType();


    /**
     * Returns true if it is ok to put a ship of the length with
     * its bow in the location. False when it is not ok to put a ship
     * of this length with its bow in the location.
     * The ship cannot overlap/touch another ship (vertical, horizontal, diagonally)
     * It cannot 'stick out' beyond array
     * @param bow's row, an integer from 0 to 9
     * @param bow's column, an integer from 0 to 9
     * @param horizontal which represents whether the Ship is horizontal
     * @param ocean which is an Ocean object
     * @return True if it is ok to place the ship and false if it is not ok to place the ship
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        int shipLength = this.getLength();


        if (horizontal) {
            //cannot stick beyond the array (based on row/column)
            if (column > 9 || column < 0 || row > 9 || row < 0) {
                return false;
            }

            //cannot partially stick beyond the array
            int backOfShipColumn = column - shipLength +1;
            if (backOfShipColumn > 9 || backOfShipColumn < 0) {
                return false;
            }

            for (int startColumn = column - shipLength; startColumn <= column + 1; startColumn++) {
                //check each row to see if there are any overlaps or possible illegal adjacent occupants

                //if any of the row/column are occupied, returns false
                boolean lowerMostRow = ocean.isOccupied(row+1, startColumn);
                boolean middleRow = ocean.isOccupied(row, startColumn);
                boolean upperMostRow = ocean.isOccupied(row -1, startColumn);
                if (lowerMostRow || middleRow || upperMostRow) {
                    return false;
                }

            }
            return true;

        } else {
            //cannot stick beyond the array
            if (row > 9 || row < 0 || column > 9 || column <0) {
                return false;
            }
            //cannot partially stick beyond the array
            int backOfShipRow = row - shipLength +1;
            if (backOfShipRow > 9 || backOfShipRow < 0) {
                return false;
            }
            //startRow is row adjacent to back of the ship

            for (int startRow = row -shipLength; startRow <= row + 1; startRow++) {
                //check each row to see if there are any overlaps or possible illegal adjacent occupants
                //if any of the row/column are occupied, returns false
                boolean leftColumn = ocean.isOccupied(startRow, column -1);
                boolean middleColumn = ocean.isOccupied(startRow, column);
                boolean RightColumn = ocean.isOccupied(startRow, column + 1);
                if (leftColumn || middleColumn|| RightColumn) {
                    return false;
                }
            }
            return true;
        }


    }

    /**
     * Puts ship in the ocean. Gives values to bowRow, bowColumn
     * and horizontal instance variables in the ship
     * Puts a reference to the ship
     * @param row or the bowRow
     * @param column or the bowColumn
     * @param horizontal instance variables of the ship
     * @param ocean which is an Ocean object
     */
    void placeShipAt(int row, int column,boolean horizontal, Ocean ocean) {
        Ship[][] ships = ocean.getShipArray();
        //set values for instance variables horizontal, bowRow, and bowColumn
        this.setHorizontal(horizontal);
        this.setBowRow(row);
        this.setBowColumn(column);
        int shipLength = this.getLength();

        //for horizontal, place the bow so that it faces the East and the new Column is the back of the boat

        if (this.horizontal) {
            //column corresponding to backOfShip is bowColumn - shipLength+1
            int backOfShip = column - shipLength +1;

            for (int index = column; index >= backOfShip; index--) {
                ships[row][index] = this;
            }

            //if vertical, then place the bow at the bottom end while new row is the back of the boat
        } else {
            int backOfShip = row - shipLength + 1;
            for (int index = row; index >= backOfShip; index--) {

                ships[index][column] = this;
            }
        }


    }

    /**
     * If a part of the ship is occupying the row and column, and the ship hasn't been sunk
     * mark the part of the ship as 'hit'
     * @param row
     * @param column
     * @return true if part of ship occupies given row and column, and hasn't been sunk
     * otherwise false
     */
    boolean shootAt(int row, int column) {
        int partOfShip = 0;
        boolean spaceIsOccupied = false;
        int shipLength = this.getLength();

        //when the ship has not been sunk and is horizontal
        if (!this.isSunk() && this.horizontal) {

            for (int index = bowColumn; index >= bowColumn - shipLength + 1; index--) {
                //System.out.println("bowColumn: "+ bowColumn);
                //System.out.println("Column: "+ column);
                //make sure that row is the same as bowRow and column is among the same range of values
                if (column == index && row == bowRow) {
                    partOfShip = bowColumn - column;
                    spaceIsOccupied = true;
                    break;

                }

            }

            if (spaceIsOccupied && !this.isSunk()) {
                //System.out.println("part of ship: " + partOfShip);
                hit[partOfShip] = true;
                //if sunk after the "last" hit, print message
                if (this.isSunk()) {
                    //prints the message when the ship is sunk (all parts of ship have been hit)
                    System.out.println("You just sank a " + this.getShipType());
                }
                return true;

            }

            // when the ship is vertical
        } else if (!this.isSunk() && !this.horizontal) {
            for (int index = bowRow; index >= bowRow - shipLength + 1; index--) {
                //make sure that row is the same as bowColumn and row is among the same range of values
                if (row == index && column == bowColumn) {
                    partOfShip = bowRow - row;
                    spaceIsOccupied = true;
                    break;

                }
            }

            if (spaceIsOccupied && !this.isSunk()) {
                hit[partOfShip] = true;
                if (this.isSunk()) {
                    //prints the message when the ship is sunk (all parts of ship have been hit)
                    System.out.println("You just sank a " + this.getShipType());
                }
                return true;

            }

            //Otherwise ship is sunk or no ship occupies the row/column
        }
        return false;
    }

    /**
     * Checks to see if the ship is sunken
     * @return true when ship is sunk (all parts of ship have been hit)
     * returns false if not sunk
     */
    boolean isSunk() {

        for (int index =0; index < length; index++) {
            if (hit[index] == false) {
                return false;
            }
        }

        return true;

    }

    /**
     *
     * Returns single character String to use in the Ocean's print method
     * Returns "s" if the ship has been sunk
     * Returns "x" if the ship has not been sunk
     */
    @Override
    public String toString() {
        if (this.isSunk()) {
            return "s";
        } else {
            return "x";
        }

    }




}



