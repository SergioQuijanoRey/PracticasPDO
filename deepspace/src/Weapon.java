
// WIP -- Lack of documentation

public class Weapon{
	// Private attributes
	//======================================================================
	private String name;
	private WeaponType type;
	private int uses;
	
	// Constructors
	//======================================================================
	public Weapon(String _name, WeaponType _type, int _uses){
		name = _name;
		type = _type;
		uses = _uses;
	}

	public Weapon(Weapon other){
		this(other.name, other.type, other.uses);
	}

	// Getters
	//======================================================================
	public WeaponType getType(){
		return type;
	}

	public int getUses(){
		return uses;
	}

	public float power(){
		return type.getPower();
	}

	// Setters
	//======================================================================
	/**
	 * @brief method to use the weapon
	 * @return	the power of the weapon, if uses > 0
	 * 			1.0 (no weapon power), otherwise
	 * */
	public float useIt(){
			if(uses > 0){
					uses = uses - 1;
					return power();
			}else{
					return 1.0;
			}

				System.out.printl("WARNING! Unexpected condition at Weapon.useIt()");
				return 1.0;
	}
}
