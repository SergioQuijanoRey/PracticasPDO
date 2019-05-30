/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 * Numeric Damage is a damage composed by
 *  - the number of shields to lose
 *  - the number of weapons to lose
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class NumericDamage extends Damage {
    /**
     * Number of weapons that will be lost.
     */
    private int nWeapons;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer.
     * @param _nWeapons number of weapons that will be lost
     * @param _nShields number of shields that will be lost
     */
    NumericDamage(int _nWeapons, int _nShields) {
        super(_nShields);
        nWeapons = _nWeapons;
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Copy getter.
     * @return a copy of the instance
     */
    @Override
    public NumericDamage copy() {
        return new NumericDamage(nWeapons, getNShields());
    }
    
    /**
     * Getter for nWeapons.
     * @return nWeapons
     */
    public int getNWeapons() {
        return nWeapons;
    }
    
    /**
     * Checks whether the damage is affecting or not.
     * @return true, if damage has effect;
     *         false, if damage has no effect
     */
    @Override
    public boolean hasNoEffect() {
        return getNShields() + nWeapons == 0;
    }
    
    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------
    
    /**
     * Creates a copy of current objet where weapons and shields which are
     * not included in arrays given as parameters are discarded. That's to say,
     * we shrink the Damage to the parameters
     * @param w weapons to fit
     * @param s shields to fit
     * @return a copy of the object adjusted as explained above
     */
    @Override
    public NumericDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        int new_nWeapons, new_nShields;
        
        if ( w.size() > nWeapons )
            new_nWeapons = nWeapons;
        else
            new_nWeapons = w.size();
        
        if ( s.size() > getNShields() )
            new_nShields = getNShields();
        else
            new_nShields = s.size();
        
        return new NumericDamage(new_nWeapons, new_nShields);
    }
    
    /**
     * Removes a given type of weapon.
     * The number of weapons decreases by 1
     * @param w weapon whose type wants to be removed
     */
    @Override
    public void discardWeapon(Weapon w) {
        if ( nWeapons > 0 )
            nWeapons--;
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
        String message = "[NumericDamage] -> "
                + "Number of shields: " + getNShields()
                + ", Number of weapons: " + nWeapons;
        return message;
    }
    
    /**
     * To UI.
     */
    @Override
    public NumericDamageToUI getUIversion() {
        return new NumericDamageToUI(this);
    }
}
