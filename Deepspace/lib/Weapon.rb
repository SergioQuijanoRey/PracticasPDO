#encoding:utf-8

# WIP -- Check english comments (possible mistakes)

# Class to represent the weapons that a space station can dispose of to
# increment its energy when shooting
class Weapon
	# Constructors
	#=======================================================================

	# Description:
	# 	Class initializer
	# Parameters:
	# 	_name: String, name of the weapon
	# 	_type: WeaponType, type of weapon
	# 	_uses: Integer, number of uses of weapon
	# Return:
	# 	Nil
	def initialize(_name, _type, _uses)
		@name = _name
		@type = _type
		@uses = _uses
	end

	# Description:
	# 	Copy initializer of the class
	# Parameter:
	# 	origin: Weapon, weapon we want to copy
	# Return:
	# 	Weapon, a copy of origin
	def newCopy(origin)
		return Weapon.new(@origin.name, @origin.type, @origin.uses)
	end

	# Getters
	#=======================================================================

	# Description:
	# 	Getter for type
	# Return
	#	WeaponType, type of the object
	def type
		return @type
	end

	# Description:
	# 	Getter for uses
	# Returns:
	#	Integer, number of uses left
	def uses
		return @uses
	end

	# Description:
	# 	Getter for power
	# Return:
	# 	Integer, power of the WeaponType of the object
	def power
		return @type.power
	end

	# Setters
	#=======================================================================
	
	# Description:
	# 	Method for using the Weapon
	# Return:
	# 	Float, power if uses > 0
	#               1.0 otherwise
	def useIt
		if @uses > 0
			@uses = @uses - 1
			return power
		else
			return 1.0
		end
	end
end
