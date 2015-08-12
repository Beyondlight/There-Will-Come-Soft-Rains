/**
 * The items in Xelient: Faded Memories.
 * 
 * @author Kyle McCloskey
 * @version 3/19/15
 */
public class Item {
    /** Holds the name of the item. */
    private String name;
    /** Holds the description of the item. */
    private String description;
    /** Holds the weight of the item. */
    private int weight;

    /**
     * Constructs a new item.
     * 
     * @param name The name of the item.
     * @param description The description of the item.
     * @param weight The weight of the item.
     */
    public Item(String name, String description, int weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Returns the item's name.
     * 
     * @return The item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the item's description.
     * 
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the item's weight.
     * 
     * @return The weight of the item. */
    public int getWeight() {
        return weight;
    }

    /**
     * Mutator for the item's description.
     * 
     * @param description The item's new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Mutator for the item's weight.
     * 
     * @param weight The item's new weight.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    /**
     * Returns a string that represents the item.
     *
     * @return Returns a string that represents the item. */
    public String toString () {
        String result = description;
        return result;
    }
}
