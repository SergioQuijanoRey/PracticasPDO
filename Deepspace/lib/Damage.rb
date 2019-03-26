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
		private def initialize(_nWeapons, _nShields)
				@nWeapons = _nWeapons
				@nShields = _nShields
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

		# Description:
		# Returns:
		# 	WeaponType[]
		def getWeapons
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
		# Parameters:
		# 	w: Weapon
		def discardWeapon(w)
		end

		# Description:
		def discardShieldBooster()
		end

		# Description:
		# Returns:
		# 	Boolean:
		def hasNoEffect()
		end
end


end	# module Deepspace
