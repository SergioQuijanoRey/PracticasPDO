#encondig:utf-8

module Deepspace

# Class to take all random decisions on the game
class Dice 

	# Constructors
	#=======================================================================

	 # Description:
	 # 	Initializer of the class
	 # 	All parameters are floats except the random number generator
	 # Return:
	 # 	Nil
	def initialize
		@NHANGARSPROB = 0.25
		@NSHIELDSPROB = 0.25
		@NWEAPONSPROB = 0.33
		@FIRSTSHOTPROB = 0.5

		# Random number generator
		@generator = Random.new()

	end

	# Getters
	#=======================================================================
	
	# Description:
	# 	Determines the number of hangars that a space station will recieve
	#	upon creation. This value is calculated based on a random value between
	#	0 and 1, and the return value depends on NHANGARSPROB's probability:
	#		- 0 if random_value <= NHANGARSPROB
	#		- 1 otherwise
	# Return
	#	Integer, 0 or 1, as specified above
	def initWithNHangars
		if @generator.rand <= @NHANGARSPROB
			return 0
		else
			return 1
		end

		# Security check
		puts "WARNING! Unexpected condition at Dice.initWithNHangars"
	end

	
	# Description:
	#	Determines the number of weapons that a space station will recieve
	#	upon creation. This value is calculated based on a random value between
	#	0 and 1, and the return value depends on NWEAPONSPROB's probability:
	#		- 1 if random_value <= NWEAPONSPROB
	#		- 2 else if random_value <= 2*NWEAPONSPROB
	#		- 3 otherwise
	# Return
	#	Integer, 1, 2 or 3 as specified above
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
		puts "WARNING! Unexpected condition at Dice.initWithNWeapons"
	end

	# Description:
	#	Determines amount of shield enhancers that a space station will recieve
	#	upon creation. This value is calculated based on a random value between
	#	0 and 1, and the return value depends on NSHIELDSPROB's probability:
	#		- 0 if random_value <= NSHIELDSPROB
	#		- 1 otherwise
	# Return
	#	Integer, 0 or 1, as specified above
	def initWithNShields
		if @generator.rand <= @NSHIELDSPROB
			return 0
		else
			return 1
		end

		# Security check
		puts "WARNING! Unexpected condition at Dice.initWithNShields"
	end

	# Description:
	#	Determines which player will start the match randomly.
	# Parameters:
	# 	nPlayers: Integer, number of players
	# Return
	#	Integer, from 0 to nPlayers-1
	def whoStarts(nPlayers)
		return @generator.rand(nPlayers)		
	end

	# Description:
	# 	Determines who shots first
	# Return:
	# 	GameCharacter, SPACESTATION if the player shoots first
	#                        ENEMYSTARSHIP if the enemy shoots first
	def firstShot
		if @generator.rand <= @FIRSTSHOTPROB
			return GameCharacter::SPACESTATION
		else
			return GameCharacter::ENEMYSTARSHIP
		end

		# Security check
		puts "WARNING! Unexpected condition at Dice.firstShot"

	end
	
	# Description:
	# 	Determines if spacestation moves in order to avoid a shoot
	# Parameters:
	# 	speed: float, speed of the space station
	# Return:
	# 	Boolean, true if space statint avoids the shoot
	#                  false, otherwise
	def spaceStationMoves(speed)
		return @generator.rand < speed
	end
end

end		# Deepspace module