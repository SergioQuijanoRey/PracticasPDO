#encoding:utf-8

module Deepspace

# WIP -- Not ended

# Description:
# 	Represents damages done on a spaceShip after loosing a combat
# 	They indicate which elements are going to be lost after loosing the combat
class Damage
		# Initializers
		#=======================================================================
		# Description:
		# 	Initializer of the class
		# 	It's just going to be used by other "constructors"
		# Parameters:
		# 	_nWeapons: Integer, number of lost weapons
		# 	_nShields: Integer, number of lost shields
		# 	_weapons: WeaponType[], types of weapons to be discard
		private def initialize(_nWeapons, _nShields, _weapons)
				@nWeapons = _nWeapons
				@nShields = _nShields
				@weapons = _weapons
		end
		
		def newNumericWeapons
		end

		def newSpecificWeapons
		end

		# Description:
		# 	Creates a copy of a given damage
		# Parameters:
		# 	d: Damage, the instance to be copied
		# Returns
		# 	Damage: a copy of the given instance
		def self.newCopy(d)
		end

		# Getters
		#=======================================================================
		def nWeapons
				return @nWeapons
		end

		def nShields
				return @nShields
		end

		def weapons
				return @weapons
		end

		def getUIVersion
		end

		# Descriptio:
		# Parameters:
		# 	w: Weapon[]
		# 	t: WeaponType
		# Returns:
		# 	Int:
		private def arrayContainsType(w, t)
		end
		
		# Setters+getWeapons()
		#=======================================================================
		# Description:
		# Parameters:
		# 	w: Weapon[]
		# 	s: shieldBooster
		# Returns:
		# 	Damage:
		def adjust(w, s)
		end

		# Description:
		# 	Removes a given type of weapon
		# 	If a list of weapon types is not available, the number of weapons to be deleted decreases by 1
		# Parameters:
		# 	w: WeaponType, the weapon type to be removed # WIP -- No concuerda con el guion de la practica!
		def discardWeapon(w)
				if @weapons.length != 0
						@weapons.delete(w)
				else
						if @nWeapons > 0
								@nWeapons = @nWeapons - 1
						else
								puts "WARNING! You tryied to have negative weapons at Damage.discardWeapon()"
				end
		end

		# Description:
		# 	Reduces by 1 the number of shieldBoosters to be removed
		def discardShieldBooster
				if @nShields > 0
						@nShields = @nShields - 1
				else
						puts "WARNING! You tryied to have negative shieldBoosters at Damage.discardShieldBooster()"
		end

		# Description:
		# Returns:
		# 	Boolean:
		def hasNoEffect()
		end
end

end	# module Deepspace
