/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * Class to represent a Supplies Package
 * It can contain ammo, fuel or shield energy
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class SuppliesPackage {
    /**
     * Parametrizes ammunition power
     */
    private float ammoPower;
    
    /**
     * Counts fuel units
     */
    private float fuelUnits;
    
    /**
     * Parametrizes shield power
     */
    private float shieldPower;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer
     * @param _ammoPower parametrizes ammunition power
     * @param _fuelUnits counts fuel units
     * @param _shieldPower parametrizes shield power
     */
    SuppliesPackage(float _ammoPower, float _fuelUnits, float _shieldPower) {
        ammoPower = _ammoPower;
        fuelUnits = _fuelUnits;
        shieldPower = _shieldPower;
    }
    
    /**
     * Copy constructor
     * @param s instance which is going to be copied
     */
    SuppliesPackage(SuppliesPackage s) {
        this.ammoPower = s.ammoPower;
        this.fuelUnits = s.fuelUnits;
        this.shieldPower = s.shieldPower;
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Getter for ammoPower
     * @return ammoPower
     */
    public float getAmmoPower() {
        return ammoPower;
    }
    
    /**
     * Getter for fuelUnits
     * @return fuelUnits
     */
    public float getFuelUnits() {
        return fuelUnits;
    }
    
    /**
     * Getter for shieldPower
     * @return shieldPower
     */
    public float getShieldPower() {
        return shieldPower;
    }
    
    // -------------------------------------------------------------------------
    // String representation
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object
     * @return string representation
     */
    public String toString() {
        String message = "[SuppliesPackage] -> ammoPower: " + ammoPower
                + ", fuelUnits: " + fuelUnits
                + ", shieldPower: " + shieldPower;
        return message;
    }
}
