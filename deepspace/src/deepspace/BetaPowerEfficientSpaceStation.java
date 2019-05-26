/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * Represents damages done on a spaceship after loosing a combat.
 * They indicate which elements are going to be lost after losing the combat
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation {
    /**
     * Extra efficiency for beta efficient.
     */
    private final float EXTRAEFFICIENCY = 1.2f;
    
    /**
     * Random utilities.
     */
    private Dice dice;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer.
     * @param station basic space station to convert to beta efficient
     */
    BetaPowerEfficientSpaceStation(SpaceStation station) {
        super(station);
        dice = new Dice();
    }
    
    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------
    
    /**
     * Makes a shot.
     * @return the shot power
     */
    @Override
    public float fire() {
        if ( dice.extraEfficiency() )
            return super.fire()*EXTRAEFFICIENCY;
        else
            return super.fire();            
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object.
     * @return string representation
     */
    @Override
    public String toString() {
        String message = "(Beta)" + super.toString();
        return message;
    }
    
    /**
     * To UI.
     */
    @Override
    public BetaPowerEfficientSpaceStationToUI getUIversion() {
        return new BetaPowerEfficientSpaceStationToUI(this);
    }
}
