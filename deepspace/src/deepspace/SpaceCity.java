package deepspace;

import java.util.ArrayList;

/**
 * A class to represent a SpaceCity
 * A SpaceCity is a set of SpaceStations with a base SpaceStation which act together
 *
 * @author Sergio Quijano Rey
 * */
class SpaceCity extends SpaceStation{
    // Private Attributes
    //==========================================================================
    private SpaceStation base;                      // The leader Space Station
    private ArrayList<SpaceStation> collaborators;  // The set of Space Stations, they don't contain the base Space Station

    // Constructors
    //==========================================================================
    /**
     * Constructor of the SpaceCity
     *
     * @param _base, the base SpaceStation
     * @param _collaborators, the set of SpaceStations
     * */
    public SpaceCity(SpaceStation _base, ArrayList<SpaceStation> _collaborators){
        super(_base);                                       // The basic space station needs to be constructed
        base = new SpaceStation(_base);                     // Secure copy
        collaborators = new ArrayList<>(_collaborators);    // Secure copy
    }

    // Getters
    //==========================================================================
    /**
     * Getter for the collaborators
     * @return a copy of the collaborators
     * */
    public ArrayList<SpaceStation> getCollaborators(){
        return new ArrayList<>(collaborators);  // Secure copy
    }

    /**
     * Method to fire
     * All the SpaceStations fires at the same time to get a more powerful attack
     * @return the power of the group shot
     * */
    @Override
    public float fire(){
        float power = base.fire();

        for(SpaceStation station : collaborators){
            power = power * station.fire();
        }

        return power;
    }

    /**
     * Method to protect
     * All the SpaceStations protect at the same time to get a more powerful protection
     * @return the power of the group protection
     * */
    @Override
    public float protection(){
        float power = base.protection();

        for(SpaceStation station : collaborators){
            power = power * station.protection();
        }

        return power;
    }

    /**
     * Gets the string representation
     * @return the string representation
     * */
    @Override
    public String toString(){
        return  "SPACECITY[[\n" + 
                "\t\tbase: "super.toString() + 
                "\t\tcollaborators: " + collaborators +
                "\n]]"
        
    }

    // Setters
    //==========================================================================
    /**
     * The space city receives a loot
     * We avoid making a transformation if the loot contains one
     * @param lo, the loot to receive
     * */
    @Override
    public Transformation setLoot(Loot lo){
        base.setLoot(lo);
        return Transformation.NOTRANSFORM;
    }
}
