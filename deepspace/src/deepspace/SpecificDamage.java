/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class SpecificDamage extends Damage {
    /**
     * Array of types of weapons that will be lost.
     */
    private ArrayList<WeaponType> weapons;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer.
     * @param _weapons array of types of weapons that will be lost
     * @param _nShields number of shields that will be lost
     */
    SpecificDamage(ArrayList<WeaponType> _weapons, int _nShields) {
        super(_nShields);
        weapons = _weapons;
    }
    
    /**
     * Copy constructor.
     * @param d instance which is going to be copied
     */
    SpecificDamage(SpecificDamage d) {
        super(d);
        weapons = d.weapons;
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Copy getter.
     * @return a copy of the instance
     */
    public SpecificDamage copy() {
        ArrayList<WeaponType> weapon_copy = new ArrayList<>();
        for ( WeaponType wt : weapons )
            weapon_copy.add(wt);
        
        return new SpecificDamage(weapon_copy, getNShields());
    }
    /**
     * Getter for weapons.
     * @return weapons
     */
    public ArrayList<WeaponType> getWeapons() {
        return weapons;
    }
    
    /**
     * Checks whether the damage is affecting or not.
     * @return true, if damage has effect;
     *         false, if damage has no effect
     */
    @Override
    public boolean hasNoEffect() {
        return getNShields() + weapons.size() == 0;
    }
    
    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------

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
    
    /**
     * Creates a copy of current objet where weapons and shields which are
     * not included in arrays given as parameters are discarded. That's to say,
     * we shrink the Damage to the parameters
     * @param w weapons to fit
     * @param s shields to fit
     * @return a copy of the object adjusted as explained above
     */
    @Override
    public SpecificDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s) {
        int new_nShields;
        
        if ( s.size() > getNShields() )
            new_nShields = getNShields();
        else
            new_nShields = s.size();
        
        ArrayList<WeaponType> new_weapons = new ArrayList<>();
        
        for ( WeaponType wt : weapons )
            if ( arrayContainsType(w, wt) != -1 ) {
                new_weapons.add(wt);
            }
        
        return new SpecificDamage(new_weapons, new_nShields);
    }
    
    /**
     * Removes a given type of weapon.
     * The number of weapons decreases by 1
     * @param w weapon whose type wants to be removed
     */
    @Override
    public void discardWeapon(Weapon w) {
        if ( weapons.size() != 0 ) {
            int position = weapons.indexOf(w.getType());
            if ( position != -1 )
                weapons.remove(position);
        }
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object.
     * @return string representation
     */
    public String toString() {
        String message = "[NumericDamage] -> "
                + "Number of shields: " + getNShields()
                + ", Weapons: " + weapons.toString();
        return message;
    }
    
    /**
     * To UI.
     */
    SpecificDamageToUI getUIversion() {
        return new SpecificDamageToUI(this);
    }
}
