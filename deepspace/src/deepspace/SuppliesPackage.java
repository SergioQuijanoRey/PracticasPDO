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
    
    public float getAmmoPower() {
        return ammoPower;
    }
    
    public float getFuelUnits() {
        return fuelUnits;
    }
    
    public float getShieldPower() {
        return shieldPower;
    }
}
