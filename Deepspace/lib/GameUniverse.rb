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

		# WIP -- Practica 3
		# Description:
		# Paramters:
		# 	station: SpaceStation
		# 	enemy: EnemyStarShip
		# Returns:
		# 	CombatResult
		def combatGo(station, enemy)
		end

		# WIP -- Practica 3
		# Description:
		# Returns:
		# 	CombatResult
		def combat()
		end

		def getUIVersion
				return GameUniverseToUI.new(self)
		end

		# Description:
		# 	Checks if spaceShip which is on turn have won
		# Returns:
		# 	Boolean	true, if the space ship has won
		# 			false, otherwise
		def haveAWinner
				if @currentStation.nMedals >= @@WIN
						return true
				else
						return false
				end
		end

		def to_s
		end

		# Setters
		#=======================================================================
		
		# Description:
		# 	The current space station discards an Hangar if GameState is INIT or AFTERCOMBAT
		# 	Otherwise, this method has no effect
		def discardHangar
				if @gameState == GameState::INIT or GameState::AFTERCOMBAT
						@currentStation.discardHangar
				end
		end

		# Description:
		# 	The current space station discards a shield Booster if GameState is INIT or AFTERCOMBAT
		# 	Otherwise, this method has no effect
		# Parameters:
		# 	i: Integer, position of the shield booster to discard
		def discardShieldBooster(i)
				if @gameState == GameState::INIT or GameState::AFTERCOMBAT
						@currentStation.discardShieldBooster(i)
				end
		end

		# Description:
		# 	The current space station discards a shield Booster in the Hangar if GameState is INIT or AFTERCOMBAT
		# 	Otherwise, this method has no effect
		# Parameters:
		# 	i: Integer, position of the shield booster to discard
		def discardShieldBoosterInHangar(i)
				if @gameState == GameState::INIT or GameState::AFTERCOMBAT
						@currentStation.discardShieldBoosterInHangar(i)
				end
		end

		# Description:
		# 	The current space station discards a weapon if GameState is INIT or AFTERCOMBAT
		# 	Otherwise, this method has no effect
		# Parameters:
		# 	i: Integer, position of the weapon to discard
		def discardWeapon(i)
				if @gameState == GameState::INIT or GameState::AFTERCOMBAT
						@currentStation.discardWeapon(i)
				end
		end

		# Description:
		# 	The current space station discards a weapon in the hangar if GameState is INIT or AFTERCOMBAT
		# 	Otherwise, this method has no effect
		# Parameters:
		# 	i: Integer, position of the weapon to discard
		def discardWeaponInHangar(i)
				if @gameState == GameState::INIT or GameState::AFTERCOMBAT
						@currentStation.discardWeaponInHangar(i)
				end
		end

		# Description:
		# 	The current space station mounts a shield booster if GameState is INIT or AFTERCOMBAT
		# 	Otherwise, this method has no effect
		# Parameters:
		# 	i: Integer, position of the shield booster to mount
		def mountShieldBooster(i)
				if @gameState == GameState::INIT or GameState::AFTERCOMBAT
						@currentStation.mountShieldBooster(i)
				end
		end

		# Description:
		# 	The current space station mounts a shield booster if GameState is INIT or AFTERCOMBAT
		# 	Otherwise, this method has no effect
		# Parameters:
		# 	i: Integer, position of the weapon to mount
		def mountWeapon(i)
				if @gameState == GameState::INIT or GameState::AFTERCOMBAT
						@currentStation.mountWeapon(i)
				end
		end

		# WIP -- Practica 3
		# Description:
		# Returns:
		# 	Boolean
		def nexTurn
		end

		# WIP -- Practica 3
		# Description:
		# Parameters:
		# 	names: String[]
		def init(names)
		end
end
end # module Deepspace
