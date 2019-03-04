#encoding:utf-8

# WIP -- Check english comments (posbile mistakes :D )

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
	DONOTRESIST=		:donotresist
	RESIST=			:resist
end

# Enum to represent the types of weapons available on the game
module WeaponType
	# Class to represent the types of weapons
	class Type
		# Description:
		# 	Initializer of the class
		# Parameters:	
		# 	pow: integer to represent the power of the weapon
		# Return:
		# 	Nil
		def initialize(pow)
			@POWER = pow
		end
		
		# Description:
		# 	Power getter
		# Return:
		# 	An integer containing the power of the weapon
		def power
			return @POWER
		end
	end

	# Types of weapons available on our game
	LASER =		Type.new(2.0)
	MISSILE =	Type.new(3.0)
	PLASMA =	Type.new(4.0)
end
