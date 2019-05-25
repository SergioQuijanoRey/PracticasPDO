/**
 * @author Sergio Quijano Rey
 * */

package deepspace;

/**
 * @brief Class to represent weapons that space station can have
 * */
class Weapon implements CombatElement{
    // Private attributes
    //======================================================================
    private String name;
    private WeaponType type;
    private int uses;
    
    // Constructors
    //======================================================================
    Weapon(String _name, WeaponType _type, int _uses){
        name = _name;
        type = _type;
        uses = _uses;
    }

    /**
     * @brief Copy constructor
     * @param other: object to be copied
     * @post the object has a copy of given parameter object
     * */
    Weapon(Weapon other){
        this(other.name, other.type, other.uses);
    }

    // Getters
    //======================================================================
    public WeaponType getType(){
        return type;
    }

    @Override
    /**
     * Gets the uses of the weapon
     * @return the uses of the weapon
     */
    public int getUses(){
        return uses;
    }

    /**
     * @brief Gets the power of the weapontype of the weapon, hence, the power of the weapon
     * @return the power of the weapon type
     * */
    public float power(){
        return type.getPower();
    }

    /**
     * @brief Gets the text representation of the object
     * @return the string representation of the object
     * */
    @Override
    public String toString(){
        return  "Weapon(" +
                "name = " + name + 
                ", type = " + type.toString() + 
                ", uses = " + uses + 
                ")";
    }

    /**
     * @brief Gets the UI representation of the weapon
     * @return the UI representation
     * */
    public WeaponToUI getUIversion(){
        return new WeaponToUI(this);
    }

    // Setters
    //======================================================================
    /**
     * Method to use the weapon
     * @return  the power of the weapon, if uses > 0
     *          1.0 (neutral weapon power), otherwise
     * */
    @Override
    public float useIt(){
            if(uses > 0){
                    uses = uses - 1;
                    return power();
            }else{
                    return (float) 1.0;
            }
    }
}
