
/**
 * The healing potions (soon to be also mana potions) in Xelient.
 * 
 * @author Kyle McCloskey
 * @version 4/21/15
 */
public class Food extends Item {
    /** The amount of health points that will be restored, if consumed. */
    private int healingEffect;
    
    /**
     * Contructs a potion.
     * 
     * @param name The potions name.
     * @param description The potions description.
     * @param weight The potions weight.
     * @param healingEffect How many points of health the potion will cure.
     */
    public Food(String name, String description, int weight, int healingEffect) {
        super(name, description, weight);
        this.healingEffect = healingEffect;
    }
    
    /**
     * Accessor for the potions health effect.
     * 
     * @return The healing effect of the potion.
     */
    public int getHealthEffect() {
        return healingEffect;
    }
}
