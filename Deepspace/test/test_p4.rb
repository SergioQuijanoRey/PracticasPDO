#!/usr/bin/env ruby

require_relative "../lib/NumericDamage.rb"

class TestP4
    def self.testNumericDamage
        puts "Testing NumericDamage"
        puts "================================================================================"
        
        test_numeric = Deepspace::NumericDamage.new(3, 4)
        puts "The created numeric damage is #{test_numeric}"
        puts ""

        puts "Creating a copy of the damage:"
        test_numeric_copy = Deepspace::NumericDamage.copy(test_numeric)
        puts "The copy is: #{test_numeric_copy}"
        puts ""

        puts "The UI version of the damage is: #{test_numeric.getUIversion}"
        puts ""

        puts "Our damage hasNoEffect: #{test_numeric.hasNoEffect}"
        puts "Empty damagae hasNoEffect: #{Deepspace::NumericDamage.new(0, 0).hasNoEffect}"
        puts ""

        puts "Testing adjust"
        weapons = []
        weapons << 2
        weapons << 2
        shields = []
        shields << 2
        puts "The adjusted damage is #{test_numeric.adjust(shields, weapons)}"
        puts ""

        puts "Discarding shields"
        5.times do |i|
            test_numeric.discardShieldBooster
            puts "\tState: #{test_numeric}"
        end

        puts "Discarding weapons"
        5.times do |i|
            test_numeric.discardWeapon("nothing")
            puts "\tState: #{test_numeric}"
        end
    end

    def self.main
        self.testNumericDamage
    end
end

if $0 == __FILE__
    TestP4.main
end
