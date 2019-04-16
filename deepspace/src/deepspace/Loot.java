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
     * Number of shields given by a loot
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
     * @param _nWeapons number of weapons given by a loot
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
    
    public int getNSupplies() {
        return nSupplies;
    }
    
    public int getNWeapons() {
        return nWeapons;
    }
    
    public int getNShields() {
        return nShields;
    }
    
    public int getNHangars() {
        return nHangars;
    }
    
    public int getNMedals() {
        return nMedals;
    }
    
}
