#!/usr/bin/env ruby

require_relative '../lib/Loot.rb'
require_relative '../lib/SuppliesPackage.rb'
require_relative '../lib/ShieldBooster.rb'
require_relative '../lib/Weapon.rb'
require_relative '../lib/enums.rb'

# WIP -- Need to develop unit testing
# WIP -- Preguntar si tenemos libertad para desarrollar los test

# Class for holding main program
class TestP1
	def initalize
	end

	def main
		# Testing class Loot
		puts "TESTING CLASS: Loot"
		puts "================================================================================"
		puts "Creating Loot.new(2, 2, 2, 2, 2)"
		loot_test = Loot.new(2, 2, 2, 2, 2)
		puts "Result: #{loot_test.to_s}"
		 
		# Testing class SuppliesPackage
		puts "TESTING CLASS: SuppliesPackage"
		puts "================================================================================"
		puts "Creating SuppliesPackage(2, 2, 2)"
		supplies_test = SuppliesPackage.new(2, 2, 2)
		puts "Result: #{supplies_test.to_s}"
		puts "Creating SuppliesPackage instance as duplicate of previous instance..."
		supplies_test2 = SuppliesPackage.newCopy(supplies_test)
		puts "Result: #{supplies_test2.to_s}"

		# Testing class ShieldBooster
		puts "TESTING CLASS: ShieldBooster"
		puts "================================================================================"
		puts "Creating ShieldBooster(\"shield_test\", 56.4, 2)"
		shieldbooster_test = ShieldBooster.new("shield_test", 56.4, 2)
		puts "Result: #{shieldbooster_test.to_s}"
		puts "Creating a copy of previous shieldbooster"
		shieldbooster_test2 = ShieldBooster.newCopy(shieldbooster_test)
		puts "Result: #{shieldbooster_test2.to_s}"
		puts "Setters testing:"
		for i in 0..3
				puts "useIt returns #{shieldbooster_test.useIt}"
		end
		puts "Checking for linked values between copies"
		puts "Original state: #{shieldbooster_test.to_s}"
		puts "Copy state: #{shieldbooster_test2.to_s}"

		# Testing class Weapon
		puts "TESTING CLASS: Weapon"
		puts "Creating Weapon(\"weapon1\", LASER, 3)"
		weapon_test = Weapon.new("weapon1", WeaponType::LASER, 3)
		puts "Result: #{weapon_test.to_s}"

		# # Testing class Dice
		# # --WIP--
		# end
	end
end

# Launching tests
test = TestP1.new
test.main()
