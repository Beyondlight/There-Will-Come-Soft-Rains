import java.util.Random;
/**
 * This class is the main class of the "Xelient: Faded Memories" application.
 * "Campus of Kings" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 * 
 * This game class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Maria Jump
 * @author Kyle McCloskey
 * @version 2015.02.01
 */

public class Game {
    /** The parser for getting input from. */
    private Parser parser;
    /** The world where the game takes place. */
    private World world;
    /** The previous room the player was in. */
    private Player player;
    /** Counts every command the player makes. */
    private int turnCounter;
    /** Whether the player is in a battle or not. */
    private boolean inBattle;
    /** Holds a random value. */
    private Random generator;
    /** Holds a monster. */
    private Monster monster;

    /**
     * Create the game and initialize its internal map.
     */
    public Game() {
        parser = new Parser();
        world = new World();
        player = new Player(world.getRoom("Living Room"));
        // set the starting room
        turnCounter = 0;
        inBattle = false;
        generator = new Random();
        monster = null;
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();
        // Enter the main game loop. Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            turnCounter++;
            if (inBattle == true) {
                finished = battle();           
                System.out.println("");
                System.out.printf("Health: %d\t%s Health: %d\n", player.getHealth(), monster.getName(), monster.getHealth());              
            }
        }
        printGoodbye();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println("  There Will Come Soft Rains");
        System.out.println(" ----------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("");
        printLocationInformation();
    }

