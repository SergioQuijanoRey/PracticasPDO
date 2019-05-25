package deepspace;

import java.util.ArrayList;

/**
 * Class to represent the damage produced when loosing vs an enemy starship
 *
 * A damage is composed by a number of shields to loose and:
 *  - A number of weapons to loose (class NumericDamage)
 *  - A list of weapons to loose (class SpecificDamage)
 *
 * @author Sergio Quijano Rey
 * */
abstract class Damage{

    // Private attributes
    //==========================================================================
    private int nShields;                       //< Number of shields to loose  

    // Constructors
    //==========================================================================
    /**
     * Base constructor for the class
     *
     * @param s, the number of shields to loose
     * */
    Damage(int s){
        nShields = s;
    }

    /**
     * Creates a copy of a damage object
     *
     * @return a copy of the object
     * */
    public abstract Damage copy();

    // Getters
    //==========================================================================
    /**
     * Getter for nShields
     * @return nShields
     * */
    public int getNShields(){
        return nShields;
    }

    /**
     * Checks if the damage has no effect
     * @return true, if the damage has no effect
     *         false, if the damage has an effect
     * */
    public abstract boolean hasNoEffect();

    /**
     * Get the string representation of the object
     * @return the string representation
     * */
    @Override
    public abstract String toString();

    /**
     * @brief Gets UI representation of the object
     *
     * @return the UI representation
     * */
    public abstract DamageToUI getUIversion();

    // Setters
    //==========================================================================
    /**
     * Adjust the damage to a given weapons and shields
     * That is to say, the damage is adjusted to the intersection of the damage 
     * and the given damage as two list parameters
     *
     * @param w, the weapon list to adjust
     * @param s, the shiedl list to adjust
     * @return the adjusted damage
     * */
    public abstract Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);


    /*
     * A weapon is discarded from this damage (the damage is reduced)
     * @param w the weapon to discard
     * */
    public abstract void discardWeapon(Weapon w);;
    
    /**
     * Discards a shieldBooster from damage
     * That is to say, nShields is decremented
     * */
    public void discardShieldBooster(){
        if(nShields == 0){
            System.out.println("Warning! There are no shields to discard, at Damage.discardShieldBooster()");
            System.out.println("Nothing is done");
        }else{
            nShields = nShields - 1;
        }
    }
}
