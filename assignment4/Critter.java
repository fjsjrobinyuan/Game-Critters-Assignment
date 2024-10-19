/* CRITTERS Critter.java
 * ECE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Chenhe Yuan
 * cy8368
 * Slip days used: <0>
 * Fall 2024
 */
package assignment4;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	/**
 	 * This one returns a random integer between 0 (inclusive) and max (exclusive).
   	 * @param max The upper bound for random integer generation.
         * @return Return a random integer between 0 (inclusive) and max (exclusive).
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	/**
	 * Sets a new seed for the random number generator.
	 * @param new_seed The seed for the random number generator.
	 */
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	/**
	 * Gets the energy of the critter.
	 * @return The current energy level of the critter.
	 */
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;

	/**
	 * Moves the critter one step in the given direction and reduces its energy accordingly.
	 * @param direction The direction in which the critter should move.
	 */
	protected final void walk(int direction) {
		energy -= Params.walk_energy_cost;
	
		switch (direction) {
			case 0: // Right
				x_coord = (x_coord + 1) % Params.world_width;
				break;
			case 1: // Up-right
				x_coord = (x_coord + 1) % Params.world_width;
				y_coord = (y_coord - 1 + Params.world_height) % Params.world_height;
				break;
			case 2: // Up
				y_coord = (y_coord - 1 + Params.world_height) % Params.world_height;
				break;
			case 3: // Up-left
				x_coord = (x_coord - 1 + Params.world_width) % Params.world_width;
				y_coord = (y_coord - 1 + Params.world_height) % Params.world_height;
				break;
			case 4: // Left
				x_coord = (x_coord - 1 + Params.world_width) % Params.world_width;
				break;
			case 5: // Down-left
				x_coord = (x_coord - 1 + Params.world_width) % Params.world_width;
				y_coord = (y_coord + 1) % Params.world_height;
				break;
			case 6: // Down
				y_coord = (y_coord + 1) % Params.world_height;
				break;
			case 7: // Down-right
				x_coord = (x_coord + 1) % Params.world_width;
				y_coord = (y_coord + 1) % Params.world_height;
				break;
		}
	}
	/**
	 * Moves the critter two steps in the given direction and reduces its energy accordingly.
	 * @param direction The direction in which the critter should move.
	 */
	protected final void run(int direction) {
		energy -= Params.run_energy_cost;
	
		switch (direction) {
			case 0: // Right
				x_coord = (x_coord + 2) % Params.world_width;
				break;
			case 1: // Up-right
				x_coord = (x_coord + 2) % Params.world_width;
				y_coord = (y_coord - 2 + Params.world_height) % Params.world_height;
				break;
			case 2: // Up
				y_coord = (y_coord - 2 + Params.world_height) % Params.world_height;
				break;
			case 3: // Up-left
				x_coord = (x_coord - 2 + Params.world_width) % Params.world_width;
				y_coord = (y_coord - 2 + Params.world_height) % Params.world_height;
				break;
			case 4: // Left
				x_coord = (x_coord - 2 + Params.world_width) % Params.world_width;
				break;
			case 5: // Down-left
				x_coord = (x_coord - 2 + Params.world_width) % Params.world_width;
				y_coord = (y_coord + 2) % Params.world_height;
				break;
			case 6: // Down
				y_coord = (y_coord + 2) % Params.world_height;
				break;
			case 7: // Down-right
				x_coord = (x_coord + 2) % Params.world_width;
				y_coord = (y_coord + 2) % Params.world_height;
				break;
		}
	}
	
	/**
	 * Creates an offspring critter and moves it one step in the specified direction.
	 * @param offspring The offspring critter to be created.
	 * @param direction The direction in which the offspring should move.
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if (this.energy < Params.min_reproduce_energy) {
			return;
		}

		offspring.energy = this.energy / 2;
		this.energy = (this.energy + 1) / 2;

		offspring.x_coord = this.x_coord;
		offspring.y_coord = this.y_coord;

		switch (direction) {
			case 0: // Right
				offspring.x_coord = (offspring.x_coord + 1) % Params.world_width;
				break;
			case 1: // Up-right
				offspring.x_coord = (offspring.x_coord + 1) % Params.world_width;
				offspring.y_coord = (offspring.y_coord - 1 + Params.world_height) % Params.world_height;
				break;
			case 2: // Up
				offspring.y_coord = (offspring.y_coord - 1 + Params.world_height) % Params.world_height;
				break;
			case 3: // Up-left
				offspring.x_coord = (offspring.x_coord - 1 + Params.world_width) % Params.world_width;
				offspring.y_coord = (offspring.y_coord - 1 + Params.world_height) % Params.world_height;
				break;
			case 4: // Left
				offspring.x_coord = (offspring.x_coord - 1 + Params.world_width) % Params.world_width;
				break;
			case 5: // Down-left
				offspring.x_coord = (offspring.x_coord - 1 + Params.world_width) % Params.world_width;
				offspring.y_coord = (offspring.y_coord + 1) % Params.world_height;
				break;
			case 6: // Down
				offspring.y_coord = (offspring.y_coord + 1) % Params.world_height;
				break;
			case 7: // Down-right
				offspring.x_coord = (offspring.x_coord + 1) % Params.world_width;
				offspring.y_coord = (offspring.y_coord + 1) % Params.world_height;
				break;
		}

		babies.add(offspring);
	}

	/**
	 * Abstract method that defines the actions taken by a critter at each time step.
	 */
	public abstract void doTimeStep();
	/**
	 * Abstract method that defines the behavior of a critter during an encounter with another critter.
	 * @param oponent A string representation of the opposing critter.
	 * @return true if this critter chooses to fight, false otherwise.
	 */
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			String qualifiedClassName = myPackage + "." + critter_class_name;

			Class <?> critterClass = Class.forName(qualifiedClassName);

			if (!Critter.class.isAssignableFrom(critterClass)) {
				throw new InvalidCritterException(critter_class_name);
			}

			Critter newCritter = (Critter) critterClass.getDeclaredConstructor().newInstance();

			newCritter.energy = Params.start_energy;
			newCritter.x_coord = getRandomInt(Params.world_width);
			newCritter.y_coord = getRandomInt(Params.world_height);

			population.add(newCritter);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		
		try {
			String qualifiedClassName = myPackage + "." + critter_class_name;

			Class<?> critterClass = Class.forName(qualifiedClassName);

			if (!Critter.class.isAssignableFrom(critterClass)) {
				throw new InvalidCritterException(critter_class_name);
			}

			for (Critter each : population) {
				if (critterClass.isInstance(each)) {
					result.add(each);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}

		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();

		babies.clear();
	}
	
	/**
	 * Conducts a simulated encounter between two Critters.
	 * @param crit1 The first Critter.
	 * @param crit2 The second Critter.
	 */
	private static void juezhan(Critter crit1, Critter crit2) {
		boolean crit1WantsToFight = crit1.fight(crit2.toString());
		boolean crit2WantsToFight = crit2.fight(crit1.toString());

		if (crit1.energy > 0 && crit2.energy <0 && crit1.x_coord == crit2.x_coord && crit1.y_coord == crit2.y_coord) {
			int crit1Roll = crit1WantsToFight ? Critter.getRandomInt(crit1.energy) : 0;
			int crit2Roll = crit2WantsToFight ? Critter.getRandomInt(crit2.energy) : 0;

			if (crit1Roll >= crit2Roll) {
				crit1.energy += crit2.energy / 2;
				crit2.energy = 0; // Critter 2 dies
			} else {
				crit2.energy += crit1.energy / 2;
				crit1.energy = 0; // Critter 1 dies
				}
		}
	}
	/**
	 * Resolves all encounters between Critters in the same location.
	 */
	private static void resolveEncounters() {
		for (int i = 0; i < population.size(); i++) {
			Critter crit1 = population.get(i);
			for (int j = i + 1; j < population.size(); j++) {
				Critter crit2 = population.get(j);
				// If they are in the same spot
				if (crit1.x_coord == crit2.x_coord && crit1.y_coord == crit2.y_coord) {
					// Resolve the encounter
					juezhan(crit1, crit2);
				}
			}
		}
	}
	/**
	 * Advances the world by one time step, updating Critters and resolving events.
	 */
	public static void worldTimeStep() {
		// Step 1: Execute doTimeStep for each critter
		for (Critter each : population) {
			each.doTimeStep();
		}
	
		// Step 2: Resolve encounters
		resolveEncounters();
	
		// Step 3: Remove dead critters after resting energy deduction
		List<Critter> deadCritters = new ArrayList<>();
		for (Critter each : population) {
			each.energy -= Params.rest_energy_cost;
			if (each.energy <= 0) {
				deadCritters.add(each);
			}
		}
		population.removeAll(deadCritters);
	
		// Step 4: Add babies to the population
		population.addAll(babies);
		babies.clear();
	
		// Step 5: Add new algae
		for (int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				Critter.makeCritter("Algae");
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Displays the current state of the world in a text-based format.
	 */
	public static void displayWorld() {
		// Create a 2D array to represent the world
		String[][] worldGrid = new String[Params.world_height][Params.world_width];
	
		// Initialize the grid with empty spaces
		for (int y = 0; y < Params.world_height; y++) {
			for (int x = 0; x < Params.world_width; x++) {
				worldGrid[y][x] = " "; // empty space represents an empty cell
			}
		}
	
		// Place critters in the grid based on their positions
		for (Critter crit : population) {
			worldGrid[crit.y_coord][crit.x_coord] = crit.toString(); // display critter
		}
	
		// Print the top border
		System.out.print("+");
		for (int i = 0; i < Params.world_width; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	
		// Print the grid with vertical borders
		for (int y = 0; y < Params.world_height; y++) {
			System.out.print("|"); // left border
			for (int x = 0; x < Params.world_width; x++) {
				System.out.print(worldGrid[y][x]); // print critter or space
			}
			System.out.println("|"); // right border
		}
	
		// Print the bottom border
		System.out.print("+");
		for (int i = 0; i < Params.world_width; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}
	
}
