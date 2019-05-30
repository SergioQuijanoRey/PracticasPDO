/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * Defines the behaviour of objects that can combat.
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public interface SpaceFighter {
    /**
     * Make a shot.
     * @return the shot power
     */
    public float fire();
    
    /**
     * Use protection shield.
     * @return the shield's energy
     */
    public float protection();
    
    /**
     * Makes the operations related to the reception of an enemy's impact.
     * @param shot enemy's impact shot power
     * @return result of shot received
     */
    public ShotResult receiveShot(float shot);
}
