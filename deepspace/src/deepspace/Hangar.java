/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 * Represents a certain hangar. A hangar is a container of shield boosters and
 * weapons, with maximum capacity
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class Hangar {
    /**
     * Maximum number of shields and weapons --combined-- the hangar can have.
     */
    private int maxElements;
    
    /**
     * Array of shield boosters the hangar has.
     */
    private ArrayList<ShieldBooster> shieldBoosters;
    
    /**
     * Array of weapons the hangar has.
     */
    private ArrayList<Weapon> weapons;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer
     * @param capacity maximum number of shields and weapons the hangar can have
     */
    Hangar(int capacity) {
        maxElements = capacity;
        shieldBoosters = new ArrayList<>();
        weapons = new ArrayList<>();
    }
    
    /**
     * Copy constructor
     * @param h instance which is going to be copied
     */
    Hangar(Hangar h) {
        maxElements = h.maxElements;
        shieldBoosters = new ArrayList<>();
        weapons = new ArrayList<>();
        
        for ( ShieldBooster s : h.shieldBoosters )
            addShieldBooster(s);
        
        for ( Weapon w : h.weapons )
            addWeapon(w);
        
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Getter for maxElements
     * @return maxElements
     */
    public int getMaxElements() {
        return maxElements;
    }
    
    /**
     * Getter for shieldBoosters
     * @return shieldBoosters
     */
    public ArrayList<ShieldBooster> getShieldBoosters() {
        return shieldBoosters;
    }
    
    /**
     * Getter for weapons
     * @return weapons
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    
    /**
     * Checks if there's space left for more elements at the hangar
     * @return true, if there's space left for at least one more element;
     *         false, if the hangar is full
     */
    private boolean spaceAvailable() {
        return maxElements > shieldBoosters.size() + weapons.size();
    }
    
    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------
    
    /**
     * Adds a new weapon to the hangar
     * @param w the weapon to be added
     * @return true, if the operation runs successfully;
     *         false, if something fails (no room for another weapon)
     */
    public boolean addWeapon(Weapon w) {
        if ( spaceAvailable() )
            return weapons.add(w);
        else
            return false;
    }
    
    /**
     * Removes a weapon from the hangar
     * @param w the position in which the weapon that wants to be removed is located
     * @return null if position is invalid or removal operation is unsuccessful;
     *         the weapon, if removal is successful
     */
    public Weapon removeWeapon(int w) {
        if ( w >= 0 && w < weapons.size() )
            return weapons.remove(w);
        else
            return null;
    }
    
    /**
     * Adds a new shield booster to the hangar
     * @param w the shield booster to be added
     * @return true, if the operation runs successfully;
     *         false, if something fails (no room for another booster)
     */
    public boolean addShieldBooster(ShieldBooster s) {
        if ( spaceAvailable() )
            return shieldBoosters.add(s);
        else
            return false;
    }
    
    /**
     * Removes a shield booster from the hangar
     * @param w the position in which the booster that wants to be removed is located
     * @return null if position is invalid or removal operation is unsuccessful;
     *         the shield booster, if removal is successful
     */
    public ShieldBooster removeShieldBooster(int s) {
        if ( s >= 0 && s < shieldBoosters.size() )
            return shieldBoosters.remove(s);
        else
            return null;
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object
     * @return string representation
     */
    public String toString() {
        String message = "[Hangar] -> Max. elements: " + maxElements
                + ", Shields: " + shieldBoosters.toString()
                + ", Weapons: " + weapons.toString();
        return message;
    }
    
    /**
     * To UI
     */
    HangarToUI getUIversion() {
        return new HangarToUI(this);
    }
}
