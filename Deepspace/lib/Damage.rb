#encoding:utf-8

#require_relative "enums"
#require_relative "Weapon"
require_relative "DamageToUI"

module Deepspace

# Description:
# 	Represents damages done on a spaceShip after loosing a combat
# 	They indicate which elements are going to be lost after loosing the combat
class Damage

	# Initializers
	#=======================================================================

	# Description:
	# 	Initializer of the class
	# 	It's just going to be used by other "constructors"
	# Parameters:
	# 	_nWeapons: Integer, number of lost weapons
	# 	_nShields: Integer, number of lost shields
	# 	_weapons: WeaponType[], types of weapons to be discard
	def initialize(_nWeapons, _nShields, _weapons)
		@nWeapons = _nWeapons
		@nShields = _nShields
		@weapons = _weapons
	end
	
	# Description:
	# 	Creates a Damage where weapon types are not known
	# Parameters:
	# 	_nWeapons: Integer, number of weapons to lose
	# 	_nShields: Integer, number of shields to lose
	def self.newNumericWeapons(_nWeapons, _nShields)
		return new(_nWeapons, _nShields, nil)
	end

	# Description:
	# 	Creates a Damage object where weapon types are specified
	# Parameters:
	# 	_weapons: WeaponType[], types of weapons to discard
	# 	_nShields: Integer, number of shields to lose
	def self.newSpecificWeapons(weapons, _nShields)
		return new(0, _nShields, weapons)
	end

	# Description:
	# 	Creates a copy of a given damage
	# Parameters:
	# 	d: Damage, the instance to be copied
	# Returns
	# 	Damage: a copy of the given instance
	def self.newCopy(d)
		if d.weapons == nil
			return newNumericWeapons(d.nWeapons, d.nShields)
		else
			return newSpecificWeapons(d.weapons, d.nShields)
		end
	end

	# Getters
	#=======================================================================

	attr_reader :nWeapons, :nShields, :weapons

	# Description:
	# 	Returns the UI representation of the object
	# Returns:
	# 	DamageToUI: the UI representation of the object
	def getUIVersion
		return DamageToUI.new(self)
	end

	# Description:
	# 	Searchs the first element of WeaponType array to match a given type
	# Parameters:
	# 	w: Weapon[], the array of weapon types where we search
	# 	t: WeaponType, the type we're looking for
	# Returns:
	# 	Int: position, if the element is found
	# 		 -1, if no element is found
	def arrayContainsType(w, t)
		i = 0
		w.each do |weapon_aux|
			if weapon_aux == t
				return i
			else
				i += 1
			end
		end

		# No element found
		return -1
	end

	def to_s
		if @weapons == nil
			return "Damage.newNumericWeapons(#{@nWeapons}, #{@nShields})"
		else
			return "Damage.newSpecificWeapons(#{@weapons}, #{@nShields})"
		end

		# Security condition
		return ""
	end
	
	# Setters
	#=======================================================================

	# Description:
	# 	Creates a copy of current objet where weapons and shields which are
	# 	not included in arrays given as parameters are discarded. That's to say,
	# 	we srink the Damage to the parameters
	# Parameters:
	# 	w: Weapon[], weapons to fit
	# 	s: ShieldBooster[], shields to fit
	# Returns:
	# 	Damage: a copy of the object adjusted as explained above
	def adjust(w, s)
		# we check which new the object has been constructed with:
		#   if weapons == nil, it has been constructed with newNumericWeapons
		#   else, it has been constructed with newSpecificWeapons
		if @weapons == nil
			if w.length > @nWeapons
				new_nWeapons = @nWeapons
			else
				new_nWeapons = w.length
			end

			if s.length > @nShields
				new_nShields = @nShields
			else
				new_nShields = s.length
			end
			
			copy = Damage.newNumericWeapons(new_nWeapons, new_nShields)
		else
			# we compute the intersection
			new_weapons = []
			for weapon in w
				if arrayContainsType(@weapons, weapon)
					new_weapons << weapon
				end
			end

			if s.length > @nShields
				new_nShields = @nShields
			else
				new_nShields = s.length
			end

			copy = Damage.newSpecificWeapons(new_weapons, new_nShields)
		end

		return copy
	end

	# Description:
	# 	Removes a given type of weapon
	# 	If a list of weapon types is not available, the number of weapons to be deleted decreases by 1
	# Parameters:
	# 	w: Weapon, the weapon whose type wants to be removed
	def discardWeapon(w)
		if weapons == nil
			if @nWeapons > 0
				@nWeapons -= 1
			else
				puts "WARNING! You tried to have negative weapons at Damage.discardWeapon()"
			end
		else
			# we delete every weapon in @weapons of type w.type
			@weapons.delete(w.type)
		end
	end

	# Description:
	# 	Reduces by 1 the number of shield boosters to be removed
	def discardShieldBooster
		if @nShields > 0
			@nShields -= 1
		else
			puts "WARNING! You tried to have negative shieldBoosters at Damage.discardShieldBooster()"
		end
	end

	# Description:
	# 	Checks wether or not damage is affecting
	# Returns:
	# 	Boolean: true, if damage has no effect
	# 			 false, if damage has effect
	def hasNoEffect
		return @nShields + @nWeapons == 0
	end

	# Visibility specifiers
	#=======================================================================
	private :arrayContainsType
	private_class_method :new
end

end	# module Deepspace
