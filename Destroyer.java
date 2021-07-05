package battleship;

public class Destroyer extends Ship {

    //static final variables for ship type and ship length
    private static final String SHIP_TYPE= "destroyer";
    private String type;
    private static final int SHIP_LENGTH = 2;

    /**
     * Constructor for Destroyer
     */
    public Destroyer() {
        super(Destroyer.SHIP_LENGTH);
        //set type of ship to default value
        this.type = Destroyer.SHIP_TYPE;
    }
    /**
     * returns the String destroyer
     */
    @Override
    public String getShipType() {
        return SHIP_TYPE;
    }
}
