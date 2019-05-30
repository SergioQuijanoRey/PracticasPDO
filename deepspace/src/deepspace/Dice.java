/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.Random;

/**
 * Class to take all random decisions on the game.
 * 
 * @author Miguel Ángel Fernández Gutiérrez, Sergio Quijano Rey
 */
class Dice {
    /**
     * Probability constant for hangars.
     */
    private final float NHANGARSPROB;
    
    /**
     * Probability constant for shields.
     */
    private final float NSHIELDSPROB;
    
    /**
     * Probability constant for weapons.
     */
    private final float NWEAPONSPROB;
    
    /**
     * Probability constant for first shot.
     */
    private final float FIRSTSHOTPROB;
    
    /**
     * Probability constant for extra efficiency.
     */
    private final float EXTRAEFFICIENCYPROB;
    
    /**
     * Random numbers generator.
     */
    private Random generator;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    /**
     * Class initializer.
     */
    Dice() {
        NHANGARSPROB = 0.25f;
        NSHIELDSPROB = 0.25f;
        NWEAPONSPROB = 0.33f;
        FIRSTSHOTPROB = 0.5f;
        EXTRAEFFICIENCYPROB = 0.8f;
        
        generator = new Random();
    }
    
    // -------------------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------------------
    
    /**
     * Determines the number of hangars that a space station will recieve
     * upon creation. This value is calculated based on a random value between
     * 0 and 1, and the return value depends on NHANGARSPROB's probability:
     *      - 0 if random_value <= NHANGARSPROB
     *      - 1 otherwise
     * @return 0 or 1, as specified above
     */
    public int initWithNHangars() {
        float prob = generator.nextFloat();
        
        if ( prob <= NHANGARSPROB )
            return 0;
        else
            return 1;
    }
    
    /**
     * Determines the number of weapons that a space station will recieve
     * upon creation. This value is calculated based on a random value between
     * 0 and 1, and the return value depends on NWEAPONSPROB's probability:
     *      - 1 if random_value <= NWEAPONSPROB
     *      - 2 else if random_value <= 2*NWEAPONSPROB
     *      - 3 otherwise
     * @return 1, 2 or 3, as specified above
     */
    public int initWithNWeapons() {
        float prob = generator.nextFloat();
        
        if ( prob <= NWEAPONSPROB )
            return 1;
        else if ( prob <= NWEAPONSPROB*2 )
            return 2;
        else
            return 3;
    }
    
    /**
     * Determines amount of shield enhancers that a space station will recieve
     * upon creation. This value is calculated based on a random value between
     * 0 and 1, and the return value depends on NSHIELDSPROB's probability:
     *      - 0 if random_value <= NSHIELDSPROB
     *      - 1 otherwise
     * @return 0 or 1, as specified above
     */
    public int initWithNShields() {
        float prob = generator.nextFloat();
        
        if ( prob <= NSHIELDSPROB )
            return 0;
        else
            return 1;
    }
    
    /**
     * Determines which player will start the match randomly.
     * @param nPlayers number of players
     * @return such player (an integer in [0, nPlayers-1])
     */
    public int whoStarts(int nPlayers) {
        return generator.nextInt(nPlayers);
    }
    
    /**
     * Determines who shoots first.
     * @return GameCharacter.SPACESTATION if the player shoots first, or
     * GameCharacter.ENEMYSTARSHIP if the enemy shoots first
     */
    GameCharacter firstShot() {
        float prob = generator.nextFloat();
        if ( prob <= FIRSTSHOTPROB )
            return GameCharacter.SPACESTATION;
        else
            return GameCharacter.ENEMYSTARSHIP;
    }
    
    /**
     * Determines if space station moves in order to avoid a shot.
     * @param speed speed of the space station
     * @return true, if the space avoids the shot; false, otherwise
     */
    public boolean spaceStationMoves(float speed) {
        float prob = generator.nextFloat();
        return prob < speed;
    }
    
    /**
     * Determines if space station gets extra efficiency.
     * @return true, if space station gets extra efficiency; false, otherwise
     */
    public boolean extraEfficiency() {
        float prob = generator.nextFloat();
        if ( prob <= EXTRAEFFICIENCYPROB )
            return true;
        else
            return false;
    }
}
