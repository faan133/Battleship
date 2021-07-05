package battleship;

public class Submarine extends Ship {
    //type of ship or submarine
    private static final String SHIP_TYPE= "submarine";
    private String type;
    //length of submarine is 1
    private static final int SHIP_LENGTH = 1;

    /**
     * Submarine constructor
     */
    public Submarine() {
        super(Submarine.SHIP_LENGTH);

        //set type of ship to default value
        this.type = Submarine.SHIP_TYPE;
    }
    /**
     * returns the String submarine
     */
    @Override
    public String getShipType() {
        return SHIP_TYPE;
    }

}