#encondig:utf-8

require_relative 'LootToUI'

module Deepspace

# Class to represent the loot obtained by defeating a enemy ship
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class Loot

	# Constructors
	# ==========================================================================

	# Class initializer
	# @param _nSupplies [Integer] number of supplies given by a loot
	# @param _nWeapons [Integer] number of weapons given by a loot
	# @param _nShields [Integer] number of boosters given by a loot
	# @param _nHangars [Integer] number of hangars given by a loot
	# @param _nMedals [Integer] number of medals given by a loot
	def initialize(_nSupplies, _nWeapons, _nShields, _nHangars, _nMedals)
		# @!attribute [Integer] number of supplies given by a loot
		@nSupplies = _nSupplies

		# @!attribute [Integer] number of weapons given by a loot
		@nWeapons = _nWeapons

		# @!attribute [Integer] number of boosters given by a loot
		@nShields = _nShields

		# @!attribute [Integer] number of hangars given by a loot
		@nHangars = _nHangars

		# @!attribute [Integer] number of medals given by a loot
		@nMedals = _nMedals
	end

	# Getters
	# ==========================================================================

	attr_reader :nSupplies, :nWeapons, :nShields, :nHangars, :nMedals
	
	# String representation, UI version
	# ==========================================================================

	# String representation of the object
	# @return [String] string representation
	def to_s
		getUIversion().to_s
	end

	# To UI
	def getUIversion
		return LootToUI.new(self)
	end
end # class Loot

end # module Deepspace