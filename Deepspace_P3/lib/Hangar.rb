#encoding:utf-8

require_relative 'Weapon'
require_relative 'ShieldBooster'
require_relative 'WeaponType'
require_relative 'HangarToUI'

module Deepspace

#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class Hangar

	# Constructors
	# ==========================================================================

	# Class initializer
	# @param capacity [Integer] maximum number of shields and weapons the hangar can have
	def initialize(capacity)
		# @!attribute [Integer] maximum number of shields and weapons --combined-- the hangar can have
		@maxElements = capacity

		# @!attribute [Array<ShieldBooster>] array of shield boosters the hangar has
		@shieldBoosters = []

		# @!attribute [Array<Weapon>] array of weapons the hangar has
		@weapons = []
	end

	# Copy constructor
	# @param d [Hangar] instance which is going to be copied
	# @return [Hangar] a copy of the given instance
	def self.newCopy(h)
		copy = new(h.maxElements)

		for shieldBooster in h.shieldBoosters
			copy.addShieldBooster(shieldBooster)
		end

		for weapon in h.weapons
			copy.addWeapon(weapon)
		end

		return copy
	end

	# Getters
	# ==========================================================================

	attr_reader :shieldBoosters, :weapons, :maxElements

	# Checks if there's space left for more elements at the hangar
	# @return [Boolean] true, if there's space left for at least one more element;
	#                   false, if the hangar is full
	def spaceAvailable
		return @maxElements > @weapons.length + @shieldBoosters.length
	end


	# Setters
	# ==========================================================================

	# Adds a new weapon to the hangar
	# @param w [Weapon] the weapon to be added
	# @return [Boolean] true, if the operation runs successfully;
	#                   false, if something fails (no room for another weapon)
	def addWeapon(w)
		if spaceAvailable
			@weapons << w
			return true
		else
			return false
		end
	end

	# Removes a weapon from the hangar
	# @param w [Integer] the position in which the weapon that wants to be removed is located
	# @return [Weapon] nil, if position is invalid or removal operation is unsuccessful;
	#                  the weapon deleted, if removal is successful
	def removeWeapon(w)
		if w < @weapons.length && w >= 0
			return @weapons.delete_at(w)
		else
			return nil
		end
	end

	# Adds a new shield booster to the hangar
	# @param w [ShieldBooster] the shield booster to be added
	# @return [Boolean] true, if the operation runs successfully;
	#                   false, if something fails (no room for another booster)
	def addShieldBooster(s)
		if spaceAvailable
			@shieldBoosters << s
			return true
		else
			return false
		end
	end

	# Removes a shield booster from the hangar
	# @param s [Integer] the position in which the booster that wants to be removed is located
	# @return [Weapon] nil, if position is invalid or removal operation is unsuccessful;
	#                  the shield booster deleted, if removal is successful
	def removeShieldBooster(s)
		if s < @shieldBoosters.length && s >= 0
			return @shieldBoosters.delete_at(s)
		else
			return nil
		end
	end

	# String representation, UI version
	# ==========================================================================

	# String representation of the object
	# @return [String] string representation
	def to_s
		getUIversion().to_s
	end

	# To UI
	def getUIversion
		return HangarToUI.new(self)
	end
end # class Hangar

end # module Deepspace