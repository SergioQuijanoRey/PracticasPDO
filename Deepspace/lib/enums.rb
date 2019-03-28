#encoding:utf-8

# Enum to control the possible combat outcomes
module CombatResult
	ENEMYWINS=		:enemywins
	NOCOMBAT=		:nocombat
	STATIONESCAPES=	:stationescapes
	STATIONWINS=	:stationwins
end

# Enum to represent the two types of characters of the game
module GameCharacter
	SPACESTATION=	:spacestation
	ENEMYSTARSHIP=	:enemystarship
end

# Enum to represent the result of a shot taken
module ShotResult
	DONOTRESIST=	:donotresist
	RESIST=			:resist
end

# Enum to represent the types of weapons available on the game
module WeaponType
	# Class to represent the types of weapons
	class Type
		# Description:
		# 	Initializer of the class
		# Parameters:	
		# 	pow: Float,representing the power of the weapon
		# 	_name: String, name of the weapon type
		# Return:
		# 	Nil
		def initialize(pow, _name)
			@POWER = pow
			@name = _name
		end
		
		# Description:
		# 	Power getter
		# Return:
		# 	Float, containing the power of the weapon
		def power
			return @POWER
		end

		def to_s
				return "#{@name}"
		end
	end

	# Types of weapons available on our game
	LASER =		Type.new(2.0, "LASER")
	MISSILE =	Type.new(3.0, "MISSILE")
	PLASMA =	Type.new(4.0, "PLASMA")
end