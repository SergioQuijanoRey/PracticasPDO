/**
 * @author Sergio Quijano Rey
 * @brief File to test first part of the Game
 * */

package deepspace;

public class TestP1 {
    public static void main(String[] args) {
        // Testing weapon type enumerated
        //======================================================================
        System.out.println("Testing WeaponType");
        System.out.println("=====================================================");
        
        WeaponType laser = WeaponType.LASER;
        WeaponType missile = WeaponType.MISSILE;
        WeaponType plasma = WeaponType.PLASMA;

        System.out.println("The power of laser is " + laser.getPower());
        System.out.println("The power of missile is " + missile.getPower());
        System.out.println("The power of plasma is " + plasma.getPower());
        System.out.println();
        System.out.println();

        // Testing Loot class
        //======================================================================
        System.out.println("Testing Loot");
        System.out.println("=======================================================");
        Loot test_loot = new Loot(10, 20, 30, 40, 50, false, false);
        System.out.print("The created loot is: ");
        System.out.println(test_loot.toString());

        // Testing getters
        System.out.println("Supplies: " + test_loot.getNSupplies()); 
        System.out.println("Weapons: " + test_loot.getNWeapons());
        System.out.println("Shields: " + test_loot.getNShields());
        System.out.println("Hangars: " + test_loot.getNHangars());
        System.out.println("Medals: " + test_loot.getNMedals());
        
        System.out.println();
        System.out.println();
        
        // Testing SuppliesPackage class
        //======================================================================
        System.out.println("Testing SuppliesPackage");
        System.out.println("=======================================================");
        SuppliesPackage test_supplies = new SuppliesPackage((float)1.20, (float)2.30, (float)3.40);
        System.out.println("The supplies package created is " + test_supplies.toString());
        
        // Testing copy constructor
        System.out.println("Testing copy constructor");
        SuppliesPackage test_supplies_copy = new SuppliesPackage(test_supplies);
        System.out.println("The copy is " + test_supplies_copy.toString());
        
        System.out.println();
        System.out.println();

        // Testing ShieldBooster
        //======================================================================
        System.out.println("Testing ShieldBooster");
        System.out.println("=======================================================");
        ShieldBooster test_shield = new ShieldBooster("Cool shield booster", (float)2.13, 4);
        System.out.println("The shieldbooster created is " + test_shield.toString());

        // Testing copy constructor
        ShieldBooster test_shield_copy = new ShieldBooster(test_shield);
        System.out.println("A copy of the shield is " + test_shield_copy.toString());

        // Testing useIt
        float current_boost;
        for(int i = 0; i < 5; i++){
            current_boost = test_shield.useIt();

            System.out.print("Iteration " + i + ": ");
            System.out.print("boost = " + current_boost + " | uses left = " + test_shield.getUses());
            System.out.println();
        }

        // Testing the copy
        System.out.println("State of the original shield: " + test_shield.toString());
        System.out.print("State of the copied shield: " + test_shield_copy.toString());
        
        System.out.println("");
        System.out.println("");
     
        // Testing Weapon class
        //======================================================================
        System.out.println("Testing Weapon class");
        System.out.println("=======================================================");
        Weapon test_weapon = new Weapon("Powerful laser", WeaponType.LASER, 3);
        System.out.println("The weapon created is " + test_weapon.toString());

        // Testing copy constructor
        Weapon test_weapon_copy = new Weapon(test_weapon);
        System.out.println("The copy of the weapon is " + test_weapon_copy.toString());
        
        // Testing getters
        System.out.println("The type of the weapon is " + test_weapon.getType());
        System.out.println("The uses left are " + test_weapon.getUses());
        System.out.println("The power of the weapon is " + test_weapon.power());

        // Testing useIt
        for(int i = 0; i < 5; i++){
            float current_power = test_weapon.useIt();

            System.out.println("Iteration " + i + ": Uses: " + test_weapon.getUses() + " | Power: " + current_power);
        }

        // Testing the copy
        System.out.println("Current state of original weapon: " + test_weapon.toString());
        System.out.println("Current state of the copied weapon: " + test_weapon_copy.toString());
        
        System.out.println();
        System.out.println();

        // Testing Dice Class
        //======================================================================
        System.out.println("Testing Dice");
        System.out.println("=======================================================");
        Dice test_dice = new Dice();

        // Testing initWithNHangars
        System.out.println("Testing initWithNHangars");
        int results_hangars[] = new int[2];
        for(int i = 0; i < 100; i++){
            results_hangars[test_dice.initWithNHangars()] += 1;
        }
        System.out.println("\t0: " + results_hangars[0] + "%");
        System.out.println("\t1: " + results_hangars[1] + "%");

        // Testing initWithNWeapons
        System.out.println("Testing initWithNWeapons");
        int results_weapons[] = new int[3];
        for(int i = 0; i < 100; i++){
            results_weapons[test_dice.initWithNWeapons()-1] += 1;
        }
        System.out.println("\t1: " + results_weapons[0] + "%");
        System.out.println("\t2: " + results_weapons[1] + "%");
        System.out.println("\t3: " + results_weapons[2] + "%");

        // Testing initWIthNShields
        System.out.println("Testing initWIthNShields");
        int results_shields[] = new int[2];
        for(int i = 0; i < 100; i++){
            results_shields[test_dice.initWithNShields()] += 1;
        }
        System.out.println("\t0: " + results_shields[0] + "%");
        System.out.println("\t1: " + results_shields[1] + "%");

        // Testing whoStarts
        System.out.println("Testing who starts for 4 players");
        int results_start[] = new int[4];
        for(int i = 0; i < 100; i++){
            results_start[test_dice.whoStarts(4)] += 1;
        }
        System.out.println("\t0: " + results_start[0] + "%");
        System.out.println("\t1: " + results_start[1] + "%");
        System.out.println("\t2: " + results_start[2] + "%");
        System.out.println("\t3: " + results_start[3] + "%");

        // Testing firstShot
        System.out.println("Testing firstShot");
        int results_character[] = new int[2];
        for(int i = 0; i < 100; i++){
            if(test_dice.firstShot() == GameCharacter.SPACESTATION){
                results_character[0] += 1;
            }else{
                results_character[1] += 1;
            }
        }
        System.out.println("\tGameCharacter.SPACESTATION: " + results_character[0] + "%");
        System.out.println("\tGameCharacter.ENEMYSTARSHIP: " + results_character[1] + "%");

        // Testing spaceStationMoves
        System.out.println("Testing spaceStationMoves with 0.612 speed");
        int results_moves[] = new int[2];
        for(int i = 0; i < 100; i++){
            if(test_dice.spaceStationMoves((float)0.612)){
                results_moves[0] += 1;
            }else{
                results_moves[1] += 1;
            }
        }
        System.out.println("\tAvoids the shoot: " + results_moves[0] + "%");
        System.out.println("\tReceives the shoot: " + results_moves[1] + "%");
    }
}
