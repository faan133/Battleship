package battleship;

public class Battleship extends Ship {
    //Type of ship
    private static final String SHIP_TYPE = "battleship";
    private String type;
    //length of battleship
    private static final int SHIP_LENGTH = 4;

    //zero-argument constructor for Battleship setting length variable to correct value
    public Battleship() {

        //calling the constructor in the super class with hard-coded  length value for each ship
        super(Battleship.SHIP_LENGTH);
        //set type of ship to default value
        this.type = Battleship.SHIP_TYPE;

    }



    /**
     * returns the String battleship
     */
    @Override
    public String getShipType() {
        return SHIP_TYPE;
    }
}
