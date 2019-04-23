#encoding:utf-8

require_relative 'ShieldToUI'

module Deepspace

# Class to represent the shield boosters that space stations can have
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class ShieldBooster

	# Constructors
	#===========================================================================
	
	# Class initializer
	# @param _name [String] name of the shield booster
	# @param _boost [Float] percentage of damage absorbed by the shield
	# @param _uses [Integer] how many uses the shield booster has
	def initialize(_name, _boost, _uses)
		# @!attribute [String] name of the shield booster
		@name = _name

		# @!attribute [Float] percentage of damage absorbed by the shield
		@boost = _boost

		# @!attribute [Integer] how many uses the shield booster has
		@uses = _uses
	end
	
	# Copy constructor
	# @param origin [ShieldBooster] instance which is going to be copied
	# @return [ShieldBooster] a copy of the given instance
	def self.newCopy(origin)
		return new(origin.name, origin.boost, origin.uses)
	end

	# Getters
	# ==========================================================================

	attr_reader :boost, :uses, :name

	# Setters
	# ==========================================================================

	# Uses shield booster.
	# If is still available (uses is greater than 0), we can use the boost
	# Otherwise, we cannot use the boost
	# @return [Float] boost if uses > 0; 1.0 if uses == 0
	def useIt
		if @uses > 0			# We can use our shield booster
			@uses -= 1
			return @boost
		else					# Shield booster is drained
			return 1.0
		end
	end

	# String representation, UI version
	# ==========================================================================

	# String representation of the object
	# @return [String] string representation
	def to_s
		message = "[ShieldBooster]-> Boost: #{@boost}, Uses: #{@uses}"
        return message
	end

	# To UI
	def getUIversion
		return ShieldToUI.new(self)
	end
end # class ShieldBooster
	
end # module Deepspace