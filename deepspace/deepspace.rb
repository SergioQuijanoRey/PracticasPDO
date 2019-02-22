#encoding:utf-8

# WIP -- DESDE AQUI : Meterlo en otro archivo
# Enum to control the possible combat outcomes
module CombatResult
	ENEMYWINS=		:enemywins
	NOCOMBAT=		:nocombat
	STATIONESCAPES=	:stationescapes
	STATIONWINS=	:stationwins
end

# Enumerado para representar los dos tipos de jugadores que admite el juego
module GameCharacter
	SPACESTATION=	:spacestation
	ENEMYSTARSHIP=	:enemystarship
end

# Enumerado para representar el resultado de un disparo recibido por una nave
# enemiga o una estación espacial
module ShotResult
	DONOTRESIST=	:donotresist
	RESIST=			:resist
end

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

# WIP -- HASTA AQUI

# Clase que representa el botin que se obtiene al vencer a una nave enemiga
class Loot

	# Constructores
	#===========================================================================

	# Description:
	# 	Inicializador de la clase 
	# Parameters:
	# 	nSupplies: entero para parametrizar el numero de Supplies que da un botin
	# 	nWeapons: entero para parametrizar el numero de Weapons que da un botin
	# 	nShields: entero que parametriza el numero de Shields que da un botin
	# 	nHangars: entero que parametriza el numero de Hangars que da un botin
	# 	nMedals: entero que parametriza el numero de Medals que da un botin
	# Return:
	# 	Nil
	def initialize(_nSupplies, _nWeapons, _nShields, _nHangars, _nMedals)
		@nSupplies = _nSupplies
		@nWeapons = _nWeapons
		@nShields = _nShields
		@nHangars = _nHangars
		@nMedals = _nMedals
	end

	# Getters
	#===========================================================================

	# Description:
	# 	Consultor publico para numero de Supplies
	# Return
	#	Entero con el numero de Supplies
	def nSupplies
		return @nSupplies
	end

	# Description:
	# 	Consultor publico para numero de Weapons
	# Return
	#	Entero con el numero de Weapons
	def nWeapons
		return @nWeapons
	end
	
	# Description:
	# 	Consultor publico para numero de Shields
	# Return
	#	Entero con el numero de Shields
	def nShields
		return @nShields
	end

	# Description:
	# 	Consultor publico para numero de Hangars
	# Return
	#	Entero con el numero de Hangars
	def nHangars
		return @nHangars
	end

	# Description:
	# 	Consultor publico para numero de Medals
	# Return
	#	Entero con el numero de Medals
	def nMedals
		return @nMedals
	end
end

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

# Class to represent the shield boosters that space stations can have
class ShieldBooster

	# Constructors
	#===========================================================================
	
	# Description:
	# 	Class initializer
	# Parameters:
	# 	name: WIP
	# 	boost: WIP
	# 	uses: integer to parametrize shield power
	# Return:
	# 	Nil
	def initialize(_name, _boost, _uses)
		@name = _name
		@boost = _boost
		@uses = _uses
	end

	# Getters
	#===========================================================================

	# Description:
	# 	Copy Constructor
	# Parameters:
	# 	origin: ShieldBooster instance which is going to be copied
	# Return:
	# 	A ShieldBooster instance holding a copy of origin
	def newCopy(origin)
		return ShieldBooster.new(@origin.name, @origin.boost, @origin.uses)
	end

	# Description:
	# 	Getter for boost
	# Return
	#	Float with boost
	def boost
		return  @boost
	end

	# Description:
	# 	Getter for uses
	# Returns:
	#	Int with uses
	def uses
		return @uses
	end

	# WIP
	#===========================================================================

	def useIt
		if @uses > 0
			@uses -= 1
			return @boost
		else
			return 1.0
		end
	end
end

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

# Class to take random decisions
class Dice 
	def initialize()
		@NHANGARSPROB = 0.25
		@NSHIELDSPROB = 0.25
		@NWEAPONSPROB = 0.33
		@FIRSTSHOTPROB = 0.5

		# Random number generator
		@generator = Random.new()

	end

	# Description:
	# 	Determines the number of hangars that a space station will recieve
	#	upon creation. This value is calculated based on a random value between
	#	0 and 1, and the return value depends on NHANGARSPROB's probability:
	#		- 0 if random_value <= NHANGARSPROB
	#		- 1 otherwise
	# Return
	#	Integer, 0 or 1
	def initWithNHangars
		if @generator.rand <= @NHANGARSPROB
			return 0
		else
			return 1
		end

		# Security check
		puts "WARNING! UNEXPECTED CONDITION AT Dice.initWithNHangars"
	end

	
	# Description:
	#	Determines the number of weapons that a space station will recieve
	#	upon creation. This value is calculated based on a random value between
	#	0 and 1, and the return value depends on NWEAPONSPROB's probability:
	#		- 1 if random_value <= NWEAPONSPROB
	#		- 2 else if random_value <= 2*NWEAPONSPROB
	#		- 3 otherwise
	# Return
	#	Integer, 0 or 1
	#int initWithNWeapons: devuelve 1 con una probabilidad de NWEAPONSPROB, 2 con la misma
	#probabilidad y 3 con una probabilidad de (1-2* NWEAPONSPROB). Este método determina el
	#número de armas que recibirá una estación espacial al ser creada.
	def initWithNWeapons
		randval = @generator.rand
		if randval <= @NWEAPONSPROB
			return 1
		elsif randval > @NWEAPONSPROB and randval <= @NWEAPONSPROB*2
			return 2
		else
			return 3
		end

		# Security check
		puts "WARNING! UNEXPECTED CONDITION AT Dice.initWIthNWeapons"
	end

	# Description:
	#	Determines amount of shield enhancers that a space station will recieve
	#	upon creation. This value is calculated based on a random value between
	#	0 and 1, and the return value depends on NSHIELDSPROB's probability:
	#		- 0 if random_value <= NSHIELDSPROB
	#		- 1 otherwise
	# Return
	#	Integer, 0 or 1
	def initWithNShields
		if @generator.rand <= @NSHIELDSPROB
			return 0
		else
			return 1
		end

		# Security check
		puts "WARNING! UNEXPECTED CONDITION AT Dice.initWIthNWeapons"
	end

	# Description:
	#	Determines which player will start the match randomly.
	# Return
	#	Integer, from 0 to nPlayers-1
	def whoStarts(nPlayers)
		return @generator.rand(nPlayers)		
	end

	def firstShot
		if @generator.rand <= @FIRSTSHOTPROB
			return GameCharacter::SPACESTATION
		else
			return GameCharacter::ENEMYSTARSHIP
		end

		# Security check
		puts "WARNING! UNEXPECTED CONDITION AT Dice.firstShot"

	end
	
	# WIP
	def spaceStationMovers(speed)
		return @generator.rand < speed
	end
end