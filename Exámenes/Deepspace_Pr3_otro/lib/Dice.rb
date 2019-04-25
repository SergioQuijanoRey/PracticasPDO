#encondig:utf-8

require_relative 'GameCharacter'

module Deepspace

# Class to take all random decisions on the game
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class Dice
	
	# Constructors
	# ==========================================================================

	# Class initializer
	def initialize
		# @!attribute [Float] Probability constant for hangars
		@NHANGARSPROB = 0.25

		# @!attribute [Float] Probability constant for shields
		@NSHIELDSPROB = 0.25

		# @!attribute [Float] Probability constant for weapons
		@NWEAPONSPROB = 0.33

		# @!attribute [Float] Probability constant for 
		@FIRSTSHOTPROB = 0.5

		# EXAMEN
		# @!attribute [Float] Probability constant for penalty
		@PENALTYPROB = 0.95

		# @!attribute [Random] Random number generator
		@generator = Random.new()
	end

	# Getters
	#=======================================================================
	
	# Determines the number of hangars that a space station will recieve
	# upon creation. This value is calculated based on a random value between
	# 0 and 1, and the return value depends on NHANGARSPROB's probability:
	# 	- 0 if random_value <= NHANGARSPROB
	# 	- 1 otherwise
	# @return [Integer] 0 or 1, as specified above
	def initWithNHangars
		if @generator.rand <= @NHANGARSPROB
			return 0
		else
			return 1
		end

		# Security check
		raise "WARNING! Unexpected condition at Dice.initWithNHangars"
	end

	# Determines the number of weapons that a space station will recieve
	# upon creation. This value is calculated based on a random value between
	# 0 and 1, and the return value depends on NWEAPONSPROB's probability:
	# 	- 1 if random_value <= NWEAPONSPROB
	# 	- 2 else if random_value <= 2*NWEAPONSPROB
	# 	- 3 otherwise
	# @return [Integer] 1, 2 or 3 as specified above
	def initWithNWeapons
		randval = @generator.rand

		if randval <= @NWEAPONSPROB
			return 1
		elsif randval <= @NWEAPONSPROB*2
			return 2
		else
			return 3
		end

		# Security check
		raise "WARNING! Unexpected condition at Dice.initWithNWeapons"
	end

	# Determines amount of shield enhancers that a space station will recieve
	# upon creation. This value is calculated based on a random value between
	# 0 and 1, and the return value depends on NSHIELDSPROB's probability:
	# 	- 0 if random_value <= NSHIELDSPROB
	# 	- 1 otherwise
	# @return [Integer] 0 or 1, as specified above
	def initWithNShields
		if @generator.rand <= @NSHIELDSPROB
			return 0
		else
			return 1
		end

		# Security check
		raise "WARNING! Unexpected condition at Dice.initWithNShields"
	end

	# Determines which player will start the match randomly.
	# @param nPlayers [Integer] number of players
	# @return [Integer] such player (an integer in [0, nPlayers-1])
	def whoStarts(nPlayers)
		return @generator.rand(nPlayers)		
	end

	# Determines who shoots first
	# @return [GameCharacter] SPACESTATION if the player shoots first,
	#                         ENEMYSTARSHIP if the enemy shoots first
	def firstShot
		if @generator.rand <= @FIRSTSHOTPROB
			return GameCharacter::SPACESTATION
		else
			return GameCharacter::ENEMYSTARSHIP
		end

		# Security check
		raise "WARNING! Unexpected condition at Dice.firstShot"

	end
	
	# Determines if spacestation moves in order to avoid a shot
	# @param speed [Float] speed of the space station
	# @return [Boolean] true if space station avoids the shot; false, otherwise
	def spaceStationMoves(speed)
		return @generator.rand < speed
	end

	# Determines if penalty has to be set
	# @return [Boolean] whether penalty must be set or not
	def penaltySet
		if @generator.rand <= @PENALTYPROB
			puts "[EXAMEN] El dado ha decidido aplicar penalización"
			return true
		else
			puts "[EXAMEN] El dado ha decidido no aplicar penalización"
			return false
		end
	end

	# Generate penalty
	# @return [Float] penalty
	def generatePenalty
		return @generator.rand(0.5..0.75)
	end

end # class Dice
	
end # module deepspace