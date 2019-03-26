#!/usr/bin/env ruby
#encoding:utf-8

require_relative "../lib/Hangar.rb"

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

		end
end

# Launching the tests
test = TestP2.new
test.main()
