
/**
 * A list of available commands.
 * 
 * @author Kyle McCloskey  
 * @version 2/28/15
 */
public enum CommandEnum {
    /** Displays the room's description, with exits. */
    LOOK("look"),

    /** Enters a particular room. */
    GO("go"),

    /** Exits the game. */
    QUIT("quit"),

    /** Displays the available commands. */
    HELP("help"),

    /** Returns the player to the previous room they were in. */
    BACK("back"),

    /** Displays the current score. */
    SCORE("score"),

    /** Displays how many commands the player has made. */
    TIME("time"),

    /** Displays the room's description, exits, score, and time. */
    STATUS("status"),

    /** Unlocks a door. */
    UNLOCK("unlock"),

    /** Examines a selected object. */
    EXAMINE("examine"),

    /** Takes items out of a ComplexItem. */
    UNPACK("unpack"),

    /** Puts items into a ComplexItem. */
    PACK("pack"),
    
    /** Puts an item into the player's inventory. */
    TAKE("take"),
    
    /** Takes an item out of the player's inventory. */
    DROP("drop"),
    
    /** Displays the player's inventory. */
    INVENTORY("inventory"),
    
    /** Starts a conversation with an NPC. */
    SAY("say"),
    
    /** Attacks a monster in battle. */
    ATTACK("attack"),
    
    /** Equips an item from the player's inventory. */
    EQUIP("equip"),
   
    /** Consumes food. */
    CONSUME("consume");
    
    /**
     * The string variable holder for the command word.
     */
    private String commandWord;

    /**
     * The constructor of the enumeration.
     * 
     * @param commandWord The command word.
     */
    private CommandEnum(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Accessor for the name of the CommandEnum object.
     * 
     * @return The command word.
     */
    public String getName() {
        return commandWord;
    }
}
