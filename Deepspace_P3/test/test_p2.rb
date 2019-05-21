#!/usr/bin/env ruby
#encoding:utf-8

require_relative "../lib/Hangar"
require_relative "../lib/EnemyStarShip"
require_relative "../lib/GameUniverse"
require_relative "../lib/Damage"
require_relative "../lib/SpaceStation"
require_relative "../lib/HangarToUI"

include Deepspace

# Class to make the tests
class TestP2
	def self.main
		puts "TEST CLASS: Hangar"
		puts "================================================================================"
		2.times do
			maxElements = rand(10)
			puts "Creating Hangar.new(#{maxElements})"
			hangar_test = Hangar.new(maxElements)
			puts "Result: #{hangar_test}"
			puts "Adding seven different weapons"
			i = 0
			7.times do
				i += 1
				name = "certainWeapon#{i}"
				type = [WeaponType::LASER, WeaponType::MISSILE, WeaponType::PLASMA][rand(3)]
				uses = rand(10)
				weapon = Weapon.new(name, type, uses)
				puts "Trying to add: #{weapon.to_s}"
				if hangar_test.addWeapon(weapon) == false
					puts "\t#{name} cannot be added on iteration #{i}"
				else
					puts "\t#{name} can be added on iteration #{i}"
				end
			end
			puts "Hangar state is: #{hangar_test}"
			puts "Removing two weapons"
			2.times do 
				removed_weapon = hangar_test.removeWeapon(0)
				puts "\tWeapon removed: #{removed_weapon}"
			end
			puts "Hangar state is:"
			puts "\t#{hangar_test}"

			puts "Adding seven different shield boosters"
			i = 0
			7.times do
				i += 1
				name = "certainShieldBooster#{i}"
				boost = rand(9) + rand()
				uses = rand(10)
				shield = ShieldBooster.new(name, boost, uses)
				puts "Trying to add booster: #{shield.to_s}"
				if hangar_test.addShieldBooster(shield) == false
					puts "\t#{name} cannot be added on iteration #{i}"
				else
					puts "\t#{name} can be added on iteration #{i}"
				end
			end
			puts "Hangar state is:"
			puts "\t#{hangar_test}"

			puts "Removing two shields"
			2.times do
				removed_shield = hangar_test.removeShieldBooster(0)
				puts "\tShield removed: #{removed_shield}"
			end
		
			puts "Testing getters:"
			puts "\tWeapons: #{hangar_test.weapons}"
			puts "\tShields: #{hangar_test.shieldBoosters}"
			puts "\tMax elements: #{hangar_test.maxElements}"
			puts "\tSpace available: #{hangar_test.spaceAvailable}"
			puts "\tUI version: #{hangar_test.getUIversion}"
			puts "--------------------------------------------------------------------------------"
		end

		puts "TEST CLASS: Damage"
		puts "================================================================================"

		3.times do
			puts "Creating both types of damage..."
			nWeapons = rand(10)
			nShields = rand(10)
			puts "Creating Damage.newNumericWeapons(#{nWeapons}, #{nShields})"
			numeric_damage_test = Damage.newNumericWeapons(nWeapons, nShields)
			weapons = []
			4.times do
				weapons << [WeaponType::LASER, WeaponType::MISSILE, WeaponType::PLASMA][rand(3)]
			end
			nShields = rand(10)
			puts "Creating Damage.newSpecificWeapons(#{weapons}, #{nShields})"
			type_damage_test = Damage.newSpecificWeapons(weapons, nShields)

			puts "Numeric Damage: \n\t#{numeric_damage_test}"
			puts "Specific damage: \n\t#{type_damage_test}"
			puts ""

			puts "Creating a copy of Numeric Damage"
			copy_test = Damage.newCopy(numeric_damage_test)
			puts "Copy Damage: \n\t#{copy_test}"
			puts ""

			puts "Creating UI version"
			ui_damage_test = numeric_damage_test.getUIversion
			puts "UI version: #{ui_damage_test}"
			puts ""

			puts "Adjusting the Specific Damage"
			srink_damage_test = type_damage_test.adjust([WeaponType::LASER, WeaponType::MISSILE], ["test", "this does nothing"])
			puts "\tAdjusted Damage: #{srink_damage_test}"
			puts "\tAdjusted Damage Weapons: #{srink_damage_test.weapons}"
			puts "" 

			puts "Testing hasNoEffect"
			puts "Has no effect: #{numeric_damage_test.hasNoEffect}"
			puts ""

			puts "Testing discardShieldBooster()"
			10.times do
					numeric_damage_test.discardShieldBooster
			end
			puts "Has no effect: #{numeric_damage_test.hasNoEffect}"
			puts ""

			puts "Testing discardWeapon"
			10.times do
					numeric_damage_test.discardWeapon("trash")
			end
			puts "Has no effect: #{numeric_damage_test.hasNoEffect}"
			puts ""
		end

		puts "TEST CLASS: EnemyStarShip"
		puts "================================================================================"
		
		puts "Creating an enemy starship"
		enemy_test = EnemyStarShip.new("enemy tank ship", 1.123, 2.123124, Loot.new(1,2,3,4,5), Damage.newNumericWeapons(3,3))
		puts "Enemy starship: #{enemy_test}"
		puts ""

		puts "Creating a copy of the enemy starship"
		enemy_copy = EnemyStarShip.newCopy(enemy_test)
		puts "Copy: #{enemy_copy}"
		puts ""

		puts "Creating an UI version of the ship"
		enemy_UI = enemy_test.getUIversion
		puts "UI version: #{enemy_UI}"
		puts ""

		puts "Protecting: #{enemy_test.protection}"
		puts ""

		puts "Firing: #{enemy_test.fire}"
		puts ""

		puts "Testing EnemyStarShip.receiveShot()"
		10.times do |power|
				puts "\tAttacking with #{power} power!"
				result = enemy_test.receiveShot(power)
				puts "\tResult: #{result}"
		end

		puts "TEST CLASS: Space Station"
		puts "================================================================================"

		puts "Creating an Space Station"
		space_test = SpaceStation.new("my tanky space station", SuppliesPackage.new(14, 12, 40))
		puts "Space Station: #{space_test}"
		puts ""

		puts "Testing speed"
		puts "Speed: #{space_test.speed}"
		puts "Space station state: #{space_test}"
		puts "Valid state: #{space_test.validState}"
		puts ""

		puts "Testing move"
		space_test.move
		puts "Space station state: #{space_test}"

		puts "Testing receive Hangar"
		space_test.receiveHangar(Hangar.new(5))
		puts "Space station: #{space_test}"
		puts ""

		puts "Testing UI version"
		puts "UI: #{space_test.getUIversion}"
		puts ""


		puts "TEST CLASS: GameUniverse"
		puts "================================================================================"
		puts "Creating a Game universe"
		universe_test = GameUniverse.new
		puts "Game Universe: #{universe_test}"
		puts ""

		puts "Testing getters:"
		puts "\tGame State: #{universe_test.gameState}"
		puts ""

		puts "Have a winner: #{universe_test.haveAWinner}"
		puts ""
	end
end

# Launching the tests
TestP2.main
