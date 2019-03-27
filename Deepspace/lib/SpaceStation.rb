#encoding:utf-8

module Deepspace

class SpaceStation
		# Class atributes
		#=======================================================================
		@@MAXFUEL = 100					# Max fuel quantity that a space station can have
		@SHIELDLOSSPERUNITSHOT = 0.1	# Shield units lost per each shot unit taken

		# Initializers
		#=======================================================================
		
		# Description:
		# 	Initializer of the class
		# Parameters:
		# 	_ammoPower: Float
		#	_fuelUnits: Float
		#	_name: String
		#	_nMedals: Integer
		#	_shieldPower: Float
		#	_pendingDamage: Damage
		#	_weapons: Weapon[]
		#	_shieldBoosters: ShieldBooster[]
		#	_hangar: Hangar
		def initialize
				@ammoPower = _ammoPower
				@fuelUnits = _fuelUnits
				@name = _name
				@nMedals = _nMedals
				@shieldPower = _shieldPower
				@pendingDamage = _pendingDamage
				@weapons = _weapons
				@shieldBoosters = _shieldBoosters
				@hangar = _hangar
		end

		# Getters
		#=======================================================================
		
		# Description:
		# 	Gets the speed of the SpaceStation
		# 	Speed is calculated as fraction of fuel units and max fuel possible
		# Returns:
		# 	Float: percentage of speed, that's to say, a number in [0, 1]
		def speed
				if @@MAXFUEL == 0
						puts "WARNING, zero division at SpaceStation.speed()"
						return 0
				else
						return @fuelUnits / @@MAXFUEL
				end
		end

		# Description:
		# 	Checks the state of SpaceShip
		# 	Valid state means no pending damage or pending damage with no effect
		# Returns:
		# 	Boolean,	true, if SpaceShip is on valid state
		# 				false, otherwise
		def validState
				return @pendingDamage.nil? or @pendingDamage.length == 0 or @pendingDamage.hasNoEffect
		end
		
		# Setters
		#=======================================================================

		# Description:
		# 	Assigns the fuel of the space station
		# 	Private method
		# Parameters:
		# 	f: Float
		def assignFuelValue(f)
				if f < @@MAXFUEL
						@fuelUnits = f
				else
						@fuelUnits = @@MAXFUEL
				end
		end

		# Description:
		# 	Certain damage is adjusted to weapon list and shieldBoosters and it's
		# 	value is stored in the object
		# Parameters:
		# 	d: Damage, the damage to set
		def setPendingDamage(d)
				@pendingDamage = @pendingDamage.adjust(@weapons, @shieldBoosters)
		end

		# Description:
		# 	If pending damage has no effect, fixes the atribute to nil
		def cleanPendingDamage
				if @pendingDamage.hasNoEffect
						@pendingDamage = nil
				end
		end

		# Description:
		# 	Tries to add a weapon to the hangar
		# Parameters:
		# 	w: Weawpon, the weapon to add
		# Returns:
		# 	Boolean:	true, if weapon is succesfully added
		# 				false, if weapon fails to be added
		def receiveWeapon(w)
				return @hangar.addWeapon(w)
		end

		# Description:
		# 	Tries to add a shield booster
		# Parameters:
		# 	s: ShieldBooster, the shield we are trying to add
		# Returns:
		# 	Boolean:	true, if shield is succesfully added
		# 				false, if shield fails to be added
		def receiveShieldBooster(s)
				return @hangar.addShieldBooster(s)
		end

		# Description:
		# 	Tries to add an hangar
		# 	If there's already an hangar, this method has no effect
		# Parameters:
		# 	h: Hangar, the hangar to add
		def receiveHangar(h)
				if @hangar.nil?
						@hangar = Hangar.newCopy(h) # Security copy
				end
		end

		# Description:
		# 	Discards current hangar (nil reference)
		def discardHangar
				@hangar = nil
		end

		# Description:
		# 	If hangar is available, discars a weapon from it
		# Parameters:
		# 	i: Integer, index of the weapon at hangar to discard
		def discardShieldBoosterInHangar(i)
				if @hangar.nil? == false
						@hangar.removeWeapon(i)
				else
						puts "WARNING! Trying to discard a weapon where no hangar is available, at SpaceStation.discardShieldBoosterInHangar()"
				end
		end

		# Description:
		# 	Shoot, shield and fuel power increase by a certain SuppliesPackage
		# Parameters:
		# 	s: SuppliesPackage, the supplies to add
		def receiveSupplies(s)
				@ammoPower = @ammoPower + s.ammoPower
				@fuelUnits = @fuelUnits + s.fuelUnits
				@shieldPower = @shieldPower + s.shieldPower
		end

		# Description:
		# 	A weapon from the hangar is mounted to be used
		# 	If method runs succesfully, weapon is erased from Hangar, and the weapon
		# 	is added to the collection of weapons in use
		# Parameters:
		# 	i: Integer, index of the weawpon to mount
		def mountWeapon(i)
				if @hangar.nil?
						puts "WARNING! No hangar available at SpaceStation.mountWeapon()"
				else
						# New weapon is deleted from the hangar
						new_weapon = @hangar.removeWeapon(i) 
						if new_weapon.nil? == false
								@weapons << new_weapon
						else
								puts "WARNING! Trying to add nil weapon on SpaceStation.mountWeapon()"
						end
				end
		end

		# Description:
		# 	A ShieldBooster from the hangar is mounted
		# 	If method runs succesfully, shield is removed from Hangar, and that shield
		# 	is added to collection of shields in use
		# Parameters:
		# 	i: Integer, position of the shield to mount
		def mountShieldBooster(i)
				if @hangar.nil?
						puts "WARNING! No hangar available, at SpaceStation.mountShieldBooster()"
				else
						new_shield = @hangar.removeShieldBooster(i)
						if new_shield.nil?
								puts "WARNING! Trying to add nil shield on Spacestation.mountShieldBooster()"
						else
								@shieldBoosters << new_shield
						end
				end
		end

		# Description:
		# 	The spaceships moves. Therefore, fuel units decrease
		def move
				@fuelUnits = (@fuelUnits * (1-@speed)).round
		end

		# Description:
		# 	Deletes all mounted weapons and mounted shields with no uses left
		def cleanUpMountedItems
				# Filtering weapons
				@weapons = @weapons.select{|weapon|  weapon.uses > 0}

				# Filtering shields
				@shieldBoosters = @shieldBoosters.select{|shield| shield.uses > 0}
		end

		# WIP -- Práctica 3
		# Description:
		# Returns:
		# 	Float
		def fire
		end
		
		# Description:
		# Returns:
		# 	Float
		def protection
		end

		# WIP -- Práctica 3
		# Description:
		# Parameters:
		# 	shot: Float
		# Returns:
		# 	ShotResult
		def receiveShot
		end

		# WIP -- Práctica 3
		# Description
		# Parameters:
		# 	loot: Loot
		def setLoot
		end

		# WIP -- Práctica 3
		# Description:
		# Parameters:
		# 	i: Integer
		def discardWeapon
		end

		# WIP -- Práctica 3
		# Description:
		# Parameters:
		# 	i: Integer
		def discardShieldBooster
		end

		# Private Specifiers
		#=======================================================================
		private :assignFuelValue, :cleanPendingDamage
end

end	# Deepspace
