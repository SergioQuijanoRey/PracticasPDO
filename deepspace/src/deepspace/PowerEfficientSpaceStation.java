package deepspace;

/**
 * Power efficient space station: gives more shot power and protection than a
 * usual space station.
 * 
 * @author Miguel Ángel Fernández Gutiérrez
 * @author Sergio Quijano Rey
 */
public class PowerEfficientSpaceStation extends SpaceStation {
    /**
     * Efficiency factor for power efficient space station.
     */
    private static final float EFFICIENCYFACTOR = 1.1f;
    
    /**
     * Class initializer.
     * @param station basic space station to convert to power efficient
     */
    PowerEfficientSpaceStation(SpaceStation station) {
        super(station);
    }
    
    /**
     * Make a shot.
     * @return the shot power
     */
    @Override
    public float fire() {
        return super.fire()*EFFICIENCYFACTOR;
    }
    
    /**
     * Use protection shield.
     * @return the shield's energy
     */
    @Override
    public float protection() {
        return super.protection()*EFFICIENCYFACTOR;
    }
    
    /**
     * Receives a loot.
     * @param loot loot to be received
     */
    @Override
    public Transformation setLoot(Loot loot) {
        Transformation trans = super.setLoot(loot);
        
        if ( trans == Transformation.SPACECITY )
            return Transformation.NOTRANSFORM;
        else
            return trans;
    }
    
    /**
     * To UI.
     */
    @Override
    public PowerEfficientSpaceStationToUI getUIversion() {
        return new PowerEfficientSpaceStationToUI(this);
    }
}
