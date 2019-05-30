/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents damages done on a spaceship after loosing a combat.
 * They indicate which elements are going to be lost after losing the combat.
 * 
 * A damage is composed by a number of shields to lose and:
 *   - A number of weapons to lose (class NumericDamage)
 *   - A list of weapons to lose (class SpecificDamage)
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
abstract class Damage {
    /**
     * Number of shields that will be lost.
     */
    private int nShields;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer.
     * @param _nShields number of shields that will be lost
     */
    Damage(int _nShields) {
        nShields = _nShields;
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Copy getter.
     * @return a copy of the instance
     */
    public abstract Damage copy();
    
    /**
     * Getter for nShields
     * @return nShields
     */
    public int getNShields() {
        return nShields;
    }
    
    /**
     * Checks whether the damage is affecting or not.
     * @return true, if damage has effect;
     *         false, if damage has no effect
     */
    public abstract boolean hasNoEffect();

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
    public abstract Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);
    
    /**
     * Removes a given type of weapon.
     * If a list of weapons is not available (object is Numeric-constructed
     * instead of Specific-construced), the number of weapons decreases by 1
     * @param w weapon whose type wants to be removed
     */
    public abstract void discardWeapon(Weapon w);
    
    /**
     * Reduces by 1 the number of shield boosters to be removed.
     */
    public void discardShieldBooster() {
        if ( nShields > 0 )
            nShields--;
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object.
     * @return string representation
     */
    public abstract String toString();
    
    /**
     * To UI.
     */
    abstract public DamageToUI getUIversion();
}
