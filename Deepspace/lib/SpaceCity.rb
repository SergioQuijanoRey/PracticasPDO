#encoding:utf-8

require_relative 'SpaceStation'
require_relative 'SpaceCityToUI'


module Deepspace

# Class to represent an association of space stations
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class SpaceCity < SpaceStation

    # Class initializer
    # @param _base [SpaceStation] base of space city
    # @param _rest [Array<SpaceStation>] rest of space cities (collaborators)
    def initialize(_base, _rest)
        copy(_base)
        
        # @!attribute [SpaceStation] base of space city
        @base = _base

        # @!attribute [Array<SpaceStation>] rest of space cities (collaborators)
        @collaborators = _rest

    end

    attr_reader :collaborators

    # Makes all space stations shoot at the same time
    # @return [Float] all accumulated shot power
    # --Overriden
    def fire
        power = @base.fire

        for station in @collaborators
            power += station.fire
        end

        return power
    end
    
    # Uses all protection shields from all stations at the same time
    # @return [Float] all accumulated shield energy
    # --Overriden
    def protection
        energy = @base.protection

        for station in @collaborators
            energy += station.protection
        end

        return energy
    end

    # Receives a loot
    # @param [Loot] loot to be received
    # --Overriden
    def setLoot(loot)
        super

        return Transformation::NOTRANSFORM
    end

    # String representation of the object
    # @return [String] string representation
    # --Overriden
    def to_s
        getUIversion().to_s
    end

    # To UI
    def getUIversion
        return SpaceCityToUI.new(self)
    end

end # class SpaceCity

end # module Deepspace