#encoding:utf-8

require_relative 'WeaponType'
require_relative 'WeaponToUI'

module Deepspace

# Class to represent the weapons that a space station can dispose of to
# increment its energy when shooting
#
# @author Miguel Ángel Fernández Gutiérrez
class Weapon

	# Initializers
	# ==========================================================================

	# Class initializer
	# @param _name [String] name of weapon
	# @param _type [WeaponType] type of weapon
	# @param _uses [Integer] number of uses of weapon
	def initialize(_name, _type, _uses)
		# @!attribute [String] name of weapon
		@name = _name

		# @!attribute [WeaponType] type of weapon
		@type = _type

		# @!attribute [Intenger] number of uses of weapon
		@uses = _uses
	end

	# Copy constructor
	# @param origin [Weapon] instance which is going to be copied
	# @return [Weapon] a copy of the given instance
	def self.newCopy(origin)
		return new(origin.name, origin.type, origin.uses)
	end

	# Getters
	# ==========================================================================

	attr_reader :type, :uses, :name

	# Get type's power
	# @return [Float] type's power
	def power
		return @type.power
	end

	# Setters
	# ==========================================================================
	
	# Uses the weapon
	# If it is still available (uses is greater than 0), we can use the weapon
	# Otherwise, we cannot use the weapon
	# @return [Float] boost if uses > 0; 1.0 otherwise
	def useIt
		if @uses > 0
			@uses = @uses - 1
			return power
		else
			return 1.0
		end
	end

	# String representation, UI version
	# ==========================================================================

	# String representation of the object
	# @return [String] string representation
	def to_s
		message = "[Weapon]-> Name: #{@name}, Type: #{@type}, Power: #{power}, " \
				+ "Uses: #{@uses}"
        return message
	end

	# To UI
	def getUIversion
		return WeaponToUI.new(self)
	end
end # class Weapon

end # module Deepspace