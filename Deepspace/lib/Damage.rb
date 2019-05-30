#encoding:utf-8

require_relative 'WeaponType'
require_relative 'Weapon'
require_relative 'DamageToUI'

module Deepspace

# Represents damages done on a spaceship after loosing a combat.
# They indicate which elements are going to be lost after losing the combat
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class Damage

	# Initializers
	# ==========================================================================

	# Class initializer.
	# @param _nShields [Integer] number of shields that will be lost
	def initialize(_nShields)
		# @!attribute [Integer] number of shields that will be lost
		@nShields = _nShields
	end
	
	# Getters
	# ==========================================================================

	attr_reader :nShields

	# Checks whether the damage is affecting or not
	# @return [Boolean] true, if damage has no effect; false, otherwise
	def hasNoEffect
		return @nShields == 0
	end
	
	# Setters
	# =========================================================================

	# Creates a copy of current objet where shields which are not
	# included in arrays given as parameters are discarded. That's to say, we
	# shrink the Damage to the parameters
	# @param s [Array<ShieldBooster>] shields to fit
	# @return [Integer] a copy of the object adjusted as explained above
	def adjust(s)
		return [s.length, @nShields].min
	end

	# Reduces by 1 the number of shield boosters to be removed
	def discardShieldBooster
		if @nShields > 0
			@nShields -= 1
		else
			raise "WARNING! You tried to have negative shieldBoosters at Damage.discardShieldBooster()"
		end
	end

	# String representation, UI version
	# ==========================================================================

	# String representation of the object
	# @return [String] string representation
	def to_s
		message = "[Damage] Number of shields: " + @nShields
        return message
	end

	# To UI
	def getUIversion
		return DamageToUI.new(self)
	end


	# Visibility specifiers
	# ==========================================================================
	private_class_method :new

end # class Damage

end	# module Deepspace