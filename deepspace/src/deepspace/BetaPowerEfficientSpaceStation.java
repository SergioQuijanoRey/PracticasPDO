package deepspace;

/**
 * Class to represent an improved EfficientSpaceStation
 *
 * @author Sergio Quijano Rey
 * */
class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation{
    // Private attributes
    //==========================================================================
    private final float EXTRAEFFICIENCY = (float)1.2;
    private Dice dice;

    // Constructors
    //==========================================================================
    /**
     * Constructor of the SpaceStation
     * @param station, the base space station which is transformed to BetaPowerEfficientSpaceStation
     * */
    public BetaPowerEfficientSpaceStation(SpaceStation station){
        // We call the PowerEfficientStation constructor
        super(station);

        // The dice for taking decissions
        dice = new Dice();
    }

    // Getters
    //==========================================================================
    /**
     * The station fires
     * There is a chance of incrementing the fire power by a EXTRAEFFICIENCY factor
     *
     * @return the power of the shot
     * */
    @Override
    public float fire(){
        if(dice.extraEfficiency()){
            return super.fire() * EXTRAEFFICIENCY;
        }else{
            return super.fire();
        }
    }

    /**
     * Gets the string representation
     * @return the string representation
     * */
    @Override
    public String toString(){
        return  "BetaPowerEfficientSpaceStation[[\n" +
                super.toString() + "\n" +
                "\tEXTRAEFFICIENCY = " + EXTRAEFFICIENCY +
                "]]\n";


    }

}
