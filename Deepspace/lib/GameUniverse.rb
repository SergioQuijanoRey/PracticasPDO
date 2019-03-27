#encoding:utf-8

require "../lib/GameUniverse.rb"

module Deepspace
class GameUniverse
		# Class atributes
		#=======================================================================
		@@WIN = 10

		# Initializers
		#=======================================================================
		# Description:
		# 	Initializer of the class
		def initialize
				@gameState = GameStateController.new
				@turns = 0
				@dice = Dice.new

				@currentStationIndex = Nil	# Integer
				@currentStation = Nil		# SpaceStation
				@spaceStations = Nil		# SpaceStation[]
				@currentEnemy = Nil			# EnemyStarShip
		end

		# Getters
		#=======================================================================
		def gameState
				return @gameState
		end

		# Description:
		# Paramters:
		# 	station: SpaceStation
		# 	enemy: EnemyStarShip
		# Returns:
		# 	CombatResult
		def combatGo(station, enemy)
		end

		# Description:
		# Returns:
		# 	CombatResult
		def combat()
		end

		def getUIVersion
				return GameUniverseToUI.new(self)
		end

		# Description:
		# Returns:
		# 	Boolean
		def haveAWinner
		end

		# Setters
		#=======================================================================
		
		def discardHangar
		end

		# Description:
		# Parameters:
		# 	i: Integer
		def discardShieldBooster(i)
		end

		# Description:
		# Parameters:
		# 	i: Integer
		def discardShieldBoosterInHangar(i)
		end

		# Description:
		# Parameters:
		# 	i: Integer
		def discardWeapon(i)
		end

		# Description:
		# Parameters:
		# 	i: Integer
		def discardWeaponInHangar(i)
		end

		# Description:
		# Parameters:
		# 	i: Integer
		def mountShieldBooster(i)
		end
		
		# Description:
		# Parameters:
		# 	i: Integer
		def mountWeapon(i)
		end

		# Description:
		# Returns:
		# 	Boolean
		def nexTurn
		end

		# Description:
		# Parameters:
		# 	names: String[]
		def init(names)
		end
end
end # module Deepspace
