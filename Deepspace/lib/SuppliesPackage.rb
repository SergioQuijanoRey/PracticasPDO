#encoding:utf-8

module Deepspace

# Class to represent a supplies package.
# It can contain ammo, fuel or shield energy
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class SuppliesPackage
	
	# Initializers
	# ==========================================================================

	# Class initializer
	# @param _ammoPower [Float] parametrizes ammunition power
	# @param _fuelUnits [Float] counts fuel units
	# @param _shieldPower [Float] parametrizes shield power
	def initialize(_ammoPower, _fuelUnits, _shieldPower)
		# @!attribute [Float] parametrizes ammunition power
		@ammoPower = _ammoPower

		# @!attribute [Float] counts fuel units
		@fuelUnits = _fuelUnits

		# @!attribute [Float] parametrizes shield power
		@shieldPower = _shieldPower
	end

	# Copy constructor
	# @param origin [SuppliesPackage] instance which is going to be copied
	# @return [SuppliesPackage] a copy of the given instance
	def self.newCopy(origin)
		return new(origin.ammoPower, origin.fuelUnits, origin.shieldPower)
	end

	# Getters
	# ==========================================================================

	attr_reader :ammoPower, :fuelUnits, :shieldPower

	# String representation
	# ==========================================================================

	# String representation of the object
	# @return [String] string representation
	def to_s
		message = "[SuppliesPackage]-> ammoPower: #{@ammoPower}, " \
				+ "fuelUnits: #{@fuelUnits}, shieldPower: #{@shieldPower}"
        return message
	end
end # class SuppliesPackage
	
end # module Deepspace