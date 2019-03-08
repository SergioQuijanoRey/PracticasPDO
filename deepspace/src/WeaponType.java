/**
 * @brief Enumerated to represent the type of weapons of our game
 * 
 * Each weapon type has a getter of it's power: getPower()
 * */
public enum WeaponType{
	// Available instances of the enumerated
	LASER(2.0), MISSILE(3.0), PLASMA(4.0);

	// Private atributes
	private float power;

	// Constructor
	private WeaponType(float _power){
		power = _power;
	}

	public float getPower(){
		return power;
	}
}
