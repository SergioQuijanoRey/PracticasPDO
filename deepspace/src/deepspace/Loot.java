/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * Class to represent the loot obtained by defeating a enemy ship
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class Loot {
    /**
     * Number of supplies given by a loot
     */
    private int nSupplies;
    
    /**
     * Number of weapons given by a loot
     */
    private int nWeapons;
    
    /**
     * Number of boosters given by a loot
     */
    private int nShields;
    
    /**
     * Number of hangars given by a loot
     */
    private int nHangars;
    
    /**
     * number of medals given by a loot
     */
    private int nMedals;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer
     * @param _nSupplies number of supplies given by a loot
     * @param _nWeapons number of boosters given by a loot
     * @param _nShields number of shields given by a loot
     * @param _nHangars number of hangars given by a loot
     * @param _nMedals number of medals given by a loot
     */
    Loot(int _nSupplies, int _nWeapons, int _nShields, int _nHangars, int _nMedals) {
        nSupplies = _nSupplies;
        nWeapons = _nWeapons;
        nShields = _nShields;
        nHangars = _nHangars;
        nMedals = _nMedals;
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Getter for nSupplies
     * @return nSupplies
     */
    public int getNSupplies() {
        return nSupplies;
    }
    
    /**
     * Getter for nWeapons
     * @return nWeapons
     */
    public int getNWeapons() {
        return nWeapons;
    }
    
    /**
     * Getter for nShields
     * @return nShields
     */
    public int getNShields() {
        return nShields;
    }
    
    /**
     * Getter for nHangars
     * @return nHangars
     */
    public int getNHangars() {
        return nHangars;
    }
    
    /**
     * Getter for nMedals
     * @return nMedals
     */
    public int getNMedals() {
        return nMedals;
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object
     * @return string representation
     */
    public String toString() {
        String message = "[Loot] -> Supplies: " + nSupplies
                + ", Weapons: " + nWeapons
                + ", Shields: " + nShields
                + ", Hangars: " + nHangars
                + ", Medals: " + nMedals;
        return message;
    }
    
    /**
     * To UI
     */
    LootToUI getUIversion() {
        return new LootToUI(this);
    }
}
