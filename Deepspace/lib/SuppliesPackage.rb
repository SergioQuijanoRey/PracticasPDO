#encoding:utf-8

module Deepspace

# Class to represent a Supplies Package
# It can contain ammo, fuel or shield energy
class SuppliesPackage
	
	# Constructors
	#===========================================================================

	# Description:
	# 	Class initializer
	# Parameters:
	# 	ammoPower: float to parametrize ammunition power
	# 	fuelUnits: float to count fuel units
	# 	shieldPower: float to parametrize shield power
	# Return:
	# 	Nil
	def initialize(_ammoPower, _fuelUnits, _shieldPower)
		@ammoPower = _ammoPower
		@fuelUnits = _fuelUnits
		@shieldPower = _shieldPower
	end

	# Description:
	# 	Copy Constructor
	# Parameters:
	# 	origin: SuppliesPackage instance which is going to be copied
	# Return:
	# 	A SuppliesPackage instance holding a copy of origin
	def self.newCopy(origin)
		return new(origin.ammoPower, origin.fuelUnits, origin.shieldPower)
	end

	# Getters
	#===========================================================================

	attr_reader :ammoPower, :fuelUnits, :shieldPower

	# Description:
	# 		Displays debug info
	# 		It uses getters to get parameters values
	# Returns:
	# 		String: containing the object data
	def to_s
		return "ammoPower: #{ammoPower}, fuelUnits: #{fuelUnits}, shieldPower: #{shieldPower}"
	end

end # class SuppliesPackage

end # module Deepspace
