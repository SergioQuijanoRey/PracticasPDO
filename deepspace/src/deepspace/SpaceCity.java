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
public class SpaceCity extends SpaceStation {
    /**
     * Base of space city.
     */
    private SpaceStation base;
    
    /**
     * Rest of space cities (collaborators).
     */
    private ArrayList<SpaceStation> collaborators;
    
    /**
     * Class intializer.
     * @param _base base of space city
     * @param _rest rest of space cities (collaborators)
     */
    SpaceCity(SpaceStation _base, ArrayList<SpaceStation> _rest) {
        super(_base);
        base = _base;
        collaborators = _rest;
    }
    
    /**
     * Getter for collaborators.
     * @return collaborators
     */
    public ArrayList<SpaceStation> getCollaborators() {
        return collaborators;
    }
    
    /**
     * Makes all space stations shoot at the same time.
     * @return all accumulated shot power
     */
    @Override
    public float fire() {
        float power = super.fire();
        
        for ( SpaceStation station : collaborators )
            power += station.fire();
        
        return power;
    }
    
    /**
     * Uses all protection shields from all stations at the same time.
     * @return all accumulated shield energy
     */
    @Override
    public float protection() {
        float energy = super.protection();
        
        for ( SpaceStation station : collaborators )
            energy += station.protection();
        
        return energy;
    }
    
    /**
     * Receives a loot.
     * @param loot loot to be received
     */
    @Override
    public Transformation setLoot(Loot loot) {
        super.setLoot(loot);
        return Transformation.NOTRANSFORM;
    }
    
    /**
     * To UI.
     */
    @Override
    public SpaceCityToUI getUIversion() {
        return new SpaceCityToUI(this);
    }
}