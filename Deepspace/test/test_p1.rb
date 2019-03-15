#!/usr/bin/env ruby

# WIP -- Need to develop unit testing
# WIP -- Preguntar si tenemos libertad para desarrollar los test

# Class for holding main program
class TestP1
	def initalize
	end

	def main
		# Testing class Loot
		puts "TESTING CLASS: Loot\n"
		puts "Creating Loot instance with the following parameters:"
		loot_test = Loot.new(2, 2, 2, 2, 2)
		puts "Instance created successfully"
		puts "Getters testing:"
		puts "    nSupplies=#{loot_test.nSupplies}"
		puts "    nWeapons=#{loot_test.nWeapons}"
		puts "    nShields=#{loot_test.nShields}"
		puts "    nHangars=#{loot_test.nHangars}"
		puts "    nMedals=#{loot_test.nMedals}"
		puts "\n\n"
		
		# Testing class SuppliesPackage
		puts "TESTING CLASS: SuppliesPackage\n"
		puts "Creating SuppliesPackage instance with the following parameters:"
		supplies_test = SuppliesPackage.new(2, 2, 2)
		puts "Instance created successfully"
		puts "Getters testing:"
		puts "    ammoPower=#{supplies_test.ammoPower}"
		puts "    fuelUnits=#{supplies_test.fuelUnits}"
		puts "    shieldPower=#{supplies_test.shieldPower}"
		puts "Creating SuppliesPackage instance as duplicate of previous instance..."
		supplies_test2 = SuppliesPackage.new(supplies_test)
		puts "Instance created successfully"
		if (supplies_test.ammoPower == supplies_test2.ammoPower) &&
		   (supplies_test.fuelUnits == supplies_test2.fuelUnits) &&
		   (supplies_test.shieldPower == supplies_test2.shieldPower)
			puts "Correct copy"			
		else
			puts "The copy is incorrect"
		end
		puts "\n\n"

		# Testing class ShieldBooster
		puts "TESTING CLASS: ShieldBooster\n"
		puts "Creating ShieldBooster instance with the following parameters:"
		shieldbooster_test = ShieldBooster.new("shield_test", 56.4, 2)
		puts "Instance created successfully"
		puts "Getters testing:"
		puts "    boost=#{shieldbooster_test.boost}"
		puts "    uses=#{shieldbooster_test.uses}"
		puts "Creating ShieldBooster instance as duplicate of previous instance..."
		shieldbooster_test2 = ShieldBooster.new(shieldbooster_test)
		puts "Instance created successfully"
		if (shieldbooster_test.boost == shieldbooster_test2.boost) &&
		   (shieldbooster_test.uses == shieldbooster_test2.uses)
			puts "Correct copy"			
		else
			puts "The copy is incorrect"
		end
		puts "Setters testing:"
		puts "Executing useIt function ---WIP--- times:"
		for i in 0..5
			puts "useIt return=#{shieldbooster_test.useIt}"
		puts "\n\n"

		# Testing class Weapon
		# --WIP--

		# Testing class Dice
		# --WIP--
	end
end

# Launching tests
test = TestP1.new
test.main()