import java.util.ArrayList;
import java.util.Iterator;
/**
 * Extends the Item class. Complex items can hold regular items.
 * 
 * @author Kyle McCloskey
 * @version 3/21/15
 */
public class ComplexItem extends Item {
    /** The stored items. */
    private ArrayList<Item> complexItems;

    /**
     * Creates a complex item.
     * 
     * @param itemName The name of the complex item.
     * @param itemDesc The description of the complex item.
     * @param itemWeight The weight the of complex item.
     */
    public ComplexItem(String itemName, String itemDesc, int itemWeight) {
        super(itemName, itemDesc, itemWeight);
        complexItems = new ArrayList<Item>();
    }

    /**
     * Adds an item to the complex item.
     * 
     * @param item The item being stored in the complex item.
     */
    public void addItem(Item item) {
        complexItems.add(item);
    } 

    /**
     * Returns the names of the items within the complex item.
     * 
     * @return The names of the items.
     */
    public String[] getItemNames() {
        String[] itemNames = new String[complexItems.size()];
        for (int i = 0; i < complexItems.size(); i++) {
            itemNames[i] = complexItems.get(i).getName();
        }
        return itemNames;
    }

    /**
     * Gets information of a specific item.
     * 
     * @param itemName The selected item.
     * 
     * @return The information of the item.
     */
    public Item getItem(String itemName) {
        Item returnValue = null;
        boolean foundItem = false;
        int i = 0;
        while(i < complexItems.size() && foundItem == false) {
            if (complexItems.get(i).getName().equalsIgnoreCase(itemName)) {
                returnValue = complexItems.get(i);
                foundItem = true;
            }
            i++;
        }
        return returnValue;
    }

    /**
     * Removes an item from the ArrayList.
     * 
     * @param name The name of the item to be removed.
     * @return The item that was removed.
     */
    public Item removeItem(String name) {
        boolean nameFound = false;
        Item returnValue = null;
        Iterator<Item> iter = complexItems.iterator();
        while(iter.hasNext() && nameFound == false) {
            Item item = iter.next();          
            if (item.getName().equals(name)) {
                nameFound = true;
                returnValue = item;
                complexItems.remove(item);
            }else{ 
                returnValue = null;
            }
        }
        return returnValue;
    }

    /**
     * Returns a string representing the complex item.
     * 
     * @return The string literal, representing the complex item.
     */
    public String toString() {
        String result = super.toString() + " ";
        for (int i = 0; i < complexItems.size(); i++) {
            result += complexItems.get(i).getName();
            if (i + 1 < complexItems.size()) {
                result += ", ";
            }else{
                result += " ";
            }
        }
        return result;
    }
}