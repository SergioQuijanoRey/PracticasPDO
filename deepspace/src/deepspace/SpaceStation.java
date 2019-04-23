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
    private float ammoPower;
    
    /**
     * Parametrizes fuel units
     */
    private float fuelUnits;
    
    /**
     * Name of the station
     */
    private String name;
    
    /**
     * Number of medals
     */
    private int nMedals;
    
    /**
     * Parametrizes shield power
     */
    private float shieldPower;
    
    /**
     * Pending damage
     */
    private Damage pendingDamage;
    
    /**
     * Array of weapons
     */
    private ArrayList<Weapon> weapons;
    
    /**
     * Array of shield boosters
     */
    private ArrayList<ShieldBooster> shieldBoosters;
    
    /**
     * Hangar
     */
    private Hangar hangar;
    
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
        ammoPower = 0f;
        fuelUnits = 0f;
        nMedals = 0;
        shieldPower = 0f;
        weapons = new ArrayList<>();
        shieldBoosters = new ArrayList<>();
        pendingDamage = null;
        hangar = null;
        
        receiveSupplies(_supplies);
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Getter for ammoPower
     * @return ammoPower
     */
    public float getAmmoPower() {
        return ammoPower;
    }
    
    /**
     * Getter for fuelUnits
     * @return fuelUnits
     */
    public float getFuelUnits() {
        return fuelUnits;
    }
    
    /**
     * Getter for hangar
     * @return hangar
     */
    public Hangar getHangar() {
        return hangar;
    }
    
    /**
     * Getter nMedals
     * @return nMedals
     */
    public int getNMedals() {
        return nMedals;
    }
    
    /**
     * Getter for pendingDamage
     * @return pendingDamage
     */
    public Damage getPendingDamage() {
        return pendingDamage;
    }
    
    /**
     * Getter for shieldBoosters
     * @return shieldBoosters
     */
    public ArrayList<ShieldBooster> getShieldBoosters() {
        // Reference protection
        /*ArrayList<ShieldBooster> shields_copy = new ArrayList<>();
        shieldBoosters.forEach(booster -> {
            shields_copy.add(booster);
        });
        
        return shields_copy;*/
        
        return shieldBoosters;
    }
    
    /**
     * Getter for weapons
     * @return weapons
     */
    public ArrayList<Weapon> getWeapons() {
        // Reference protection
        /*ArrayList<Weapon> weapons_copy = new ArrayList<>();
        weapons.forEach(weapon -> {
            weapons_copy.add(weapon);
        });
        
        return weapons_copy;*/
        
        return weapons;
    }
    
    /**
     * Getter for shieldPower
     * @return shieldPower
     */
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
     * Shot, shield and fuel power increase by a certain supplies package
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
        Weapon new_weapon;
        if ( i >= 0 && i < hangar.getWeapons().size() ) {
            if ( hangar != null ) {
                new_weapon = hangar.removeWeapon(i);

                if ( new_weapon != null )
                    weapons.add(new_weapon);
            }
        }
    }
    
    /**
     * A booster from the hangar is mounted to be used.
     * If method runs succesfully, booster is erased from Hangar, and the booster
     * is added to the collection of boosters in use
     * @param i index of the booster to mount
     */
    public void mountShieldBooster(int i) {
        ShieldBooster new_booster;
        if ( i >= 0 && i < hangar.getShieldBoosters().size() ) {
            if ( hangar != null ) {
                new_booster = hangar.removeShieldBooster(i);
                
                if ( new_booster != null )
                    shieldBoosters.add(new_booster);
            }
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
    
    /**
     * Makes a shot
     * @return the shot power
     */
    public float fire() {
        float factor = 1f;
        
        for ( Weapon w : weapons )
            factor *= w.useIt();
        
        return factor;
    }
    
    /**
     * Use protection shield
     * @return the shield's energy
     */
    public float protection() {
        float factor = 1f;
        
        for ( ShieldBooster s : shieldBoosters )
            factor *= s.useIt();
        
        return factor;
    }
    
    /**
     * Makes the operations related to the reception of an enemy's impact
     * @param shot enemy's impact shot power
     * @return true, if the shield resisted the impact; else, otherwise
     */
    public ShotResult receiveShot(float shot) {
        float myProtection = protection();
        
        if ( myProtection >= shot ) {
            shieldPower -= SHIELDLOSSPERUNITSHOT*shot;
            if ( shieldPower < 0 )
                shieldPower = 0;
            
            return ShotResult.RESIST;
        } else {
            shieldPower = 0;
            
            return ShotResult.DONOTRESIST;
        }
    }
    
    /**
     * Receives a loot
     * @param loot loot to be received
     */
    public void setLoot(Loot loot) {
        CardDealer dealer = CardDealer.getInstance();
        int h = loot.getNHangars();
        
        if ( h > 0 ) {
            hangar = dealer.nextHangar();
            receiveHangar(hangar);
        }
        
        int elements = loot.getNSupplies();
        for ( int i = 1; i <= elements; i++ ) {
            SuppliesPackage sup = dealer.nextSuppliesPackage();
            receiveSupplies(sup);
        }
        
        elements = loot.getNWeapons();
        for ( int i = 1; i <= elements; i++ ) {
            Weapon weap = dealer.nextWeapon();
            receiveWeapon(weap);
        }
        
        elements = loot.getNShields();
        for ( int i = 1; i <= elements; i++ ) {
            ShieldBooster sh = dealer.nextShieldBooster();
            receiveShieldBooster(sh);
        }
        
        int medals = loot.getNMedals();
        nMedals += medals;
    }
    
    /**
     * Discards weapon in certain position from the collection of weapons in use
     * @param i index where the weapon that wants to be discarded is located
     */
    public void discardWeapon(int i) {
        int size = weapons.size();
        if ( i >= 0 && i < size ) {
            Weapon w = weapons.remove(i);
            if ( pendingDamage != null ) {
                pendingDamage.discardWeapon(w);
                cleanPendingDamage();                
            }
        }
    }
    
    /**
     * Discards shield in certain position from the collection of weapons in use
     * @param i index where the shield that wants to be discarded is located
     */
    public void discardShieldBooster(int i) {
        int size = shieldBoosters.size();
        if ( i >= 0 && i < size ) {
            ShieldBooster s = shieldBoosters.remove(i);
            if ( pendingDamage != null ) {
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();                
            }
        }
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
    public SpaceStationToUI getUIversion() {
        return new SpaceStationToUI(this);
    }
}
