#encoding:utf-8

require_relative "../lib/SpaceStation"
require_relative "../lib/Hangar"
require_relative "../lib/Weapon"
require_relative "../lib/enums"
require_relative "../lib/ShieldBooster"
require_relative "../lib/Damage"

module Test
    class Examen3
        def principal
            supplies = Deepspace::SuppliesPackage.new(2, 5, 3)
            station = Deepspace::SpaceStation.new("Miguel Ángel", supplies)
            puts "(2.a) #{station}"

            hangar = Deepspace::Hangar.new(3)
            station.receiveHangar(hangar)
            puts "(2.b) #{station}"

            weapon = Deepspace::Weapon.new("Espada", Deepspace::WeaponType::LASER, 1)
            station.receiveWeapon(weapon)
            puts "(2.c) #{station}"

            3.times do
                station.receiveWeapon(weapon)
            end
            puts "(2.d) #{station}"

            2.times do
                station.mountWeapon(0)
            end
            puts "(2.e) #{station}"

            booster = Deepspace::ShieldBooster.new("Escudo", 1.5, 2)
            station.receiveShieldBooster(booster)
            puts "(2.f) #{station}"

            2.times do
                station.mountShieldBooster(0)
            end
            puts "(2.g) #{station}"

            station.mountShieldBooster(1)
            puts "(2.h) #{station}"

            weapons = [Deepspace::WeaponType::PLASMA, Deepspace::WeaponType::LASER, Deepspace::WeaponType::MISSILE]
            damage = Deepspace::Damage.newSpecificWeapons(weapons, 3)

            puts "(3.b) #{damage}"


        end
    end

    class Examen
        @@contador = 0
        def self.principal
            12.times do
            puts "Before: #{@@contador}"
            contabilizar
            puts "After: #{contador}"
            end

            puts "EJ2"
            puts Deepspace::EnumeradoExamen::CUADRADO
            arr = [Deepspace::EnumeradoExamen::CIRCULO, Deepspace::EnumeradoExamen::CUADRADO, Deepspace::EnumeradoExamen::HEXAGONO]
            puts arr
            mag_med = arr.at(0).getMagnitud + arr.at(1).getMagnitud + arr.at(2).getMagnitud
            mag_med /=3.0
            puts mag_med
        end

        def self.contador
            @@contador
        end

        def self.contabilizar
            @@contador += 1
            if @@contador > 10
                @@contador = 10
            end
        end
    end
end

test = Test::Examen3.new
test.principal

puts "EXAMEN"
Test::Examen.principal