import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * The player in Xelient: Faded Memories.
 * 
 * @author Kyle McCloskey
 * @version 4/15/15
 */
public class Player {
    /** Where the player is at the current time. */
    private Room currentRoom;
    /** The previous room the player was in. */
    private Room previousRoom;
    /** Counts the player's score. */
    private int score;
    /** The items the player currently has. */
    private ArrayList<Item> inventoryItems;
    /** The characters the player can interact with. */
    private ArrayList<Character> characters;
    /** The maximum weight the player can carry. */
    private int maxWeight;
    /** The total weight the player is carrying at any time. */
    private int totalWeight;
    /** The health of the player. */
    private int health;
    /** The Warrior's rage count. */
    private int rage;
    /** The player's name. */
    private String name;
    /** The probability to hit a monster. */
    private int hitProbability;
    /** The damage the player inflicts. */
    private int damage;
    /** The player's equipped weapon. */
    private Weapon equippedWeapon;
    /** The player's equipped piece of armor. */
    private Armor equippedArmor;
    /** Holds a random value. */
    private Random random;

    /**
     * The constructor for the player.
     * 
     * @param currentRoom The starting room of Xelient: Faded Memories.
     */
    public Player(Room currentRoom) {
        inventoryItems = new ArrayList<Item>();
        characters = new ArrayList<Character>();
        BottleCaps bottlecaps = new BottleCaps("Bottle Caps", "Currency of Arcaria", 0, 0);
        inventoryItems.add(bottlecaps);
        score = 0;
        this.currentRoom = currentRoom;
        previousRoom = null;
        maxWeight = 150;
        totalWeight = 0;
        health = 100;
        rage = 100;
        hitProbability = 82;
        name = null;
        random = new Random();
    }

    /**
     * Returns the player's score.
     * 
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds points to the player's total score.
     * 
     * @param scoreAddition The points being added.
     */
    public void addScore(int scoreAddition) {
        score += scoreAddition;
    }

    /**
     * Sets the previous room to the current room to be used for the back method.
     * 
     * @param currentRoom The current room.
     */
    public void setCurrentRoom(Room currentRoom) {
        previousRoom = this.currentRoom;
        this.currentRoom = currentRoom;
    }

    /**
     * Returns the room the player is in.
     * 
     * @return The current room the player is in.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * The directly previous room the player was in.
     * 
     * @return The previous room.
     */
    public Room getPreviousRoom() {
        return previousRoom;
    }

    /**
     * Puts an Item into the player's inventory.
     * 
     * @param item The Item being taken.
     */
    public void takeItem(Item item) {
        int itemWeight = item.getWeight();
        if (totalWeight + itemWeight <= getMaxWeight()) {
            inventoryItems.add(item);
            totalWeight = totalWeight + itemWeight;
        }
    }

    /**
     * Takes an Item out of the player's inventory.
     * 
     * @param itemName The name of the Item being taken out of the inventory.
     * @return The Item being taken out of the inventory.
     */
    public Item dropItem(String itemName) {
        Item returnValue = consumeItem(itemName);
        if (returnValue != null) {
                currentRoom.addItem(returnValue); 
            }             
        return returnValue;
    }

    /**
     * Returns all Items in the player's inventory.
     * 
     * @return The Items in the inventory.
     */
    public String getInventoryItems() {
        String playerInventoryItems = "Inventory: ";
        int itemsPerLine = 5;
        if (!inventoryItems.isEmpty()) {    
            int inventoryCycle = 0;
            for (Item currentItem : inventoryItems) {
                inventoryCycle++;
                if (!currentItem.getName().equals("Quanta")) { 
                    if (inventoryCycle % itemsPerLine == 0) {
                        playerInventoryItems = playerInventoryItems + "\n";
                    }
                    if (inventoryCycle < inventoryItems.size()) {
                        if (currentItem == equippedWeapon) {
                            playerInventoryItems += "(E)";
                        }
                        if (currentItem == equippedArmor) {
                            playerInventoryItems += "(E)";
                        }
                        playerInventoryItems = playerInventoryItems + currentItem.getName() + ", ";
                    }else{
                        if (currentItem == equippedWeapon) {
                            playerInventoryItems += "(E)";
                        }
                        if (currentItem == equippedArmor) {
                            playerInventoryItems += "(E)";
                        }
                        playerInventoryItems = playerInventoryItems + currentItem.getName();                   
                    }
                }
            }
        }
        return playerInventoryItems;
    }

