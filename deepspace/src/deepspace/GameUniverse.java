/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
public class GameUniverse {
    /**
     * Amount of medals necessary to win the game
     */
    private static final int WIN = 10;
    
    /**
     * Index of the station that is currently playing
     */
    private int currentStationIndex;
    
    /**
     * Number of turns
     */
    private int turns;
    
    /**
     * Game dice
     */
    private Dice dice;
    
    /**
     * Current space station
     */
    private SpaceStation currentStation;
    
    /**
     * Set of space stations that are playing
     */
    private ArrayList<SpaceStation> spaceStations;
    
    /**
     * Current enemy star ship
     */
    private EnemyStarShip currentEnemy;
    
    /**
     * Game state
     */
    private GameStateController gameState;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    public GameUniverse() {
        currentStationIndex = -1;
        turns = 0;
        dice = new Dice();
        currentStation = null;
        spaceStations = new ArrayList<>();
        currentEnemy = null;
        gameState = new GameStateController();
    }
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    /**
     * Getter for gameState
     * @return gameState
     */
    public GameState getState() {
        return gameState.getState();
    }
    
    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------
    
    /**
     * Discard the hangar from the current space station.
     * Discarded if gameState is INIT or AFTERCOMBAT
     */
    public void discardHangar() {
        if ( gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT )
            currentStation.discardHangar();
    }
    
    /**
     * Discard a certain shield booster from the current space station.
     * Discarded if gameState is INIT or AFTERCOMBAT
     * @param i position of the shield booster to discard
     */
    public void discardShieldBooster(int i) {
        if ( gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT )
            currentStation.discardShieldBooster(i);
    }
    
    /**
     * Discard a certain shield booster in the hangar from the current space station.
     * Discarded if gameState is INIT or AFTERCOMBAT
     * @param i position of the shield booster to discard
     */
    public void discardShieldBoosterInHangar(int i) {
        if ( gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT )
            currentStation.discardShieldBoosterInHangar(i);
    }
    
    /**
     * Discard a certain weapon from the current space station.
     * Discarded if gameState is INIT or AFTERCOMBAT
     * @param i position of the weapon to discard
     */    
    public void discardWeapon(int i) {
        if ( gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT )
            currentStation.discardWeapon(i);
    }
    
    /**
     * Discard a certain weapon in the hangar from the current space station.
     * Discarded if gameState is INIT or AFTERCOMBAT
     * @param i position of the weapon to discard
     */
    public void discardWeaponInHangar(int i) {
        if ( gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT )
            currentStation.discardWeaponInHangar(i);
    }
    
    /**
     * Mount a certain shield booster from the current space station.
     * Mounted if gameState is INIT or AFTERCOMBAT
     * @param i position of the shield booster to mount
     */
    public void mountShieldBooster(int i) {
        if ( gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT )
            currentStation.mountShieldBooster(i);
    }
    
    /**
     * Mount a certain weapon from the current space station.
     * Mounted if gameState is INIT or AFTERCOMBAT
     * @param i position of the weapon to mount
     */
    public void mountWeapon(int i) {
        if ( gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT )
            currentStation.mountShieldBooster(i);
    }
    
    // -------------------------------------------------------------------------
    // Game methods
    // -------------------------------------------------------------------------
    
    /**
     * Checks if the space ship that has the turn has won
     * @return true, if the space ship has won; else, otherwise
     */
    public boolean haveAWinner() {
        if ( currentStation == null )
            throw new NullPointerException("ERROR: no currentStation on GameUniverse");
        else
            if ( currentStation.getNMedals() >= WIN )
                return true;
            else
                return false;
    }
    
