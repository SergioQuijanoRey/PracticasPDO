package deepspace;

import java.util.ArrayList;
import java.util.stream.Collectors; // For filtering an ArrayList

/**
 * Class to represent a Space Station
 *
 * @author Miguel Angel Fernandez
 * @author Sergio Quijano Rey
 * */
class SpaceStation implements SpaceFighter{
    // Class Attributes
    //==========================================================================
    private static final int MAXFUEL = 100;                         //< The max ammount of fuel that the space station can have
    private static final float SHIELDLOSSPERUNITSHOT = (float)0.1;  //< The shield loss when an unit of shot power is received

    // Instance Attributes
    //==========================================================================
    private float ammoPower;                            //< The ammo power of the space station
    private float fuelUnits;                            //< The fuel units left
    private String name;                                //< The name of the space station
    private int nMedals;                                //< The number of medals that the space station have
    private float shieldPower;                          //< The power of the space station's shield
    private Damage pendingDamage;                       //< The pending damage done to the space station
    private ArrayList<Weapon> weapons;                  //< The mounted weapons of the space station
    private ArrayList<ShieldBooster> shieldBoosters;    //< The mounted shieldBoosters of the space station
    private Hangar hangar;                              //< The hangar which holds unmounted shields and weapons
    
    // Constructors
    //==========================================================================
    /**
     * @brief The constructor of the class
     * @param n, the name of the space station
     * @param supplies, the starting supplies package with ammoPower, shieldPower and fuelUnits
     * */
    SpaceStation(String n, SuppliesPackage supplies){
        // The name given as a parameter
        name = n;

        // Default values for all the attributes
        ammoPower = (float)0.0;
        fuelUnits = (float)0.0;
        nMedals = 0;
        shieldPower = (float)0.0;
        pendingDamage = null;
        weapons = new ArrayList<>(0);
        shieldBoosters = new ArrayList<>(0);
        hangar = null;

        // We add the supplies given as a parameter -- WIP overridable method call on constructor, may lead to bugs
        receiveSupplies(supplies);
    }

    /**
     * Copy contructor
     * @param other, the object to copy
     * */
    SpaceStation(SpaceStation other){
        // This elements do not need to be copied securely
        ammoPower = other.ammoPower;
        fuelUnits = other.fuelUnits;
        name = other.name;
        nMedals = other.nMedals;
        shieldPower = other.shieldPower;

        // This elements need a secure copy
        if(other.pendingDamage != null){
            pendingDamage = other.pendingDamage.copy();
        }
        if(other.weapons != null){
            weapons = new ArrayList<>(other.weapons);
        }
        if(other.shieldBoosters != null){
            shieldBoosters = new ArrayList<>(other.shieldBoosters);
        }
        if(other.hangar != null){
            hangar = new Hangar(other.hangar);
        }       
    }

    // Getters
    //==========================================================================
    /**
     * @brief ammoPower getter
     * */
    public float getAmmoPower(){
        return ammoPower;
    }

    /**
     * @brief fuelUnits getter
     * */
    public float getFuelUnits(){
        return fuelUnits;
    }

    /**
     * @brief hangar getter
     * */
    public Hangar getHangar(){
        return hangar;
    }

    /**
     * @brief name getter
     * */
    public String getName(){
        return name;
    }

    /**
     * @brief nMedals getter
     * */
    public int getNMedals(){
        return nMedals;
    }

    /**
     * @brief pendingDamage getter
     * */
    public Damage getPendingDamage(){
        return pendingDamage;
    }

    /**
     * @brief shieldBoosters getter
     * */
    public ArrayList<ShieldBooster> getShieldBoosters(){
        // Copy return
        return new ArrayList<>(shieldBoosters);
    }

    /**
     * @brief weapons getter
     * */
    public ArrayList<Weapon> getWeapons(){
        // Copỳ return
        return new ArrayList<>(weapons);
    }

