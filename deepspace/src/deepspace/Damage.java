/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents damages done on a spaceShip after loosing a combat.
 * They indicate which elements are going to be lost after loosing the combat
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
    private ArrayList<WeaponType> weapons = new ArrayList<>();
    
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
     * @param _weapons
     * @param _nShields
     */
    Damage(ArrayList<WeaponType> _weapons, int _nShields) {
        nWeapons = -1;  // used for distinction from types Numeric and Specific
        nShields = _nShields;
        
        _weapons.forEach( weapon -> {
            weapons.add(weapon);
        });
    }
    
    /**
     * Copy constructor
     * @param d instance which is going to be copied
     */
    Damage(Damage d) {
        nWeapons = d.nWeapons;
        nShields = d.nShields;
        
        d.weapons.forEach( weapon -> {
            weapons.add(weapon);
        });
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    public int getNWeapons() {
        return nWeapons;
    }
    
    public int getNShields() {
        return nShields;
    }
    
    public ArrayList<WeaponType> getWeapons() {
        // Reference protection
        ArrayList<WeaponType> weapons_copy = new ArrayList<>();
        
        weapons.forEach( weapon -> {
            weapons_copy.add(weapon);
        });
        
        return weapons_copy;
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
     * we srink the Damage to the parameters
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
    
    
}
