package deepspace;

/**
 * Class to represent a PowerEfficientSpaceStation
 * 
 * A PowerEfficientSpaceStation is the same as a standar SpaceStation, but its
 * protections and fires are incremented by a fixed factor
 *
 * A PowerEfficientSpaceStation cannot transform to SpaceCity, but can convert to BetaPowerEfficientSpaceStation
 *
 *
 * @author Sergio Quijano Rey
 * */
class PowerEfficientSpaceStation extends SpaceStation{
    // Private attributes
    //==========================================================================
    private final float EFFICIENCYFACTOR = (float) 1.10;
    
    // Constructors
    //==========================================================================
    public PowerEfficientSpaceStation(SpaceStation station){
        super(station);
    }
    
    // Getters
    //==========================================================================
    /**
     * Gets the string representation
     * @return the string representation
     * */
    @Override
    public String toString(){
        return  "EFFICIENTSTATION\n[[" + super.toString() + 
                "EFFICIENCYFACTOR = " + EFFICIENCYFACTOR +
                "\n]]";
    }

    // Setters
    //==========================================================================
    /**
     * The station fires with its power incremented by EFFICIENCYFACTOR factor
     * @return the power of the shot
     * */
    @Override
    public float fire(){
        return super.fire() * EFFICIENCYFACTOR;

    }

    /**
     * The station protects with its shield power incremented by EFFICIENCYFACTOR factor
     * @return the power of the protection
     * */
    @Override
    public float protection(){
        return super.protection() * EFFICIENCYFACTOR;
    }

    /**
     * The station receives a loot
     * We avoid transformations
     * @param lo, the received loot
     * @return Transformation.GETEFFICIENT, if we are going to transform to a BetaPowerEfficientSpaceStation
     *         Transformation.NWEAPONSPROB, otherwise
     * */
    @Override
    public Transformation setLoot(Loot lo){
        Transformation trans = super.setLoot(lo);

        // We check if we transform to BetaPowerEfficientSpaceStation
        if(trans == Transformation.GETEFFICIENT){
            return Transformation.GETEFFICIENT;
        }else{
            return Transformation.NOTRANSFORM;
        }
    }


}
