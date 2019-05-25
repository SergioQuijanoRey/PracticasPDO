package deepspace;

/**
 * Interface to define the behaivour of an usable element (Shield and Weapon)
 * @author Sergio Quijano Rey
 * */
interface CombatElement{
    /**
     * Method to consult how many uses are left
     * @return the uses left
     * */
    public int getUses();

    /**
     * Method to use the element
     * @return the value given by the element after using it
     * */
    public float useIt();
}
