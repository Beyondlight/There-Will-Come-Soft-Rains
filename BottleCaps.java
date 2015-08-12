
/**
 * The currency of Xelient: Faded Memories
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BottleCaps extends Item {
    /** The amount of Bottle Caps. */
    private int bottlecaps;
    
    /**
     * Contructs Bottle Caps.
     * 
     * @param name The Quanta's name.
     * @param description The Quanta's description.
     * @param weight The Quanta's weight.
     * @param quanta The amount of Quanta.
     */
    public BottleCaps(String name, String description, int weight, int quanta) {
        super("Bottle Cap", "The currency of Arcaria", 0);
        this.bottlecaps = bottlecaps;
    }
    
    /**
     * The accessor for the amount of Quanta.
     * 
     * @return The amount of Quanta.
     */
    public int getAmount() {
        return bottlecaps;
    }
    
    /**
     * Removes a particular amount of Quanta.
     * 
     * @param removeAmount The amount being removed.
     * @return The amount being removed.
     */
    public BottleCaps removeAmount(int removeAmount) {
        BottleCaps result = null;
        if (bottlecaps > removeAmount) {
            bottlecaps = bottlecaps - removeAmount;
            result = new BottleCaps(getName(), getDescription(), getWeight(), removeAmount);
        }
        return result;
    }
    
    /**
     * Adds a particular amount of Quanta.
     * 
     * @param addAmount The amount being added.
     */
    public void addAmount(int addAmount) {
        bottlecaps = bottlecaps + addAmount;
    }
}
