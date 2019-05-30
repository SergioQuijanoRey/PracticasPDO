package deepspace;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * SpecificDamage is a damage composed by 
 *  - the number of shields to lose
 *  - a list of weapon types to lose
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class SpecificDamage extends Damage{
    /**
     * Array of types of weapons that will be lost.
     */
    ArrayList<WeaponType> weapons;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer.
     * @param _weapons array of types of weapons that will be lost
     * @param _nShields number of shields that will be lost
     */
    public SpecificDamage(ArrayList<WeaponType> _weapons, int _nShields){
        super(_nShields);
        if( _weapons != null )
            weapons = new ArrayList<>(_weapons);    // Secure copy
        else
            weapons = new ArrayList<>(0);           // Security check
    }

    /**
     * Copy constructor.
     * @param other instance which is going to be copied
     */
    public SpecificDamage(SpecificDamage other){
        this(other.getWeapons(), other.getNShields());

    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Copy getter.
     * @return a copy of the instance
     */
    @Override
    public SpecificDamage copy(){
        return new SpecificDamage(this);
    }
    
    /**
     * Getter for weapons
     * @return a copy (secure return) of weapons
     **/
    public ArrayList<WeaponType> getWeapons(){
        if ( weapons != null )
            return new ArrayList<WeaponType>(weapons);
        else {
            System.out.println("Returning null weapons at Damage.getWeapons()");
            System.out.println("Returning instead an empty ArrayList<WeaponType>");
            return new ArrayList<WeaponType>(0);
        }
    }

    /**
     * Checks if the damage has no effect
     * @return true, if the damage has no effect
     *         false, if the damage has an effect
     **/
    @Override
    public boolean hasNoEffect(){
        if ( weapons != null )
            return weapons.size() == 0 && getNShields() == 0;
        else
            return getNShields() == 0;
    }

    
    /**
     * Get the string representation of the object
     * @return the string representation
     **/
    @Override
    public String toString(){
        return  "Damage(" +
                "weapons = " + weapons +
                ", nShields = " + getNShields() +
                ")";
    }

    /**
     * Gets the UI version of the object
     *
     * @return the UI version of the object
     * */
    @Override
    public SpecificDamageToUI getUIversion(){
        return new SpecificDamageToUI(this);
    }
   
    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------
    
    /**
     * Adjusts the damage to a given weapons and shields
     * The number of shields is decreased depending of the given list of shields
     * The weapons which are not the same type as given as a parameter are removed
     *
     * @param w, the list of weapons to adjust
     * @param s, the list of shields to adjust
     * @return a new SpecificDamage adjusted as specified above
     **/
    @Override
    public SpecificDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        // New number of shields is get, as much shields lost as s indicates
        int new_shields = Math.min(s.size(), getNShields());

            // New weapon_type list is get
            ArrayList<WeaponType> new_weapon_types = new ArrayList<WeaponType>(0);

            for(WeaponType wt : weapons){
                if(arrayContainsType(w, wt) != -1){ // The weapon type is in the w array
                    new_weapon_types.add(wt);
                }
            }

        return new SpecificDamage(new_weapon_types, new_shields);
    }

    /**
     * A given weapon is discarded from the Damage
     *
     * @param w, the weapon to discard
     * */
    @Override
    public void discardWeapon(Weapon w){
        weapons = new ArrayList<WeaponType>(weapons.stream().filter(wtype -> wtype != w.getType()).collect(Collectors.toList()));

    }
    
    // AUXILIAR
    //==========================================================================
    
    /**
     * Searchs the first weapon with the same type as given type through parameter
     * 
     * @param w, the weapon array where to make the search
     * @param t, the weapon type to search
     * @return  the position, if the weapon is found
     *          -1, if the weapon is not found
     * */
    private int arrayContainsType(ArrayList<Weapon> w, WeaponType t){
        int position = -1;

        for(int i = 0; i < w.size() && position == -1; i++){
            if(w.get(i).getType() == t){
                position = i;
            }
        }

        return position;
    }
}
