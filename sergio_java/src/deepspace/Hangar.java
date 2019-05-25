/**
 * @author Sergio Quijano Rey
 * @file Hangar.java
 * */

package deepspace;

import java.util.ArrayList;

/**
 * @brief class to represent an hangar
 *
 * An hangar is a container with ShieldBoosters and Weapons
 * An hangar has a maximun number of shieldboosters and weapons
 *
 * WIP -- He cambiado de Array a ArrayList, puede que haya roto bastantes cosas
 * */
class Hangar{
    // Private attributes
    //==========================================================================
    private int maxElements;                            //< Max number of shields and weapons
    private ArrayList<ShieldBooster> shieldBoosters;    //< ShieldBoosters of the hangar
    private ArrayList<Weapon> weapons;                  //< Weapons of the hangar
    
    // Constructors
    //==========================================================================
    /**
     * @brief Constructor of the class
     * @param capacity the maximun of elements that the hangar can hold
     * */
    Hangar(int capacity){
        maxElements = capacity;
        shieldBoosters = new ArrayList<ShieldBooster>(0);
        weapons = new ArrayList<Weapon>(0);
    }

    /**
     * @brief Copy constructor
     * @param h the hangar which is copied
     * @post we have an hangar with same attributes
     *
     * WIP -- Tengo que testear porque no se si la copia de los arrays es muy correcta
     * */
    Hangar(Hangar h){
        // We construct the object
        this(h.getMaxElements());

        // We add the weapons
        weapons = new ArrayList<Weapon>(h.getWeapons());

        // We add the shieldBoosters
        shieldBoosters = new ArrayList<ShieldBooster>(h.getShieldBoosters());
    }

    // Getters -- WIP -- Devuelvo el objeto, seria mas seguro devolver una copia de los arrays
    //==========================================================================
    /**
     * @brief Getter of maxElements
     * @return maxElements
     * */
    public int getMaxElements(){
        return maxElements;
    }

    /**
     * @brief Getter of shieldBoosters
     * @return shieldBoosters
     * */
    public ArrayList<ShieldBooster> getShieldBoosters(){
        // Secure return
        return new ArrayList<ShieldBooster>(shieldBoosters);
    }

    /**
     * @brief Getter of weapons
     * @return weapons
     * */
    public ArrayList<Weapon> getWeapons(){
        // Secure return
        return new ArrayList<Weapon>(weapons);
    }

    /**
     * @brief Gets the string representation of the object
     * @return the string representation of the object
     * */
    @Override
    public String toString(){
        return  "Hangar(" + 
                "maxElements =  " + maxElements + 
                ", weapons =  " + weapons + 
                ",ShieldBoosters: " + shieldBoosters +
                ")";
    }

    /**
     * @brief Get the UI representation of the object
     * @return the UI representation of the object
     * */
    HangarToUI getUIversion(){
        return new HangarToUI(this);
    }

    /**
     * @brief Checks if there are space available for more shields or weapons
     * @return  true, if there is space for at least one more element
     *          false, otherwise
     * */
    private boolean spaceAvailable(){
        return weapons.size() + shieldBoosters.size() < maxElements;
    }

    // Setters
    //==========================================================================
    /**
     * @brief Adds a new weapon to the hangar
     * @param w the weapon to be added
     * @return  true, if the weapon has added succesfully
     *          false, if something went wrong
     * */
    public boolean addWeapon(Weapon w){
        if(spaceAvailable()){
            // We create a copy for security
            Weapon copy = new Weapon(w);

            // We add the copy
            weapons.add(copy);

            return true;
        }else{
            return false;
        }
    }

    /**
     * @brief Adds a new ShieldBooster to the hangar
     * @param s the shield to be added
     * @return  true, if the shield has added succesfully
     *          false, if something went wrong
     * */
    public boolean addShieldBooster(ShieldBooster s){
        if(spaceAvailable()){
            // We create a copy for security
            ShieldBooster copy = new ShieldBooster(s);

            // We add the copy
            shieldBoosters.add(copy);

            return true;
        }else{
            return false;
        }
    }

    /**
     * @brief Removes a shieldbooster from the hangar
     * @param s the position of the shield to be removed
     * @return  the remved shield, if the operation runned succesfully
     *          null, if something went wrong
     * */
    public ShieldBooster removeShieldBooster(int s){
        if(s < 0 || s >= shieldBoosters.size()){
            System.out.println("Warning! You introduced an invalid position on Hangar.removeShieldBooster()");
            System.out.println("Returning null");
            return null;
        }else{
            return shieldBoosters.remove(s);
        }
    }

    /**
     * @brief Removes a weapon from the hangar 
     * @param w the position of the weapon to be removed
     * @return  the removed weapon, if the operation runned succesfully
     *          null, if something went wrong
     * */
    public Weapon removeWeapon(int w){
        if(w < 0 || w >= weapons.size()){
            System.out.println("Warning! You introduced an invalid position on Hangar.removeWeapon()");
            System.out.println("Returning null");
            return null;
        }else{
            return weapons.remove(w);
        }
    }
}
