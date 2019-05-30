#encoding:utf-8

require_relative 'SpaceStation'
require_relative 'Transformation'
require_relative 'PowerEfficientSpaceStationToUI'
#require_relative 'SuppliesPackage'


module Deepspace

# Power efficient space station: gives more shot power and protection than a usual space station
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class PowerEfficientSpaceStation < SpaceStation

    # @!attribute [Float] efficiency factor for power efficient space station
    @@EFFICIENCYFACTOR = 1.10

    # Class initializer
    # @param station [SpaceStation] basic space station to convert to efficient
    # --Overriden
    def initialize(station)
        copy(station)
    end
    
    # Makes a shot
    # @return [Float] the shot power
    # --Overriden
    def fire
        return super*@@EFFICIENCYFACTOR
        # WIP COMPROBAR QUE ES @@EFFICIENCYFACTOR DE ESTA CLASE
    end

    # Use protection shield
    # @return [Float] the shield's energy
    # --Overriden
    def protection
        return super*@@EFFICIENCYFACTOR
    end

    # Receives a loot
    # @param [Loot] loot to be received
    # @return [Transformation] if loot received was efficient (GETEFFICIENT)
    #         or not (NOTRANSFORM)
    # --Overriden
    def setLoot(loot)
        super
        
        if loot.efficient
            return Transformation::GETEFFICIENT
        else
            return Transformation::NOTRANSFORM
        end
    end

end # class PowerEfficientSpaceStation

end # module Deepspace