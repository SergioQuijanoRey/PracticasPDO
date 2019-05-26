/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * Class to represent the shield boosters that space stations can have
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class ShieldBooster implements CombatElement {
    /**
     * Name of the shield booster.
     */
    private String name;
    
    /**
     * Percentage of damage absorbed by the shield.
     */
    private float boost;
    
    /**
     * How many uses the shield booster has.
     */
    private int uses;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer.
     * @param _name name of the shield booster
     * @param _boost percentage of damage absorbed by the shield
     * @param _uses how many uses the shield booster has
     */
    ShieldBooster(String _name, float _boost, int _uses) {
        name = _name;
        boost = _boost;
        uses = _uses;
    }
    
    /**
     * Copy constructor.
     * @param s instance which is going to be copied
     */
    ShieldBooster(ShieldBooster s) {
        this.name = s.name;
        this.boost = s.boost;
        this.uses = s.uses;
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Getter for boost.
     * @return boost
     */
    public float getBoost() {
        return boost;
    }
    
    /**
     * Getter for uses.
     * @return uses
     */
    public int getUses() {
        return uses;
    }
    
    /**
     * Getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }
    
    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------
    
    /**
     * Uses the shield booster.
     * If it is still available (uses is greater than 0), we can use the boost
     * Otherwise, we cannot use the boost
     * @return boost if uses > 0; 1.0 if uses == 0
     */
    public float useIt() {
        if ( uses > 0 ) {
            uses--;
            return boost;
        } else
            return 1f;
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object.
     * @return string representation
     */
    public String toString() {
        String message = "[ShieldBooster] -> Boost: " + boost
                + ", Uses: " + uses;
        return message;
    }
    
    /**
     * To UI.
     */
    ShieldToUI getUIversion() {
        return new ShieldToUI(this);
    }
}
