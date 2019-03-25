# WIP -- Not finished
# WIP -- lack of documentation

module Deepspace

class Hangar
		# Initializers
		#=======================================================================

		# Description:
		# 		Initializer of the class
		# Parameters:
		# 		capacity: Integer
		def initialize(capacity)
				@maxElements = capacity
		end

		# Description:
		# 		Copy initializer
		# Param:
		# 		h: Hangar, the objet to be copied
		# Returns:
		# 		Hangar, a copy of the hangar parameter given
		def self.newCopy(h)
				return Hangar.new(h.maxElements)
		end

		# Getters
		#=======================================================================
		def maxElements
				return @maxElements
		end

		# Description:
		# Returns:
		# 		Boolean
		def spaceAvailable
		end

		# Setters
		#=======================================================================
		
		# Description:
		# Parameters:
		# 		s: Integer
		def removeShieldBooster(s)
		end

		# Description:
		# Parameters:
		# 		w: Integer
		def removeWeapon(w)
		end

		def to_s
		end

		def getUIversion
		end
end

end # module Deepspace
