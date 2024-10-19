/* CRITTERS Fox.java
 * ECE422C Project 3 submission by
 * Chenhe Yuan
 * cy8368
 * Slip days used: 0
 * Fall 2024
 * Note: This is my custom Critter class.
 */
package assignment4;

/**
 * The Fox class is a type of Critter that has a strong preference 
 * for moving in a specific direction. It is highly aggressive and fights all encounters.
 * Each Fox critter has a set of genes that determine its movement patterns.
 */
public class Fox extends Critter {

    /**
     * Represents the total sum of gene values.
     */
    private static final int GENE_TOTAL = 24;

    /**
     * Array representing the movement preferences of the Fox critter.
     * Each index corresponds to one of the eight possible directions.
     */
    private int[] genes = new int[8];

    /**
     * The current direction of movement for the Fox critter.
     */
    private int dir;

    /**
     * Creates a new Fox critter with preset gene values and a random initial direction.
     */
    public Fox() {
        genes[0] = 10;
        genes[1] = 2;
        genes[2] = 9;
        genes[3] = 1;
        genes[4] = 8;
        genes[5] = 3;
        genes[6] = 7;
        genes[7] = 3;
        dir = Critter.getRandomInt(8);
    }

    /**
     * Returns the single-character string representation of the Fox critter.
     * @return "F" as the representation of this critter.
     */
    @Override
    public String toString() {
        return "F";
    }

    /**
     * Defines the Fox's response to an encounter with another critter.
     * Always chooses to fight.
     * @param not_used Unused parameter for the fight method.
     * @return true, indicating that the Fox always chooses to fight.
     */
    public boolean fight(String not_used) {
        return true;
    }

    /**
     * Executes one time step for the Fox critter.
     * The Fox moves in the current direction, reproduces if it has sufficient energy,
     * and chooses a new direction based on its genes.
     */
    @Override
    public void doTimeStep() {
        walk(dir);

        if (getEnergy() > 150) {
            Fox child = new Fox();
            for (int k = 0; k < 8; k += 1) {
                child.genes[k] = this.genes[k];
            }
            int g = Critter.getRandomInt(8);
            while (child.genes[g] == 0) {
                g = Critter.getRandomInt(8);
            }
            child.genes[g] -= 1;
            g = Critter.getRandomInt(8);
            child.genes[g] += 1;
            reproduce(child, Critter.getRandomInt(8));
        }

        int roll = Critter.getRandomInt(GENE_TOTAL);
        int turn = 0;
        while (genes[turn] <= roll) {
            roll = roll - genes[turn];
            turn = turn + 1;
        }
        assert (turn < 8);

        dir = (dir + turn) % 8;
    }

    /**
     * Displays statistics for all Fox critters in the population.
     * The statistics include the percentages of genes oriented towards different directions.
     * @param foxes List of all Fox critters.
     */
    public static void runStats(java.util.List<Critter> foxes) {
        int total_straight = 0;
        int total_left = 0;
        int total_right = 0;
        int total_back = 0;
        for (Object obj : foxes) {
            Fox c = (Fox) obj;
            total_straight += c.genes[0];
            total_right += c.genes[1] + c.genes[2] + c.genes[3];
            total_back += c.genes[4];
            total_left += c.genes[5] + c.genes[6] + c.genes[7];
        }
        System.out.print(foxes.size() + " total Foxes    ");
        System.out.print(total_straight / (GENE_TOTAL * 0.01 * foxes.size()) + "% straight   ");
        System.out.print(total_back / (GENE_TOTAL * 0.01 * foxes.size()) + "% back   ");
        System.out.print(total_right / (GENE_TOTAL * 0.01 * foxes.size()) + "% right   ");
        System.out.print(total_left / (GENE_TOTAL * 0.01 * foxes.size()) + "% left   ");
        System.out.println();
    }
}
