#encoding:utf-8

module Deepspace

# Class to represent the shield boosters that space stations can have
class ShieldBooster

	# Constructors
	#===========================================================================
	
	# Description:
	# 	Class initializer
	# Parameters:
	# 	name: String, name of the shield booster
	# 	boost: Float, percentage of damage absorbed by the shield
	# 	uses: Integer, how many uses the ShieldBooster have
	# Return:
	# 	Nil
	def initialize(_name, _boost, _uses)
		@name = _name
		@boost = _boost
		@uses = _uses
	end
	
	# Description:
	# 	Copy Constructor
	# Parameters:
	# 	origin: ShieldBooster instance which is going to be copied
	# Return:
	# 	A ShieldBooster instance holding a copy of origin
	def self.newCopy(origin)
		return ShieldBooster.new(origin.name, origin.boost, origin.uses)
	end

	# Getters
	#===========================================================================

	# Description:
	# 	Getter for boost
	# Return
	#	Float with boost
	def boost
		return  @boost
	end

	# Description:
	# 	Getter for uses
	# Returns:
	#	Int with uses
	def uses
		return @uses
	end

	# Description:
	# 	Gets the UI representation of the ShieldBooster object
	# Returns:
	#	ShieldToUI: the UI representation
	def getUIVersion
		return ShieldToUI.new(self)

	# Description:
	# 	Getter for name
	# Returns:
	#	String: name of the shield booster
	def name
			return @name
	end

	# Description:
	# 	Displays all the relevant data of the object
	# Returns:
	# 	String, containing all parameters value
	def to_s
		return "ShieldBooster(\"#{name}\", #{boost}, #{uses})"
	end

	# Description:
	# 	Gets the UI representation of the object
	# Returns:
	# 	ShieldToUI: the UI representation
	def getUIVersion
			return ShieldToUI.new(self)
	end

	# SETTERS
	#===========================================================================

	# Description:
	# 	Uses our shield booster
	# 	If is still available (uses is greater than 0), we can use the boost
	# 	Otherwise, we cannot use the boost
	# Return:
	# 	Float:
	# 		Boost if uses > 0
	#		1.0 if uses == 0
	def useIt
		if @uses > 0			# We can use our shield booster
			@uses = @uses - 1
			return @boost
		else					# Shield booster is drained
			return 1.0
		end
	end
end # class

end # module
