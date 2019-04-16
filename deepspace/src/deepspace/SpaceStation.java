/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class SpaceStation {
    /**
     * Maximum fuel quantity that a space station can have
     */
    private static final float MAXFUEL = 100f;
    
    /**
     * Shield units lost per each shot unit taken
     */
    private static final float SHIELDLOSSPERUNITSHOT = 0.1f;
    
    /**
     * Parametrizes ammunition power
     */
    private float ammoPower = 0f;
    
    /**
     * Parametrizes fuel units
     */
    private float fuelUnits = 0f;
    
    /**
     * Name of the station
     */
    private String name;
    
    /**
     * Number of medals
     */
    private int nMedals = 0;
    
    /**
     * Parametrizes shield power
     */
    private float shieldPower = 0f;
    
    /**
     * Pending damage
     */
    private Damage pendingDamage = null;
    
    /**
     * Array of weapons
     */
    private ArrayList<Weapon> weapons = new ArrayList<>();
    
    /**
     * Array of shield boosters
     */
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList<>();
    
    /**
     * Hangar
     */
    private Hangar hangar = null;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer
     * @param _name name of the station
     * @param _supplies starting fuel units, weapons and shields
     */
    SpaceStation(String _name, SuppliesPackage _supplies) {
        name = _name;
        receiveSupplies(_supplies);
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
    
    public float getFuelUnits() {
        return fuelUnits;
    }
    
    public Hangar getHangar() {
        // Reference protection
        return new Hangar(hangar);
    }
    
    public int getNMedals() {
        return nMedals;
    }
    
    public Damage getPendingDamage() {
        // Reference protection
        return new Damage(pendingDamage);
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters() {
        // Reference protection
        ArrayList<ShieldBooster> shields_copy = new ArrayList<>();
        shieldBoosters.forEach(booster -> {
            shields_copy.add(booster);
        });
        
        return shields_copy;
    }
    
    public ArrayList<Weapon> getWeapons() {
        // Reference protection
        ArrayList<Weapon> weapons_copy = new ArrayList<>();
        weapons.forEach(weapon -> {
            weapons_copy.add(weapon);
        });
        
        return weapons_copy;
    }
    
    public float getShieldPower() {
        return shieldPower;
    }
    
    /**
     * Gets the speed of the space station.
     * Speed is calculated as fraction of fuel units and max fuel possible
     * @return percentage of speed, that's to say, a number in [0, 1]
     */    
    public float getSpeed() {
        if ( MAXFUEL == 0 ) {
            throw new IllegalArgumentException("Zero division at SpaceStation.getSpeed(): MAXFUEL cannot be zero");
            //return 0f;
        } else
            return fuelUnits / MAXFUEL;
    }
    
    /**
     * Checks the state of the space ship.
     * Valid state means no pending damage or pending damage with no effect
     * @return true, if the space ship is on a valid state;
     *         false, otherwise
     */
    public boolean validState() {
        if ( pendingDamage == null )
            return false;
        else
            return pendingDamage.hasNoEffect();
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object
     * @return string representation
     */
    public String toString() {
        String message = "[SpaceStation] -> Name: " + name
                + "\n\tNo. Medals: " + nMedals
                + ", Fuel units: " + fuelUnits
                + ", ammoPower: " + ammoPower
                + ", shieldPower: " + shieldPower
                + "\n\tWeapons: " + weapons.toString()
                + "\n\tShield Boosters: " + shieldBoosters.toString()
                + "\n\tHangar: " + hangar.toString()
                + "\n\tPending damage: " + pendingDamage.toString()
                + "\n-------- end of Space Station >> " + name + " << --------";
        return message;
    }
    
    /**
     * To UI
     */
    public SpaceStationToUI getUIVersion() {
        return new SpaceStationToUI(this);
    }
    
    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------
    
    /**
     * Assigns the fuel of the space station
     * @param f fuel value to be assigned
     */
    private void assignFuelValue(float f) {
        if ( f < MAXFUEL )
            fuelUnits = f;
        else
            fuelUnits = MAXFUEL;
    }
    
    /**
     * Adjusts certain damage to some weapon and shieldBoosters lists, and its
     * value is stored in the object
     * @param d damage to be set
     */
    public void setPendingDamage(Damage d) {
        pendingDamage = d.adjust(weapons, shieldBoosters);
    }
    
    /**
     * If pending damage has no effect, fixes the atribute to null
     */
    private void cleanPendingDamage() {
        if ( pendingDamage != null )
            if ( pendingDamage.hasNoEffect() )
                pendingDamage = null;
    }
    
    /**
     * Tries to add a weapon to the hangar
     * @param w the weapon to add
     * @return true, if the weapon is successfully added;
     *         false, if the operation fails
     */
    public boolean receiveWeapon(Weapon w) {
        if ( hangar != null ) 
            return hangar.addWeapon(w);
        else
            return false;
    }
    
    /**
     * Tries to add a shield booster to the hangar
     * @param s the shield booster to add
     * @return true, if the shield booster is successfully added;
     *         false, if the operation fails
     */
    public boolean receiveShieldBooster(ShieldBooster s) {
        if ( hangar != null ) 
            return hangar.addShieldBooster(s);
        else
            return false;
    }
    
    /**
     * Tries to add an hangar.
     * If there's already a hangar, this method has no effect
     * @param h hangar to be added
     */
    public void receiveHangar(Hangar h) {
        if ( hangar != null )
            // Reference protection
            hangar = new Hangar(h);
    }
    
    /**
     * Discards current hangar
     */
    public void discardHangar() {
        hangar = null;
    }
    
    /**
     * If a hangar is available, it discards a weapon from it, in a certain
     * position
     * @param i index where the weapon that wants to be discarded is located in
     * the hangar
     */
    public void discardWeaponInHangar(int i) {
        if ( hangar != null )
            hangar.removeWeapon(i);
    }
    
    /**
     * If a hangar is available, it discards a shield booster from it, in a
     * certain position
     * @param i index where the shield booster that wants to be discarded is
     * located in the hangar
     */
    public void discardShieldBoosterInHangar(int i) {
        if ( hangar != null )
            hangar.removeShieldBooster(i);
    }
    
    /**
     * Shot, shield and fuel power increase by a certain SuppliesPackage
     * @param s the supplies to add
     */
    public void receiveSupplies(SuppliesPackage s) {
        ammoPower += s.getAmmoPower();
        assignFuelValue(fuelUnits + s.getFuelUnits());
        shieldPower += s.getShieldPower();
    }
    
    /**
     * A weapon from the hangar is mounted to be used.
     * If method runs succesfully, weapon is erased from Hangar, and the weapon
     * is added to the collection of weapons in use
     * @param i index of the weapon to mount
     */
    public void mountWeapon(int i) {
        if ( hangar != null ) {
            Weapon new_weapon = hangar.removeWeapon(i);
            
            if ( new_weapon != null )
                weapons.add(new_weapon);
        }
    }
    
    /**
     * A booster from the hangar is mounted to be used.
     * If method runs succesfully, booster is erased from Hangar, and the booster
     * is added to the collection of boosters in use
     * @param i index of the booster to mount
     */
    public void mountShieldBooster(int i) {
        if ( hangar != null ) {
            ShieldBooster new_booster = hangar.removeShieldBooster(i);
            
            if ( new_booster != null )
                shieldBoosters.add(new_booster);
        }
    }
    
    /**
     * The spaceship moves
     */
    public void move() {
        fuelUnits -= fuelUnits*getSpeed();
        if ( fuelUnits <= 0f )
            fuelUnits = 0f;
    }
    
    /**
     * Deletes all mounted weapons and mounted shields with no uses left
     */
    public void cleanUpMountedItems() {
        weapons.forEach(weapon -> {
           if ( weapon.getUses() <= 0 )
               weapons.remove(weapon);
        });
        
        shieldBoosters.forEach(booster -> {
            if ( booster.getUses() <= 0 )
                shieldBoosters.remove(booster);
        });
    }
    
    /* WIP - SE IMPLEMENTARÁN EN LA PRÁCTICA SIGUIENTE */
    
    public float fire() {
        throw new UnsupportedOperationException();
    }
    
    public float protection() {
        throw new UnsupportedOperationException();
    }
    
    public ShotResult receiveShot(float shot) {
        throw new UnsupportedOperationException();
    }
    
    public void setLoot(Loot loot) {
        throw new UnsupportedOperationException();
    }
    
    public void discardWeapon(int i) {
        throw new UnsupportedOperationException();
    }
    
    public void discardShieldBooster(int i) {
        throw new UnsupportedOperationException();
    }
}
