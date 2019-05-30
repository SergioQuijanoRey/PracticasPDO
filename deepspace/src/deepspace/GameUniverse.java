/**
 * @file GameUniverse.java
 * @author Sergio Quijano Rey
 * */

package deepspace;

import java.util.ArrayList;

/**
 * Class to represent the game
 * It acts as the controller of the game
 * Their methods are called from Controller.java
 *
 * @author Sergio Quijano Rey
 * */
public class GameUniverse{
    // Class Attributes
    //==========================================================================
    private static final int WIN = 10;

    // Instance Attributes
    //==========================================================================
    private int currentStationIndex;                // Index of the station with the turn
    private int turns;                              // Counter of the number of played turns
    private Dice dice;                              // Dice of the game
    private GameStateController gameState;          // Game controller
    private SpaceStation currentStation;            // The station with the turn
    private ArrayList<SpaceStation> spaceStations;  // The space stations which are playing
    private EnemyStarShip currentEnemy;             // The enemy which the curretnStation is facing
    private boolean haveSpaceCity;                  // Wether or not there is a player with a SpaceCity

    // Constructors
    //==========================================================================
    /**
     * Constructor of the class
     * */
    public GameUniverse(){
        // The dice is created
        dice = new Dice();

        // The GameStateController is created
        gameState = new GameStateController();

        // Turns are set to zero
        turns = 0;

        // The rest of parameters are initilized
        currentStationIndex = 0;
        currentStation = null;
        currentEnemy = null;
        spaceStations = new ArrayList<>(0);
        haveSpaceCity = false;
    }

    // Getters
    //==========================================================================
    /**
     * gameState getter
     * @return the gameState
     * 
     * WIP -- Antes devolvia un GameStateController y ahora devuelve un GameState 
     *        segun lo que pone en el diagrama de clases UML
     * */
    public GameState getState(){
        return gameState.getState();    // WIP -- He añadido el getState, no se si es correcto
    }

    /**
     * Get the UI representation of the object
     * @param currentStation, the current spacestation playing
     * @param currentEnemy, the current enemy playing
     * @return the UI version
     * */

    /**
     * Gets the UI version of the GameUniverse
     * @return the UI version
     * */
    public GameUniverseToUI getUIversion(){
        if(currentStation != null && currentEnemy != null){
            return new GameUniverseToUI(currentStation, currentEnemy);
        }else{
            System.out.println("WARNING! No currentEnemy or currentStation in GameUniverse.getUIversion()");
            System.out.println("Trying to call GameUniverseToUI constructor, it is going to fail!");
            return new GameUniverseToUI(currentStation, currentEnemy);
        }
    }

    /**
     * Gets the string representation
     * @return the string representation
     * */
    @Override
    public String toString(){
        return  "GameUniverse(\n" +
                "\tcurrentStationIndex = " + currentStationIndex + "\n" +
                "\tcurrentStation = " + currentStation + "\n" +
                "\tcurrentEnemy = " + currentEnemy + "\n" +
                "\tturns = " + turns + "\n" +
                "\tdice = " + dice + "\n" +
                "\tgameState = " + gameState + "\n" +
                "\tspaceStations = " + spaceStations + "\n" +
                "\tWIN = " + WIN + "\n" +
                ")";
    }

