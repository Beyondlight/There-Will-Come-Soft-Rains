/**
 * Class Door - a door or portal between two Rooms in an adventure game.
 * 
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 * 
 * A "Door" represents a door or portal between two locations of the game.
 * It stores a reference to the neighboring room and whether that door
 * or portal is locked.  Doors are not locked by default.
 * 
 * @author Maria Jump
 * @version 2015.02.01
 */
public class Door {

    /** The room that this door leads to. */
    private Room destination;
    /** Whether this door is locked. */
    private boolean locked;
    /** The item that is contained in the door. */
    private Item key;

    /**
     * Constructor for the Door class.
     * @param destination The room this door leads to
     */
    public Door(Room destination) {
        this.destination = destination;
        this.locked = false;
        key = null;
    }

    /**
     * A getter for the room this door leads to.
     * @return The room this door leads to
     */
    public Room getDestination() {
        return destination;
    }

    /**
     * A getter for whether this door is locked.
     * @return Whether this door is locked.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * A setter for whether this door is locked.
     * @param locked Whether this door is locked.
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * A setter for a door holds a key.
     * 
     * @param key The key for the door.
     */
    public void setKey(Item key) {
        this.key = key;
    }

    /**
     * A getter for the door's key.
     * 
     * @return Returns the door's key.
     */
    public Item getKey() {
        return key;
    }
}

