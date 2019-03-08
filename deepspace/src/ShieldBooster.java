
// WIP -- Lack of documentation

public class ShieldBooster{
	// Private attributes
	//======================================================================
	private String name;
	private float boost;
	private int uses;

	// Constructors
	//======================================================================
	public ShieldBooster(String _name, float _boost, int _uses){
		name = _name;
		boost = _boost;
		uses = _uses;
	}

	public ShieldBooster(ShieldBooster other){
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
	 * @return boost, if there are uses left
	 * 	   1.0 (neutral boost) otherwise
	 * */
	public float useIt(){
		if(uses > 1){
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
