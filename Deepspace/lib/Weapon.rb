#encoding:utf-8

require_relative "WeaponToUI"
#require_relative "enums"

module Deepspace

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
	def self.newCopy(origin)
		return new(origin.name, origin.type, origin.uses)
	end

	# Getters
	#=======================================================================

	attr_reader :type, :uses, :name

	# Description:
	# 	Getter for power
	# Return:
	# 	Integer, power of the WeaponType of the object
	def power
		return @type.power
	end

	# Description:
	# 	Displays relevant data
	# Returns
	# 	String: values of the parameters
	def to_s
		return "[Weapon]-> Name: #{@name}, Type: #{@type}, Power: #{power}, Uses: #{@uses}"
	end

	# Description:
	# 	Gets a UI representation of the object
	# Returns
	# 	WeaponToUI: the object representation
	def getUIVersion
		return WeaponToUI.new(self)
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

end # module Deepspace