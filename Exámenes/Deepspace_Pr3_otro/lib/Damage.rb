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
	# It's just going to be used by other "constructors"
	# @param _nWeapons [Integer] number of weapons that will be lost
	# @param _nShields [Integer] number of shields that will be lost
	# @param _weapons [Array<WeaponType>] array of weapons that will be lost
	def initialize(_nWeapons, _nShields, _weapons)
		# @!attribute [Integer] number of weapons that will be lost
		@nWeapons = _nWeapons

		# @!attribute [Integer] number of shields that will be lost
		@nShields = _nShields

		# @!attribute [Array<WeaponType>] array of weapons that will be lost
		@weapons = _weapons
	end
	
	# Initializer type Numeric.
	# Initializes to use the class with a number of weapons and a number of shields
	# @param _nWeapons [Integer] number of weapons that will be lost
	# @param _nShields [Integer] number of shields that will be lost
	def self.newNumericWeapons(_nWeapons, _nShields)
		# NOTA podría usarse:
		# return new([0, _nWeapons].max, _nShields, nil)
		# de este modo nos aseguramos que -1 distingue el tipo de constructor
		return new(_nWeapons, _nShields, nil)
	end

	# Initializer type Specific.
	# Initializes to use the class with an array of weapons and a number of shields
	# @param _weapons [Array<WeaponType>] array of weapons that will be lost
	# @param _nShields [Integer] number of shields that will be lost
	def self.newSpecificWeapons(_weapons, _nShields)
		return new(-1, _nShields, _weapons)
		# -1 value for distinction
	end

	# Copy constructor
	# @param d [Damage] instance which is going to be copied
	# @return [Damage] a copy of the given instance
	def self.newCopy(d)
		if d.nWeapons == -1
			return newSpecificWeapons(d.weapons, d.nShields)
		else
			return newNumericWeapons(d.nWeapons, d.nShields)
		end
	end

	# Getters
	# ==========================================================================

	attr_reader :nWeapons, :nShields, :weapons

	# Checks whether the damage is affecting or not
	# @return [Boolean] true, if damage has no effect; false, otherwise
	def hasNoEffect
		if @nWeapons == -1
			return @weapons.empty? && @nShields == 0
		else
			return @nShields + @nWeapons == 0
		end
	end

	# Searches the first element of WeaponType array to match a given type
	# @param w [Array<Weapon>] the array of weapon types where we search
	# @param t [WeaponType] the type we're looking for
	# @return [Integer] position, if the element is found;
	#                   -1, if no element is found
	def arrayContainsType(w, t)
		i = 0
		w.each do |weapon_aux|
			if weapon_aux.type == t
				return i
			else
				i += 1
			end
		end

		# No element found
		return -1
	end
	
	# Setters
	# =========================================================================

	# Creates a copy of current objet where weapons and shields which are not
	# included in arrays given as parameters are discarded. That's to say, we
	# shrink the Damage to the parameters
	# @param w [Array<Weapon>] weapons to fit
	# @param s [Array<ShieldBooster>] shields to fit
	# @return [Damage] a copy of the object adjusted as explained above
	def adjust(w, s)
		# we check which new the object has been constructed with:
		#   if weapons == -1, it has been Specific-constructed
		#   else, it has been Numeric-constructed
		if @nWeapons == -1
			# we compute the intersection
			#TEST --DELETE THIS new_weapons = []
			#for weapon in @weapons
			#	if arrayContainsType(w, weapon)
			#		new_weapons << weapon
			#	end
			#end

			weapons_copy = @weapons.clone

			new_weapons = w.map do |weapon|
				weapons_copy.delete_at(weapons_copy.index(weapon.type) || weapons_copy.length)
			end

			new_weapons.compact!

			self.class.newSpecificWeapons(new_weapons, [@nShields, s.length].min)
		else
			self.class.newNumericWeapons([@nWeapons, w.length].min, [@nShields, s.length].min)
		end
	end

	# Removes a given type of weapon.
	# If a list of weapon types is not available (object is Numeric-constructed
	# instead of Specific-constructed), the number of weapons decreases by 1
	# @param w [Weapon] the weapon whose type wants to be removed
	def discardWeapon(w)
		if @nWeapons == -1
			if @weapons.length != 0
				position = @weapons.index(w.type)
				if position != nil
					@weapons.delete_at(position)
				else
					raise "WARNING! No weapon type match at Damage.discardWeapon()"
				end
			end
		else
			if @nWeapons > 0
				@nWeapons -= 1
			else
				raise "WARNING! You tried to have negative weapons at Damage.discardWeapon()"
			end
		end
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
		message = "[Damage "
        if @nWeapons == -1
            message = message + " (Specific-constructed)] -> " \
                    + "Number of shields: " + nShields \
                    + ", Weapons: " + weapons.to_s
        else
            message = message + " (Numeric-constructed)] -> " \
                    + "Number of shields: " + nShields \
					+ ", Number of weapons: " + nWeapons
		end
        return message
	end

	# To UI
	def getUIversion
		return DamageToUI.new(self)
	end


	# Visibility specifiers
	# ==========================================================================
	private :arrayContainsType
	private_class_method :new

end # class Damage

end	# module Deepspace