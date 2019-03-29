#encoding:utf-8

require_relative "GameStateController"
require_relative "GameUniverseToUI"
require_relative "Dice"

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

		@currentStationIndex = -1	# Integer
		@currentStation = nil		# SpaceStation
		@spaceStations = []			# SpaceStation[]
		@currentEnemy = nil			# EnemyStarShip
	end

	# Getters
	#=======================================================================
	
	attr_reader :gameState

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
	def combat
	end

	# Description:
	# 	Gets the UI representation of the object
	# Returns:
	# 	GameUniverseToUI: the UI representation
	def getUIVersion
		return GameUniverseToUI.new(@currentStation, @currentEnemy)
	end

	# Description:
	# 	Checks if spaceShip which is on turn have won
	# Returns:
	# 	Boolean	true, if the space ship has won
	# 			false, otherwise
	def haveAWinner
		if @currentStation.nil?
			puts "Warning! @currentStation nil on GameUniverse.haveAWinner()"
		else
			if @currentStation.nMedals >= @@WIN
				return true
			else
				return false
			end
		end
	end

	def to_s
		out = "[GameUniverse]-> Game state: #{@gameState}, Turns: #{@turns}, Dice: #{@dice}\n"
		out += "\tCurrent station: #{@currentStation}\n"
		out += "\tCurrent enemy: #{@currentEnemy}"
		return out
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
	def nextTurn
	end

	# WIP -- Practica 3
	# Description:
	# Parameters:
	# 	names: String[]
	def init(names)
	end

end # class GameUniverse

end # module Deepspace