    /**
     * Returns a particular item in the player's inventory.
     * 
     * @param itemName The name of the Item.
     * @return The Item.
     */
    public Item getItem(String itemName) {
        Item returnValue = null;
        boolean foundItem = false;
        int i = 0;
        while(i < inventoryItems.size() && foundItem == false) {
            if (inventoryItems.get(i).getName().equalsIgnoreCase(itemName)) {
                returnValue = inventoryItems.get(i);
                foundItem = true;
            }
            i++;
        }
        return returnValue;
    }

    /**
     * Accessor for a particular NPC in the current room.
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
     * Returns the player's total weight (at the time).
     *
     *@return The player's total weight.
     */
    public int getTotalWeight() {
        return totalWeight;
    }

    /**
     * Returns the maximum weight the player can hold.
     * 
     * @return The maximum weight.
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * The accessor for the player's name.
     * 
     * @return The player's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * The accessor for the player's hit probability.
     * 
     * @return The player's hit probability.
     */
    public double getHitProbability() {
        return hitProbability;
    }

    /**
     * The accessor for the player's Quanta.
     * 
     * @return The amount of Quanta the player has.
     */
    public int getBottleCapAmount() {
        Item item = getItem("Quanta");
        int amount = 0;
        if (item != null) {
            if (item instanceof BottleCaps) {
                BottleCaps bottlecaps = (BottleCaps)item;
                amount = bottlecaps.getAmount();
            }
        }
        return amount;
    }

    /**
     * Removes a particular amount of Quanta from the player. Mainly for purchasing items.
     * 
     * @param price The amount being removed.
     */
    public void removeQuantaAmount(int price) {
        Item item = getItem("Quanta");
        if (item != null) {
            if (item instanceof BottleCaps) {
                BottleCaps bottlecaps = (BottleCaps)item;
                bottlecaps.removeAmount(price);
            }
        }
    }

    /**
     * Gives a particular amount of Quanta to the player.
     * 
     * @param amount The amount being given.
     * @return The amount being given.
     */
    public int addQuantaAmount(int amount) {
        Item item = getItem("Quanta");
        if (item != null) {
            if (item instanceof BottleCaps) {
                BottleCaps bottlecaps = (BottleCaps)item;
                bottlecaps.addAmount(amount);
            }
        }
        return amount;
    }

    /**
     * The accessor for the player's health.
     * 
     * @return The player's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the player's health to a particular value. 
     * 
     * @param health The player's health.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets the particular weapon to be the current weapon equipped by the player.
     * 
     * @param newWeapon The weapon being equipped.
     * @return If the player was successful in this attempt.
     */
    public boolean setEquippedWeapon(Weapon newWeapon) {
        boolean successfulEquip = false;
        if (getItem(newWeapon.getName()) != null) {
            this.equippedWeapon = newWeapon;
            successfulEquip = true;
        }
        return successfulEquip;
    }

    /**
     * The accessor for the equipped weapon.
     * 
     * @return The equipped weapon.
     */
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * Sets the particular piece of armor to be the current piece of armor equipped by the player.
     * 
     * @param newArmor The piece of armor being equipped.
     * @return If the player was successful in this attempt.
     */
    public boolean setEquippedArmor(Armor newArmor) {
        boolean successfulEquip = false;
        if (getItem(newArmor.getName()) != null) {
            this.equippedArmor = newArmor;
            successfulEquip = true;
        }
        return successfulEquip;
    }

    /**
     * The accessor for the equipped piece of armor.
     * 
     * @return The equipped piece of armor.
     */
    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    /**
     * The accessor for how much damage the player will inflict on a monster.
     * 
     * @return The hit points being inflicted.
     */
    public int getDamage() {
        damage = random.nextInt(9) + 1;
        if (equippedWeapon != null) {
            damage += equippedWeapon.getHitPoints();
        }
        return damage;
    }

    /**
     * Accessor for the player's max health.
     * 
     * @return The max health.
     */
    public int getMaxHealth() {
        int maxHealth = 100;
        return maxHealth;
    }
    
    /**
     * Uses an item and removes it entirely from the game.
     * 
     * @param itemName The name of the item being consumed.
     * @return The item being consumed.
     */
     public Item consumeItem(String itemName) {
        Item returnValue = null;
        boolean foundItem = false;
        Iterator<Item> iter = inventoryItems.iterator();
        while (iter.hasNext() && foundItem == false) {
            Item item = iter.next();
            if (item.getName().equalsIgnoreCase(itemName)) {
                returnValue = item;           
                int itemWeight = returnValue.getWeight();
                totalWeight = totalWeight - itemWeight;
                iter.remove();
                foundItem = true;
            }             
        }
        return returnValue;
    }
    
    /**
     * Sets the name of the player.
     * 
     * @param name The name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the maximum weight the player can carry.
     * 
     * @param weight The max value to be set.
     */
    public void setMaxWeight(int weight) {
        this.maxWeight = weight;
    }
}