
// WIP -- Lack of documentation

public class Loot{
	// Private atributes
	//======================================================================
	private int nSupplies;
	private int nWeapons;
	private int nShields;
	private int nHangars;
	private int nMedals;

	// Constructors
	//======================================================================
	public Loot(int _nSupplies, int _nWeapons, int _nShields, int _nHangars, int _nMedals){
		nSupplies = _nSupplies;
		nWeapons = _nWeapons;
		nShields = _nShields;
		nHangars = _nHangars;
		nMedals = _nMedals;

	}

	// Getters
	//======================================================================
	public int getNSupplies(){
		return nSupplies;
	}

	public int getNWeapons(){
		return nWeapons;
	}

	public int getNShields(){
		return nShields;
	}

	public int getNHangars(){
		return nHangars;
	}

	public int getNMedals(){
		return nMedals;
	}
}
