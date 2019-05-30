/**
 * @author Sergio Quijano Rey
 * @file TestP2.java
 * @brief Tests for all the code implemented at the second session
 * */

package deepspace;

import java.util.ArrayList;

class TestP2{
    public static void main(String[] args){
        // Testing hangar
        //======================================================================
        System.out.println("Testing Hangar");
        System.out.println("================================================================================");
       
        // Creating an hangar
        Hangar test_hangar = new Hangar(5);
        System.out.println("The created hangar is " + test_hangar.toString());

        // Creating a copy
        Hangar test_hangar_copy = new Hangar(test_hangar);
        System.out.println("The copy of the previous hangar is " + test_hangar_copy.toString());

        // Testing the UI Version 
        HangarToUI test_hangar_ui = test_hangar.getUIversion();
        System.out.println("The hangar UI Version is " + test_hangar_ui.toString());

        // Testing add weapon and add shield
        Weapon test_weapon = new Weapon("test weapon", WeaponType.LASER, 2);
        ShieldBooster test_shield = new ShieldBooster("test shield", (float)2.3123, 4);

        System.out.println("Adding some weapons and shields");
        for(int i = 0; i < 3; i++){
            if(test_hangar.addWeapon(test_weapon)){
                System.out.println("Weapon added succesfully to test_weapon");
            }else{
                System.out.println("Weapon failed to add to test_weapon");
            }
        }

        for(int i = 0; i < 3; i++){
            if(test_hangar.addShieldBooster(test_shield)){
                System.out.println("ShieldBooster added succesfully to test_weapon");
            }else{
                System.out.println("ShieldBooster failed to add to test_weapon");
            }
        }

        // We show that the copy is different from the original
        System.out.println("State of the original hangar: " + test_hangar.toString());
        System.out.println("State of the copy: " + test_hangar_copy.toString());

        // We remove a few weapons
        System.out.println("Removing two weapons");
        for(int i = 0; i < 2; i++){
            test_hangar.removeWeapon(0);
        }

        System.out.println("Removing two shields");
        for(int i = 0; i < 2; i++){
            test_hangar.removeShieldBooster(0);
        }

        System.out.println("State of the original hangar: " + test_hangar.toString());
        System.out.println("State of the copy: " + test_hangar_copy.toString());

        System.out.println("");
        System.out.println("");

        // Testing Damage
        //======================================================================
        System.out.println("Testing Damage");
        System.out.println("================================================================================");
        ArrayList<WeaponType> weapon_type_list = new ArrayList<WeaponType>(0);
        weapon_type_list.add(WeaponType.LASER);
        weapon_type_list.add(WeaponType.MISSILE);
        Damage test_damage_a = new NumericDamage(3, 2);
        Damage test_damage_b = new SpecificDamage(weapon_type_list, 2);
        System.out.println("First damage: " + test_damage_a.toString());
        System.out.println("Second damage: " + test_damage_b.toString());

        // Testing copies
        Damage test_damage_a_copy = test_damage_a.copy();
        Damage test_damage_b_copy = test_damage_b.copy();
        System.out.println("The copy of the first damage is: " + test_damage_a_copy);
        System.out.println("The copy of the second damage is: " + test_damage_b_copy);

        // Testing the adjust method
        System.out.println("TESTING Damage.adjust()");
        
        ArrayList<Weapon> weapons_to_adjust = new ArrayList<>(0);
        weapons_to_adjust.add(new Weapon("first weapon", WeaponType.LASER, 3));
        
        ArrayList<ShieldBooster> shields_to_adjust = new ArrayList<ShieldBooster>();
        shields_to_adjust.add(new ShieldBooster("shield to adjust", (float)2.3134, 4));        
        shields_to_adjust.add(new ShieldBooster("shield to adjust", (float)2.3134, 4));
 
        Damage adjusted = test_damage_a.adjust(weapons_to_adjust, shields_to_adjust);
        System.out.println("First adjusted damage is " + adjusted.toString());
        adjusted = test_damage_b.adjust(weapons_to_adjust, shields_to_adjust);
        System.out.println("Second adjusted damage is " + adjusted.toString());

        // Testing discard methods
        System.out.println("Discarding some weapons");
        for(int i = 0; i < 4; i++){
            System.out.println("Discarding " + i + " weapon on first damage");     
            test_damage_a.discardWeapon(test_weapon);
            System.out.println("Discarding " + i + " weapon on second damage");
            test_damage_b.discardWeapon(test_weapon);
        }

        System.out.println("Discarding some shields");
        for(int i = 0; i < 4; i++){
            System.out.println("Discarding " + i + " shield on first damage");
            test_damage_a.discardShieldBooster();
            System.out.println("Discarding " + i + " shield on second damage");
            test_damage_b.discardShieldBooster();
        }

        System.out.println("State of the first damage: " + test_damage_a.toString());
        System.out.println("State of the second damage: " + test_damage_b.toString());

        System.out.println("");
        System.out.println("");

        // Testing EnemyStarShip
        //======================================================================
        System.out.println("Testing EnemyStarShip");
        System.out.println("================================================================================");
        Damage test_damage = new NumericDamage(2, 3);
        Loot test_loot = new Loot(1, 2, 3, 4, 5, false, false);
        
        // Testing the constructor
        EnemyStarShip test_enemy = new EnemyStarShip("Enemy Test", (float)1.1, (float)2.2, test_loot, test_damage);
        System.out.println("The testing EnemyStarShip is " + test_enemy.toString());

        // Testing the copy constructor
        EnemyStarShip test_enemy_copy = new EnemyStarShip(test_enemy);
        System.out.println("The copy of the enemy is " + test_enemy_copy.toString());

        // Testing the UI version
        System.out.println("The UI version is " + test_enemy.getUIversion().toString());

        // Testing some shots
        System.out.println("The enemy is going to receive some shots");
        for(int i = 0; i < 10; i++){
            ShotResult shotResult = test_enemy.receiveShot(i);
            System.out.println("Receiving " + i + " power shot results in " + shotResult);
        }
        
        System.out.println("");
        System.out.println("");
    } 
}
