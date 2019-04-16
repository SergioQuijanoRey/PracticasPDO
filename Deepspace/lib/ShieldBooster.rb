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
	# 	uses: Integer, how many uses the ShieldBooster has
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
		return new(origin.name, origin.boost, origin.uses)
	end

	# Getters
	#===========================================================================

	attr_reader :boost, :uses, :name

	# Description:
	# 	Gets the UI representation of the ShieldBooster object
	# Returns:
	#	ShieldToUI: the UI representation
	def getUIVersion
		return ShieldToUI.new(self)
	end

	# Description:
	# 	Displays all the relevant data of the object
	# Returns:
	# 	String, containing all parameters value
	def to_s
		return "[ShieldBooster]-> Boost: #{@boost}, Uses: #{@uses}"
	end

	# Description:
	# 	Gets the UI representation of the object
	# Returns:
	# 	ShieldToUI: the UI representation
	def getUIVersion
		return ShieldToUI.new(self)
	end

	# Setters
	#===========================================================================

	# Description:
	# 	Uses the shield booster
	# 	If it is still available (uses is greater than 0), we can use the boost
	# 	Otherwise, we cannot use the boost
	# Return:
	# 	Float:
	# 		Boost if uses > 0
	#		1.0 if uses == 0
	def useIt
		if @uses > 0			# We can use our shield booster
			@uses -= 1
			return @boost
		else					# Shield booster is drained
			return 1.0
		end
	end

end # class ShieldBooster

end # module Deepspace
