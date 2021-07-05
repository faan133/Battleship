package battleship;

public class EmptySea extends Ship {

    //type of the ship or empty sea
    private static final String SHIP_TYPE= "empty";

    //length of the empty sea
    private static int SHIP_LENGTH = 1;

    //keeps track whether an empty sea location has been shot at
    private boolean shotLocation = false;

    private String type;
    //EmptySea constructor; sets the length variable to 1
    //by calling the constructor in the super class
    public EmptySea() {
        // call the superclass default constructor to initialize length
        super(EmptySea.SHIP_LENGTH);
        this.type = EmptySea.SHIP_TYPE;
    }


    //EmptySea methods

    /**
     *
     * @param row
     * @param column
     * @returns false when nothing is hit
     */
    @Override
    boolean shootAt(int row, int column) {
        // location has been shot but no ship there
        shotLocation = true;
        return false;
    }

    /**
     *
     * @returns false to indicate that you didn't sink anything
     */
    @Override
    boolean isSunk() {
        return false;
    }

    /**
     * returns single character "-" for the Ocean print method
     */
    @Override
    public String toString() {
        if (shotLocation) {
            return " -";
        } else {
            return " .";
        }

    }

    /**
     *
     * @return the string "empty"
     */
    @Override
    public String getShipType() {
        return SHIP_TYPE;
    }

}
