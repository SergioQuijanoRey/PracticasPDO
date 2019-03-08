import java.util.Random;
import GameCharacter;

// WIP -- Lack of documentation
// WIP -- Bug on import

public class Dice{
		// Private attributes
		//======================================================================
		private final float NHANGARSPROB;
		private final float NSHIELDSPROB;
		private final float NWEAPONSPROB;
		private final float FIRSTSHOTPROB;
		private Random generator;

		// Constructors
		//======================================================================
		public Dice(){
				NHANGARSPROB = 0.25;
				NSHIELDSPROB = 0.25;
				NWEAPONSPROB = 0.33;
				FIRSTSHOTPROB = 0.5;

				generator = new Random(System.currentTimeMillis());
		}

		// Getters
		//======================================================================
		/**
		 * @brief WIP -- What does this does?
		 * @return 	0 with a probability of NHANGARSPROB
		 * 			1 otherwise
		 * */
		public int initWithNHangars(){
				if(generator.nextFloat() < NHANGARSPROB){
						return 0;
				}else{
						return 1;
				}
		}

		/**
		 * @brief WIP -- What does this does?
		 * @return 	1 with NWEAPONSPROB probability
		 * 			2 with NWEAPONSPROB probability
		 * 			3 with 1 - 2*NWEAPONSPROB probability
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
		 * @brief WIP -- What does this does?
		 * @return 	0 with NSHIELDSPROB probability
		 * 			1 otherwise
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
