#encoding:utf-8

module Deepspace

class SpaceStation

    @@MAXFUEL = 100                 # maximum amount of fuel
    @@SHIELDLOSSPERUNITSHOT = 0.1   # shields lost per unit of power in shot

    # Constructors
	#===========================================================================

	# Description:
	# 	Class initializer
	# Parameters:
	# 	n: String, name of the space station
	# 	supplies: SuppliesPackage, package of supplies for the station to start with
	# Return:
	# 	Nil
    def initialize(n, supplies)
        @name = n
        @ammoPower = supplies.ammoPower
        @fuelUnits = supplies.fuelUnits
        @nMedals = # --WIP--
        @shieldPower = supplies.shieldPower
        @hangar = Nil
        @weapons = Weapon []
        @shieldBoosters = ShieldBooster []
    end

    # Description:
    #   Assign fuel value to certain value, without exceeding certain limit
    # Parameters:
    #   f: Integer, counts fuel units to add
    # Return:
    #   Nil
    def assignFuelValue(f)
        if (@fuelUnits + f) > @@MAXFUEL then
            @fuelUnits = @@MAXFUEL
        else
            @fuelUnits += f
        end
    end

    # Description:
    #   If pending damage has no effect, it cleans it (setting reference to Nil)
    # Return:
    #   Nil
    def cleanPendingDamage
        # WIP hace falta comprobar @pendingDamage.nil? ??
        if @pendingDamage.hasNoEffect then
            @pendingDamage = Nil
        end
    end

    # Description:
    #   Receives a weapon
    # Parameters:
    #   w: Weapon, weapon to add
    # Return:
    #   Bool: if hangar is available, it returns the result of trying to add the
    #   weapon to the shield. If no hangar is available, returns false
    def receiveWeapon(w)
        # wip
    end

    # Description:
    #   Receives a shield booster
    # Parameters:
    #   s: ShieldBooster, shield booster that is received
    # Return:
    #   Bool: if hangar is available, it returns the result of trying to add the
    #   shield booster to the shield. If no hangar is available, returns false
    def receiveShieldBooster(s)
        #wip
    end

    # Description:
    #   Receives a hangar
    # Parameters:
    #   h: Hangar, hangar that is received
    # Return:
    #   Nil: if no hangar is available, the parameter is established as the space
    #   station's hangar; otherwise, this operation has no effect
    def receiveHangar(h)
        if @hangar.nil? then
            @hangar = h     # WIP hace falta copia?
        end
    end

    # Description:
    #   Discards hangar
    # Return:
    #   Nil
    def discardHangar
        @hangar = Nil
    end

    # Description:
    #   Receives certain supplies, incrementing the ammonition power, fuel
    #   units and shield power by the quantities supplied
    # WIP DUDA los máximos hay que mantenerlos (?) yo lo he hecho jeje
    # Parameters:
    #   s: SuppliesPackage, supplies package that is received
    # Return:
    #   Nil
    def receiveSupplies(s)
        if (@fuelUnits + s.fuelUnits) > @@MAXFUEL then
            @fuelUnits = @@MAXFUEL
        else
            @fuelUnits += s.fuelUnits
        end

        @ammoPower += s.ammoPower
        @shieldPower += s.shieldPower
    end

    # Description:
    #   Sets pending damage
    # Parameters:
    #   d: Damage, damage received
    # Return:
    #   Nil
    def setPendingDamage(d)
        # WIP
    end

    # Description:
    #   Tries to mount weapon into the hangar's i-th position. If hangar is
    #   available, the weapon in that position is deleted, and if that is
    #   successful, the hangar is added to the collection of weapons in use
    # Parameters:
    #   i: Integer, position in which the weapon wants to be mounted
    # Return:
    #   Nil
    def mountWeapon(i)
    end

    # Description:
    #   Tries to mount shield booster into the hangar's i-th position. If hangar
    #   is available, the shield booster in that position is deleted, and if
    #   that is successful, the shield booster is added to the collection of
    #   shield boosters in use
    # Parameters:
    #   i: Integer, position in which the shield booster wants to be mounted
    # Return:
    #   Nil
    def mountShieldBooster(i)
    end

    # Description:
    #   If hangar is available, it solicitates to unmount weapon in the i-th
    #   position
    # Parameters:
    #   i: Integer, position in which the weapon wants to be unmounted
    # Return:
    #   Nil
    def discardWeaponInHangar(i)
    end

    # Description:
    #   If hangar is available, it solicitates to unmount shield booster in the
    #   i-th position
    # Parameters:
    #   i: Integer, position in which the shield booster wants to be unmounted
    # Return:
    #   Nil
    def discardShieldBoosterInHangar(i)
    end

    # Description:
    #   Get the speed of the space station
    # Return:
    #   Float, returns the speed of the space station
    def getSpeed
        return @fuelUnits / @@MAXFUEL
    end

    # Description:
    #   Moves the space station
    # Return:
    #   Nil
    def moves
        @fuelUnits -= @fuelUnits * getSpeed
        if @fuelUnits < 0 then
            @fuelUnits = 0
        end
    end

    # Description:
    #   Test whether the space station is in a valid state
    # Return:
    #   Bool
    def validState
        # WIP
    end

    # Description:
    #   Remove all weapons and shield boosters mounted that have no uses left
    # Return:
    #   Nil
    def cleanUpMountedItems
        # WIP
    end

    # Visibility specifiers
	#=======================================================================
    private :assignFuelValue, :cleanPendingDamage

end # class SpaceStation

end # module Deepspace