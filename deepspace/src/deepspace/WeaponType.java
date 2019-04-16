/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * @brief Enum to represent the types of weapons available on the game
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public enum WeaponType {
    LASER(2.0f), MISSILE(3.0f), PLASMA(4.0f);
    
    private float Power;
    
    /**
     * @brief Initializer
     * @param power Represents weapon power
     */
    WeaponType(float power) {
        this.Power = power;
    }
    
    /**
     * @brief Getter
     * @return Weapon power
     */
    float getPower() {
        return Power;
    }
}
