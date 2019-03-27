#!/usr/bin/env ruby
#encoding:utf-8

require_relative "../lib/Hangar.rb"
require_relative "../lib/EnemyStarShip.rb"
require_relative "../lib/GameUniverse.rb"
require_relative "../lib/Damage.rb"
require_relative "../lib/SpaceStation.rb"

include Deepspace

# Class to make the tests
class TestP2
		def main
				puts "TEST CLASS: Hangar"
				puts "================================================================================"

				hangar_test = Hangar.new(3)
				puts "Hangar created:"
				puts "\t#{hangar_test}"

				puts "Adding 7 times same weapon"
				weapon = Weapon.new("Laser", WeaponType::LASER, 10)
				7.times do |i|
						if hangar_test.addWeapon(weapon) == false
								puts "\tWeapon cannot be added on iteration #{i}"
						else
								puts "\tWeawpon can be added on iteration #{i}"
						end
				end
				puts "Hangar state is:"
				puts "\t#{hangar_test}"

				puts "Removing two weapons"
				2.times do 
						removed_weapon = hangar_test.removeWeapon(0)
						puts "\tWeapon removed: #{removed_weapon}"
				end
				puts "Hangar state is:"
				puts "\t#{hangar_test}"

				puts "Adding 7 times same shieldBooster"
				shield = ShieldBooster.new("shield", 0.78, 10)
				7.times do |i|
						if hangar_test.addShieldBooster(shield) == false
								puts "\tShield cannot be added on iteration #{i}"
						else
								puts "\tShield can be added on iteration #{i}"
						end
				end
				puts "Hangar state is:"
				puts "\t#{hangar_test}"

				puts "Removing two shields"
				2.times do
						removed_shield = hangar_test.removeShieldBooster(0)
						puts "\tRemoved shield: #{removed_shield}"
				end

				puts "Testing getters:"
				puts "\tWeapons: #{hangar_test.weapons}"
				puts "\tShields: #{hangar_test.shieldBoosters}"
				puts "\tMax Elements: #{hangar_test.maxElements}"
				puts "\tSpace available: #{hangar_test.spaceAvailable}"
				puts "\tUI version: #{hangar_test.getUIversion}"

				puts "TEST CLASS: Damage"
				puts "================================================================================"

				puts "Creating both types of damage"
				numeric_damage_test = Damage.newNumericWeapons(3, 10)
				type_damage_test = Damage.newSpecificWeapons([WeaponType::LASER, WeaponType::MISSILE, WeaponType::PLASMA], 3)
				copy_test = Damage.newCopy(numeric_damage_test)

				puts "Numeric Damage: \n\t#{numeric_damage_test}"
				puts "Specific damage: \n\t#{type_damage_test}"
				puts ""

				puts "Creating a copy of Numeric Damage"
				copy_test = Damage.newCopy(numeric_damage_test)
				puts "Copy Damage: \n\t#{copy_test}"
				puts ""

				puts "Creating UI version"
				ui_damage_test = numeric_damage_test.getUIVersion
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
				enemy_UI = enemy_test.getUIVersion
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

				# WIP -- Not tested properly -- Practica 3
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
				puts "UI: #{space_test.getUIVersion}"
				puts ""


				# WIP -- Not tested properly -- Practica 3
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
test = TestP2.new
test.main()
