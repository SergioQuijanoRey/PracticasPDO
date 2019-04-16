/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class EnemyStarShip {
    /**
     * Parametrizes ammunition power
     */
    private float ammoPower;
    
    /**
     * Name of the ship
     */
    private String name;
    
    /**
     * Parametrizes shield power
     */
    private float shieldPower;
    
    /**
     * Loot associated
     */
    private Loot loot;
    
    /**
     * Damage associated
     */
    private Damage damage;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer
     * @param _name name of the ship
     * @param _ammoPower parametrizes ammunition power
     * @param _shieldPower parametrizes shield power
     * @param _loot associated loot
     * @param _damage associated damage
     */
    EnemyStarShip(String _name, float _ammoPower, float _shieldPower, Loot _loot, Damage _damage) {
        name = _name;
        ammoPower = _ammoPower;
        shieldPower = _shieldPower;
        loot = _loot;
        /*
        WIP - Reference protection necessary?
        loot = new Loot(_loot);
        */
        damage = _damage;
    }
    
    /**
     * Copy constructor
     * @param e instance which is going to be copied
     */
    EnemyStarShip(EnemyStarShip e) {
        name = e.name;
        ammoPower = e.ammoPower;
        shieldPower = e.shieldPower;
        loot = e.loot;
        // WIP - Reference protection necessary?
        damage = e.damage;
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    public String getName() {
        return name;
    }
    
    public float getAmmoPower() {
        return ammoPower;
    }
    
    public float getShieldPower() {
        return shieldPower;
    }
    
    public Loot getLoot() {
        // Reference protection
        return new Loot(loot.getNSupplies(), loot.getNWeapons(),
                loot.getNShields(), loot.getNHangars(), loot.getNMedals());
    }
    
    public Damage getDamage() {
        // Reference protection
        return new Damage(damage);
    }
    
    public float fire() {
        return ammoPower;
    }
    
    public float protection() {
        return shieldPower;
    }
    
    /**
     * 
     * @param shot power of shot taken
     * @return ShotResult.RESIST, if shieldPower >= shot;
     *         ShotResult.DONOTRESIST, if shieldPower < shot
     */
    public ShotResult receiveShot(float shot) {
        if ( shieldPower >= shot )
            return ShotResult.RESIST;
        else
            return ShotResult.DONOTRESIST;
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object
     * @return string representation
     */
    public String toString() {
        String message = "[EnemyStarShip] -> Name: " + name
                + ", ammoPower: " + ammoPower
                + ", shieldPower: " + shieldPower
                + ", Loot: " + loot.toString()
                + ", Damage: " + damage.toString();
        return message;
    }
    
    public EnemyToUI getUIVersion() {
        return new EnemyToUI(this);
    }
}