    /**
     * Starts a match
     * @param names collection with the names of the players
     */
    public void init(ArrayList<String> names) {
        GameState state = gameState.getState();
        
        if ( state == GameState.CANNOTPLAY ) {
            ArrayList<SpaceStation> spaceStations = new ArrayList<>();
            CardDealer dealer = CardDealer.getInstance();
            
            for ( int i = 0; i < names.size(); i++ ) {
                SuppliesPackage supplies = dealer.nextSuppliesPackage();
                SpaceStation station = new SpaceStation(names.get(i), supplies);
                spaceStations.add(station);
                
                int nh = dice.initWithNHangars();
                int nw = dice.initWithNWeapons();
                int ns = dice.initWithNShields();
                
                Loot lo = new Loot(0, nw, ns, nh, 0);
                station.setLoot(lo);
            }
            
            int nPlayers = names.size();
            int currentStationIndex = dice.whoStarts(nPlayers);
            currentStation = spaceStations.get(currentStationIndex);
            currentEnemy = dealer.nextEnemy();
            
            gameState.next(turns, spaceStations.size());
        }
    }
    
    /**
     * Takes turn to the next player, if there is no pending damage
     * @return true, if the turn could be changed; else, otherwise
     */
    public boolean nextTurn() {
        GameState state = gameState.getState();
        
        if ( state == GameState.AFTERCOMBAT ) {
            boolean stationState = currentStation.validState();
            
            if ( stationState ) {
                currentStationIndex = ( currentStationIndex + 1 )
                        % spaceStations.size();
                turns++;
                
                currentStation = spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                
                CardDealer dealer = CardDealer.getInstance();
                currentEnemy = dealer.nextEnemy();
                
                gameState.next(turns, spaceStations.size());
                
                return true;
            }
            
            return false;
        }
        
        return false;
    }
    
    /**
     * Makes combat between the space station that holds the turn and the
     * current enemy. The combat is realized if the app is on a state where
     * combat is allowed
     * @return combat result
     */
    public CombatResult combat() {
        GameState state = gameState.getState();
        
        if ( state == GameState.BEFORECOMBAT || state == GameState.INIT ) {
            return combat(currentStation, currentEnemy);
        } else {
            return CombatResult.NOCOMBAT;
        }
    }
    
    /**
     * Executes combat, according to game rules
     * @param station station in combat
     * @param enemy enemy in combat
     * @return combat result
     */
    CombatResult combat(SpaceStation station, EnemyStarShip enemy) {
        GameCharacter ch = dice.firstShot();
        
        boolean enemyWins;
        float fire;
        ShotResult result;
        CombatResult combatResult;
        
        if ( ch == GameCharacter.ENEMYSTARSHIP ) {
            fire = enemy.fire();
            result = station.receiveShot(fire);
            
            if ( result == ShotResult.RESIST ) {
                fire = station.fire();
                result = enemy.receiveShot(fire);
                
                enemyWins = (result == ShotResult.RESIST);
            } else
                enemyWins = true;
        } else {
            fire = station.fire();
            result = enemy.receiveShot(fire);
            
            enemyWins = (result == ShotResult.RESIST);
        }
        
        if ( enemyWins ) {
            float s = station.getSpeed();
            boolean moves = dice.spaceStationMoves(s);
            
            if ( !moves ) {
                Damage damage = enemy.getDamage();
                station.setPendingDamage(damage);
                
                combatResult = CombatResult.ENEMYWINS;
            } else {
                station.move();
                
                combatResult = CombatResult.STATIONESCAPES;
            }
        } else {
            Loot aLoot = enemy.getLoot();
            station.setLoot(aLoot);
            
            combatResult = CombatResult.STATIONWINS;
        }
        
        gameState.next(turns, spaceStations.size());
        
        return combatResult;
    }
    
    // -------------------------------------------------------------------------
    // String representation, UI version
    // -------------------------------------------------------------------------
    
    /**
     * String representation of the object
     * @return string representation
     */
    public String toString() {
        String message = "[GameUniverse] -> Game state: " + gameState.toString()
                + ", Turns: " + turns + ", Dice: " + dice.toString()
                + "\n\tCurrent station: " + currentStation.toString()
                + "\n\tCurrent enemy: " + currentEnemy.toString();
        return message;
    }
    
    /**
     * To UI
     */
    public GameUniverseToUI getUIversion() {
        return new GameUniverseToUI(currentStation, currentEnemy);
    }
    
}
