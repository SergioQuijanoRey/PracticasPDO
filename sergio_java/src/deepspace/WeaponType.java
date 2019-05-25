/**
 * @author Sergio Quijano Rey
 * */

package deepspace;

/**
 * @brief Enumerated to represent the type of weapons of our game
 * 
 * Each weapon type has a getter of it's power: getPower()
 * */
public enum WeaponType{
    // Available instances of the enumerated
    LASER((float)2.0), 
    MISSILE((float)3.0), 
    PLASMA((float)4.0);

    // Private atributes
    private float power;

    // Constructor
    WeaponType(float _power){
        power = _power;
    }

    // Getter
    float getPower(){
        return power;
    }
}
