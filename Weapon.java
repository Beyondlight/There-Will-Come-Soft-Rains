
/**
 * The weapons in Xelient.
 * 
 * @author Kyle McCloskey
 * @version 4/20/15
 */
public class Weapon extends Item {
    /** The amount of hit points the weapon adds to the player's base damage. */
    private int hitPoints;

    /**
     * Constructs a weapon.
     * 
     * @param name The name of the weapon.
     * @param description The description of the weapon.
     * @param weight The weight of the weapon.
     * @param hitPoints How many extra points the weapon adds to the player's base damage.
     */
    public Weapon(String name, String description, int weight,int hitPoints) {
        super(name, description, weight);
        this.hitPoints = hitPoints;
    }
    
    /**
     * Accessor for the weapon's extra hit points.
     * 
     * @return The extra hit points.
     */
    public int getHitPoints() {
        return hitPoints;
    }
}