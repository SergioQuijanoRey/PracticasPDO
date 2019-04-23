#encoding:utf-8

require_relative "SpaceStationToUI"
require_relative "SuppliesPackage"

module Deepspace

#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class SpaceStation

	# Class atributes
	# ==========================================================================

	# @!attribute [Integer] Maximum fuel quantity that a space station can have
	@@MAXFUEL = 100

	# @!attribute [Float] Shield units lost per each shot unit taken
	@SHIELDLOSSPERUNITSHOT = 0.1

	# Constructors
	# ==========================================================================
	
	# Class initializer
	# @param _name [String] name of the space station
	# @param _supplies [SuppliesPackage] starting fuel units, weapons and shields
	def initialize(_name, _supplies)
		# !@attribute [String] name of the space station
		@name = _name

		# !@attribute [Float] Parametrizes ammunition power
		@ammoPower = 0.0

		# !@attribute [Float] Parametrizes fuel units
		@fuelUnits = 0.0
		
		# !@attribute [Integer] Number of medals
		@nMedals = 0
		
		# !@attribute [Float] Parametrizes shield power
		@shieldPower = 0.0
		
		# !@attribute [Damage] Pending damage
		@pendingDamage = nil

		# !@attribute [Array<Weapon>] Array of weapons
		@weapons = []

		# !@attribute [Array<ShieldBooster] Array of shield boosters
		@shieldBoosters = []
		
		# !@attribute [Hangar] Hangar
		@hangar = nil
		
		# Supplies are added
		receiveSupplies(_supplies)
	end

	# Getters
	# ==========================================================================

	attr_reader :ammoPower, :fuelUnits, :hangar, :name, :nMedals,
			:pendingDamage, :shieldBoosters, :shieldPower, :weapons

	# Gets the speed of the space station.
	# Speed is calculated as fraction of fuel units and max fuel possible
	# @param [Float] percentage of speed, that's to say, a number in [0, 1]
	def speed
		if @@MAXFUEL == 0
			raise "WARNING, zero division at SpaceStation.speed()"
			return 0
		else
			return @fuelUnits.to_f / @@MAXFUEL
		end
	end

	# Checks the state of the space ship.
	# Valid state means no pending damage or pending damage with no effect
	# @return [Boolean] true, if the space ship is on a valid state;
	#                   false, otherwise
	def validState
		if @pendingDamage.nil?
			return false
		else
			#TEST return @pendingDamage.length == 0 || @pendingDamage.hasNoEffect
			return @pendingDamage.hasNoEffect
		end
	end

	# Description
	# 	Gets UI representation of the object
	# Returns
	# 	SpaceStationToUI, the UI representation
	def getUIVersion
		if validState
			return SpaceStationToUI.new(self)
		else
			puts "WARNING! Not UI version for space station on invalid state"
			return nil
		end
	end

	def to_s
		out = "[Space Station]-> Name: #{@name}\n"
		out += "\tNo. Medals: #{@nMedals}, Fuel units: #{@fuelUnits.round(2)}, ammoPower: #{@ammoPower}, shieldPower: #{@shieldPower}\n"
		out += "\tWeapons: [#{@weapons.join(' ,')}]\n"
		out += "\tShieldBoosters: [#{@shieldBoosters.join(', ')}]\n"
		out += "\tHangar: #{@hangar}\n"
		out += "\tPendingDamage: #{@pendingDamage}\n"
		out += "-------- end of Space Station >> #{@name} << --------"
		return out
	end
	
	# Setters
	# ==========================================================================

	# Assigns the fuel of the space station
	# @param f [Float] fuel value to be assigned
	def assignFuelValue(f)
		if f < @@MAXFUEL
			@fuelUnits = f
		else
			@fuelUnits = @@MAXFUEL
		end
	end

	# Adjusts certain damage to some weapon and shieldBoosters lists, and its
	# value is stored in the object
	# @param d [Damage] the damage to be set
	def setPendingDamage(d)
		@pendingDamage = d.adjust(@weapons, @shieldBoosters)
	end

	# If pending damage has no effect, fixes the atribute to nil
	def cleanPendingDamage
		if !@pendingDamage.nil?
			if @pendingDamage.hasNoEffect
				@pendingDamage = nil
			end
		end
	end

	# Tries to add a weapon to the hangar
	# @param [Weapon] the weapon to add
	# @return [Boolean] true, if weapon is successfully added;
	#                   false, if the operation fails
	def receiveWeapon(w)
		if hangar != nil
			return @hangar.addWeapon(w)
		else
			return false
		end
	end

	# Tries to add a shield booster to the hangar
	# @param [ShieldBooster] the shield booster to add
	# @return [Boolean] true, if booster is successfully added;
	#                   false, if the operation fails
	def receiveShieldBooster(s)
		if hangar != nil
			return @hangar.addShieldBooster(s)
		else
			return false
		end
	end

	# Tries to add a hangar.
	# If there's already a hangar, this method has no effect
	# @param h [Hangar] the hangar to add
	def receiveHangar(h)
		if @hangar.nil?
			@hangar = Hangar.newCopy(h) # Security copy
		end
	end

	# Discards current hangar (nil reference)
	def discardHangar
		@hangar = nil
	end

	# If a hangar is available, discards a weapon from it, in a certain
	# position
	# @param i [Integer] index where the weapon that wants to be discarded is
	#                    located in the hangar
	def discardWeaponInHangar(i)
		if !@hangar.nil?
			@hangar.removeWeapon(i)
		else
			raise "WARNING! Trying to discard a weapon where no hangar is available, at SpaceStation.discardWeaponInHangar()"
		end
	end

	# If a hangar is available, discards a shield booster from it, in a certain
	# position
	# @param i [Integer] index where the booster that wants to be discarded is
	#                    located in the hangar
	def discardShieldBoosterInHangar(i)
		if !@hangar.nil?
			@hangar.removeShieldBooster(i)
		else
			raise "WARNING! Trying to discard a shield booster where no hangar is available, at SpaceStation.discardShieldBoosterInHangar()"
		end
	end

	# Shot, shield and fuel power increase by a certain supplies package
	# @param s [SuppliesPackage] the supplies to add
	def receiveSupplies(s)
		@ammoPower += s.ammoPower
		assignFuelValue(@fuelUnits+s.fuelUnits)
		@shieldPower += s.shieldPower
	end

	# A weapon from the hangar is mounted to be used.
	# If method runs successfully, weapon is erased from Hangar, and the weapon
	# is added to the collection of weapons in use
	# @param i [Integer] index of the weapon to mount
	def mountWeapon(i)
		if @hangar.nil?
			raise "WARNING! No hangar available at SpaceStation.mountWeapon()"
		else
			# New weapon is deleted from the hangar
			new_weapon = @hangar.removeWeapon(i) 
			if !new_weapon.nil?
				@weapons << new_weapon
			else
				raise "WARNING! Trying to add nil weapon on SpaceStation.mountWeapon()"
			end
		end
	end

	# A shield booster from the hangar is mounted to be used.
	# If method runs successfully, booster is erased from Hangar, and the weapon
	# is added to the collection of boosters in use
	# @param i [Integer] index of the booster to mount
	def mountShieldBooster(i)
		if @hangar.nil?
			raise "WARNING! No hangar available, at SpaceStation.mountShieldBooster()"
		else
			new_shield = @hangar.removeShieldBooster(i)
			if new_shield.nil?
				raise "WARNING! Trying to add nil shield on Spacestation.mountShieldBooster()"
			else
				@shieldBoosters << new_shield
			end
		end
	end

	# The spaceships moves. Therefore, fuel units decrease
	def move
		@fuelUnits -= @fuelUnits*speed
		if @fuelUnits <= 0
			@fuelUnits = 0
		end
	end

	# Deletes all mounted weapons and mounted shields with no uses left
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
	def receiveShot(shot)
	end

	# WIP -- Práctica 3
	# Description
	# Parameters:
	# 	loot: Loot
	def setLoot(loot)
	end

	# WIP -- Práctica 3
	# Description:
	# Parameters:
	# 	i: Integer
	def discardWeapon(i)
	end

	# WIP -- Práctica 3
	# Description:
	# Parameters:
	# 	i: Integer
	def discardShieldBooster(i)
	end

	# Private Specifiers
	#=======================================================================
	private :assignFuelValue, :cleanPendingDamage
end

end	# Deepspace