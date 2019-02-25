#encondig:utf-8

# WIP -- Documentation needed
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
