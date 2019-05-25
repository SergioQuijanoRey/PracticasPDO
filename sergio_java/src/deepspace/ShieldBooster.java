package deepspace;

/**
 * Class to represent shield booster that space stations can have
 * 
 * @author Sergio Quijano Rey
 * */
class ShieldBooster implements CombatElement{
    // Private attributes
    //======================================================================
    private String name;
    private float boost;
    private int uses;

    // Constructors
    //======================================================================
    ShieldBooster(String _name, float _boost, int _uses){
        name = _name;
        boost = _boost;
        uses = _uses;
    }

    /**
     * @brief Copy constructor
     * @param other: the ShieldBooster to be copied
     * @post the object has an exact copy of the other given object
     * */
    ShieldBooster(ShieldBooster other){
        this(other.name, other.boost, other.uses);
    }

    // Getters
    //======================================================================
    public float getBoost(){
        return boost;
    }

    /**
     * Gets the uses left of the shield
     * @return the uses left
     */
    @Override
    public int getUses(){
        return uses;
    }

    /**
     * @brief gets the string representation of the object
     * @return the string representation of the object
     * */
    @Override
    public String toString(){
        return  "ShieldBooster(" +
                "name = " + name +
                ", boost = " + boost +
                ", uses = " + uses +
                ")";
    }

    /**
     * @brief Gets the UI representation of the object
     * @return the UI representation
     * */
    public ShieldToUI getUIversion(){
        return new ShieldToUI(this);
    }

    // Setters
    //======================================================================
    /**
     * Uses the shield booster
     * @return  boost, if there are uses left, in which case uses are decreased
     *          neutral boost (1.0) if there are not uses left
     * */
    @Override
    public float useIt(){
        if(uses > 0){
            uses = uses - 1;
            return boost;
        }else{
            return (float) 1.0;
        }
    }
}
