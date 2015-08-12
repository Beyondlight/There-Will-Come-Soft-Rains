import java.util.HashMap;
/**
 * The player's character in Xelient.
 * 
 * @author Kyle McCloskey
 * @version 4/10/15
 */
public class Character {

    /** The NPC's name. */
    private String name;
    /** The room the NPC is in. */
    private Room room;
    /** A unique word that starts a conversation with an NPC. */
    private String triggerWord;
    /** The NPC's conversation. */
    private Conversation conversation;
    /** Holds items the character can hold. */
    private HashMap<String, Item> items;

    /**
     * Constructs an NPC.
     * 
     * @param name The NPC's name.
     * @param triggerWord The NPC's trigger word.
     * @param conversation The NPC's conversation dialogue.
     */
    public Character(String name, String triggerWord, Conversation conversation) {
        items = new HashMap<String, Item>();
        this.name = name;
        this.room = room;
        this.triggerWord = triggerWord;
        this.conversation = conversation;
    }

    /**
     * The accessor for the NPC's name.
     * 
     * @return The NPC's name.a
     */
    public String getName() {
        return name;
    }

    /**
     * The accessor for the room the NPC is located.
     * 
     * @return The room the NPC is in.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * The accessor for the NPC's trigger word.
     * 
     * @return The NPC's trigger word.
     */
    public String getTriggerWord() {
        return triggerWord;
    }
    
    /**
     * The accessor for the NPC's conversation.
     * 
     * @return The NPC's conversation.
     */
    public Conversation getConversation() {
        return conversation;
    }
    
    /**
     * Adds an item to the Character's "inventory".
     * 
     * @param item The item being given.
     */
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }
    
    /**
     * Removes an item from the Character's "inventory".
     * 
     * @param itemName The item being removed.
     * @return The item being removed.
     */
    public Item removeItem(String itemName) {
        return items.remove(itemName);
    }
}
