#encoding:utf-8
# WIP -- Translation
# Clase que representa a un paquete de suministros para una estacion espacial.
# Puede contener armamento, combustible y/o energia para los escudos
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
