package deepspace;

/**
 * Class to represent the loot obtained by defeating a enemy ship.
 * It is composed by a number of:
 *  - SuppliesPackage
 *  - Weapon
 *  - ShieldBooster
 *  - Hangar
 *  - Medals
 * and the SpaceStation that receives this loot can transform to:
 *  - PowerEfficientSpaceStation or BetaPowerEfficientSpaceStation
 *  - SpaceCity
 * 
 * @author Miguel Ángel Fernández Gutiérrez
 * @author Sergio Quijano Rey
 */
class Loot {
    /**
     * Number of supplies given by a loot.
     */
    private int nSupplies;
    
    /**
     * Number of weapons given by a loot.
     */
    private int nWeapons;
    
    /**
     * Number of boosters given by a loot.
     */
    private int nShields;
    
    /**
     * Number of hangars given by a loot.
     */
    private int nHangars;
    
    /**
     * number of medals given by a loot.
     */
    private int nMedals;
    
    /**
     * Whether space station will be converted to efficient.
     */
    private boolean efficient;
    
    /**
     * Whether space station will be converted to city.
     */
    private boolean spaceCity;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer.
     * @param _nSupplies number of supplies given by a loot
     * @param _nWeapons number of boosters given by a loot
     * @param _nShields number of shields given by a loot
     * @param _nHangars number of hangars given by a loot
     * @param _nMedals number of medals given by a loot
     * @param _efficient whether space station will be converted to efficient
     * @param _spaceCity whether space station will be converted to city
     */
    Loot(int _nSupplies, int _nWeapons, int _nShields, int _nHangars, int _nMedals, boolean _efficient, boolean _spaceCity) {
        nSupplies = _nSupplies;
        nWeapons = _nWeapons;
        nShields = _nShields;
        nHangars = _nHangars;
        nMedals = _nMedals;
        efficient = _efficient;
        spaceCity = _spaceCity;
    }
    
    /**
     * Class initializer without efficient and spaceCity
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
        efficient = false;
        spaceCity = false;
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Getter for nSupplies.
     * @return nSupplies
     */
    public int getNSupplies() {
        return nSupplies;
    }
    
    /**
     * Getter for nWeapons.
     * @return nWeapons
     */
    public int getNWeapons() {
        return nWeapons;
    }
    
    /**
     * Getter for nShields.
     * @return nShields
     */
    public int getNShields() {
        return nShields;
    }
    
    /**
     * Getter for nHangars.
     * @return nHangars
     */
    public int getNHangars() {
        return nHangars;
    }
    
    /**
     * Getter for nMedals.
     * @return nMedals
     */
    public int getNMedals() {
        return nMedals;
    }
    
    /**
     * Getter for efficient.
     * @return efficient
     */
    public boolean getEfficient() {
        return efficient;
    }
    
    /**
     * Getter for spaceCity.
     * @return spaceCity
     */
    public boolean spaceCity() {
        return spaceCity;
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object.
     * @return string representation
     */
    @Override
    public String toString(){
        return  "Loot(nSupplies = " + nSupplies + 
                ", nWeapons = " + nWeapons + 
                ", nShields = " + nShields + 
                ", nHangars = " + nHangars + 
                ", nMedals = " + nMedals + 
                ", getEfficient = " + efficient + 
                ", spaceCity = " + spaceCity + 
                ")"; 
    }
    
    /**
     * To UI.
     */
    LootToUI getUIversion() {
        return new LootToUI(this);
    }
}
