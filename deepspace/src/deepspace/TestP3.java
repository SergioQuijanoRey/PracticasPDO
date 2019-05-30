/**
 * @author Sergio Quijano Rey
 * @file TestP3.java
 * @brief File for testing the code written in the third session
 * */

package deepspace;

import java.util.ArrayList;

public class TestP3{
    /**
     * @brief Tests the SpaceStation class independently
     * */
    private static void testSpaceStation(){
        CardDealer dealer = CardDealer.getInstance();

        // Testing SpaceStation
        //======================================================================
        System.out.println("Testing SpaceStation");
	    System.out.println("================================================================================");

        SuppliesPackage test_supplies = new SuppliesPackage((float)1.11, (float)2.22, (float)3.33);
        SpaceStation test_station = new SpaceStation("My space station", test_supplies);
        System.out.println("The created SpaceStation is " + test_station.toString());
        System.out.println("The speed of the space station is " + test_station.getSpeed());
        System.out.println("The state of the space station is " + test_station.validState());
        System.out.println("The UI version of the station is " + test_station.getUIversion());
        System.out.println("");

        // Testing add loot
        System.out.println("Adding a loot to the station");
        Loot loot = new Loot(1, 1, 1, 1, 1, false, false);
        System.out.println("The loot is " + loot.toString());
        test_station.setLoot(loot);
        System.out.println("After adding the loot the station is " + test_station.toString());
        System.out.println("");

        // Testing set damage
        System.out.println("We add some elements and mount them in order to get a damage");
        loot = new Loot(1, 1, 1, 1, 1, false, false);
        test_station.setLoot(loot);
        test_station.mountShieldBooster(0);
        test_station.mountWeapon(0);
        System.out.println("State of the station: " + test_station);
        System.out.println("Adding some damage to the station");
        Damage damage = dealer.nextEnemy().getDamage();
        System.out.println("The damage to add is: " + damage.toString());
        test_station.setPendingDamage(damage);
        System.out.println("After adding the damage the state is " + test_station.toString());
        System.out.println("The state of the space station is " + test_station.validState());
        System.out.println("");

        // Testing mountShieldBooster
        System.out.println("Mounting a shieldBooster");
        test_station.mountShieldBooster(0);
        System.out.println("The state of the station is " + test_station.toString());
        System.out.println("");

        // Testing mountWeapon
        System.out.println("Mounting a weapon");
        test_station.mountWeapon(0);
        System.out.println("The state of the station is " + test_station.toString());
        System.out.println("");

        // Testing cleanUpMountedItems

        // Using the shield
        System.out.println("Using the shield until making it useless");
        if(test_station.getShieldBoosters() != null){
            if(test_station.getShieldBoosters().size() > 0 && test_station.getShieldBoosters().get(0) != null){
                while(test_station.getShieldBoosters().get(0).getUses() > 0){
                    test_station.getShieldBoosters().get(0).useIt();
                }
            
                System.out.println("State before cleaning the mounted items: " + test_station);
                test_station.cleanUpMountedItems();
                System.out.println("State after cleaning the mounted items: " + test_station);
                System.out.println("");
            }else{
                System.out.println("There was not a shield to use it");
                System.out.println("");
            }
        }else{
            System.out.println("There was not a shieldBoosters ArrayList to access");
            System.out.println("");
        }

        // Using the weapon
        System.out.println("Using the weapon until making it useless");
        if(test_station.getWeapons() != null){
        if(test_station.getWeapons().size() > 0 && test_station.getWeapons().get(0) != null){
            while(test_station.getWeapons().get(0).getUses() > 0){
                test_station.getWeapons().get(0).useIt();
            }
            System.out.println("State before cleaning the mounted items: " + test_station);
            test_station.cleanUpMountedItems();
            System.out.println("State after cleaning the mounted items: " + test_station);
            System.out.println("");
        }else{
            System.out.println("There was no weapon to use");
            System.out.println("");
        }
        }else{
            System.out.println("There was no weapon ArrayList to access");
            System.out.println();
        }

        // Testing discardShieldBooster, discardWeapon
        System.out.println("We add some elements and mount them in order to discard them");
        loot = new Loot(1, 1, 1, 1, 1, false, false);
        test_station.setLoot(loot);
        test_station.mountShieldBooster(0);
        test_station.mountWeapon(0);
        System.out.println("State of the station: " + test_station);
        System.out.println("Discarding a shieldBooster");
        test_station.discardShieldBooster(0);
        System.out.println("State of the station: " + test_station);
        System.out.println("Discarding a weapon");
        test_station.discardWeapon(0);
        System.out.println("State of the station: " + test_station);
        System.out.println("");

        // Testing discardShieldBoosterInHangar and discardWeaponInHangar
        System.out.println("We add some elements in order to discard them from the hangar");
        loot = new Loot(1, 1, 1, 1, 1, false, false);
        test_station.setLoot(loot);
        System.out.println("State of the station: " + test_station);
        System.out.println("Discarding a weapon from the hangar");
        test_station.discardWeaponInHangar(0);
        System.out.println("State of the station: " + test_station);
        System.out.println("Discardign a ShieldBooster from the hangar");
        test_station.discardShieldBoosterInHangar(0);
        System.out.println("State of the station: " + test_station);
        System.out.println("");

        // Testing space station actions
        System.out.println("Adding some elements and mounting them in order to make some actions");
        loot = new Loot(1, 1, 1, 1, 1, false, false);
        test_station.setLoot(loot);
        test_station.mountShieldBooster(0);
        test_station.mountWeapon(0);
        System.out.println("State of the station: " + test_station);
        System.out.println("Fire: " + test_station.fire());
        System.out.println("Protect: " + test_station.protection());
        System.out.println("State of the station: " + test_station);

        for(int i = 0; i < 10; i++){
            System.out.println("Result of receiving a shot of power " + i + ": " + test_station.receiveShot(i));
        }

        System.out.println("");
        System.out.println("");
    }

