#encoding:utf-8

require_relative 'SuppliesPackage'
require_relative 'Hangar'
require_relative 'Damage'
require_relative 'Weapon'
require_relative 'WeaponType'
require_relative 'Loot'
require_relative 'ShieldBooster'
require_relative 'CardDealer'
require_relative 'SpaceStationToUI'

module Deepspace

# Class to represent a space station a player can have
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class SpaceStation

	# Class atributes
	# ==========================================================================

	# @!attribute [Integer] Maximum fuel quantity that a space station can have
	@@MAXFUEL = 100

	# @!attribute [Float] Shield units lost per each shot unit taken
	@@SHIELDLOSSPERUNITSHOT = 0.1

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
			raise "Zero division at SpaceStation.speed(): MAXFUEL cannot be zero"
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
			return true
		else
			return @pendingDamage.hasNoEffect
		end
	end

	# Setters
	# ==========================================================================

	# Makes copy
	# @param other [SpaceStation] instance to copy
	def copy(other)
		@name = other.name
		@ammoPower = other.ammoPower
		@shieldPower = other.shieldPower
		@fuelUnits = other.fuelUnits
		@nMedals = other.nMedals

		if !other.hangar.nil?
			@hangar = Hangar.newCopy(other.hangar)
		else
			@hangar = nil
		end

		if !other.pendingDamage.nil?
			@pendingDamage = other.pendingDamage.copy
		else
			@pendingDamage = nil 
		end

		@shieldBoosters = []
		for s in other.shieldBoosters
			@shieldBoosters << ShieldBooster.newCopy(s)
		end

		@weapons = []
		for w in other.weapons
			@weapons << Weapon.newCopy(w)
		end
	end

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
		if !d.nil?
			@pendingDamage = d.adjust(@weapons, @shieldBoosters)
		end
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
		if !hangar.nil?
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
		if !hangar.nil?
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
			@hangar = h
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
		#else
		#	raise "WARNING! Trying to discard a weapon where no hangar is available, at SpaceStation.discardWeaponInHangar()"
		end
	end

	# If a hangar is available, discards a shield booster from it, in a certain
	# position
	# @param i [Integer] index where the booster that wants to be discarded is
	#                    located in the hangar
	def discardShieldBoosterInHangar(i)
		if !@hangar.nil?
			@hangar.removeShieldBooster(i)
		#else
		#	raise "WARNING! Trying to discard a shield booster where no hangar is available, at SpaceStation.discardShieldBoosterInHangar()"
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
		if i >= 0 && i < @hangar.weapons.length
			if !@hangar.nil?
				# New weapon is deleted from the hangar
				new_weapon = @hangar.removeWeapon(i)
				if !new_weapon.nil?
					@weapons << new_weapon
				else
					raise "WARNING! Trying to add a nil weapon on SpaceStation.mountWeapon()"
				end
			else
				raise "WARNING! No hangar available at SpaceStation.mountWeapon()"
			end
		end
	end

	# A shield booster from the hangar is mounted to be used.
	# If method runs successfully, booster is erased from Hangar, and the weapon
	# is added to the collection of boosters in use
	# @param i [Integer] index of the booster to mount
	def mountShieldBooster(i)
		if i >=0 && i < @hangar.shieldBoosters.length
			if !@hangar.nil?
				# New shield booster is deleted from the hangar
				new_shield = @hangar.removeShieldBooster(i)
				if !new_shield.nil?
					@shieldBoosters << new_shield
				else
					raise "WARNING! Trying to add a nil shield on SpaceStation.mountShieldBooster()"
				end
			else
				raise "WARNING! No hangar available at SpaceStation.mountShieldBooster()"
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

	# Makes a shot
	# @return [Float] the shot power
	def fire
		factor = 1.0

		@weapons.each do |w|
			factor *= w.useIt
		end

		return @ammoPower*factor
	end
	
	# Use protection shield
	# @return [Float] the shield's energy
	def protection
		factor = 1.0

		@shieldBoosters.each do |s|
			factor *= s.useIt
		end

		return @shieldPower*factor
	end

	# Make the operations related to the reception of an enemy's impact
	# @param shot [Float] enemy's impact shot power
	# @return [Boolean] true, if the shield resisted the impact; else, otherwise
	def receiveShot(shot)
		myProtection = protection

		if myProtection >= shot
			@shieldPower -= @@SHIELDLOSSPERUNITSHOT
			if @shieldPower < 0
				shieldPower = 0.0
			end

			return ShotResult::RESIST
		else
			@shieldPower = 0.0

			return ShotResult::DONOTRESIST
		end
	end

	# Receives a loot
	# @param [Loot] loot to be received
	# @return [Transformation] WIP MODIFICAR URGENTE
	def setLoot(loot)
		dealer = CardDealer.instance # behaviour introduced by Singleton
		h = loot.nHangars

		if h > 0
			hangar = dealer.nextHangar
			receiveHangar(hangar)
		end

		elements = loot.nSupplies
		elements.times do
			sup = dealer.nextSuppliesPackage
			receiveSupplies(sup)
		end

		elements = loot.nWeapons
		elements.times do
			weap = dealer.nextWeapon
			receiveWeapon(weap)
		end

		elements = loot.nShields
		elements.times do
			sh = dealer.nextShieldBooster
			receiveShieldBooster(sh)
		end

		medals = loot.nMedals
		@nMedals += medals

		if loot.efficient
			return Transformation::GETEFFICIENT
		elsif loot.spaceCity
			return Transformation::SPACECITY
		else
			return Transformation::NOTRANSFORM
		end
	end

	# Discards weapon in certain position from the collection of weapons in use
	# @param i [Integer] index where the weapon that wants to be discarded is located
	def discardWeapon(i)
		size = @weapons.length
		if i >= 0 && i < size
			w = @weapons.delete_at(i)
			if !@pendingDamage.nil?
				@pendingDamage.discardWeapon(w)
				cleanPendingDamage
			end
		end
	end

	# Discards shield in certain position from the collection of weapons in use
	# @param i [Integer] index where the shield that wants to be discarded is located
	def discardShieldBooster(i)
		size = @shieldBoosters.length
		if i >= 0 && i < size
			s = @shieldBoosters.delete_at(i)
			if !@pendingDamage.nil?
				@pendingDamage.discardShieldBooster
				cleanPendingDamage
			end
		end
	end

	# String representation, UI version
	# ==========================================================================

	# String representation of the object
	# @return [String] string representation
	def to_s
		message = "[Space Station]-> Name: #{@name}\n" \
				+ "\tNo. Medals: #{@nMedals}, Fuel units: #{@fuelUnits.round(2)}, " \
				+ "ammoPower: #{@ammoPower}, shieldPower: #{@shieldPower}\n" \
				+ "\tWeapons: [#{@weapons.join(' ,')}]\n" \
				+ "\tShieldBoosters: [#{@shieldBoosters.join(', ')}]\n" \
				+ "\tHangar: #{@hangar}\n" \
				+ "\tPendingDamage: #{@pendingDamage}\n" \
				+ "-------- end of Space Station >> #{@name} << --------"
        return message
	end

	# To UI
	def getUIversion
		return SpaceStationToUI.new(self)
	end

	# Visibility specifiers
	# ==========================================================================
	private :assignFuelValue, :cleanPendingDamage
end # class SpaceStation

end	# Deepspace