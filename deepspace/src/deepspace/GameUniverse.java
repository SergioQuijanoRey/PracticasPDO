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
class GameUniverse {
    /**
     * 
     */
    private static final int WIN = 10;
    
    /**
     * 
     */
    private int currentStationIndex = -1;
    
    /**
     * 
     */
    private int turns = 0;
    
    /**
     * 
     */
    private Dice dice = new Dice();
    
    /**
     * 
     */
    private SpaceStation currentStation = null;
    
    /**
     * 
     */
    private ArrayList<SpaceStation> spaceStations = new ArrayList<>();
    
    /**
     * 
     */
    private EnemyStarShip currentEnemy = null;
    
    /**
     * 
     */
    private GameStateController gameState;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    // GameUniverse() {}
    
    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------
    
    public GameStateController getGameState() {
        return gameState;
    }
    
    /* WIP - Continuar práctica 2 */
}