    /**
     * @brief Tests the GameUniverse class independently
     * */
    private static void testGameUniverse(){
        // Testing GameUniverse
        //======================================================================
        System.out.println("Testing GameUniverse");
        System.out.println("================================================================================");

        // Testing the constructor
        GameUniverse test_universe = new GameUniverse();
        System.out.println("The created GameUniverse is " + test_universe);
        System.out.println("The current state of the game is: " + test_universe.getState());
        System.out.println("");

        // Testing the init -- BUG al primer jugador no le da un Hangar
        System.out.println("Testing the init method for players Sergio and Miguel Angel");
        ArrayList<String> player_names = new ArrayList<String>();
        player_names.add("Sergio");
        player_names.add("Miguel Angel");
        System.out.println("The players are: " + player_names);
        test_universe.init(player_names);
        System.out.println("After starting the game the universe state is " + test_universe);
        System.out.println("The game universe UI version is " + test_universe.getUIversion().toString());
        System.out.println("");

        // Testing the state of the game and the haveAWinner
        System.out.println("The current state of the game is: " + test_universe.getState());
        System.out.println("Have we got a winner?: " + test_universe.haveAWinner());
        System.out.println("");

        // Testing the combat
        System.out.println("Testing the combat:");
        CombatResult test_result = test_universe.combat();
        System.out.println("The result of the combat was: " + test_result);
        System.out.println("State of the game " + test_universe);
        System.out.println("");
        
        // Testing nextTurn
        System.out.println("Testing next turn");
        test_universe.nextTurn();
        System.out.println("State after next turn: " + test_universe);
    }

    /**
     * @brief Main program for testing both SpaceStation and GameUniverse
     * */
    public static void main(String[] args){
        testSpaceStation();
        testGameUniverse();
    }
}
