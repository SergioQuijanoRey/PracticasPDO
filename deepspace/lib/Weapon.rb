#encoding:utf-8

# WIP -- Documentation needed
# Class to represent the weapons that a space station can dispose of to
# increment its energy when shooting
class Weapon

	def initialize(_name, _type, _uses)
		@name = _name
		@type = _type
		@uses = _uses
	end

	def newCopy(origin)
		return Weapon.new(@origin.name, @origin.type, @origin.uses)
	end

	# Description:
	# 	Getter for type
	# Return
	#	WeaponType with type
	def type
		return @type
	end

	# Description:
	# 	Getter for uses
	# Returns:
	#	Int with uses
	def uses
		return @uses
	end

	def power
		return @type.power
	end

	def useIt
		if @uses > 0
			@uses -= 1
			return power
		else
			return 1.0
		end
	end
end