    // Game Actions
    //==========================================================================
    /**
     * @brief the SpaceStation and EnemyStarShip given combat
     * @param station, the SpaceStation that comabats
     * @param enemy, the EnemyStarShip that combats
     * @return the result of the combat
     * */
    CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        GameState state = gameState.getState();
        if(state == GameState.BEFORECOMBAT || state == GameState.INIT){
            // Some variables
            CombatResult combatResult;
            float fire;
            ShotResult shotResult;
            boolean enemyWins;

            // We decide who starts the battle
            GameCharacter ch = dice.firstShot();
            if(ch == GameCharacter.ENEMYSTARSHIP){  // The enemy shots first
                // The enemy shots to the station
                fire = enemy.fire();
                shotResult = station.receiveShot(fire);

                if(shotResult == ShotResult.RESIST){
                    // The station resists, therefore shots the enemy
                    fire = station.fire();
                    shotResult = enemy.receiveShot(fire);

                    // The enemy may resist or not
                    enemyWins = (shotResult == ShotResult.RESIST);
                }else{
                    // The station didn't resist the shot --> the enemy wins
                    enemyWins = true;

                }
            }else{  // The station shots first
                // We get the shot power from the station and see if the enemy resists
                fire = station.fire();
                shotResult = enemy.receiveShot(fire);

                // If the station can't destroy the enemy at once shot, the enemy
                // wins directly
                enemyWins = (shotResult == ShotResult.RESIST);
            }

            
            if(enemyWins){ // The enemy has won, the station has a change of escaping
                float speed = station.getSpeed();
                boolean moves = dice.spaceStationMoves(speed);

                if(moves == false){
                    // We get the damage of the enemy and give it to the station
                    Damage damage = enemy.getDamage();
                    station.setPendingDamage(damage);

                    // The station have lost
                    combatResult = CombatResult.ENEMYWINS;
                }else{
                    // The station manages to escape
                    station.move();

                    // The station have escaped
                    combatResult = CombatResult.STATIONESCAPES;
                }
            }else{ // The station have won, enemy gives its loot to the station

                // The loot earned is given -- The transformation is catched
                Loot aLoot = enemy.getLoot();
                Transformation transformation = station.setLoot(aLoot);

                // We check the transformation
                if(transformation == Transformation.GETEFFICIENT){
                    makeStationEfficient();
                    combatResult = CombatResult.STATIONWINSANDCONVERTS;
                }else if(transformation == Transformation.SPACECITY){
                    createSpaceCity();
                    combatResult = CombatResult.STATIONWINSANDCONVERTS;
                }else{
                    combatResult = CombatResult.STATIONWINS;
                }
            }

            // Next turn is set --> The GameState is modified 
            gameState.next(turns, spaceStations.size());

            // The result is returned
            return combatResult;

        }else{
            System.out.println("WARNING! Unexpected state at GameUniverse.combat(station, enemy)");
            return CombatResult.NOCOMBAT;
        }
    }

    /**
     * @brief the currentStation and currentEnemy combat
     * @return the result of the combat
     * */
    public CombatResult combat(){
        return combat(currentStation, currentEnemy);
    }

    /**
     * @brief checks if the current space staion have reached the number of medals to win
     * @return  false, if the currentStation has not reached that number, or there is no currentStation
     *          true, if the currentStation has reached that number
     * */
    public boolean haveAWinner(){
        if(currentStation == null){
            return false;
        }

        // The number of medals is checked
        if(currentStation.getNMedals() == WIN){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @brief moves to the next turn
     *        Before moving to the next turn, the pendingDamage of the currentStation
     *        is checked and resolved
     * @return true, if the turn is moved, that's to say, the pendingDamage of the currentStation has no effect
     *         false, if the turn could not be moved, that's to say, the pendingDamage 
     *         of the currentStation needs to be resolved or the GameState is not correct
     * */
    public boolean nextTurn(){
        if(gameState.getState() == GameState.AFTERCOMBAT){
            if(currentStation.validState() == true){
                // The turn is increased and the index of the currentStation is increased as well
                currentStationIndex = (currentStationIndex + 1) % (spaceStations.size());
                turns = turns + 1;

                // We get the new currentStation and we clean its useless weapons and shields
                currentStation = spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();

                // We get a new enemy from the card dealer
                CardDealer dealer = CardDealer.getInstance();
                currentEnemy = dealer.nextEnemy();

                // Next turn is set --> The GameState is modified 
                gameState.next(turns, spaceStations.size());

                return true;

            }else{
                System.out.println("Current station is not on valid state, at GameUniverse.nextTurn()");
                return false;
            }

        }else{
            System.out.println("Unexpected state on GameUniverse.nextTurn()");
            System.out.println("The current GameState is: " + gameState.getState());
            return false;
        }
    }

    /**
     * @brief It starts a game
     *        - For each player, a SpaceStation is created, a SuppliesPackage is given
     *          and weapons, shields and hangar is created
     *        - The starting turn is set, as well as the starting enemy
     * @param names, the names of the players
     * */
    public void init(ArrayList<String> names){
        if(gameState.getState() == GameState.CANNOTPLAY){
            // The space stations are created from sratch
            spaceStations = new ArrayList<SpaceStation>(0);

            // A card dealer for getting the supplies packages
            CardDealer dealer = CardDealer.getInstance();

            // A space station is created for each player
            for(int i = 0; i < names.size(); i++){
                // The starting supplies package for the station
                SuppliesPackage supplies = dealer.nextSuppliesPackage();

                // The station is created and added to the list
                SpaceStation station = new SpaceStation(names.get(i), supplies);
                spaceStations.add(station);

                // A random loot is created 
                int nh = dice.initWithNHangars();
                int nw = dice.initWithNWeapons();
                int ns = dice.initWithNShields();
                Loot lo = new Loot(0, nw, ns, nh, 0, false, false);

                // We give to the station the random loot created for the starting
                // weapons, shield and hangars (neither medals nor suppliesPackages
                // are given here)
                station.setLoot(lo);
            }

            // The starting player is decided
            currentStationIndex = dice.whoStarts(names.size());
            currentStation = spaceStations.get(currentStationIndex);

            // The starting enemy is decided
            currentEnemy = dealer.nextEnemy();

            // Next turn is set --> The GameState is modified 
            gameState.next(turns, spaceStations.size());
        }else{
            System.out.println("Unexpected gamestate at GameUniverse.init()");
            System.out.println("The game state is: " + gameState.getState());
            System.out.println("Nothing is done!");
        }
    }

    // Discard elements
    //==========================================================================
    /**
     * @brief the current space stationd discards an hangar in case the gameState
     *        is INIT or AFTERCOMBAT
     * */
    public void discardHangar(){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardHangar();
        }
    }

    /**
     * @brief the current space stationd discards a shieldBooster in case the gameState
     *        is INIT or AFTERCOMBAT
     * @param i, the position of the shieldBooster to discard
     * */
    public void discardShieldBooster(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardShieldBooster(i);
        }
    }

    /**
     * @brief the current space stationd discards a shieldBooster from its hangar 
     * in case the gameState is INIT or AFTERCOMBAT
     * @param i, the position of the shieldBooster in the hangar to discard
     * */
    public void discardShieldBoosterInHangar(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardShieldBoosterInHangar(i);
        }
    }

    /**
     * @brief the current space stationd discards a weapon in case the gameState 
     *        is INIT or AFTERCOMBAT
     * @param i, the position of the weapon to discard
     * */
    public void discardWeapon(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardWeapon(i);
        }
    }

    /**
     * @brief the current space stationd discards a weapon in its hangar in case 
     * the gameState is INIT or AFTERCOMBAT
     * @param i, the position of the weapon to discard in the hangar
     * */
    public void discardWeaponInHangar(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardWeaponInHangar(i);
        }
    }

    // Mount elements
    //==========================================================================
    /**
     * @brief the current space stationd mount a weapon from its hangar in case 
     * the gameState is INIT or AFTERCOMBAT
     * @param i, the position of the weapon to mount from the hangar
     * */
    public void mountWeapon(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.mountWeapon(i);
        }
    }

    /**
     * @brief the current space stationd mount a shieldBooster from its hangar in case 
     * the gameState is INIT or AFTERCOMBAT
     * @param i, the position of the shieldBooster to mount from the hangar
     * */
    public void mountShieldBooster(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.mountShieldBooster(i);
        }
    }

    // Transformations
    //==========================================================================
    /**
     * If there is no already a SpaceCity, it creates one who's base is currentStation
     * and collaborators are the rest of stations
     *
     * The attributes are modified as expected
     * */
    private void createSpaceCity(){
        if(haveSpaceCity == false){
            // The space stations without the currentStation
            ArrayList<SpaceStation> collaborators = new ArrayList<>(spaceStations);
            collaborators.remove(currentStationIndex);

            // The new spaceCity
            currentStation = new SpaceCity(currentStation, collaborators);

            // We update the spaceStations state
            spaceStations.remove(currentStationIndex);
            spaceStations.add(currentStationIndex, currentStation);

            // We have a new SpaceCity
            haveSpaceCity = true;
        }
    }

    /**
     * The current station is transformed to a PowerEfficientStation
     * Asks the dice wether to convert the currentStation to a PowerEfficientSpaceStation or to a BetaPowerEfficientSpaceStation
     * */
    private void makeStationEfficient(){
        currentStation = new PowerEfficientSpaceStation(currentStation);

        // There is a chance of getting a BetaPowerEfficientSpaceStation
        if(dice.extraEfficiency()){
            currentStation = new BetaPowerEfficientSpaceStation(currentStation);
        }

        // We change the spaceStations (the currentStation is no longer in spaceStations)
        spaceStations.remove(currentStationIndex);
        spaceStations.add(currentStationIndex, currentStation);
    }

}
