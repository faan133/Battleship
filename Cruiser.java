package battleship;

public class Cruiser extends Ship {

    private static final String SHIP_TYPE= "cruiser";
    private String type;
    public static final int SHIP_LENGTH = 3;
    /**
     * constructor for Cruiser
     */
    public Cruiser() {
        super(Cruiser.SHIP_LENGTH);
        //set type of ship to default value
        this.type = Cruiser.SHIP_TYPE;
    }


    /**
     * returns the String cruiser
     */
    @Override
    public String getShipType() {
        return SHIP_TYPE;
    }

}