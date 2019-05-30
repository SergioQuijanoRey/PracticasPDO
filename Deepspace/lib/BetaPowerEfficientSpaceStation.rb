#encoding:utf-8

require_relative 'Dice'
require_relative 'PowerEfficientSpaceStation'
require_relative 'BetaPowerEfficientSpaceStationToUI'

module Deepspace

# Beta power efficient space station: gives more shot power than an efficient space station
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
class BetaPowerEfficientSpaceStation < PowerEfficientSpaceStation

    # @!attribute [Float] extra efficiency for beta efficient
    @@EXTRAEFFICIENCY = 1.20

    # Class initializer
    # @param station [SpaceStation] basic space station to convert to beta efficient
    # --Overriden
    def initialize(station)
        super

        # @!attribute [Dice] random utilities
        @dice = Dice.new
    end

    # Makes a shot
    # @return [Float] the shot power
    # --Overriden
    def fire
        if @dice.extraEfficiency
            return @@EXTRAEFFICIENCY*super
        else
            return super
        end
    end

    # String representation
    # @return [String] string representation
    def to_s
        message = "(Beta) " + super
        return message
    end

    # To UI
    def getUIversion
        return BetaPowerEfficientSpaceStationToUI.new(self)
    end

end # class BetaPowerEfficientSpaceStation

end # module Deepspace