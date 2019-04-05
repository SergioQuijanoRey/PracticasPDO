/**
 * @author Sergio Quijano Rey
 * */

package Deepspace;

/**
 * @brief Class to represent shield booster that space stations can have
 * */
class ShieldBooster{
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

	public int getUses(){
		return uses;
	}

	// Setters
	//======================================================================
	/**
	 * @brief method to use the shield booster
	 * @return 	boost, if there are uses left
	 * 	   		1.0 (neutral boost) otherwise
	 * */
	public float useIt(){
		if(uses > 0){
			uses = uses - 1;
			return boost;
		}else{
			return 1.0;
		}

		// Security check
		System.out.println("WARNING! Unexpected condition at ShieldBooster.useIt()");
		return 1.0;
	}
}
