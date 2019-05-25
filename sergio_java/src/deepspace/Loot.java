/**
 * @author Sergio Quijano Rey
 * @file Loot.java
 * */

package deepspace;

/**
 * Class to represent the loot given at victory on an enemy ship
 * Loot is composed of a number of given:
 *  - SuppliesPackages
 *  - WeaponBoosters
 *  - ShieldBoosters
 *  - Hangars 
 *  - Medals 
 * and if the SpaceStation which receives this loot can transform to one of the following:
 *  - PowerEfficientSpaceStation
 *  - SpaceCity
 *
 * A loot should not have a PowerEfficientSpaceStation and SpaceCity transformation
 * at the same time !!!
 *
 * @author Sergio Quijano Rey
 * */
public class Loot{
    // Private attributes
    //==========================================================================
    private int nSupplies;          // Number of given supplies
    private int nWeapons;           // Number of given weapons
    private int nShields;           // Number of given shields
    private int nHangars;           // Number of given Hangars
    private int nMedals;            // Number of given medals
    private boolean getEfficient;   // Transformation to PowerEfficientSpaceStation
    private boolean spaceCity;      // Transformation to SpaceCity

    // Constructors
    //==========================================================================
    /**
     * Constructor of the class
     * */
    Loot(int _nSupplies, int _nWeapons, int _nShields, int _nHangars, int _nMedals, boolean _getEfficient = false, boolean _spaceCity = false){
        nSupplies = _nSupplies;
        nWeapons = _nWeapons;
        nShields = _nShields;
        nHangars = _nHangars;
        nMedals = _nMedals;
        getEfficient = _getEfficient;
        spaceCity = _spaceCity;
    }

    // Getters
    //==========================================================================
    public int getNSupplies(){
        return nSupplies;
    }

    public int getNWeapons(){
        return nWeapons;
    }

    public int getNShields(){
        return nShields;
    }

    public int getNHangars(){
        return nHangars;
    }

    public int getNMedals(){
        return nMedals;
    }

    /**
     * Getter for getEfficient
     * @return true, if a SpaceStation can transform to a PowerEfficientSpaceStation
     *         false, otherwise
     * */
    public boolean getEfficient(){
        return getEfficient;
    }

    /**
     * Getter for spaceCity
     * @return true, if a SpaceStation can transform to a SpaceCity
     *         false, otherwise
     * */
    public boolean SpaceCity(){
        return spaceCity;
    }

    /**
     * Gets the UI version of the object
     * @return the UI version
     * */
    public LootToUI getUIversion(){
        return new LootToUI(this);
    }
    
    /**
     * Gets the String representation of the object
     * @return the text representatio of the object
     * */
    public String toString(){
        return  "Loot(nSupplies = " + nSupplies + 
                ", nWeapons = " + nWeapons + 
                ", nShields = " + nShields + 
                ", nHangars = " + nHangars + 
                ", nMedals = " + nMedals + 
                ")"; 
    }
}
