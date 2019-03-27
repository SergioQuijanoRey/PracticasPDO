#encoding:utf-8

require_relative 'enums'
require_relative 'EnemyToUI'

# wip -complete docs
class EnemyStarShip

    # Constructors
	#=======================================================================

    # Description:
    # 	Initializer of the class
    # Parameters:
	# 	n: String
	# 	a: Float
	# 	s: Float
	# 	l: Loot
	# 	d: Damage
    # Return:
    #   Nil
    def initialize(n, a, s, l, d)
        @name = n
        @ammoPower = a
        @shieldPower = s
        @loot = l
        @damage = d
    end

    def self.newCopy(e)
        return EnemyStarShip.new(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage)
    end

    def self.getUIversion
        return EnemyToUI.new(self)
    end

    def fire
        return @ammoPower
    end

    def ammoPower
        return @ammoPower
    end

    def damage
        return @damage
    end

    def loot
        return @loot
    end

    def name
        return @name
    end

    def shieldPower
        return @shieldPower
    end

    def protection
        return @shieldPower
    end

    def receiveShot(shot)
        if protection < shot
            return ShotResult::RESIST
        else
            return ShotResult::DONOTRESIST
        end
    def
end