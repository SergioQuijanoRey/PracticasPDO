/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deepspace;

import java.util.Random;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class TestP2 {
    
    private static Random rand = new Random();
    
    public static void puts(String message) {
        System.out.println(message);
    }
    
    public static void main(String[] args) {
        puts("TEST CLASS: Hangar");
        puts("================================================================================");
	for ( int i = 0; i < 2; i++ ) {
            int maxElements = rand.nextInt(10);
            puts("Creating Hangar("+maxElements+")");
            Hangar hangar_test = new Hangar(maxElements);
            puts("Result: " + hangar_test);
            puts("Adding seven different weapons");
            for ( int j = 0; j < 7; j++ ) {
                String name = "certainWeapon" + j;
                WeaponType[] types = {WeaponType.LASER, WeaponType.MISSILE, WeaponType.PLASMA};
                WeaponType type = types[rand.nextInt(3)];
                int uses = rand.nextInt(10);
                Weapon weapon = new Weapon(name, type, uses);
                puts("Trying to add: " + weapon.toString());
                if ( hangar_test.addWeapon(weapon) == false )
                    puts("\t"+name+" cannot be added on iteration "+j);
                else
                    puts("\t"+name+" can be added on iteration "+j);
            }
            
            puts("Hangar state is: " + hangar_test.toString());
            puts("Removing two weapons");
            for ( int j = 0; j < 2; j++ ) {
                Weapon removed_weapon = hangar_test.removeWeapon(0);
                puts("\tWeapon removed: " + removed_weapon.toString());
            }
            
            puts("Hangar state is:\n\t" + hangar_test.toString());
            
            puts("Adding seven different shield boosters");
            
            for ( int j = 0; j < 7; j++ ) {
                String name = "certainShieldBooster" + j;
                float boost = rand.nextInt(9) + rand.nextFloat();
                int uses = rand.nextInt(10);
                ShieldBooster shield = new ShieldBooster(name, boost, uses);
                puts("Trying to add booster: " + shield.toString());
                if ( hangar_test.addShieldBooster(shield) ==  false )
                    puts("\t" + name + " cannot be added on iteration " + j);
                else
                    puts("\t" + name + " can be added on iteration " + j);
            }
            
            puts("Hangar state is:\n\t" + hangar_test.toString());
            
            puts("Removing two shields");
            
            for ( int j=0; j < 2; j++ ) {
                ShieldBooster removed_shield = hangar_test.removeShieldBooster(0);
                puts("\tShield removed: "+removed_shield.toString());
            }
            
            puts("Testing getters:");
			puts("\tWeapons: " + hangar_test.getWeapons().toString());
			puts("\tShields: "+hangar_test.getShieldBoosters().toString());
			puts("\tMax elements: "+hangar_test.getMaxElements());
			puts("--------------------------------------------------------------------------------");
            }
    }
                
/*
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
			ui_damage_test = numeric_damage_test.getUIVersion
			puts "UI version: #{ui_damage_test}"
			puts ""

			#WIP mianfg -- he llegado hasta aquí
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
    }
*/
}
