
/**
 * The armor in Xelient.
 * 
 * @author Kyle McCloskey
 * @version 4/20/15
 */
public class Armor extends Item {
    /** The amount of points to be taken away from the monster's hit. */
    private int damageReduction;
    
    /**
     * Constructs a piece of armor.
     * 
     * @param name The name of the armor.
     * @param description The armor's description.
     * @param weight The armor's weight.
     * @param damageReduction The armor's overall effect.
     */
    public Armor(String name, String description, int weight, int damageReduction) {
        super(name, description, weight);
        this.damageReduction = damageReduction;
    }
    
    /**
     * Accessor for the armor's health effect.
     * 
     * @return The enemy's damage reduction.
     */
    public int getHealthEffect() {
        return damageReduction;
    }
}
