import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Class Room - a room in an adventure game.
 * 
 * This class is part of the "Xelient: Faded Memories application. "Campus of Kings" is a
 * very simple, text based adventure game.
 * 
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via doors. The doors are labeled north, east, south, west.
 * For each direction, the room stores a reference to an instance of door.
 * 
 * @author Maria Jump
 * @version 2015.02.01
 */
public class Room {
    /** The name of this room. */
    private String name;
    /** The description of this room. */
    private String description;
    /** The HashMap for arbitrary movement. */
    private HashMap<String, Door> exits;
    /** Stores a collection of items. */
    private ArrayList<Item> items;
    /** Stores a collection of NPCs. */
    private ArrayList<Character> characters;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     * 
     * @param name The room's name.
     * @param description The room's description.
     */
    public Room(String name, String description) {
        exits = new HashMap<String, Door>();
        items = new ArrayList<Item>();
        characters = new ArrayList<Character>();
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the description of this room.
     * 
     * @return The description of this room.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the name of this name of the room.
     * 
     * @return The name of this room.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the direction of the exits.
     * 
     * @param direction The direction the exits are.
     * @return The direction the exits are.
     */
    public Door getExit(String direction) {
        Door doorExit = exits.get(direction);
        return doorExit;
    }

    /**
     * Define an exit from this room.
     *
     * @param direction The direction of the exit.
     * @param neighbor The door in the given direction. */
    public void setExit(String direction , Door neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Return a description of the room's exits.
     * For example, "Exits: north, west".
     * 
     * @return A description of the available exits.
     */
    public String getExitString() {
        String availableExits = "Exits:  ";
        int numberFinished = 0;
        for (String currentKey : exits.keySet()) {
            numberFinished++;
            if (numberFinished < exits.size()) {
                availableExits = availableExits + currentKey + ", ";
            }else{
                availableExits = availableExits + currentKey;
            }
        }
        return availableExits;
    }

    /**
     * Return a description of the items in the room.
     * For example, "Items: Furnace, Anvil
     * 
     * @return A description of the items in the room.
     */
    public String getItemString() {
        String itemsInRoom = "";
        int minimumAmount = 0;
        if (items.size() > minimumAmount) {
            itemsInRoom += "Items: ";
            int numberFinished = 0;
            for (Item currentItem : items) {
                numberFinished++;
                if (numberFinished < items.size()) {
                    itemsInRoom = itemsInRoom + currentItem.getName() + ", ";
                }else{
                    itemsInRoom = itemsInRoom + currentItem.getName();
                }
                itemsInRoom += "";
            }
        }
        return itemsInRoom;
    }
    
    /**
     * Accessor for all NPCs in a room.
     * 
     * @return The names of all NPCs.
     */
    public String getNPCString() {
        String charactersInRoom = "";
        int minimumAmount = 0;
        if (characters.size() > minimumAmount) {
            charactersInRoom = "NPCs: ";
            int numberFinished = 0;
            for (Character currentNPC : characters) {
                numberFinished++;
                if (numberFinished < characters.size()) {
                    charactersInRoom = charactersInRoom + currentNPC.getName() + ", ";
                }else{
                    charactersInRoom = charactersInRoom + currentNPC.getName() + "\n";
                }
                charactersInRoom += "";
            }
        }
        return charactersInRoom;
    }

    /**
     * Adds an item to the room.
     * 
     * @param itemName The name of the item.
     * @param itemDesc The description of the item.
     * @param itemWeight The weight of the item.
     * 
     * @return The item being added.
     */
    public Item addItem(String itemName, String itemDesc, int itemWeight) {
        Item anItem = new Item(itemName, itemDesc, itemWeight);
        items.add(anItem);
        return anItem;
    }

    /**
     * Adds an item to the ComplexItem.
     * 
     * @param item The item being added.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Adds an NPC to a room.
     * 
     * @param character The NPC being added.
     */
    public void addCharacter(Character character) {
        characters.add(character);
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
        while(i < items.size() && foundItem == false) {
            if (items.get(i).getName().equalsIgnoreCase(itemName)) {
                returnValue = items.get(i);
                foundItem = true;
            }
            i++;
        }
        return returnValue;
    }

    /**
     * Gets information of a particular NPC.
     * 
     * @param characterName The name of the NPC.
     * @return The NPC.
     */
    public Character getNPC(String characterName) {
        Character returnValue = null;
        boolean foundCharacter = false;
        int i = 0;
        while(i < characters.size() && foundCharacter == false) {
            if (characters.get(i).getName().equalsIgnoreCase(characterName)) {
                returnValue = characters.get(i);
                foundCharacter = true;
            }
            i++;
        }
        return returnValue;
    }

    /**
     * Removes an item from a room.
     * 
     * @param item The item being removed.
     * 
     * @return The item being removed.
     */
    public Item removeItem(String item) {
        Item returnValue = null;
        boolean foundItem = false;
        int i = 0;
        while (i < items.size() && foundItem == false) {
            if (items.get(i).getName().equalsIgnoreCase(item)) {
                returnValue = items.get(i);
                items.remove(returnValue);
                foundItem = true;
            }
            i++;
        }
        return returnValue;
    }

    /**
     * Whether or not a room has a (or multiple) NPCs.
     * 
     * @return If the room has at least one NPC.
     */
    public boolean hasNPCs() {
        return characters.size() != 0;
    }

    /**
     * If the room has items in it.
     * 
     * @return If the items found is greater than 0.
     */
    public boolean hasItems() {
        return items.size() != 0;
    }
    
    /**
     * Accessor for a particular NPC's conversation.
     * 
     * @param triggerWord The NPC's trigger word.
     * @return The NPC's conversation.
     */
    public Conversation getConversation(String triggerWord) {
        // for each NPC, see if they have the triggerword
        Conversation result = null;
        Iterator<Character> iter = characters.iterator();
        while(iter.hasNext() && result == null) {
            Character dude = iter.next();
            if(dude.getTriggerWord().equals(triggerWord)) {
                result = dude.getConversation();
            }
        }
        return result;
    }
    
    /**
     * Clears exits in the room.
     */
    public void clearExits() {
        exits.clear();
    }
}
