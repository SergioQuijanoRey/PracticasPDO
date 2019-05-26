#encoding:utf-8

module Deepspace

# Enum to represent the types of weapons available on the game
#
# @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
module WeaponType

    # Class to represent the types of weapons
    class Type
        # Description:
        # 	Initializer of the class
        # Parameters:	
        # 	pow: Float,representing the power of the weapon
        # 	_name: String, name of the weapon type
        # Return:
        # 	Nil
        def initialize(pow, _name)
            @POWER = pow
            @name = _name
        end
        
        # Description:
        # 	Power getter
        # Return:
        # 	Float, containing the power of the weapon
        def power
            return @POWER
        end

        def to_s
            return "#{@name}"
        end
    end

    # Types of weapons available on our game
    LASER =		Type.new(2.0, "LASER")
    MISSILE =	Type.new(3.0, "MISSILE")
    PLASMA =	Type.new(4.0, "PLASMA")
end # enum WeaponType

end # module Deepspace
    
