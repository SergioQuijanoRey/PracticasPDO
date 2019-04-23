#encoding:utf-8

require_relative 'GameUniverseToUI'
require_relative 'GameStateController'
require_relative 'Dice'
require_relative 'CombatResult'
require_relative 'GameCharacter'
require_relative 'ShotResult'
require_relative 'SpaceStation'
require_relative 'CardDealer'
require_relative 'EnemyStarShip'

module Deepspace

#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class GameUniverse

	# Class atributes
	# ==========================================================================

	# @!attribute [Integer] amount of medals necessary to win the game
	@@WIN = 10

	# Constructors
	# ==========================================================================
	
	# Class initializer
	def initialize
		# @!attribute [GameStateController] game state
		@gameState = GameStateController.new

		# @!attribute [Integer] number of turns
		@turns = 0

		# @!attribute [Dice] game dice
		@dice = Dice.new

		# @!attribute [Integer] index of the station that is currently playing
		@currentStationIndex = -1

		# @!attribute [SpaceStation] current space station
		@currentStation = nil

		# @!attribute [Array<SpaceStation>] set of space stations that are playing
		@spaceStations = []

		# @!attribute [EnemyStarShip] current enemy star ship
		@currentEnemy = nil
	end

	# Getters
	# ==========================================================================
	
	attr_reader :gameState

	# Setters
	# ==========================================================================

	# Discard the hangar from the current space station
	# Discarded if gameState is INIT or AFTERCOMBAT
	def discardHangar
		if @gameState.state == GameState::INIT or GameState::AFTERCOMBAT
			@currentStation.discardHangar
		end
	end

	# Discard a certain shield booster from the current space station
	# Discarded if gameState is INIT or AFTERCOMBAT
	# @param i [Integer] position of the shield booster to be discarded
	def discardHangar(i)
		if @gameState.state == GameState::INIT or GameState::AFTERCOMBAT
			@currentStation.discardShieldBooster(i)
		end
	end
	
	# Discard a certain shield booster in the hangar from the current space station.
    # Discarded if gameState is INIT or AFTERCOMBAT
    # @param i [Integer] position of the shield booster to discard
    def discardShieldBoosterInHangar(i)
		if @gameState.state == GameState::INIT or GameState::AFTERCOMBAT
            @currentStation.discardShieldBoosterInHangar(i)
		end
	end
    
	# Discard a certain weapon from the current space station.
    # Discarded if gameState is INIT or AFTERCOMBAT
    # @param i [Integer] position of the weapon to discard
    def discardWeapon(i)
		if @gameState.state == GameState::INIT or GameState::AFTERCOMBAT
			@currentStation.discardWeapon(i)
		end
	end
    
	# Discard a certain weapon in the hangar from the current space station.
    # Discarded if gameState is INIT or AFTERCOMBAT
    # @param i [Integer] position of the weapon to discard
    def discardWeaponInHangar(i)
		if @gameState.state == GameState::INIT or GameState::AFTERCOMBAT
            @currentStation.discardWeaponInHangar(i)
		end
	end
    
    # Mount a certain shield booster from the current space station.
    # Mounted if gameState is INIT or AFTERCOMBAT
    # @param i [Integer] position of the shield booster to mount
    def mountShieldBooster(i)
		if @gameState.state == GameState::INIT or GameState::AFTERCOMBAT
			@currentStation.mountShieldBooster(i)
		end
	end
	
    # Mount a certain weapon from the current space station.
    # Mounted if gameState is INIT or AFTERCOMBAT
    # @param i [Integer] position of the weapon to mount
    def mountWeapon(i)
		if @gameState.state == GameState::INIT or GameState::AFTERCOMBAT
			@currentStation.mountShieldBooster(i)
		end
	end

	# Checks if space ship that has the turn has won
	# @return [Boolean] true, if the space ship has won; false, otherwise
	def haveAWinner
		if @currentStation.nil?
			raise "Warning! @currentStation nil on GameUniverse.haveAWinner()"
		else
			if @currentStation.nMedals >= @@WIN
				return true
			else
				return false
			end
		end
	end

	# Game methods
	# ==========================================================================

	# Starts a match
	# @param names [Array<String>] collection with the names of the players
	def init(names)
		state = @gameState.state
		
		if state == GameState::CANNOTPLAY
			spaceStations = []
			dealer = CardDealer.instance # behaviour introduced by Singleton
			names.each do |name|
				supplies = dealer.nextSuppliesPackage
				station = SpaceStation.new(name, supplies)
				spaceStations.push(station)

				nh = @dice.initWithNHangars
				nw = @dice.initWithNWeapons
				ns = @dice.initWithNShields

				lo = Loot.new(0, nw, ns, nh, 0)
				@station.loot = lo # WIP-setter como .loot= o .setLoot?
			end

			nPlayers = names.length
			@currentStationIndex = dice.whoStarts(nPlayers)
			@currentStation = @spaceStations.at(currentStationIndex)
			@currentEnemy = dealer.nextEnemy

			@gameState.next(@turns, @spaceStations.length)
		end
	end

	# Takes turn to the next player, if there is no pending damage
	# @return [Boolean] true, if the turn could be changed; else, otherwise
	def nextTurn
		state = @gameState.state

		if state == GameState::AFTERCOMBAT
			stationState = @currentStation.validState

			if stationState
				@currentStationIndex = ( @currentStationIndex + 1 ) \
						% @spaceStations.size
				@turns += 1

				@currentStation = @spaceStations.at(@currentStationIndex)
				@currentStation.cleanUpMountedItems

				dealer = CardDealer.instance # behaviour introduced by Singleton
				@currentEnemy = dealer.nextEnemy

				@gameState.next(@turns, @spaceStations.length)
				
				return true
			end

			return false
		end

		return false
	end

	# Makes combat between the space station that holds the turn and the
	# current enemy. The combat is realized if the app is on a state where
	# combat is allowed
	# @return [CombatResult] combat result
	def combat
		state = @gameState.state

		if state == GameState::BEFORECOMBAT or GameState::INIT
			return combatGo(@currentStation, @currentEnemy)
		end
	end

	# Executes combat, according to game rules
	# @param station [SpaceStation] station in combat
	# @param enemy [EnemyStarShip] enemy in combat
	# @return [CombatResult] combat result
	def combatGo(station, enemy)
		station = args[0]
		enemy = args[1]

		ch = @dice.firstShot

		if ch == GameCharacter::ENEMYSTARSHIP
			fire = enemy.fire
			result = station.receiveShot(fire)

			if ( result == ShotResult::RESIST )
				fire = station.fire
				result = enemy.receiveShot(fire)

				enemyWins = (result == ShotResult::RESIST)
			else
				enemyWins = true
			end
		else
			fire = station.fire
			result = enemy.receiveShot(fire)

			enemyWins = (result == ShotResult::RESIST)
		end

		if enemyWins
			s = station.getSpeed
			moves = dice.spaceStationMoves(s)

			if !moves
				damage = enemy.damage
				station.setPendingDamage(damage)

				combatResult = CombatResult::ENEMYWINS
			else
				station.move

				combatResult = CombatResult::STATIONESCAPES
			end
		else
			aLoot = enemy.loot
			station.loot = aLoot # WIP - depends on how we make setter

			combatResult = CombatResult::STATIONWINS
		end

		@gameState.next(@turns, @spaceStations.length)

		return combatResult
	end


	# String representation, UI version
	# ==========================================================================

	# String representation of the object
	# @return [String] string representation
	def to_s
		message = "[GameUniverse] -> Game state: #{@gameState.to_s},"
				+ "Turns: #{@turns}, Dice: #{@dice.to_s}\n"
				+ "\tCurrent station: #{@currentStation.to_s}\n"
				+ "\tCurrent enemy: #{@currentEnemy.to_s}"
        return message
	end

	# To UI
	def getUIVersion
		return EnemyToUI.new(self)
	end
	# Description:
	# 	Gets the UI representation of the object
	# Returns:
	# 	GameUniverseToUI: the UI representation
	def getUIVersion
		return GameUniverseToUI.new(@currentStation, @currentEnemy)
	end
end # class GameUniverse

end # module Deepspace