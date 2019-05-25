/**
 * @author Sergio Quijano Rey
 * */

package deepspace;

/**
 * Class to represent an enemy starship
 *
 * @author Sergio Quijano Rey
 * */
class EnemyStarShip implements SpaceFighter{
    // Private attributes
    //==========================================================================
    private float ammoPower;    //< Ammo power of the enemy starship
    private float shieldPower;  //< Shield power of the enemy starship
    private Loot loot;          //< Given loot when defeated
    private Damage damage;      //< Damage when defeated by the starship
    private String name;        //< Name of the enemy starship

    // Constructors
    //==========================================================================
    /**
     * @brief Constructor
     * @param n the name of the starship
     * @param a the ammoPower of the starship
     * @param s the shieldPower of the starShip
     * @param l the loot given when the starship loooses
     * @param d the damage done when the starship wins
     * */
    EnemyStarShip(String n, float a, float s, Loot l, Damage d){
        name = n;
        ammoPower = a;
        shieldPower = s;
        loot = l;
        damage = d;
    }

    /**
     * @brief Copy constructor
     * @param e the object to copy
     * */
    EnemyStarShip(EnemyStarShip e){
        this(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage);
    }
    
    // Getters
    //==========================================================================
    /**
     * @brief getter for the ammo power
     * @return the ammoPower
     * */
    public float getAmmoPower(){
        return ammoPower;
    }

    /**
     * @brief getter for the shield power
     * @return the shieldPower
     * */
    public float getShieldPower(){
        return shieldPower;
    }

    /**
     * @brief getter for the loot
     * @return the loot
     * */
    public Loot getLoot(){
        return loot;
    }

    /**
     * @brief getter for the name
     * @return the name
     * */
    public String getName(){
        return name;
    }

    /**
     * @brief getter for the damage
     * @return the damage
     * */
    public Damage getDamage(){
        return damage;
    }

    /**
     * @brief gets the UI version of the object
     * @return the UI version
     * */
    EnemyToUI getUIversion(){
        return new EnemyToUI(this);
    }

    /**
     * @brief gets the shield power of the enemy starship (same as getShieldPower)
     * @return the shieldPower
     * */
    @Override
    public float protection(){
        return shieldPower;
    }

    /**
     * @brief gets the ammo power of the enemy starship (same as getAmmoPower)
     * @return the ammoPower
     * */
    @Override
    public float fire(){
        return ammoPower;
    }

    /**
     * @brief gets the string representation of the object
     * @return the string representation
     * */
    @Override
    public String toString(){
        return  "EnemyStarShip(" +
                "name = " + name +
                ", ammoPower = " + ammoPower +
                ", shieldPower = " + shieldPower +
                ", loot = " + loot +
                ", damage = " + damage +
                ")";
    }

    // Setters
    //==========================================================================
    /**
     * @brief calculates the result of receiving a shot 
     * @return  ShotResult.RESIST, if the enemy starship resists the shot 
     *          ShotResult.DONOTRESIST, if the enemy starship doesn't resist the shot
     * */
    @Override
    public ShotResult receiveShot(float shot){
        if(protection() < shot){
            return ShotResult.DONOTRESIST;
        }else{
            return ShotResult.RESIST;
        }
    }
}
