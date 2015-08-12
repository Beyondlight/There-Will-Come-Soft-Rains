import java.util.HashMap;
/**
 * The merchants within Xelient: Faded Memories.
 * 
 * @author Kyle McCloskey
 * @version 4/20/15
 */
public class Merchant extends Character {
    /** Holds all merchandise for the Merchants to sell. */
    private HashMap<String, String> merchandise;
    /** Holds all prices for the corresponding merchandise. */
    private HashMap<String, Integer> prices;

    /**
     * Contructs a Merchant.
     * 
     * @param name The Merchant's name.
     * @param conversation The Merchant's conversation dialogue.
     */
    public Merchant(String name, Conversation conversation) {
        super(name, "hello", conversation);
        merchandise = new HashMap<String, String>();
        prices = new HashMap<String, Integer>();
    }

    /**
     * Adds merchandise to a Merchant.
     * 
     * @param key The name of the item
     * @param value The final response dealing with the item from within the conversation.
     * @param price The price of the item.
     */
    public void addMerchandise(String key, String value, int price) {
        merchandise.put(key, value);
        prices.put(value, price);
    }
    
    /**
     * Accessor for the Merchant's item.
     * 
     * @param key The item's name.
     * @return The item to be removed from the Merchant's inventory.
     */
    public Item getItem(String key) {
        String value = merchandise.get(key);
        return removeItem(value);
    }
    
    /**
     * Accessor for the item's price.
     * 
     * @param value The item's final response value.
     * @return The item's price.
     */
    public Integer getPrice(String value) {
        return prices.get(value);
    }
}
