#encondig:utf-8

require_relative 'LootToUI'

module Deepspace

# Description:
# 	Class to represent the loot obtained by defeating an enemy's ship
class Loot

	# Constructores
	#===========================================================================

	# Description:
	# 	Class Initializer
	# Parameters:
	# 	nSupplies: integer representing number of Supplies given by a loot
	# 	nWeapons: integer representing number of Weapons given by a loot
	# 	nShields: integer representing number of Shields given by a loot
	# 	nHangars: integer representing number of Hangars given by a loot
	# 	nMedals: integer representing number of Medals given by a loot
	# Return:
	# 	Nil
	def initialize(_nSupplies, _nWeapons, _nShields, _nHangars, _nMedals)
		@nSupplies = _nSupplies
		@nWeapons = _nWeapons
		@nShields = _nShields
		@nHangars = _nHangars
		@nMedals = _nMedals
	end

	# Getters
	#===========================================================================

	# Description:
	# 	Number of Supplies getter
	# Return
	#	Integer, number of Supplies given by a loot
	def nSupplies
		return @nSupplies
	end

	# Description:
	# 	Number of Weapons getter
	# Return
	#	Integer, number of Weapons given by a loot
	def nWeapons
		return @nWeapons
	end
	
	# Description:
	# 	Number of Shields getter
	# Return
	#	Integer, number of Shields given by a loot
	def nShields
		return @nShields
	end

	# Description:
	# 	Number of Hangars getter
	# Return
	#	Integer, number of Hangars given by a loot
	def nHangars
		return @nHangars
	end

	# Description:
	# 	Number of Medals Getter
	# Return
	#	Integer, number of Medals given by a loot
	def nMedals
		return @nMedals
	end

	# Description:
	# 	Displays debuf info of the object
	# 	It uses getters to get the parameters value
	# Returns:
	# 	String, containing all the relevant data
	def to_s
		return "Loot(#{nSupplies}, #{nWeapons}, #{nShields}, #{nHangars}, #{nMedals})"
	end

	def getUIVersion
			return LootToUI.new(self)
	end
	
end # class Loot

end # module deepspace
