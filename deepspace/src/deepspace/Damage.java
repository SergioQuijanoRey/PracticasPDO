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
 * They indicate which elements are going to be lost after losing the combat
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class Damage {
    /**
     * Number of shields that will be lost
     */
    private int nShields;
    
    /**
     * Number of weapons that will be lost
     */
    private int nWeapons;
    
    /**
     * Array of weapons that will be lost
     */
    private ArrayList<WeaponType> weapons;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer type Numeric.
     * Initializes to use the class with a number of weapons and a number of shields
     * @param _nWeapons number of weapons that will be lost
     * @param _nShields number of shields that will be lost
     */    
    Damage(int _nWeapons, int _nShields) {
        nWeapons = _nWeapons;
        nShields = _nShields;
    }
    
    /**
     * Class initializer type Specific.
     * Initializes to use the class with an array of weapons and a number of shields
     * @param _weapons array of weapons that will be lost
     * @param _nShields number of shields that will be lost
     */
    Damage(ArrayList<WeaponType> _weapons, int _nShields) {
        nWeapons = -1;  // used for distinction from types Numeric and Specific
        nShields = _nShields;
        weapons = _weapons;
    }
    
    /**
     * Copy constructor
     * @param d instance which is going to be copied
     */
    Damage(Damage d) {
        nWeapons = d.nWeapons;
        nShields = d.nShields;
        if ( nWeapons == -1 ) {
            weapons = d.weapons;
        }
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Getter for nShields
     * @return nShields
     */
    public int getNShields() {
        return nShields;
    }
    
    /**
     * Getter for nWeapons
     * @return nWeapons
     */
    public int getNWeapons() {
        return nWeapons;
    }
    
    /**
     * Getter for weapons
     * @return weapons
     */
    public ArrayList<WeaponType> getWeapons() {
        return weapons;
    }
    
    /**
     * Checks whether the damage is affecting or not
     * @return true, if damage has effect;
     *         false, if damage has no effect
     */
    public boolean hasNoEffect() {
        if ( nWeapons == -1 )
            // Specific-constructed object
            return nShields + weapons.size() == 0;
        else
            // Numeric-constructed object
            return nShields + nWeapons == 0;
    }
    
    /**
     * Searches the first element of WeaponType array to match a given type
     * @param w the array of weapon types where we search
     * @param t the type we're looking for
     * @return position, if the element is found;
     *         -1, if the element is not found
     */    
    private int arrayContainsType(ArrayList<Weapon> w, WeaponType t) {
        Iterator<Weapon> it = w.iterator();
        int i = 0;
        
        Weapon weapon_aux = (Weapon) it;
        
        while ( it.hasNext() && weapon_aux.getType() != t ) {
            it.next();
            weapon_aux = (Weapon) it;
            i++;
        }
        
        if ( it.hasNext() )
            return i;
        else
            return -1;
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
    public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        // we check how the object has been constructed:
        //   if nWeapons == -1, it is a Specific-constructed object;
        //   else, it is a Numeric-constructed object
        if ( nWeapons == - 1 ) {
            // Specific-constructed
            
            ArrayList<Weapon> weapons_copy = new ArrayList<>();
            
            //WIP -- TERMINAR
            
            ArrayList<WeaponType> new_weapons = new ArrayList<>();
            int new_nShields;
            
            // we compute the intersection
            w.forEach(weapon -> {
                if ( arrayContainsType(w, weapon.getType()) != -1 )
                    new_weapons.add(weapon.getType());
            });
            
            if ( s.size() > nShields )
                new_nShields = nShields;
            else
                new_nShields = s.size();
            
            return new Damage(new_weapons, new_nShields);
        } else {
            // Numeric-constructed
            
            int new_nWeapons, new_nShields;
            
            if ( w.size() > nWeapons )
                new_nWeapons = nWeapons;
            else
                new_nWeapons = w.size();
            
            if ( s.size() > nShields )
                new_nShields = nShields;
            else
                new_nShields = s.size();
            
            return new Damage(new_nWeapons, new_nShields);
        }
    }
    
    /**
     * Removes a given type of weapon.
     * If a list of weapons is not available (object is Numeric-constructed
     * instead of Specific-construced), the number of weapons decreases by 1
     * @param w weapon whose type wants to be removed
     */
    public void discardWeapon(Weapon w) {
        if ( nWeapons == -1 ) {
            // object is Specific-constructed
            if ( weapons.size() != 0 ) {
                int position = weapons.indexOf(w.getType());
                if ( position != -1 )
                    weapons.remove(position);
            }
        } else {
            // object is Numeric-construced
            if ( nWeapons > 0 )
                nWeapons--;
        }
    }
    
    /**
     * Reduces by 1 the number of shield boosters to be removed
     */
    public void discardShieldBooster() {
        if ( nShields > 0 )
            nShields--;
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object
     * @return string representation
     */
    public String toString() {
        String message = "[Damage ";
        if ( nWeapons == -1 )
            message = message + " (Specific-constructed)] -> "
                    + "Number of shields: " + nShields
                    + ", Weapons: " + weapons.toString();
        else
            message = message + " (Numeric-constructed)] -> "
                    + "Number of shields: " + nShields
                    + ", Number of weapons: " + nWeapons;
        return message;
    }
    
    /**
     * To UI
     */
    DamageToUI getUIversion() {
        return new DamageToUI(this);
    }
}
