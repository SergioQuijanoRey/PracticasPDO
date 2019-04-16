/**
 * @author Sergio Quijano Rey
 * */

package Deepspace;

import java.util.Random;

/**
 * @brief Class to take all random decisions of the game
 * */
class Dice{
		// Private class attributes
		//======================================================================
		private final float NHANGARSPROB;
		private final float NSHIELDSPROB;
		private final float NWEAPONSPROB;
		private final float FIRSTSHOTPROB;

		// Private attributes
		//======================================================================
		private Random generator;

		// Constructors
		//======================================================================
		// WIP -- Deberia asignarse los valores constantes arriba, en la declaracion de los atributos
		Dice(){
				NHANGARSPROB = 0.25;
				NSHIELDSPROB = 0.25;
				NWEAPONSPROB = 0.33;
				FIRSTSHOTPROB = 0.5;

				generator = new Random(System.currentTimeMillis());
		}

		// Getters
		//======================================================================
		/**
		 * @brief determines the number of hangars that a space station receives at creation
		 * @return 	0, with NHANGARSPROB probability
		 * 			1, with 1 - NHANGARSPROB probability
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
		 * @return 	1, with NWEAPONSPROB probability
		 * 			2, with NWEAPONSPROB probability
		 * 			3, with 1 - 2*NWEAPONSPROB probability, that's to say, otherwise
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

				System.out.println("WARNING! Unexpected condition at Dice.initWithNWeapons()");
				return 1;
		}

		/**
		 * @brief Determines the number of shield boosters given to a space station on creation
		 * @return 	0, with NSHIELDSPROB probability
		 * 			1, with 1 - NSHIELDSPROB probability
		 * */
		public int initWithNShields(){
				if(generator.nextFloat() < NSHIELDSPROB){
						return 0;
				}else{
						return 1;
				}

				System.out.println("WARNING! Unexpected condition at Dice.initWithNShields()");
				return 1;
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
		 * @return 	GameCharacter.SPACESTATION with a probability of FIRSTSHOTPROB
		 * 			GameCharacter.ENEMYSTARSHIP otherwise
		 * */
		public GameCharacter firstShot(){
				if(generator.nextFloat() < FIRSTSHOTPROB){
						return GameCharacter.SPACESTATION;
				}else{
						return GameCharacter.ENEMYSTARSHIP;
				}

				System.out.println("WARNING! Unexpected condition at Dice.firstShot()");
				return GameCharacter.SPACESTATION;
		}

		/**
		 * @brief determines if a spacestation moves to avoid a shoot
		 * @param speed: the current speed of the space station
		 * @return 	true, with a probability of speed
		 * 			false, otherwise
		 * */
		public boolean spaceStationMoves(float speed){
				if(generator.nextFloat() < speed){
						return true;
				}else{
						return false;
				}
		}
}
