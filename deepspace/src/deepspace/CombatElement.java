/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 * Interface to determine the behaviour of a usable element (shield and weapon).
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public interface CombatElement {
    /**
     * Consult how many uses are left.
     * @return the uses left
     */
    public int getUses();
    
    /**
     * Use the element.
     * @return the value given by the element after using it
     */
    public float useIt();
}
