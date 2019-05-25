/**
 * @author Sergio Quijano Rey
 * */

package deepspace;

/**
 * @brief Class to represent a supplies package given to a space station
 *
 * A supplies package can have ammo, fuel and shield energy
 * */
class SuppliesPackage{
    // Private attributes
    //======================================================================
    private float ammoPower;
    private float fuelUnits;
    private float shieldPower;

    // Constructors
    //======================================================================
    SuppliesPackage(float _ammoPower, float _fuelUnits, float _shieldPower){
        ammoPower = _ammoPower;
        fuelUnits = _fuelUnits;
        shieldPower = _shieldPower;
    }

    /**
     * @brief Copy constructor
     * @param other: the other SuppliesPackage object to copy
     * @post the object has a copy of @other
     * */
    SuppliesPackage(SuppliesPackage other){
        this(other.getAmmoPower(), other.getFuelUnits(), other.getShieldPower());
    }

    //Getters
    //======================================================================
    public float getAmmoPower(){
        return ammoPower;
    }

    public float getFuelUnits(){
        return fuelUnits;
    }

    public float getShieldPower(){
        return shieldPower;
    }

    /**
     * @brief Gets the string representation of the object
     * @return the string which represents an object
     * */
    public String toString(){
        return  "SuppliesPackage(ammoPower = " + ammoPower + 
                ", fuelUnits = " + fuelUnits + 
                ", shieldPower = " + shieldPower +
                ")";
    }
}
