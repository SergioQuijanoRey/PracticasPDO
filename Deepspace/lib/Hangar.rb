# WIP -- lack of documentation

require_relative "../lib/Weapon.rb"
require_relative "../lib/ShieldBooster.rb"
require_relative "../lib/enums.rb"
require_relative "../lib/HangarToUI.rb"

module Deepspace

class Hangar
		# Initializers
		#=======================================================================

		# Description:
		# 		Initializer of the class
		# Parameters:
		# 		capacity: Integer, max number of shieldBoosters and weapons
		def initialize(capacity)
				@maxElements = capacity
				@shieldBoosters = []
				@weapons = []
		end

		# Description:
		# 		Copy initializer
		# Param:
		# 		h: Hangar, the objet to be copied
		# Returns:
		# 		Hangar, a copy of the hangar parameter given
		def self.newCopy(h)
				copy =  Hangar.new(h.maxElements)
				for shieldBooster in @shieldBoosters
						copy.addShieldBooster(shieldBooster)
				end

				for weapon in @weapons
						copy.addWeapon(weapon)
				end

				return copy
		end

		def shieldBoosters
				return @shieldBoosters
		end

		def weapons
				return @weapons
		end

		# Description:
		# 	Checks if there's space left for more elements at the hangar
		# Returns:
		# 		Boolean:	true, if there's space left for at least one more element
		# 					false, if the hangar is full
		def spaceAvailable
				return @maxElements - used_capacity() > 0
		end

		# Description:
		# 	Gets the UI representation of the object
		# Returns
		# 	HangarToUI: the associated UI representation 
		def getUIversion
				return HangarToUI.new(self)
		end

		# Description:
		# 	Returns a string representation of the object
		# Returns:
		# 	String: a representation of the object
		def to_s
				return "Hangar(#{@maxElements}) with #{@weapons} weapons and #{@shieldBoosters} shields"
		end

		# WIP -- Not in the UML diagram -- Because of DRY
		private def used_capacity
				return @weapons.length + @shieldBoosters.length
		end

		# Setters
		#=======================================================================

		# Description:
		# 	Adds a new weapon to the Hangar
		# Parameters:
		# 		w: Weapon, the weapon to be added
		# Returns:
		# 		Boolean:	true, if the operation runs successfully
		# 					false, if something fails (no room for another weapon)
		def addWeapon(w)
				if spaceAvailable()
						@weapons << w
						return true
				else
						return false
				end
		end

		# Description:
		# 	Removes a weapon from the Hangar
		# Parameters:
		# 	w: Integer
		# Returns:
		# 	Weapon:	Nil, if position is invalid
		# 			the weapon deleted, if position is valid
		def removeWeapon(w)
				if w >= @weapons.length or w < 0
						puts "WARNING! Invalid position to specify a weapon, on Hangar.removeWeapon(w: Integer)"
						return Nil
				else
						return @weapons.delete_at(w)
				end
		end

		# Description:
		# 	Adds a new shieldBooster to the Hangar
		# Parameters:
		# 		s: ShielBooster, the shield to be added
		# Returns:
		# 		Boolean:	true, if the operation runs successfully
		# 					false, if something fails (no room for another shield)
		def addShieldBooster(s)
				if spaceAvailable()
						@shieldBoosters << s
						return true
				else
						return false
				end
		end

		# Description:
		# 	Removes a shield from the Hangar
		# Parameters:
		# 	s: Integer, position of the shield to remove
		# Returns:
		# 	ShielBooster,	Nil, if position is not valid
		# 					the shieldBooster which has been deleted
		def removeShieldBooster(s)
				if s >= @shieldBoosters.length or s < 0
						puts "WARNING! Invalid position to specify a shield, on Hangar.removeShieldBooster(s: Integer)"
						return Nil
				else
						return  @shieldBoosters.delete_at(s)
				end
		end

end