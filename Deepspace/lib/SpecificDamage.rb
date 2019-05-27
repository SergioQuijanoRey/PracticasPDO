#encoding:utf-8

require_relative 'Damage'
require_relative 'SpecificDamageToUI'


module Deepspace

# Class to represent specific damage
# @see Deepspace::Damage
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class SpecificDamage < Damage

    public_class_method :new

    # Class initializer
	# @param _weapons [Array<WeaponType>] array of types of weapons that will be lost
	# @param _nShields [Integer] number of shields that will be lost
    def initialize(_weapons, _nShields)
        super(-1, _nShields, _weapons)
    end

    #attr_reader :nWeapons # WIP inherited?

    # Creates a copy of current objet where weapons and shields which are not
	# included in arrays given as parameters are discarded. That's to say, we
	# shrink the Damage to the parameters
	# @param w [Array<Weapon>] weapons to fit
	# @param s [Array<ShieldBooster>] shields to fit
	# @return [SpecificDamage] a copy of the object adjusted as explained above
    # --Overriden
    def adjust(w, s)
        weapons_copy = @weapons.clone

		new_weapons = w.map do |weapon|
			weapons_copy.delete_at(weapons_copy.index(weapon.type) || weapons_copy.length)
		end

		new_weapons.compact!
        
        return self.class.new(new_weapons, [@nShields, s.length].min)
    end

    # Copy getter
    # @return [SpecificDamage] a copy of the current instance
    # --Overriden
    def copy
        return new(weapons, nShields)
    end

    # String representation of the object
    # @return [String] string representation
    def to_s
        message = "[Specific Damage] -> Shields: #{@nShields}, Weapon types: " + getWeaponInfo
        return message
    end

    # To UI
    def getUIversion
        return SpecificDamageToUI.new(self)
    end
    
end # class NumericDamage

end # module Deepspace