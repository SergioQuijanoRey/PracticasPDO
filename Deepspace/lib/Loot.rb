#encondig:utf-8

# WIP -- Check English comments (possible mistakes :D )

require_relative "../lib/LootToUI.rb"

module Deepspace

# Class to represent the loot obtained by defeating a enemy ship
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

	attr_reader :nSupplies, :nWeapons, :nShields, :nHangars, :nMedals
	
	# Description:
	# 	Displays debuf info of the object
	# 	It uses getters to get the parameters value
	# Returns:
	# 	String, containing all the relevant data
	def to_s
		return "Loot
		\tnSupplies: #{nSupplies}
		\tnWeapons: #{nWeapons}
		\tnShields: #{nShields}
		\tnHangars: #{nHangars}
		\tnMedals: #{nMedals})"
	end

	def getUIVersion
		return LootToUI.new(self)
	end

end # class Loot

end # module Deepspace
