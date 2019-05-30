#encoding:utf-8

require_relative 'Damage'
require_relative 'NumericDamageToUI'


module Deepspace

# Class to represent numeric damage
# @see Deepspace::Damage
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class NumericDamage < Damage

    public_class_method :new

    # Class initializer
    # @param _nWeapons [Integer] number of weapons that will be lost
	# @param _nShields [Integer] number of shields that will be lost
    def initialize(_nWeapons, _nShields)
        super(_nShields)

        # @!attribute [Integer] number of weapons that will be lost
        @nWeapons = _nWeapons
    end

    attr_reader :nWeapons

    # Checks whether the damage is affecting or not
	# @return [Boolean] true, if damage has no effect; false, otherwise
    # --Overriden
    def hasNoEffect
		return (super && @nWeapons == 0)
	end

    # Creates a copy of current objet where weapons and shields which are not
	# included in arrays given as parameters are discarded. That's to say, we
	# shrink the Damage to the parameters
	# @param w [Array<Weapon>] weapons to fit
	# @param s [Array<ShieldBooster>] shields to fit
	# @return [NumericDamage] a copy of the object adjusted as explained above
    # --Overriden
    def adjust(w, s)
        return self.class.new([@nWeapons, w.length].min, super(s))
    end

    # Copy getter
    # @return [NumericDamage] a copy of the current instance
    # --Overriden
    def copy
        return self.class.new(@nWeapons, @nShields)
    end

    # Removes a given type of weapon.
    # As a list of weapon types is not available the number of weapons
    # decreases by 1
	# @param w [Weapon] the weapon whose type wants to be removed
    def discardWeapon(w)
        if @nWeapons > 0
            @nWeapons -= 1
        end
    end

    # String representation of the object
    # @return [String] string representation
    def to_s
        message = "[Numeric Damage] -> Weapons: #{@nWeapons}, Shields: #{@nShields}"
        return message
    end

    # To UI
    def getUIversion
        return NumericDamageToUI.new(self)
    end

end # class NumericDamage

end # module Deepspace