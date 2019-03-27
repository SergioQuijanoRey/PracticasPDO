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
		
		# Setters
		#=======================================================================

		# Description:
		# 	Assigns the fuel of the space station
		# 	Private method
		# Parameters:
		# 	f: Float
		def assignFuelValue(f):
				if f < @@MAXFUEL
						@fuelUnits = f
				else
						@fuelUnits = @@MAXFUEL
				end
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

		# Private Specifiers
		#=======================================================================
		private :assignFuelValue, :cleanPendingDamage
end

end	# Deepspace

+cleanUpMountedItems() : void
+discardHangar() : void
+discardShieldBooster(i : int) : void
+discardShieldBoosterInHangar(i : int) : void
+discardWeapon(i : int) : void
+discardWeaponInHangar(i : int) : void
+fire() : float
+getAmmoPower() : float
+getFuelUnits() : float
+getHangar() : Hangar
+getName() : string
+getNMedals() : int
+getPendingDamage() : Damage
+getShieldBoosters() : ShieldBooster[]
+getShieldPower() : float
+getSpeed() : float
+getUIversion() : SpaceStationToUI
+getWeapons() : Weapon[]
+mountShieldBooster(i : int) : void
+mountWeapon(i : int) : void
+move() : void
+protection() : float
+receiveHangar(h : Hangar) : void
+receiveShieldBooster(s : ShieldBooster) : boolean
+receiveShot(shot : float) : ShotResult
+receiveSupplies(s : SuppliesPackage) : void
+receiveWeapon(w : Weapon) : boolean
+setLoot(loot : Loot) : void
+setPendingDamage(d : Damage) : void
+validState() : boolean
