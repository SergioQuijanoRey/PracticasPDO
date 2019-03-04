#encoding:utf-8

# Class to represent the shield boosters that space stations can have
class ShieldBooster

	# Constructors
	#===========================================================================
	
	# Description:
	# 	Class initializer
	# Parameters:
	# 	name: WIP
	# 	boost: WIP
	# 	uses: integer to parametrize shield power
	# Return:
	# 	Nil
	def initialize(_name, _boost, _uses)
		@name = _name
		@boost = _boost
		@uses = _uses
	end

	# Getters
	#===========================================================================

	# Description:
	# 	Copy Constructor
	# Parameters:
	# 	origin: ShieldBooster instance which is going to be copied
	# Return:
	# 	A ShieldBooster instance holding a copy of origin
	def newCopy(origin)
		return ShieldBooster.new(@origin.name, @origin.boost, @origin.uses)
	end

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

	# WIP
	#===========================================================================

	def useIt
		if @uses > 0
			@uses -= 1
			return @boost
		else
			return 1.0
		end
	end
end
