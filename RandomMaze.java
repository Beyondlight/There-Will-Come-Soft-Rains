import java.util.Random;
import java.util.ArrayList;
/**
 * The generator for the Wilderness.
 * 
 * @author Kyle McCloskey
 * @version 4/16/15
 */
public class RandomMaze extends Room {
    /** All  exits for the Wilderness. */
    private static final String[] DIRECTIONS = {"north", "south", "west", "east", "northeast", "northwest", "southeast", "southwest"};
    /** All monsters found in the Wilderness. */
    private ArrayList<Monster> monsters;
    /** The required number of turns the player needs to be in the Wilderness. */
    private int requiredCount;
    /** The number of turns the player has been in the Wilderness. */
    private int actualCount;
    /** The exit of the Wilderness. */
    private String actualDirection;
    /** The door to exit the Wilderness. */
    private Door actualExit;
    /** A random number generator. */
    private Random generator;

    /**
     * Constructs the Wilderness.
     * 
     * @param name The name of the Wilderness.
     * @param description The Wilderness's description.
     * @param requiredCount The amount of screens required in order to leave.
     */
    public RandomMaze(String name, String description, int requiredCount) {
        super(name, description);
        monsters = new ArrayList<Monster>();
        this.requiredCount = requiredCount;
        this.actualCount = 0;
        this.actualDirection = null;
        this.actualExit = null;
        this.generator = new Random();   
        this.generateRandomExits();
    }

    /**
     * Generates the exits for the Wilderness.
     */
    private void generateRandomExits() {
        super.clearExits();

        int numberOfExits = generator.nextInt(3) + 2;
        int monsterEncounter = generator.nextInt(10);
        for (int i = 0; i < numberOfExits; i++) {
            int index = generator.nextInt(DIRECTIONS.length);
            Door door = new Door(this);
            super.setExit(DIRECTIONS[index], door);
        }
    }

    /**
     * Generates a monster for the Wilderness.
     * 
     * @return The monter encountered in that part of the Wilderness.
     */
    public Monster generateMonster() {
        int enemyEncounter = generator.nextInt(5);
        Monster monster = null;
        if (enemyEncounter <= 1) {
            int index = generator.nextInt(monsters.size());
            monster = monsters.get(index).clone();
        }
        return monster;
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit.
     * @param neighbor The door in the given direction. */
    public void setExit(String direction , Door neighbor) {
        actualDirection = direction;
        actualExit = neighbor;
    }

    /**
     * Returns the direction of the exits.
     * 
     * @param direction The direction the exits are.
     * @return The direction the exits are.
     */
    public Door getExit(String direction) {
        Door doorExit = super.getExit(direction);
        if (doorExit != null) {
            actualCount++;
            generateRandomExits();
            if (actualCount >= requiredCount) {
                super.setExit(actualDirection, actualExit);
            }
            if (doorExit == actualExit) {
                actualCount = 0;
            }
        }
        return doorExit;
    } 

    /**
     * Adds a monster to the Wilderness.
     * 
     * @param monster The monster being added.
     */
    public void addMonster(Monster monster) {
        monsters.add(monster);
    }
}