    /**
     * @brief shieldPower getter
     * */
    public float getShieldPower(){
        return shieldPower;
    }

    /**
     * @brief returns the speed of the space station
     *        This speed is the fraction between fuelUnits and MAXFUEL
     *        Therefore, speed is in [0, 1] interval
     * */
    public float getSpeed(){
        return fuelUnits / MAXFUEL;
    }

    /**
     * @brief Checks wether or not the space station is on a valid state
     *        Valid state means no pending damage or pending damage with no effect
     * @return true, if the space station is on a valid state
     *         false, if the space station is not on a valid state
     * */
    public boolean validState(){
        if(pendingDamage == null){
            return true;
        }else{
            return pendingDamage.hasNoEffect();
        }

    }

    /**
     * @brief Gets the UI version
     * @return the UI Version
     * */
    public SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }

    /**
     * @brief gets the string representation of the object
     * @return the string representation
     * */
    @Override
    public String toString(){
        return  "SpaceStation(\n" + 
                "\tammoPower = " + ammoPower + "\n" +
                "\tfuelUnits = " + fuelUnits + "\n" +
                "\tname = " + name + "\n" +
                "\tnMedals = " + nMedals + "\n" +
                "\tshieldPower = " + shieldPower + "\n" +
                "\tpendingDamage = " + pendingDamage + "\n" +
                "\tweapons = " + weapons + "\n" +
                "\tshieldBoosters = " + shieldBoosters + "\n" +
                "\tHangar = " + hangar + "\n" +
                ")";
    }

    // Setters
    //==========================================================================
    /**
     * The space station receives a loot
     * The card dealer gives us elements as specified on the loot
     * The elements are stored with their respective methods
     * @param loot, the loot which is received
     * @return the transformation that the loot indicates (may be NOTRANSFORM)
     *
     * BUG -- no funciona como deberiBUG
     * */
    public Transformation setLoot(Loot loot){
        // We get a card dealer for the elements received at the loot
        CardDealer dealer = CardDealer.getInstance();

        // We receive the hangars from the loot
        for(int i = 0; i < loot.getNHangars(); i++){
            Hangar local_hangar = dealer.nextHangar();
            receiveHangar(local_hangar); 
        }

        // We receive the supplies
        for(int i = 0; i < loot.getNSupplies(); i++){
            SuppliesPackage supplies = dealer.nextSuppliesPackage();
            receiveSupplies(supplies); 
        }

        // We receive the weapons
        for(int i = 0; i < loot.getNWeapons(); i++){
            Weapon weapon = dealer.nextWeapon();
            if(receiveWeapon(weapon) == false){
                System.out.println("Warning! Weapon could not be added propperly on SpaceStation.setLoot()");
            }
        }

        // We receive the shieldBoosters
        for(int i = 0; i < loot.getNShields(); i++){
            ShieldBooster sh = dealer.nextShieldBooster();
            if(receiveShieldBooster(sh) == false){
                System.out.println("Warning! ShieldBooster could not be added propperly on SpaceStation.setLoot()");
            }
        }

        // We receive the medals
        nMedals = nMedals + loot.getNMedals();

        // We return the transformation
        if(loot.getEfficient()){
            return Transformation.GETEFFICIENT;
        }else if(loot.spaceCity()){
            return Transformation.SPACECITY;
        }else{
            return Transformation.NOTRANSFORM;
        }
    }

    /**
     * @brief the adjusted damage is calculated and stored
     * @param d, the damage to be adjusted to the list of weapons and shields of the hangar
     *
     * WIP -- Necesita ser testeado intensamente para ver que no da fallos
     * */
    public void setPendingDamage(Damage d){
        pendingDamage = d.adjust(weapons, shieldBoosters);
    }
    
    // Receive Elements
    //==========================================================================

    /**
     * @brief Receives an hangar
     *        If there is already an hangar this method has no effect
     * @param h, the hangar to receive
     * */
    public void receiveHangar(Hangar h){
        if(hangar == null){
            hangar = h;
        }
    }

    /**
     * @brief Receives a supplies package
     *        The ammoPower, fuelUnits and shieldPower is incremented by the
     *        SuppliesPackage
     * @param s, the supplies to receive
     * */
    public void receiveSupplies(SuppliesPackage s){
        // Ammo power is incremented
        ammoPower = ammoPower + s.getAmmoPower();

        // Shield power is incremented
        shieldPower = shieldPower + s.getShieldPower();

        // Fuel units are incremented
        assignFuelValue(fuelUnits + s.getFuelUnits());
    }

    /**
     * @brief Tries to add a shieldBooster to the hangar
     * @param s, the shieldBooster to add
     * @return  true, if the shieldBooster is added successfully
     *          false, if the shieldbooster could not be added
     * */
    public boolean receiveShieldBooster(ShieldBooster s){
        // Security check
        if(hangar == null){
            System.out.println("WARNING! No hangar on SpaceStation.receiveShieldBooster()");
            return false;
        }

        // We try to add the shield
        return hangar.addShieldBooster(s);
    }

    /**
     * @brief Tries to add a weapon to the hangar
     * @param w, the weapon to add
     * @return  true, if the weapon is added successfully
     *          false, if the weapon could not be added
     * */
    public boolean receiveWeapon(Weapon w){
        // Security check
        if(hangar == null){
            System.out.println("WARNING! No hangar on SpaceStation.receiveWeapon()");
            return false;
        }

        // We try to add the weapon
        return hangar.addWeapon(w);
    }

    // Mount Elements
    //==========================================================================
    
    /**
     * @brief Tries to mount a shieldBooster from the hangar
     * @param i, the position of the shieldBooster in the hangar
     * */
    public void mountShieldBooster(int i){
        if(hangar == null){
            System.out.println("WARNING! No hangar on SpaceStation.mountShieldBooster()");
        }else{
            // The weapon is removed from the hangar
            ShieldBooster s = hangar.removeShieldBooster(i);

            // The weapon is added to the list of weapons if it is not null
            if(s == null){
                System.out.println("WARNING! No shield on SpaceStation.mountShieldBooster()");
            }else{
                shieldBoosters.add(s);
            }
        }
        
    }

    /**
     * @brief Tries to mount a weapon from the hangar
     * @param i, the position of the weapon in the hangar
     * */
    public void mountWeapon(int i){
        if(hangar == null){
            System.out.println("WARNING! No hangar on SpaceStation.mountWeapon()");
        }else{
            // The weapon is removed from the hangar
            Weapon w = hangar.removeWeapon(i);

            // The weapon is added to the list of weapons if it is not null
            if(w == null){
                System.out.println("WARNING! No weapon on SpaceStation.mountWeapon()");
            }else{
                weapons.add(w);
            }
        }
    }

    // Discard Elements
    //==========================================================================
    /**
     * @brief Removes the mounted weapons and shieldBoosters with no uses left
     * */
    public void cleanUpMountedItems(){
        // Cleaning weapons -- WIP programacion funcional
        weapons = new ArrayList<>(weapons.stream().filter(weapon -> weapon.getUses() > 0).collect(Collectors.toList()));

        // Cleaning shieldBoosters -- WIP programacion funcional
        shieldBoosters = new ArrayList<>(shieldBoosters.stream().filter(shield -> shield.getUses() > 0).collect(Collectors.toList()));
    }

    /**
     * @brief Discards an hangar, that's to say, it is set to null
     * */
    public void discardHangar(){
        hangar = null;
    }

    /**
     * @brief Discards a shield in use, due to a pending damage
     *        Therefore, the pending damage is actualized
     * @param i, the position of the shield to discard
     * */
    public void discardShieldBooster(int i){
        if(i >= 0 && i < shieldBoosters.size()){
            ShieldBooster s = shieldBoosters.remove(i); // A shield is removed

            if(pendingDamage != null){
                pendingDamage.discardShieldBooster();   // The pending damage has to be actualized
                cleanPendingDamage();
            }

        }else{
            System.out.println("WARNING! Bad given index on SpaceStation.discardShieldBooster()");
        }
    }

    /**
     * @brief Discards a ShieldBooster from the hangar
     * @param i, the position of the shield booster in the hangar
     * */
    public void discardShieldBoosterInHangar(int i){
        if(hangar == null){
            System.out.println("WARNING! No hangar on SpaceStation.discardShieldBoosterInHangar()");
        }else{
            hangar.removeShieldBooster(i);
        }
    }

    /**
     * @brief A weapon in use is discarded, due to a pendingDamage
     *        Therefore, the pending damage is actualized
     * @param i, the position of the weapon to discard
     * */
    public void discardWeapon(int i){
        if(i >= 0 && i < weapons.size()){
            Weapon w = weapons.remove(i);   // A weapon is removed

            if(pendingDamage != null){
                pendingDamage.discardWeapon(w); // The pending damage has to be actualized
                cleanPendingDamage();
            }

        }else{
            System.out.println("WARNING! Bad given index on SpaceStation.discardWeapon()");
        }
    }

    /**
     * @brief Discards a weapon from the hangar
     * @param i, the position of the weapon in the hangar
     * */
    public void discardWeaponInHangar(int i){
        if(hangar == null){
            System.out.println("WARNING! No hangar on SpaceStation.discardWeaponInHangar()");
        }else{
            hangar.removeWeapon(i);
        }
    }

    // Space Station actions methods
    //==========================================================================
    /**
     * @brief Makes a shot and returns its power
     * @return the power of the shot made
     * */
    @Override
    public float fire(){
        float factor = (float) 1.0; // This variable is used for acummulating the extra power given by weapons 

        // We use the mounted weapons
        for(Weapon w : weapons){
            factor = factor * w.useIt();
        }

        return ammoPower * factor;
    }

    /**
     * @brief the shield is used, and it's protection is returned
     * @return the protection given by the shield
     * */
    @Override
    public float protection(){
        float factor = (float)1.0;  // This variable is used for acummulating the extra protection given by ShieldBoosters

        // The ShieldBoosters are used
        for(ShieldBooster s : shieldBoosters){
            factor = factor * s.useIt();
        }

        return shieldPower * factor;
    }

    /**
     * @brief makes the operations due to a received shot
     *        The shieldPower is reduced due  the the received shot
     * @param shot, the power of the received shot
     * @return the result of the received shot
     * */
    @Override
    public ShotResult receiveShot(float shot){
        if(protection() >= shot){
            // The shield is damaged
            shieldPower = shieldPower - SHIELDLOSSPERUNITSHOT * shot;   
            shieldPower = Math.max((float)0.0, shieldPower);

            // The space station resists the shot
            return ShotResult.RESIST;
        }else{
            // The shield is destroyed
            shieldPower = (float) 0.0;

            // The space station does not resist the shot
            return ShotResult.DONOTRESIST;
        }
    }


    /**
     * @brief Fuel units are decreased due to space station movement
     *        The decrement is the speed of the space station
     * */
    public void move(){
        fuelUnits = Math.max(0, fuelUnits * (1 - getSpeed()));
    }

    // Auxiliar Methods
    //==========================================================================
    /**
     * @brief Assigns fuel value without exceeding the limit
     * @param f, the new fuel value
     * */
    private void assignFuelValue(float f){
        fuelUnits = Math.min(f, MAXFUEL);
    }

    /**
     * @brief If pending damage has no effect, the pending damage is set to null
     * */
    private void cleanPendingDamage(){
        if(pendingDamage.hasNoEffect()){
            pendingDamage = null;
        }
    }
}
