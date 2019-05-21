#encoding:utf-8

require_relative 'EnemyToUI'
require_relative 'Damage'
require_relative 'Loot'
require_relative 'ShotResult'

module Deepspace

# Represents the enemy of the game: an enemy star ship
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class EnemyStarShip

	# Constructors
	# ==========================================================================
	
	# Class initializer
	# @param _name [String] name of the ship
	# @param _ammoPower [Float] parametrizes ammunition power
	# @param _shieldPower [Float] parametrizes shield power
	# @param _loot [Loot] associated loot
	# @param _damage [Damage] associated damage
	def initialize(_name, _ammoPower, _shieldPower, _loot, _damage)
		# @!attribute [String] name of the ship
		@name = _name

		# @!attribute [Float] parametrizes ammunition power
		@ammoPower = _ammoPower

		# @!attribute [Float] parametrizes shield power
		@shieldPower = _shieldPower

		# @!attribute [Loot] loot associated
		@loot = _loot

		# @!attribute [Damage] damage associated
		@damage = _damage
	end

	# Copy constructor
	# @param enemy [EnemyStarShip] instance which is going to be copied
	# @return [EnemyStarShip] a copy of the given instance
	def self.newCopy(enemy)
		return new(enemy.name, enemy.ammoPower, enemy.shieldPower, enemy.loot, enemy.damage)
	end
	
	# Getters
	# ==========================================================================

	attr_reader :name, :ammoPower, :shieldPower, :loot, :damage

	# Return star ship's ammoPower
	# @return [Float] the damage it can do
	def fire
		return @ammoPower
	end

	# Return star ship's shieldPower
	# @return [Float] the protection it assures
	def protection
		return @shieldPower
	end

	# Returns whether the star ship receives a certain shot or not
	# @param shot [Float] power of shot taken
	# @return [ShotResult] RESIST, if shieldPower >= shot;
	#                      DONOTRESIST, if shieldPower < shot
	def receiveShot(shot)
		if @shieldPower >= shot
			return ShotResult::RESIST
		else
			return ShotResult::DONOTRESIST
		end
	end

	# String representation, UI version
	# ==========================================================================

	# String representation of the object
	# @return [String] string representation
	def to_s
		message = "[EnemyStarShip] -> Name: " + name \
				+ ", ammoPower: " + ammoPower \
				+ ", shieldPower: " + shieldPower \
				+ ", Loot: " + loot.to_s \
				+ ", Damage: " + damage.to_s
        return message
	end

	# To UI
	def getUIversion
		return EnemyToUI.new(self)
	end
end # class EnemyStarShip

end	# module Deepspace