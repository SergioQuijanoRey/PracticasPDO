#encoding:utf-8

# WIP -- Check english comments (posible mistakes :D )

# Class to represent a Supplies Package
# It can contain ammo, fuel or shield energy
class SuppliesPackage
	
	# Constructors
	#===========================================================================

	# Description:
	# 	Class initializer
	# Parameters:
	# 	ammoPower: integer to parametrize ammunition power
	# 	fuelUnits: integer to count fuel units
	# 	shieldPower: integer to parametrize shield power
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
		return SuppliesPackage.new(@origin.ammoPower, @origin.fuelUnits, @origin.shieldPower)
	end

	# Getters
	#===========================================================================

	# Description:
	# 	Getter for ammoPower
	# Return
	#	Integer with ammoPower
	def ammoPower
		return @ammoPower
	end

	# Description:
	# 	Getter for fuelUnits
	# Return
	#	Integer with fuelUnits
	def fuelUnits
		return @fuelUnits
	end

	# Description:
	# 	Getter for shieldPower
	# Return
	#	Integer with shieldPower
	def shieldPower
		return @shieldPower
	end

end
