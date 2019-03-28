#!/usr/bin/env ruby
#enconding:utf-8

require_relative '../lib/Loot'
require_relative '../lib/SuppliesPackage'
require_relative '../lib/Weapon'
require_relative '../lib/Dice'
require_relative '../lib/ShieldBooster'
require_relative '../lib/enums'

include Deepspace

# Class for holding main program
class TestP1
	def self.main
		# Testing class Loot
		puts "TESTING CLASS: Loot"
		puts "================================================================================"

		3.times do
			nSupplies = rand(10)
			nWeapons = rand(10)
			nShields = rand(10)
			nHangars = rand(10)
			nMedals = rand(10)
			puts "Creating Loot.new(#{nSupplies}, #{nWeapons}, #{nShields}, #{nHangars}, #{nMedals})"
			loot_test = Loot.new(nSupplies, nWeapons, nShields, nHangars, nMedals)
			puts "Result: #{loot_test.to_s}"
			puts "--------------------------------------------------------------------------------"		end
		puts ""
			
		# Testing class SuppliesPackage
		puts "TESTING CLASS: SuppliesPackage"
		puts "================================================================================"
		3.times do
			ammoPower = rand(10)
			fuelUnits = rand(10)
			shieldPower = rand(10)
			puts "Creating SuppliesPackage(#{ammoPower}, #{fuelUnits}, #{shieldPower})"
			supplies_test = SuppliesPackage.new(ammoPower, fuelUnits, shieldPower)
			puts "Result: #{supplies_test.to_s}"
			puts "Creating SuppliesPackage instance as duplicate of previous instance..."
			supplies_test2 = SuppliesPackage.newCopy(supplies_test)
			puts "Result: #{supplies_test2.to_s}"
			puts "--------------------------------------------------------------------------------"
		end
		puts ""

		# Testing class ShieldBooster
		puts "TESTING CLASS: ShieldBooster"
		puts "================================================================================"
		3.times do
			name = "shieldTest"
			boost = rand(9) + rand()
			uses = rand(10)
			puts "Creating ShieldBooster(\"shield_test\", #{boost}, #{uses})"
			shieldbooster_test = ShieldBooster.new(name, boost, uses)
			puts "Result: #{shieldbooster_test.to_s}"
			puts "Creating a copy of previous shieldbooster"
			shieldbooster_test2 = ShieldBooster.newCopy(shieldbooster_test)
			puts "Result: #{shieldbooster_test2.to_s}"
			puts "Setters testing:"
			3.times do
				puts "useIt returns #{shieldbooster_test.useIt}"
			end
			puts "Checking for linked values between copies"
			puts "Original state: #{shieldbooster_test.to_s}"
			puts "Copy state: #{shieldbooster_test2.to_s}"
			puts "--------------------------------------------------------------------------------"
		end
		puts ""

		# Testing class Weapon
		puts "TESTING CLASS: Weapon"
		puts "================================================================================"
		weaponType_arr = [WeaponType::LASER, WeaponType::MISSILE, WeaponType::PLASMA]
		3.times do
			name = "weaponTest"
			type = weaponType_arr[rand(3)]
			uses = rand(10)
			puts "Creating Weapon(#{name}, #{type}, #{uses})"
			weapon_test = Weapon.new(name, type, uses)
			puts "Result: #{weapon_test.to_s}"
			puts "Creating a copy of the weapon"
			weapon_test2 = Weapon.newCopy(weapon_test)
			puts "Result: #{weapon_test2.to_s}"
			puts "Testing setters"
			3.times do
				puts "useIt returns #{weapon_test.useIt}"
			end
			puts "Checking for linked values between copies"
			puts "Original state: #{weapon_test.to_s}"
			puts "Copy state: #{weapon_test2.to_s}"
			puts "--------------------------------------------------------------------------------"
		end
		puts ""

		# Testing class Dice
		puts "TESTING CLASS: Dice"
		puts "================================================================================"

		# Array of dices
		dices = []

		# Hundred dices are added to values
		for i in 0..99
				dices << Dice.new
		end
		puts "Hundred dices initialized succesfully!"

		puts "Testing Dice.initWithNHangars()"
		first_case = 0
		second_case = 0

		for dice in dices
			value = dice.initWithNHangars
			if value == 0
				first_case = first_case + 1
			elsif value == 1
				second_case = second_case + 1
			else
				puts "ERROR, invalid state on Dice.initWithNHangars() testing"
			end
		end

		puts "First case probability: #{first_case}%"
		puts "Second case probability: #{second_case}%"
		puts ""

		puts "Testing Dice.initWIthNWeapons()"
		first_case = 0
		second_case = 0
		third_case = 0
		for dice in dices
			val = dice.initWithNWeapons()
			if val == 1
				first_case = first_case + 1
			elsif val == 2
				second_case = second_case + 1
			elsif val == 3
				third_case = third_case + 1
			else
				puts "ERROR, invalid state on Dice.initWIthNWeapons()"
			end
		end

		puts "First case probability: #{first_case}%"
		puts "Second case probability: #{second_case}%"
		puts "Third case probability: #{third_case}%"
		puts ""

		puts "Testing Dice.initWithNShields()"
		first_case = 0
		second_case = 0

		for dice in dices
			val = dice.initWithNShields
			if val == 0
				first_case = first_case + 1
			elsif val == 1
				second_case = second_case + 1
			else
				puts "ERROR, invalid state on Dice.initWithNShields()"
			end
		end

		puts "First case probability: #{first_case}%"
		puts "Second case probability: #{second_case}%"
		puts ""

		puts "Testing Dice.whoStarts()"
		start_values = Array.new(10, 0)

		for dice in dices
			start_value = dice.whoStarts(10)
			start_values[start_value] = start_values[start_value] + 1
		end

		puts "Percentages for 10 playes:"
		for index in 0..9
			puts "Percentage of #{index}: #{start_values[index]}%"
		end
		puts ""

		puts "Testing DIce.firstShot()"
		first_case = 0
		second_case = 0
		for dice in dices
			val = dice.firstShot
			if val == GameCharacter::SPACESTATION
				first_case = first_case + 1
			elsif val == GameCharacter::ENEMYSTARSHIP
				second_case = second_case + 1
			else
				puts "ERROR, invalid state on Dice.firstShot()"
			end
		end

		puts "First case probability: #{first_case}%"
		puts "Second case probability: #{second_case}%"
		puts ""

		puts "Testing Dice.spaceStationMoves(0.60)"
		first_case = 0
		second_case = 0
		for dice in dices
			val = dice.spaceStationMoves(0.60)
			if val == true
				first_case = first_case + 1
			else
				second_case = second_case + 1
			end
		end

		puts "First case probability: #{first_case}%"
		puts "Second case probability: #{second_case}%"
		puts ""

	end
end

# Launching tests
TestP1.main
