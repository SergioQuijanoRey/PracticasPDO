/**
 * @author Sergio Quijano Rey
 * @file TestP4.java
 * @brief Test for the code implemented on fourth session
 */

package deepspace;

import java.util.ArrayList;

public class TestP4 {
    static void testNumericDamage(){
        System.out.println("Testing Numeric Damge");
        System.out.println("=======================================================================");

        CardDealer dealer = CardDealer.getInstance();
        ArrayList<Weapon> w = new ArrayList<>();
        ArrayList<ShieldBooster> s = new ArrayList<>();
        
        w.add(dealer.nextWeapon());
        s.add(dealer.nextShieldBooster());
        
        NumericDamage test_numeric = new NumericDamage(2, 4);
        System.out.println("The state of the new Numeric Damage is: " + test_numeric);
        System.out.println("");

        System.out.println("Creating a copy of the numeric damage");
        NumericDamage test_numeric_copy = test_numeric.copy();
        System.out.println("State of the copy: " + test_numeric_copy);
        System.out.println("");

        System.out.println("Testing NumericDamage.copy()");
        NumericDamage test_numeric_rare_copy = test_numeric.copy();
        System.out.println("State of the copy: " + test_numeric_rare_copy);
        System.out.println("");


        System.out.println("Testing hasNoEffect");
        System.out.println("Our damage hasNoEffect: " + test_numeric.hasNoEffect());
        System.out.println("NumericDamage(0,0) hasNoEffect: " + new NumericDamage(0, 0).hasNoEffect());
        System.out.println("");

        System.out.println("Discarding a weapon!");
        test_numeric.discardWeapon(null);
        System.out.println("State afther discarding a weapon: " + test_numeric);
        System.out.println("Discarding a shield Booster:");
        test_numeric.discardShieldBooster();
        System.out.println("State after discarding a shieldBooster: " + test_numeric);
        System.out.println("");

        System.out.println("Testing adjust");
        System.out.println("Weapon list: " + w);
        System.out.println("Shield list: " + s);
        NumericDamage test_adjusted = test_numeric.adjust(w, s);
        System.out.println("State after adjusting: " + test_adjusted);
        System.out.println("");

        System.out.println("UI version of the Damage: " + test_numeric.getUIversion());
        System.out.println();

        System.out.println("State of the copy: " + test_numeric_copy);
        System.out.println("State of the Damage.copy copy: " + test_numeric_rare_copy);

    }

    static void testSpecificDamage(){
        System.out.println("Testing Specific Damage!");
        System.out.println("================================================================================");

        // The base damage to work with it
        ArrayList<WeaponType> test_weapon_list = new ArrayList<WeaponType>();
        test_weapon_list.add(WeaponType.LASER);
        SpecificDamage test_specific = new SpecificDamage(test_weapon_list, 3);
        System.out.println("The created specific damage is " + test_specific);
        System.out.println("");

        System.out.println("Creating a copy of the damage, both methods:");
        SpecificDamage test_specific_copy = new SpecificDamage(test_specific);
        Damage test_specific_rare = test_specific.copy();
        System.out.println("State of the copy: " + test_specific_copy);
        System.out.println("State of the rare copy: " + test_specific_rare);
        System.out.println("");

        System.out.println("Testing has no effect");
        System.out.println("Test damage has no effect: " + test_specific.hasNoEffect());
        SpecificDamage empty = new SpecificDamage(null, 0);
        System.out.println("Empty damage has no effect: " + empty.hasNoEffect());
        System.out.println("");

        System.out.println("The UI version is: " + test_specific.getUIversion());
        System.out.println("");

        System.out.println("Testing adjust");
        CardDealer dealer = CardDealer.getInstance();
        ArrayList<Weapon> w = new ArrayList<>();
        ArrayList<ShieldBooster> s = new ArrayList<>();
        w.add(dealer.nextWeapon());
        s.add(dealer.nextShieldBooster());
        SpecificDamage test_specific_adjusted = test_specific.adjust(w, s);
        System.out.println("State after the adjust: " + test_specific_adjusted);
        System.out.println("");

        System.out.println("Testing discard weapon");
        Weapon test_weapon1 = new Weapon("Weapon1", WeaponType.MISSILE, 2);
        Weapon test_weapon2 = new Weapon("Weapon1", WeaponType.LASER, 2);
        System.out.println("Discarding a MISSILE");
        test_specific.discardWeapon(test_weapon1);
        System.out.println("State of the damage: " + test_specific);
        System.out.println("Discarding a LASER");
        test_specific.discardWeapon(test_weapon2);
        System.out.println("State of the damage: " + test_specific);
        System.out.println("");

        System.out.println("Discarding a shield booster");
        test_specific.discardShieldBooster();
        System.out.println("State of the damage: " + test_specific);
        System.out.println("Discarding other shield Booster");
        test_specific.discardShieldBooster();
        System.out.println("State of the damage: " + test_specific);
        System.out.println("");

    }

