/**
 * @author Sergio Quijano Rey
 * */

package deepspace;

import java.util.Random;

/**
 * @brief Class to take all random decisions of the game
 * */
class Dice{
    // Private attributes
    //==========================================================================
    private final float NHANGARSPROB;
    private final float NSHIELDSPROB;
    private final float NWEAPONSPROB;
    private final float FIRSTSHOTPROB;
    private final float EXTRAEFFICIENCYPROB;
    private Random generator;

    // Constructors
    //==========================================================================
    Dice(){
        NHANGARSPROB = (float)0.25;
        NSHIELDSPROB = (float)0.25;
        NWEAPONSPROB = (float)0.33;
        FIRSTSHOTPROB = (float)0.5;
        EXTRAEFFICIENCYPROB = (float)0.8;

        generator = new Random(System.currentTimeMillis());
    }

    // Getters
    //==========================================================================
    /**
     * @brief determines the number of hangars that a space station receives at creation
     * @return  0, with NHANGARSPROB probability
     *          1, with 1 - NHANGARSPROB probability
     * */
    public int initWithNHangars(){
        if(generator.nextFloat() < NHANGARSPROB){
            return 0;
        }else{
            return 1;
        }
    }

    /**
     * @brief determines the number of weapons given to a space station on creation
     * @return  1, with NWEAPONSPROB probability
     *          2, with NWEAPONSPROB probability
     *          3, with 1 - 2*NWEAPONSPROB probability, that's to say, otherwise
     * */
    public int initWithNWeapons(){
        float rand_value = generator.nextFloat();

        if(rand_value < NWEAPONSPROB){
            return 1;
        }else if(rand_value < 2*NWEAPONSPROB){
            return 2;
        }else{
            return 3;
        }
    }

    /**
     * @brief Determines the number of shield boosters given to a space station on creation
     * @return  0, with NSHIELDSPROB probability
     *          1, with 1 - NSHIELDSPROB probability
     * */
    public int initWithNShields(){
        if(generator.nextFloat() < NSHIELDSPROB){
            return 0;
        }else{
            return 1;
        }
    }

    /**
     * @brief decides who starts the game
     * @return an integer in the interval [0, nPlayers)
     * */
    public int whoStarts(int nPlayers){
        return generator.nextInt(nPlayers);

    }

    /**
     * @brief determines who shots first on a combat
     * @return  GameCharacter.SPACESTATION with a probability of FIRSTSHOTPROB
     *          GameCharacter.ENEMYSTARSHIP otherwise
     * */
    public GameCharacter firstShot(){
        if(generator.nextFloat() < FIRSTSHOTPROB){
            return GameCharacter.SPACESTATION;
        }else{
            return GameCharacter.ENEMYSTARSHIP;
        }
    }

    /**
     * @brief determines if a spacestation moves to avoid a shoot
     * @param speed: the current speed of the space station
     * @return  true, with a probability of speed
     *          false, otherwise
     * */
    public boolean spaceStationMoves(float speed){
        if(generator.nextFloat() < speed){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Decides wether or not a space station is going to be converted to a extraEfficiency spacestation
     * @return true, with a probability of EXTRAEFFICIENCYPROB
     *         false, with a probability of 1 - EXTRAEFFICIENCYPROB
     * */
    public boolean extraEfficiency(){
        if(generator.nextFloat() < EXTRAEFFICIENCYPROB){
            return true;
        }else{
            return false;
        }
    }
}
