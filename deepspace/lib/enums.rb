#encoding:utf-8

# Enum to control the possible combat outcomes
module CombatResult
	ENEMYWINS=		:enemywins
	NOCOMBAT=		:nocombat
	STATIONESCAPES=	:stationescapes
	STATIONWINS=	:stationwins
end

# WIP -- English translation
# Enumerado para representar los dos tipos de jugadores que admite el juego
module GameCharacter
	SPACESTATION=	:spacestation
	ENEMYSTARSHIP=	:enemystarship
end

# WIP -- English translation
# Enumerado para representar el resultado de un disparo recibido por una nave
# enemiga o una estación espacial
module ShotResult
	DONOTRESIST=	:donotresist
	RESIST=			:resist
end

# WIP -- English translation
# Enumerado para representar los tipos de armas
module WeaponType
	# Clase que representa los tipos de armas
	class Type
		def initialize(pow)
			@POWER = pow
		end
		
		def power
			return @POWER
		end
	end

	# Tipos de armas que acepta nuestro juego
	LASER=		Type.new(2.0)
	MISSILE=	Type.new(3.0)
	PLASMA=		Type.new(4.0)
end
