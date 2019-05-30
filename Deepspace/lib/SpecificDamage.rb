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
        super(_nShields)

        # @!attribute [Array<WeaponType>] array of types of weapons that will be lost
        @weapons  = []
    end

    attr_reader :weapons

    # Checks whether the damage is affecting or not
    # @return [Boolean] true, if damage has no effect; false, otherwise
    # --Overriden
	def hasNoEffect
		return (super && @weapons.length == 0)
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
        
        return self.class.new(new_weapons, super(s))
    end

    # Copy getter
    # @return [SpecificDamage] a copy of the current instance
    # --Overriden
    def copy
        return self.class.new(weapons, nShields)
    end

    # Removes a given type of weapon.
	# If a list of weapon types is not available (object is Numeric-constructed
	# instead of Specific-constructed), the number of weapons decreases by 1
    # @param w [Weapon] the weapon whose type wants to be removed
    # --Overriden
    def discardWeapon(w)
        if @weapons.length != 0
            position = @weapons.index(w.type)
            if position != nil
                @weapons.delete_at(position)
            end
        end
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