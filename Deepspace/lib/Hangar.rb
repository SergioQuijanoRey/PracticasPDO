#encoding:utf-8

class Hangar

	# Constructors
	#=======================================================================

	 # Description:
	 # 	Initializer of the class
	 # 	All parameters are floats except the random number generator
	 # Return:
	 # 	Nil
	def initialize(_maxElements)
		@maxElements = _maxElements
		@weapons = Array.new
		@shieldBoosters = Array.new
	end

	def self.newCopy(origin)
		return Hangar.new(origin.maxElements)
	end

	def self.getUIversion
		# --WIP
	end

	private

	def spaceAvailable
		return (@weapons.length + @shieldBoosters.length) < @maxElements
	end

	public

	def addWeapon(w)
		if spaceAvailable
			@weapons.push(w)
			return true
		else
			return false
		end
	end

	def addShieldBoosters(s)
		if spaceAvailable
			@shieldBoosters.push(s)
			return true
		else
			return false
		end
	end

	def getMaxElements
		return @maxElements
	end

	def getShieldBoosters
		return @shieldBoosters
	end

	def getWeapons
		return @weapons
	end

	def removeShieldBoosters(s)
		if s < @shieldBoosters.length
			@shieldBoosters[s] # --WIP cómo devolver algo que elimino luego: se hace de forma automática o creo un shieldBooster nuevo?
			@shieldBoosters.delete_at(s)
		else
			return nil
		end
	end

	def removeWeapon(w)
		if w < @weapons.length
			@weapons[w]
			@weapons.delete_at(w)
		else
			return nil
		end
	end


end