#encoding:utf-8

module Deepspace

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

	module EnumeradoExamen
		# Class to represent the types of weapons
		class Type
			# Description:
			# 	Initializer of the class
			# Parameters:	
			# 	pow: Float,representing the power of the weapon
			# 	_name: String, name of the weapon type
			# Return:
			# 	Nil
			def initialize(_dimension, _magnitud)
				@dimension = _dimension
				@magnitud = _magnitud
			end
			
			# Description:
			# 	Power getter
			# Return:
			# 	Float, containing the power of the weapon
			def getDimension
				return @dimension
			end

			def getMagnitud
				return @magnitud
			end

			def to_s
					return "Enum: dim #{getDimension}, mag: #{getMagnitud}"
			end
		end

		# Types of weapons available on our game
		CIRCULO =	Type.new("Radio", 5)
		CUADRADO =	Type.new("Lado", 2)
		HEXAGONO =	Type.new("Apotema", 3)
	end

end # module Deepspace