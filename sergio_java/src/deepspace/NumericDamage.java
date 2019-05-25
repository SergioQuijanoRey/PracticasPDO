/**
 * Implements a damage
 * @author Sergio Quijano Rey
 * */

package deepspace;

import java.util.ArrayList;

/**
 * Numeric Damage is a damage composed by the number of shields and weapons to loose
 * @author Sergio Quijano Rey
 * */
class NumericDamage extends Damage{
    // Private attributes
    //==========================================================================
    int nWeapons;   // The number of weapons to loose

    // Constructors
    //==========================================================================
    /**
     * Constructor of the class
     * @param w,  the number of weapons to loose
     * @param s, the number of shields to loose
     * */
    public NumericDamage(int w, int s){
        super(s);
        nWeapons = w;
    }

    /**
     * Copy constructor
     *
     * @param other, the other object to copy
     * */
    public NumericDamage(NumericDamage other){
        this(other.getNWeapons(), other.getNShields());
    }
    
    /**
     * Creates a copy of the damage
     *
     * @return the copy of the damage
     * */
    @Override
    public NumericDamage copy(){
        return new NumericDamage(this);
    }

    // GETTERS
    //==========================================================================

    /**
     * Getter for nWeapons
     * @return nWeapons
     * */
    public int getNWeapons(){
        return nWeapons;
    }

    /**
     * Checks if the damage has no effect
     * @return true, if the damage has no effect
     *         false, if the damage has an effect
     * */
    @Override
    public boolean hasNoEffect(){
        return getNWeapons() == 0 && getNShields() == 0;
    }

    /**
     * Get the string representation of the object
     * @return the string representation
     * */
    @Override
    public String toString(){
        return  "Damage(" +
                "nWeapons = " + getNWeapons() +
                ", nShields = " + getNShields() +
                ")";
    }

    /**
     * Gets the UI representation of the object
     * @return the UI representation
     * */
    @Override
    public NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
    }

   
    // SETTERS
    //==========================================================================
    
    /**
     * Adjust the damage to a given list of weapons and shields 
     * That is to say, the number of lost shields and weapons is the min of that 
     * number in the object and the sizes of the given lists
     *
     * @param w, the list of weapons to adjust
     * @param s, the list of shields to adjust
     * @return a new NumericDamage with the adjusted version as specified above
     * */
    @Override
    public NumericDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        // New number of shields is get, as much shields lost as s indicates
        int new_shields = Math.min(s.size(), getNShields());
        int new_weapon_number = Math.min(w.size(), getNWeapons());

        // The adjusted NumericDamage is retured
        return new NumericDamage(new_weapon_number, new_shields);
    }

    /**
     * Discards a weapon from the damage
     * That is to say, the number of weapons to loose is decreased
     *
     * @param w, the weapon to loose (useless)
     * */
    @Override
    public void discardWeapon(Weapon w){
        if(getNWeapons() < 1){
            System.out.println("Warning! No weapons to discard at Damage.discardWeapon()");
            System.out.println("Nothing is done");
        }else{
            nWeapons = getNWeapons() - 1;
        }
    }

}
