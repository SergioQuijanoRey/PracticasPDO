/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * Class to represent the weapons that a space station can dispose of to
 * increment its energy when shooting
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class Weapon implements CombatElement {
    /**
     * Name of weapon.
     */
    private String name;
    
    /**
     * Type of weapon.
     */
    private WeaponType type;
    
    /**
     * Number of uses of weapon.
     */
    private int uses;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer.
     * @param _name name of weapon
     * @param _type type of weapon
     * @param _uses number of uses of weapon
     */
    Weapon(String _name, WeaponType _type, int _uses) {
        name = _name;
        type = _type;
        uses = _uses;
    }
    
    /**
     * Copy constructor.
     * @param w instance which is going to be copied
     */
    Weapon(Weapon w) {
        this.name = w.name;
        this.type = w.type;
        this.uses = w.uses;
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Getter for type.
     * @return type
     */
    public WeaponType getType() {
        return type;
    }
    
    /**
     * Getter for uses.
     * @return uses
     */
    public int getUses() {
        return uses;
    }
    
    /**
     * Get type's power.
     * @return type's power
     */
    public float power() {
        return type.getPower();
    }
    
    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------
    
    /**
     * Uses the weapon.
     * If it is still available (uses is greater than 0), we can use the weapon
     * Otherwise, we cannot use the weapon
     * @return boost if uses > 0, 1.0 if uses == 0
     */
    public float useIt() {
        if ( uses > 0 ) {
            uses--;
            return power();
        } else
            return (float) 1.0;
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object.
     * @return string representation
     */
    public String toString() {
        String message = "[Weapon] -> Name: " + name
                + ", Type: " + type
                + ", Power: " + power()
                + ", Uses: " + uses;
        return message;
    }
    
    /**
     * To UI.
     */
    WeaponToUI getUIversion() {
        return new WeaponToUI(this);
    }
}