    static void testSpaceCity(){
        System.out.println("Testing Space City!");
        System.out.println("================================================================================");
        
        // Base stations to work with
        SuppliesPackage base = new SuppliesPackage(1.1111f, 2.222f, 3.333f);
        SpaceStation sergio = new SpaceStation("sergio", base);
        SpaceStation miguel = new SpaceStation("miguel", base);
        SpaceStation lucia = new SpaceStation("lucia", base);
        ArrayList<SpaceStation> collaborators = new ArrayList<>(0);
        collaborators.add(miguel);
        collaborators.add(lucia);

        SpaceCity test_city = new SpaceCity(sergio, collaborators);
        System.out.println("The created SpaceCity is: " + test_city);

        System.out.println("Firing with a power of " + test_city.fire());
        System.out.println("State after firing: " + test_city);
        System.out.println("");

        System.out.println("Protecting with a power of " + test_city.protection());
        System.out.println("State after protecting: " + test_city);
        System.out.println("");

        // BUG -- No se añade el loot de forma correcta
        Loot test_loot = new Loot(1, 2, 3, 4, 5, false, true);
        System.out.println("Adding the loot " + test_loot);
        test_city.setLoot(test_loot);
        System.out.println("State of the city: " + test_city);
        System.out.println("");
    }
 
    static void testPowerEfficientStation(){
        System.out.println("Testing PowerEfficientSpaceStation");
        System.out.println("================================================================================");

        SuppliesPackage base = new SuppliesPackage(1.1111f, 2.222f, 3.333f);
        SpaceStation test_station = new SpaceStation("sergio", base);
        PowerEfficientSpaceStation test_power = new PowerEfficientSpaceStation(test_station);
        System.out.print("The created power efficient station is: " + test_power);
        System.out.println("");

        System.out.println("The station fires: " + test_power.fire());
        System.out.println("The station protects: " + test_power.protection());
        System.out.println("State of the power efficient station: " + test_power);
        System.out.println("");

        // BUG -- Lanza warnings que no deberia
        Loot test_loot = new Loot(1, 2, 3, 4, 5, true, true);
        System.out.println("The station receives the loot " + test_loot);
        Transformation test_transform = test_power.setLoot(test_loot);
        System.out.println("Transformation returned: " + test_transform);
        System.out.println("State of the power efficient station: " + test_power);
        
    }

    static void testBetaPowerEfficientStation(){
        System.out.println("Testing BetaPowerEfficientSpaceStation");
        System.out.println("================================================================================");
        
        SuppliesPackage base = new SuppliesPackage(1.1111f, 2.222f, 3.333f);
        SpaceStation test_station = new SpaceStation("sergio", base);
        BetaPowerEfficientSpaceStation test_beta = new BetaPowerEfficientSpaceStation(test_station);
        System.out.println("The created BetaPowerEfficientSpaceStation is " + test_beta);

        System.out.println("Firing a few times");
        for(int i = 0; i < 5; i++){
            System.out.println("\nThe station fires: " + test_beta.fire());
        }
        System.out.println("");

        System.out.println("The Ui version of the station is " + test_beta.getUIversion());
        System.out.println("");
    }

    static void testLoot(){
        System.out.println("Testing Loot");
        System.out.println("================================================================================");

        Loot test_loot = new Loot(1, 2, 3, 4, 5, false, false);
        System.out.println("The created loot is: " + test_loot);
        System.out.println("");

        System.out.println("The UI version is: " + test_loot.getUIversion());
        System.out.println("");

    }

    static void testGameUniverse(){
        System.out.println("Testing GameUniverse");
        System.out.println("================================================================================");
    
        GameUniverse test_universe = new GameUniverse();
        System.out.println("The created universe is " + test_universe);
        System.out.println("");

        System.out.println("Initializing the game with {\"Sergio\", \"Miguel\"}");
        ArrayList<String> names = new ArrayList<>(0);
        names.add("Sergio");
        names.add("Miguel");
        test_universe.init(names);
        System.out.println("State after initializing: " + test_universe);
        System.out.println("");

        System.out.println("Combat");
        CombatResult test_result = test_universe.combat();
        System.out.println("Result of the combat: " + test_result);
        System.out.println("State of the GameUniverse: " + test_universe);
        System.out.println("");

        System.out.println("Next turn:");
        test_universe.nextTurn();
        System.out.println("State of the GameUniverse: " + test_universe);
        System.out.println("");

        System.out.println("Combat");
        test_result = test_universe.combat();
        System.out.println("Result of the combat: " + test_result);
        System.out.println("State of the GameUniverse: " + test_universe);
        System.out.println("");

        System.out.println("Next turn:");
        test_universe.nextTurn();
        System.out.println("State of the GameUniverse: " + test_universe);
        System.out.println("");

        System.out.println("Have a winner: " + test_universe.haveAWinner());

    }

    public static void main(String[] args){
        //testNumericDamage();
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //testSpecificDamage();
        System.out.println("");
        System.out.println("");
        System.out.println("");

        // Bug -- No se añade el loot de forma correcta
        //testSpaceCity();
        System.out.println("");
        System.out.println("");
        System.out.println("");

        // BUG -- El setLoot lanza warnings que no deberia
        //testPowerEfficientStation();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        //testBetaPowerEfficientStation();
        System.out.println("");
        System.out.println("");
        System.out.println("");

        //testLoot();
        System.out.println("");
        System.out.println("");
        System.out.println("");

        testGameUniverse();
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
}
