#encoding:utf-8

require_relative "../lib/DamageToUI.rb"

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
		
		# Description:
		# 	Creates a Damage where weapon types are not known
		# Parameters:
		# 	_nWeawpons: Integer, number of weapons to loose
		# 	_nShields: Integer, number of shields to loose
		def newNumericWeapons(_nWeapons, _nShields)
				return new(_nWeapons, _nShields, [])
		end

		# Description:
		# 	Creates a Damage object where weapon types are specified
		# Parameters:
		# 	_weapons: WeaponType[], types of weapons to discard
		# 	_nShields: Integer, number of shields to loose
		def newSpecificWeapons(weapons, _nShields)
				return new(0, _nShields, weapons)
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

		# Description:
		# 	Returns the UI representation of the object
		# Returns:
		# 	DamageToUI: the UI representation of the object
		def getUIVersion
				return DamageToUI(self)
		end

		# Description:
		# 	Searchs the first element of WeaponType array to match a given type
		# Parameters:
		# 	w: Weapon[], the array of weapon types where we search
		# 	t: WeaponType, the type we're looking for
		# Returns:
		# 	Int:	position, if the element is found
		# 			-1, if no element is found
		private def arrayContainsType(w, t)
				for index in 0..w.length-1
						# Fist position found
						if w[index] == t
								return index
						end
				end

				# No element found
				return -1
		end
		
		# Setters
		#=======================================================================
		# Description:
		# 	Creates a copy of current objet where weapons and shields which are
		# 	not included in arrays given as parameters are discarded. That's to say,
		# 	we srink the Damage to the parameters
		# Parameters:
		# 	w: Weapon[], weapons to fit
		# 	s: shieldBooster[], shields to fit
		# Returns:
		# 	Damage: a copy of the object adjusted as explained above
		def adjust(w, s)
				# Copy of the current object
				copy = Damage.newCopy(self)

				# Weapons adjust
				for weapon in w
						if copy.weapons.include? weapon == false
								copy.discardWeapon(weapon)
						end
				end

				# Shields adjust
				for shield in s
						if copy.shieldBoosters.include? shield == false
								copy.discardShieldBooster(shield)
						end
				end

				return copy
		end

		# Description:
		# 	Removes a given type of weapon
		# 	If a list of weapon types is not available, the number of weapons to be deleted decreases by 1
		# Parameters:
		# 	w: WeaponType, the weapon type to be removed # WIP -- No concuerda con el guion de la practica!
		def discardWeapon(w)
				if @weapons.length != 0
						position = arrayContainsType(weapons, w)
						if position != -1
								@weapons.delete_at(position)
						else
								puts "WARNING! No weapon type match at Damage.discardWeapon()"
						end

				else
						if @nWeapons > 0
								@nWeapons = @nWeapons - 1
						else
								puts "WARNING! You tryied to have negative weapons at Damage.discardWeapon()"
						end
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
		end

		# Description:
		# 	Checks wether or not damage is affecting
		# Returns:
		# 	Boolean:	true, if damage has effective effect
		# 				false, if damage has no effective effect
		def hasNoEffect
				return @nShields + @nWeapons > 0
		end
end

end	# module Deepspace
