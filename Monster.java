import java.util.Random;
/**
 * The monsters found in the Wilderness.
 * 
 * @author Kyle McCloskey
 * @version 4/17/15
 */
public class Monster {
    /** Holds the monster's name. */
    private String name;
    /** Holds the monster's health. */
    private int health;
    /** Holds the monster's potential damage. */
    private int damage;
    /** Holds the monster's probability to hit the player. */
    private int hitProbability;
    /** Holds how much Quanta the monster will drop if killed. */
    private int quantaAmount;
    /** Holds a random value. */
    private Random generator;

    /**
     * Contructs a new Monster.
     * 
     * @param name The Monster's name.
     * @param health The Monster's health.
     * @param damage The Monster's hit points.
     * @param hitProbability The probability the monster will hit the player.
     * @param quantaAmount The amount of Quanta the Monster will drop if killed.
     */
    public Monster(String name, int health, int damage, int hitProbability, int quantaAmount) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.hitProbability = hitProbability;
        this.quantaAmount = quantaAmount;
        generator = new Random();
    }

    /**
     * The name of the Monster.
     * 
     * @return The Monster's name.
     */
    public String getName() {
        return name;
    }

    /**
     * The Monster's health.
     * 
     * @return The Monster's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * How much damage the Monster can inflict on the player.
     * 
     * @return The points of damage the Monster can inflict on the player.
     */
    public int getDamage() {
        int result = generator.nextInt(damage) + 1;
        return result;
    }

    /**
     * The probability that the Monster will connect with an attack.
     * 
     * @return The % out of 100 that the Monster will hit the player.
     */
    public int getHitProbability() {
        return hitProbability;
    }

    /**
     * How much Quanta the Monster will drop if killed.
     * 
     * @return The amount of Quanta.
     */
    public int getQuantaAmount() {
        return quantaAmount;
    }

    /**
     * Sets the Monster's health to a particular value. This is for player attacks.
     * 
     * @param health The Monster's remaining health.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Clones a Monster so it can be encountered again within the particular section of the Wilderness.
     * 
     * @return The cloned Monster.
     */
    public Monster clone() {
        Monster clone = new Monster(name, health, damage, hitProbability, quantaAmount);
        return clone;
    }
}
