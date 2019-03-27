#encoding:utf-8

require_relative "../lib/enums.rb"
require_relative "../lib/EnemyToUI.rb"

module Deepspace

class EnemyStarShip
		# Initializers
		#=======================================================================
		# Description:
		# 		Initializer of the class
		# Parameters:
		# 		_name: String
		# 		_ammoPower: Float
		# 		_shieldPower: Float
		# 		_loot: Loot
		# 		_damage: Damage
		def initialize(_name, _ammoPower, _shieldPower, _loot, _damage)
				@name = _name
				@ammoPower = _ammoPower
				@shieldPower = _shieldPower
				@loot = _loot
				@damage = _damage
		end

		def self.newCopy
		end
		
		# Getters
		#=======================================================================
		def name
				return @name
		end

		def ammoPower
				return @ammoPower
		end

		def shieldPower
				return @shieldPower
		end

		def loot
				return @loot
		end

		def damage
				return @damage
		end

		# Description:
		# 	Gets UI representation of the object
		# Returns:
		# 	EnemyToUI: the UI representation
		def getUIVersion
				return EnemyToUI.new(self)
		end

		# Description
		# 	EnemyStarShip attacks with certain power
		# Returns:
		# 		Float: the damage it can do
		def fire
				return @ammoPower
		end

		# Description:
		# 	EnemyStarShip protects from one attack
		# Returns:
		# 	Float: the protection it takes
		def protection
				return @shieldPower
		end

		# Setters
		#=======================================================================
		# Parameters
		# 	shot: Float, the power of taken shot
		# Returns:
		# 	ShotResult:	ShotResult::RESIST, if shieldPower >= shot
		# 				ShotResult::DONOTRESIST, if shieldPower = shot
		def receiveShot(shot)
				if @shieldPower >= shot
						return ShotResult::RESIST
				else
						return ShotResult::DONOTRESIST
				end
		end

end

end	# module Deepspace
