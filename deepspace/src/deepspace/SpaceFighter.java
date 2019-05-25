package deepspace;

/**
 * Interface to define the behaivour of a any class which can combat
 * @author Sergio Quijano Rey
 * */
interface SpaceFighter{
    /**
     * Method to make a shoot
     * @return the power of it's shot
     * */
    public float fire();

    /**
     * Method to protect from a shot
     * @return the power of the protection taken
     * */
    public float protection();

    /**
     * Method to receive a shot
     * @param shot, the power of the received shot
     * @return the result of the received shot
     * */
    public ShotResult receiveShot(float shot);
}