    /**
     * Print out the closing message for the player.
     */
    private void printGoodbye() {
        System.out.println("You have earned " + player.getScore() + " points in " + turnCounter + " turns."); 
        System.out.println("War never changes.");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command
     *            The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
        } else {

            CommandEnum commandWord = command.getCommandWord();

            switch(commandWord) {
                case HELP:
                printHelp();
                break;

                case GO:
                goRoom(command);
                break;

                case QUIT:
                wantToQuit = quit(command);
                break;

                case LOOK:
                look();
                break;

                case BACK:
                back();
                break;

                case SCORE:
                System.out.println();
                System.out.println("Score: " + player.getScore());                         
                System.out.println("");
                break;

                case TIME:
                System.out.println();
                System.out.println("Time: " + turnCounter);
                System.out.println("");
                break;

                case STATUS:
                System.out.println();
                System.out.println("Score: " + player.getScore());
                System.out.println("Time: " + turnCounter);
                look();
                break;

                case UNLOCK:
                unlockRoom(command);
                break;

                case EXAMINE:
                examineItem(command);
                break;

                case UNPACK:
                unpackItem(command);
                break;

                case PACK:
                packItem(command);
                break;

                case TAKE:
                takeItem(command);
                break;

                case DROP:
                dropItem(command);
                break;

                case INVENTORY:
                displayInventory();
                break; 

                case SAY:
                startConversation(command);
                break;

                case ATTACK:
                wantToQuit = playerAttack();
                break;

                case EQUIP:
                equipItem(command);
                break;
                
                case CONSUME:
                consume(command);
                break;

                default:
                System.out.println("Not implemented yet!");
                break;

            }
        }
        return wantToQuit;
    }

    /**
     * Print out some help information. Here we print some stupid (arguable), cryptic
     * message and a list of the command words.
     */
    private void printHelp() {
        System.out.println("You find shelter and retreat to your thoughts...");
        System.out.println();
        System.out.println("Your command words are: " + parser.getCommandString());
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     * 
     * @param command
     *            The command to be processed.
     */
    private void goRoom(Command command) {
        if (inBattle == false) {
            if (!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Go where?");
            } else {
                String direction = command.getRestOfLine();
                // Try to leave current .
                Door doorway = player.getCurrentRoom().getExit(direction);
                if (doorway == null ) {
                    System.out.println("There is no door!");
                } else if (doorway.isLocked() == false) {
                    player.setCurrentRoom(doorway.getDestination());
                    Room currentRoom = doorway.getDestination();
                    if (currentRoom instanceof RandomMaze) {
                        monster = ((RandomMaze)currentRoom).generateMonster();
                        if(monster != null) {
                            inBattle = true;
                            System.out.println();
                            System.out.println("You encountered an enemy!");
                        }
                    }
                    printLocationInformation();
                } else {
                    System.out.println("The door is locked.");
                }
            }
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we
     * really quit the game.
     *
     * @param command
     *            The command to be processed.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        boolean wantToQuit = true;
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            wantToQuit = false;
        }
        return wantToQuit;
    }

    /**
     * Print out information of where the player is at the current time.
     */
    private void printLocationInformation(){
        System.out.println("");
        if (inBattle == false) {
            //System.out.printf("Health: %d\tBottle Caps: %d\n", player.getHealth(), player.getBottleCapAmount());
            System.out.printf("Health: %d\n", player.getHealth());
            System.out.println("");
            System.out.println("You are " + player.getCurrentRoom().getDescription());
            System.out.println(player.getCurrentRoom().getExitString());
            System.out.println(player.getCurrentRoom().getItemString());
            System.out.println(player.getCurrentRoom().getNPCString());
        }      
        System.out.print("");
    }

    /**
     * Print out the description of the room and exits. 
     */
    private void look() {
        if (inBattle == false) {
            printLocationInformation();
        }else{
            System.out.println("You are in a battle!");
        }
    }

    /**
     * Return to the previous room.
     */
    private void back() {
        if (inBattle == false) {
            if (player.getPreviousRoom() == null) {
                System.out.println("You cannot go back!");
                System.out.println("");
            }else{
                player.setCurrentRoom(player.getPreviousRoom());
            }
            printLocationInformation();
        }else{
            System.out.println("You are in a battle!");
        }
    }

    /**
     * Unlocks the selected room.
     * 
     * @param command The command to be processed.
     */
    private void unlockRoom(Command command) {  
        if (inBattle == false) {
            if (!command.hasSecondWord()) {
                System.out.println("Unlock what?");
            } else {
                String direction = command.getRestOfLine();
                Door doorway = player.getCurrentRoom().getExit(direction);
                if (doorway == null) {
                    System.out.println("There is no door!");
                } else if (doorway.isLocked() == true) {
                    System.out.println("With what?");
                    String response = parser.getResponse(true);  
                    if (player.getItem(response) == doorway.getKey()) {
                        doorway.setLocked(false);
                        System.out.println("You unlocked the door.");
                        System.out.println("");
                        player.dropItem(response);
                    } else {
                        System.out.println("You can not use the " + response);
                    }
                } else {
                    System.out.println("The door is already unlocked.");
                }
            }
        }else{
            System.out.println("You are in a battle!");
        }
    }

    /**
     * Examines the selected item.
     * 
     * @param command The command to be processed.
     */
    private void examineItem(Command command) {
        if (inBattle == false) {
            if (!command.hasSecondWord()) {
                System.out.println("Examine what?");
            }else{
                String itemName = command.getRestOfLine();
                Item currentItem = player.getCurrentRoom().getItem(itemName);
                if (currentItem != null) {
                    System.out.println(currentItem);
                }else{
                    System.out.println("You do not see a " + itemName);
                }
            }
        }else{
            System.out.println("You are in a battle!");
        }
    }

    /**
     * Takes the Items out of the ComplexItem.
     * 
     * @param command The command to be processed.
     */
    private void unpackItem(Command command) {
        if (inBattle == false) {
            if (!command.hasSecondWord()) {
                System.out.println("Unpack what?");
            }else{
                String itemName = command.getRestOfLine();
                Item containedItems = null;
                Item returnedItem = null;
                containedItems = player.getCurrentRoom().getItem(itemName);
                if (containedItems instanceof ComplexItem) {
                    ComplexItem newContainedItem = (ComplexItem)containedItems;
                    String[] containedItemNames = newContainedItem.getItemNames();
                    for (String currentItemName : containedItemNames) {
                        returnedItem = newContainedItem.removeItem(currentItemName);
                        player.getCurrentRoom().addItem(returnedItem);
                    }
                    System.out.println("You unpacked the " + itemName);
                }else {
                    System.out.println("There is nothing within the " + itemName);
                }
            }
        }else{
            System.out.println("You are in a battle!");
        }
    }

    /**
     * Puts an item into the selected ComplexItem.
     * 
     * @param command The command to be processed.
     */
    private void packItem(Command command) {
        if (inBattle == false) {
            if (!command.hasSecondWord()) {
                System.out.println("Pack what?");
            }else{
                String itemName = command.getRestOfLine();
                Item item = null;
                Item complexItem = null;
                item = player.getCurrentRoom().getItem(itemName);
                if (item != null) {
                    System.out.println("What would you like to put the " + itemName + " into?");
                    String complexItemName = parser.getResponse(true);  
                    complexItem = player.getCurrentRoom().getItem(complexItemName);
                    if (complexItem == null) {
                        System.out.println("You do not see a " + complexItemName);
                    } 
                    else if (complexItem instanceof ComplexItem) {
                        ComplexItem actualComplexItem = (ComplexItem)complexItem;
                        actualComplexItem.addItem(item);
                        player.getCurrentRoom().removeItem(itemName);
                        System.out.println("You put the " + itemName + " into the " + complexItemName);
                    }
                }
                else {
                    System.out.println("You do not see a " + itemName);
                }
            }
        }else{
            System.out.println("You are in a battle!");
        }
    }

    /**
     * Puts an Item into the player's inventory.
     * 
     * @param command The command to be processed.
     */
    private void takeItem(Command command) {
        if (inBattle == false) {
            if (!command.hasSecondWord()) {
                System.out.println("Take what?");
            }else{
                String itemName = command.getRestOfLine();
                Item item = null;
                item = player.getCurrentRoom().getItem(itemName);
                if (item != null) {
                    if ((player.getTotalWeight() + item.getWeight()) <= player.getMaxWeight()) {
                        player.takeItem(item);
                        player.getCurrentRoom().removeItem(itemName);
                        System.out.println("You picked up the " + itemName);
                    }else{
                        System.out.println("You can't hold anymore items");   
                    }
                }else{
                    System.out.println("You do not see a " + itemName);
                }
            }
        }else{
            System.out.println("You are in a battle!");
        }
    }

    /**
     * Takes an Item out of the player's inventory.
     * 
     * @param command The command to be processed.
     */
    private void dropItem(Command command) {
        if (inBattle == false) {
            if (!command.hasSecondWord()) {
                System.out.println("Drop what?");
            }else{
                String itemName = command.getRestOfLine();
                Item item = null;
                item = player.getItem(itemName);
                if (item != null) {
                    player.dropItem(itemName);
                    System.out.println("You dropped the " + itemName);
                }else{
                    System.out.println("You do not have a " + itemName);
                }
            }
        }else{
            System.out.println("You are in a battle!");
        }
    }

    /**
     * Displays the player's inventory.
     */
    private void displayInventory() {
        System.out.println(player.getInventoryItems());
        System.out.println("Weight: " + player.getTotalWeight());
    }

    /**
     * Starts a conversation with an NPC.
     * 
     * @param command The command to be processed.
     */
    private void startConversation(Command command) {                                
        Item batchiaPass = player.getItem("Batchia Pass");
        Item deadPass = player.getItem("Dead End City Pass");
        Item svalbardPass = player.getItem("Svalbard Pass");
        Item convivialityPass = player.getItem("Conviviality Pass");
        Item moonwellPassPass = player.getItem("Moonwell Pass Pass");
        if (!command.hasSecondWord()) {
            System.out.println("Say what?");
        }else{
            String triggerWord = command.getRestOfLine();
            Room currentRoom = player.getCurrentRoom();
            if(currentRoom.hasNPCs()) {
                Conversation conversation = currentRoom.getConversation(triggerWord);
                if (conversation == null) {
                    System.out.println("Maybe try saying hello?");
                }
                else{
                    Character npc = currentRoom.getNPC(conversation.getName());  
                    String lastKey = conversation.startConversation(triggerWord);
                    if (conversation.getName().equals("Booker James")) {
                        Item rustyKey = currentRoom.getNPC("Booker James").removeItem("Rusty Key");
                        if (lastKey.equals("hello01") || lastKey.equals("hello0201")) {
                            if (player.getItem("Rusty Key") == null) {
                                player.takeItem(rustyKey);
                                System.out.println();
                                System.out.println("You receive the Rusty Key");
                            }else{
                                System.out.println();
                                System.out.println("You already received the Rusty Key");
                            }
                        }
                    }
                    if (conversation.getName().equals("Harold the Drunken Chancellor")) {
                        if (lastKey.equals("hello01")) {
                            Item silverKey = currentRoom.getNPC("Harold the Drunken Chancellor").removeItem("Silver Key");
                            if (player.getItem("Silver Key") == null) {
                                player.takeItem(silverKey);
                                System.out.println("");
                                System.out.println("You receive the Silver Key");
                            }else{
                                System.out.println();
                                System.out.println("You already received the Silver Key"); 
                            }
                        }     
                    }
                    if (conversation.getName().equals("Master Roth")) {
                        if (lastKey.equals("hello01")) {
                            player.setMaxWeight(200);
                            System.out.println();
                            System.out.println("Your weight capacity increased to 200!");
                        }
                    }
                    if (conversation.getName().equals("Batchia Chariot")) {
                        batchiaPass = currentRoom.getNPC("Batchia Chariot").removeItem("Batchia Pass");
                        if (player.getItem("Batchia Pass") == null) {
                            player.takeItem(batchiaPass);
                        }else{
                            if (lastKey.equals("hello0101")) {
                                if (deadPass != null) {
                                    player.setCurrentRoom(world.getRoom("Dead End City"));
                                    look();
                                }else{
                                    System.out.println("Sorry, looks like you don't have that pass.");
                                }
                            }
                            if (lastKey.equals("hello0201")) {
                                if (svalbardPass != null) {
                                    player.setCurrentRoom(world.getRoom("The boat port into Svalbard"));
                                    look();
                                }else{
                                    System.out.println("Sorry, looks like you don't have that pass.");
                                }
                            }
                            if (lastKey.equals("hello0301")) {
                                if (convivialityPass != null) {
                                    player.setCurrentRoom(world.getRoom("The floating casino town, Conviviality"));
                                    look();
                                }else{
                                    System.out.println("Sorry, looks like you don't have that pass.");
                                }
                            }
                            if (lastKey.equals("hello0401")) {
                                if (moonwellPassPass != null) {
                                    player.setCurrentRoom(world.getRoom("The first 1/3rd of Moonwell Pass"));
                                    look();
                                }else{
                                    System.out.println("Sorry, looks like you don't have that pass.");
                                }
                            }
                        }
                    }

                    if (conversation.getName().equals("Dead End City Chariot")) {
                        deadPass = currentRoom.getNPC("Dead End City Chariot").removeItem("Dead End City Pass");
                        if (player.getItem("Dead End City Pass") == null) {
                            player.takeItem(deadPass);
                        }else{
                        if (lastKey.equals("hello0101")) {
                            if (batchiaPass != null) {
                                player.setCurrentRoom(world.getRoom("Batchia"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0201")) {
                            if (svalbardPass != null) {
                                player.setCurrentRoom(world.getRoom("The boat port into Svalbard"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0301")) {
                            if (convivialityPass != null) {
                                player.setCurrentRoom(world.getRoom("The floating casino town, Conviviality"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0401")) {
                            if (moonwellPassPass != null) {
                                player.setCurrentRoom(world.getRoom("The first 1/3rd of Moonwell Pass"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                    }
                }

                if (conversation.getName().equals("Svalbard Chariot")) {
                    svalbardPass = currentRoom.getNPC("Svalbard Chariot").removeItem("Svalbard Pass");
                    if (player.getItem("Svalbard Pass") == null) {
                        player.takeItem(svalbardPass);
                    }else{                    
                        if (lastKey.equals("hello0101")) {
                            if (batchiaPass != null) {
                                player.setCurrentRoom(world.getRoom("Batchia"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0201")) {
                            if (deadPass != null) {                          
                                player.setCurrentRoom(world.getRoom("Dead End City"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0301")) {
                            if (convivialityPass != null) {
                                player.setCurrentRoom(world.getRoom("The floating casino town, Conviviality"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0401")) {
                            if (moonwellPassPass != null) {
                                player.setCurrentRoom(world.getRoom("The first 1/3rd of Moonwell Pass"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                    }
                }

                if (conversation.getName().equals("Conviviality Chariot")) {
                    convivialityPass = currentRoom.getNPC("Conviviality Chariot").removeItem("Conviviality Pass");                    
                    if (player.getItem("Conviviality Pass") == null) {
                        player.takeItem(convivialityPass);
                    }else{
                        if (lastKey.equals("hello0101")) {
                            if (batchiaPass != null) {
                                player.setCurrentRoom(world.getRoom("Batchia"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0201")) {
                            if (deadPass != null) {
                                player.setCurrentRoom(world.getRoom("Dead End City"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0301")) {
                            if (svalbardPass != null) {
                                player.setCurrentRoom(world.getRoom("The boat port into Svalbard"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0401")) {
                            if (moonwellPassPass != null) {
                                player.setCurrentRoom(world.getRoom("The first 1/3rd of Moonwell Pass"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                    }
                }

                if (conversation.getName().equals("Moonwell Pass Chariot")) {
                    moonwellPassPass = currentRoom.getNPC("Moonwell Pass Chariot").removeItem("Moonwell Pass Pass");
                    if (player.getItem("Moonwell Pass Pass") == null) {
                        player.takeItem(moonwellPassPass);
                    }else{
                        if (lastKey.equals("hello0101")) {
                            if (batchiaPass != null) {
                                player.setCurrentRoom(world.getRoom("Batchia"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0201")) {
                            if (deadPass != null) {
                                player.setCurrentRoom(world.getRoom("Dead End City"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0301")) {
                            if (svalbardPass != null) {
                                player.setCurrentRoom(world.getRoom("The boat port into Svalbard"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                        if (lastKey.equals("hello0401")) {
                            if (convivialityPass != null) {
                                player.setCurrentRoom(world.getRoom("The floating casino town, Conviviality"));
                                look();
                            }else{
                                System.out.println("Sorry, looks like you don't have that pass.");
                            }
                        }
                    }
                }
            
                if (npc instanceof Merchant) {
                    Merchant currentMerchant = (Merchant)npc;
                    Item soldItem = currentMerchant.getItem(lastKey);    
                    if (soldItem != null) {
                        int price = currentMerchant.getPrice(soldItem.getName());
                        if (player.getBottleCapAmount() >= price) {
                            if ((player.getTotalWeight() + soldItem.getWeight()) < player.getMaxWeight()) {
                                player.removeQuantaAmount(price);
                                player.takeItem(soldItem);
                            }else{
                                System.out.println("You can't carry anymore items!");
                            }
                        }else{
                            System.out.println("You do not have enough Quanta!");
                        }
                    }
                }
            }
        }
            else{
                System.out.println("There is nobody here to talk to.");
            }
        }
    }

    /**
     * Attacks a monster.
     * 
     * @return If the player has won the game.
     */
    private boolean playerAttack() {
        boolean win = false;
        if (inBattle == true) {
            int hit = generator.nextInt(100);
            if (hit < player.getHitProbability()) {
                int playerHit = player.getDamage();
                System.out.println();
                System.out.println("You hit for " + playerHit + " points!");
                monster.setHealth(monster.getHealth() - playerHit);
                if (monster.getHealth() <= 0) {
                    if (!monster.getName().equals("King Xelient")) {
                        System.out.println("You defeated the " + monster.getName() + "!");
                        System.out.println("You gained " + monster.getQuantaAmount() + " Quanta!");
                        int quantaDrop = monster.getQuantaAmount();
                        player.addQuantaAmount(quantaDrop);
                        player.addScore(quantaDrop);
                        inBattle = false;                        
                    }else{
                        System.out.println();
                        System.out.println("Congratulations! You defeated King Xelient!");
                        System.out.println();
                        System.out.println("You saved Arcaria from devastation, hero!");
                        System.out.println();
                        inBattle = false;
                        win = true;
                    }
                }
            }else{
                System.out.println("You missed!");
            }
        }else{
            System.out.println("You can't attack anything!");
        }
        return win;
    }

    /**
     * A battle with the monster's attack.
     * 
     * @return If the player has died.
     */
    private boolean battle() {
        boolean playerDied = false;
        if (player.getHealth() > 0) {
            if (monster.getHealth() > 0) {
                int hit = generator.nextInt(100);
                if (hit < monster.getHitProbability()) {
                    int monsterHit = monster.getDamage();
                    //System.out.println("\nInitial monster hit " + monsterHit);
                    if (player.getEquippedArmor() != null) {                        
                        int damageReduction = player.getEquippedArmor().getHealthEffect();
                        //System.out.println("Armor reduction " + damageReduction);
                        monsterHit -= damageReduction;
                    }
                    if (monsterHit < 0) {
                        monsterHit = 0;
                    }
                    System.out.println(monster.getName() + " hits for " + monsterHit + " points.");
                    player.setHealth(player.getHealth() - monsterHit);
                }else{               
                    System.out.println(monster.getName() + " missed!");
                }
            }
        }
        if (player.getHealth() <= 0) {
            playerDied = true;
            System.out.println();
            System.out.println("You will live on in the memories of the living...");
        }
        return playerDied;
    }

    /**
     * Equips a weapon or piece of armor in the player's inventory.
     * 
     * @param command The weapon or armor to be equipped.
     */
    private void equipItem(Command command) {  
        if (!command.hasSecondWord()) {
            System.out.println("Equip what?");
        }else{
            String itemName = command.getRestOfLine();
            Item item = null;
            item = player.getItem(itemName);
            if (item != null) {
                if (item instanceof Weapon) {
                    Weapon equippedWeapon = (Weapon)item;
                    boolean returnStatement = player.setEquippedWeapon(equippedWeapon);
                    if (returnStatement == true) {
                        System.out.println("You equipped the " + equippedWeapon.getName());
                    }               
                }
                if (item instanceof Armor) {
                    Armor equippedArmor = (Armor)item;
                    boolean returnStatement = player.setEquippedArmor(equippedArmor);
                    if (returnStatement == true) {
                        System.out.println("You equipped the " + equippedArmor.getName());
                    }
                }
            }else{
                System.out.println("You do not have a " + itemName);
            }
        }      
    }

    /**
     * Drinks a particular potion.
     * 
     * @param command The potion to be drank.
     */
    private void consume(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Consume what?");
        }else{
            String drinkName = command.getRestOfLine();
            Item item = null;
            item = player.getItem(drinkName);
            if (item instanceof Food) {
                Food consumed = (Food)item;
                if (player.getHealth() != player.getMaxHealth()) {
                    player.setHealth(player.getHealth() + consumed.getHealthEffect());
                    player.consumeItem(consumed.getName());
                    System.out.println("You healed " + consumed.getHealthEffect() + " points of damage!");
                }else{
                    System.out.println("You already have full health!");
                }
                if (player.getHealth() > player.getMaxHealth()) {
                    player.setHealth(player.getMaxHealth());
                }
                System.out.println("Health: " + player.getHealth());
            }
        }
    }
}