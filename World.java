import java.util.HashMap;
import java.util.Random;
/**
 * This class represents the entire world that makes up the "Xelient: Faded Memories" application.
 * "Xelient: Faded Memories" is a role playing adventure game (need more description when inspired)
 * 
 * This world class creates the world where the game takes place.
 * 
 * @author Maria Jump, Kyle McCloskey
 * @version 2015.02.01
 */
public class World {
    /** The rooms in the world. */
    private HashMap<String, Room> rooms;
    /** The characters in the world. */
    private HashMap<String, Character> characters;
    /** The player. */
    private Player player;
    /** Holds a random value. */
    private Random generator;

    /**
     * Constructor for the world.
     */
    public World() {
        rooms = new HashMap<String, Room>();
        characters = new HashMap<String, Character>();
        generator = new Random();
        createRooms();
        createNPCs();
        createItems();
        createMonsters();
    }

    /**
     * This method takes care of creating all of the aspects of the world
     * for the "Campus of Kings" application.
     * 
     * @param roomName The name of the room.
     * @return The room's name.
     */
    public Room getRoom(String roomName) {
        return rooms.get(roomName);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // Start of private helper methods
    /** Creates NPCs, puts them into a room, and constructs their dialogue. */
    private void createNPCs() {
        // Batchia
        // Booker James, Batchia Inn
        String tWCSRPoem = "You are sure to remember this one sir!\nThis is called, 'There Will Come Soft Rains', by Sara Teasdale.\n" + 
            "\nThere will come soft rains and the smell of the ground,\n" +
            "And swallows circling with their shimmering sound;\n" +
            "\nAnd frogs in the pools singing at night,\n" +
            "And wild plum trees in tremulous white;\n" +
            "\nRobins will wear their feathery fire,\n" +
            "Whistling their whims on a low fence-wire;\n" +
            "\nAnd not one will know of the war, not one\n" +
            "Will care at last when it is done.\n" +
            "\nNot one would mind, neither bird nor tree,\n" +
            "If mankind perished utterly;\n" +
            "\nAnd Spring herself, when she woke at dawn\n" +
            "Would scarcely know that we were gone.\n" +
            "\n... You loved hearing that by Uncle Wadsworth!";   
        Conversation wadsworthTalk = new Conversation("Wadsworth", "Wadsworth");
        wadsworthTalk.addReply("hello", 
            " Hello sir! How are you doing on this beautifully sad day?\n" +
            "   01.) Who are you?\n" +
            "   02.) What can you do?\n" +
            "   03.) What day is it?\n" +
            "\n" +
            "   04.) I have to get going.");
        wadsworthTalk.addReply("hello01", "Why sir, I am Wadsworth, your personal robotic butler!");
        wadsworthTalk.addReply("hello02", "Odd question sir but I can do many things!\nFor instance, when you were a child, you used to love hearing poems!\n" +
            "   01.) (Sit down and listen to a poem)\n" +
            "\n" +
            "   02.) Leave");
        wadsworthTalk.addReply("hello0201", tWCSRPoem);
        wadsworthTalk.addReply("hello0202", "Okay sir, feel free to visit anytime! Wadsworth signing out!");
        wadsworthTalk.addReply("hello03", "Sir, it is February 16th, 2435! Wadsworth signing out!");
        wadsworthTalk.addReply("hello04", "No problem sir, for I am Wadsworth!\nIf you need anything at all, do not hesitate to ask!");
        Character wadsworth = new Character("Wadsworth", "hello", wadsworthTalk);
        rooms.get("Bedroom").addCharacter(wadsworth);
        characters.put("Wadsworth", wadsworth);
        // Moonwell Pass
        //         // Curtron Keysal
        //         Conversation curtronTalk = new Conversation("Curtron Keysal", "Transaction");
        //         curtronTalk.addReply("hello",
        //             "Ey! Would you like to purchase something?\n" +
        //             "   01.) Buy\n" +
        //             "\n" +
        //             "   03.) Leave");
        //         // Weapons
        //         curtronTalk.addReply("hello01", "Good vibes. Feel free to browse!\n" +
        //             "   01.) Weapons\n" +
        //             "   02.) Armor\n" +
        //             "   03.) Items \n" +
        //             "\n" +
        //             "   04.) Leave");
        //         // Wooden Sword
        //         curtronTalk.addReply("hello0101", "Violence man.. Damn.\n" +
        //             "   01.) Wooden Sword (100 Quanta)\n" +
        //             "   02.) Wooden Staff (150 Quanta)\n" +
        //             "\n" +
        //             "   03.) Iron Sword (175 Quanta)\n" +
        //             "   04.) Iron Staff (225 Quanta)\n" +
        //             "   05.) Iron Axe (200 Quanta)\n" +
        //             "\n" +
        //             "   06.) Glass Sword (250 Quanta)\n" +
        //             "   07.) Glass Staff (300 Quanta)\n" +
        //             "   08.) Glass Axe (275 Quanta)\n" +
        //             "\n" +
        //             "   09.) Diamond Sword (450 Quanta)\n" +
        //             "   10.) Diamond Staff (515 Quanta)\n" +
        //             "   11.) Diamond Axe (475 Quanta)\n" +
        //             "\n" +
        //             "   12.) Moonlight Sword (525 Quanta)\n" +
        //             "   13.) Moonlight Staff (600 Quanta)\n" +
        //             "\n" +
        //             "   14.) Leave");
        //         curtronTalk.addReply("hello010101", "A simple wooden sword made of the wilderness's trees\n" +
        //             "\n" +
        //             "Increases damage chance by 10\n" +
        //             "\n" +
        //             "Would you like to buy the Wooden Sword?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01010101", "Transaction complete!");
        //         curtronTalk.addReply("hello01010102", "Transaction ended.");
        //         // Wooden Staff
        //         curtronTalk.addReply("hello010102", "A wooden staff, used for simple offensive attacks\n" +
        //             "\n" +
        //             "Increases damage chance by 20\n" +
        //             "\n" +
        //             "Would you like to buy the Wooden Staff?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01010201", "Transaction complete!");
        //         curtronTalk.addReply("hello01010202", "Transaction ended.");
        //         // Iron Sword
        //         curtronTalk.addReply("hello010103", "One handed sword made of polished iron.\n" +
        //             "\n" +
        //             "Increases damage chance by 25\n" +
        //             "\n" +
        //             "Would you like to buy the Iron Sword?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01010301", "Transaction complete!");
        //         curtronTalk.addReply("hello01010302", "Transaction ended.");
        //         // Iron Staff
        //         curtronTalk.addReply("hello010104", "Iron forged into a staff.\n" +
        //             "\n" +
        //             "Increases damage chance by 45\n" +
        //             "\n" +
        //             "Would you like to buy the Iron Staff?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01010401", "Transaction complete!");
        //         curtronTalk.addReply("hello01010402", "Transaction ended.");
        //         curtronTalk.addReply("hello010105", "An axe made from iron.\n" +
        //             "\n" +
        //             "Increases damage chance by 40\n" +
        //             "\n" +
        //             "Would you like to buy the Iron Axe?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01010501", "Transaction complete!");
        //         curtronTalk.addReply("hello01010502", "Transaction ended.");
        //         curtronTalk.addReply("hello010106", "A thick, green sword made of glass.\n" +
        //             "\n" +
        //             "Increases damage chance by 50\n" +
        //             "\n" +
        //             "Would you like to buy the Glass Sword?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01010601", "Transaction complete!");
        //         curtronTalk.addReply("hello01010602", "Transaction ended.");
        //         curtronTalk.addReply("hello010107", "A thick, amber staff made of glass.\n" +
        //             "\n" +
        //             "Increases damage chance by 60\n" +
        //             "\n" +
        //             "Would you like to buy the Glass Staff?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01010701", "Transaction complete!");
        //         curtronTalk.addReply("hello01010702", "Transaction ended.");
        //         curtronTalk.addReply("hello010108", "An axe, made from blue glass.\n" +
        //             "\n" +
        //             "Increases damage chance by 55\n" +
        //             "\n" +
        //             "Would you like to buy the Glass Axe?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01010801", "Transaction complete!");
        //         curtronTalk.addReply("hello01010802", "Transaction ended.");
        //         curtronTalk.addReply("hello010109", "A diamond sword. It's blocky.\n" +
        //             "\n" +
        //             "Increases damage chance by 90\n" +
        //             "\n" +
        //             "Would you like to buy the Diamond Sword?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01010901", "Transaction complete!");
        //         curtronTalk.addReply("hello01010902", "Transaction ended.");
        //         curtronTalk.addReply("hello010110", "Diamond cut into a staff.\n" +
        //             "\n" +
        //             "Increases damage chance by 110\n" +
        //             "\n" +
        //             "Would you like to buy the Diamond Staff?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01011001", "Transaction complete!");
        //         curtronTalk.addReply("hello01011002", "Transaction ended.");
        //         curtronTalk.addReply("hello010111", "A Diamond Axe.. A DIAMOND AXE.\n" +
        //             "\n" +
        //             "Increases damage chance by 100\n" +
        //             "\n" +
        //             "Would you like to buy the Diamond Axe?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01011101", "Transaction complete!");
        //         curtronTalk.addReply("hello01011102", "Transaction ended.");
        //         curtronTalk.addReply("hello010112", "The Moonlight Sword. Made by the local's\n" +
        //             "ancestors, said to have been crafted in the lights of the moon itself.\n" +
        //             "\n" +
        //             "Increases damage chance by 125\n" +
        //             "\n" +
        //             "Would you like to buy the Moonlight Sword?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01011201", "Transaction complete!");
        //         curtronTalk.addReply("hello01011202", "Transaction ended.");
        //         curtronTalk.addReply("hello010113", "A staff said to have been made from the\n" +
        //             "surface of the moon.\n" +
        //             "\n" +
        //             "Increases damage chance by 140\n" +
        //             "\n" +
        //             "Would you like to buy the Moonlight Staff?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01011301", "Transaction complete!");
        //         curtronTalk.addReply("hello01011302", "Transaction ended.");
        //         // Armor
        //         curtronTalk.addReply("hello0102", "Defense man, what are you afraid of?\n" +
        //             "\n" +
        //             "   01.) Stone Armor (115 Quanta)\n" +
        //             "   02.) Iron Armor (215 Quanta)\n" +
        //             "   03.) Plate Armor (275 Quanta)\n" +
        //             "   04.) Gold Armor (350 Quanta)\n" +
        //             "   05.) Moonstone Armor (500 Quanta)\n" +
        //             "\n" +
        //             "   06.) Leave");
        //         curtronTalk.addReply("hello010201", "Stone slabs to be thrown on the body.\n" +
        //             "\n" +
        //             "Decreases enemy damage by 2 points.\n" +
        //             "\n" +
        //             "Would you like to buy the Stone Armor?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01020101", "Transaction complete!");
        //         curtronTalk.addReply("hello01020102", "Transaction ended.");
        //         curtronTalk.addReply("hello010202", "Shiny iron armor. Much better than stone.\n" +
        //             "\n" +
        //             "Decreases enemy damage by 10 points.\n" +
        //             "\n" +
        //             "Would you like to buy the Iron Armor?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01020201", "Transaction complete!");
        //         curtronTalk.addReply("hello01020202", "Transaction ended.");
        //         curtronTalk.addReply("hello010203", "Body armor made of solid plate.\n" +
        //             "\n" +
        //             "Decreases enemy damage by 20 points.\n" +
        //             "\n" +
        //             "Would you like to buy the Plate Armor?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01020301", "Transaction complete!");
        //         curtronTalk.addReply("hello01020302", "Transaction ended.");
        //         curtronTalk.addReply("hello010204", "Armor made of gold.. Shouldn't be\n" +
        //             "effective, but it is." +
        //             "\n" +
        //             "Decreases enemy damage by 45 points.\n" +
        //             "\n" +
        //             "Would you like to buy the Gold Armor?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01020401", "Transaction complete!");
        //         curtronTalk.addReply("hello01020402", "Transaction ended.");
        //         curtronTalk.addReply("hello010205", "Forged by the surface of the moon,\n" +
        //             "sealed by the moon's light." +
        //             "\n" +
        //             "Decreases enemy damage by 100 points.\n" +
        //             "\n" +
        //             "Would you like to buy the Moonstone Armor?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01020501", "Transaction complete!");
        //         curtronTalk.addReply("hello01020502", "Transaction ended.");
        //         // Items
        //         // Simple Health Potion
        //         curtronTalk.addReply("hello0103", "Take a little bit of Conviviality with you!\n" +
        //             "\n" +
        //             "   01.) Apprentice Health Potion (20 Quanta)\n" +
        //             "   02.) Swift Health Potion (40 Quanta)\n" +
        //             "   03.) Master Health Potion (64 Quanta)\n" +
        //             "\n" +
        //             "   04.) Leave");
        //         curtronTalk.addReply("hello010301", "A common brew with the locals.\n" +
        //             "\n" +
        //             "If drank, 30 health points are restored.\n" +
        //             "\n" +
        //             "Would you like to buy the Apprentice Health Potion?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01030101", "Transaction complete!");
        //         curtronTalk.addReply("hello01030102", "Transaction ended.");
        //         curtronTalk.addReply("hello010302", "A light orange potion.\n" +
        //             "\n" +
        //             "If drank, 50 health points are restored.\n" +
        //             "\n" +
        //             "Would you like to buy the Swift Health Potion?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01030201", "Transaction complete!");
        //         curtronTalk.addReply("hello01030202", "Transaction ended.");
        //         curtronTalk.addReply("hello010303", "A deep red potion.\n" +
        //             "\n" +
        //             "If drank, 100 health points are restored.\n" +
        //             "\n" +
        //             "Would you like to buy the Master Health Potion?\n" +
        //             "\n" +
        //             "   01.) Yes\n" +
        //             "   02.) No");
        //         curtronTalk.addReply("hello01030301", "Transaction complete!");
        //         curtronTalk.addReply("hello01030302", "Transaction ended.");
        //         curtronTalk.addReply("hello03", "Transaction ended.");
        //         curtronTalk.addReply("hello0104", "Transaction ended.");
        //         curtronTalk.addReply("hello010114", "Transaction ended.");
        //         curtronTalk.addReply("hello010206", "Transaction ended.");
        //         curtronTalk.addReply("hello010304", "Transaction ended.");
        //         Merchant curtron = new Merchant("Curtron Keysal", curtronTalk);
        //         rooms.get("The trade store of Moonwell Pass").addCharacter(curtron);
        //         characters.put("Curtron Keysal", curtron);
        //         curtron.addMerchandise("hello01010101", "Wooden Sword", 100);
        //         curtron.addMerchandise("hello01010201", "Wooden Staff", 150);
        //         curtron.addMerchandise("hello01010301", "Iron Sword", 175);
        //         curtron.addMerchandise("hello01010401", "Iron Staff", 225);
        //         curtron.addMerchandise("hello01010501", "Iron Axe", 200);
        //         curtron.addMerchandise("hello01010601", "Glass Sword", 250);
        //         curtron.addMerchandise("hello01010701", "Glass Staff", 300);
        //         curtron.addMerchandise("hello01010801", "Glass Axe", 275);
        //         curtron.addMerchandise("hello01010901", "Diamond Sword", 450);
        //         curtron.addMerchandise("hello01011001", "Diamond Staff", 515);
        //         curtron.addMerchandise("hello01011101", "Diamond Axe", 475);
        //         curtron.addMerchandise("hello01011201", "Moonlight Sword", 525);
        //         curtron.addMerchandise("hello01011301", "Moonlight Staff", 600);
        //         curtron.addMerchandise("hello01020101", "Stone Armor", 115);
        //         curtron.addMerchandise("hello01020201", "Iron Armor", 215);
        //         curtron.addMerchandise("hello01020301", "Plate Armor", 275);
        //         curtron.addMerchandise("hello01020401", "Gold Armor", 350);
        //         curtron.addMerchandise("hello01020501", "Moonstone Armor", 500);
        //         curtron.addMerchandise("hello01030101", "Apprentice Health Potion", 25);
        //         curtron.addMerchandise("hello01030201", "Swift Health Potion", 40);
        //         curtron.addMerchandise("hello01030301", "Master Health Potion", 64);

    }

    /**
     * Adds items to the specified room.
     */
    private void createItems() {
        // Merchant Weapons
        Weapon woodenSword = new Weapon("Wooden Sword", "A simple sword made of wood", 20, 10);
        Weapon woodenStaff = new Weapon("Wooden Staff", "A simple staff made of wood", 25, 20);
        Weapon ironSword = new Weapon("Iron Sword", "One handed sword made of polished iron", 30, 25);
        Weapon ironStaff = new Weapon("Iron Staff", "Iron forged into the shape of a staff", 40, 45);
        Weapon ironAxe = new Weapon("Iron Axe", "An axe made of iron", 35, 40);
        Weapon glassSword = new Weapon("Glass Sword", "A thick, green glass sword", 35, 50);
        Weapon glassStaff = new Weapon("Glass Staff", "A thick, amber glass staff", 45, 60);
        Weapon glassAxe = new Weapon("Glass Axe", "An axe made from blue glass", 40, 55);
        Weapon gamblerSword = new Weapon("Gambler's Sword", "Property of Conviviality", 50, 75);
        Weapon jesterStaff = new Weapon("Jester's Staff", "Property of Conviviality", 60, 85);
        Weapon luckyAxe = new Weapon("Lucky Axe", "Property of Conviviality", 55, 80);
        Weapon diamondSword = new Weapon("Diamond Sword", "It's blocky, thanks Notch", 60, 90);
        Weapon diamondStaff = new Weapon("Diamond Staff", "Owned by one, Snoopy Dogg", 70, 110);
        Weapon diamondAxe = new Weapon("Diamond Axe", "Gimli wishes he could wield this", 65, 100);
        Weapon moonlightSword = new Weapon("Moonlight Sword", "The moon's light flows through this sword", 75, 125);
        Weapon moonlightStaff = new Weapon("Moonlight Staff", "This staff is crafted by the moon's crust", 85, 140);
        //characters.get("Curtron Keysal").addItem(woodenSword);

        // Merchant Armor
        Armor stoneArmor = new Armor("Stone Armor", "Stone slabs to be thrown on the body", 30, 2);
        Armor ironArmor = new Armor("Iron Armor", "Average iron armor", 40, 10);
        Armor plateArmor = new Armor("Plate Armor", "Full body armor made of polished plate", 50, 20);
        Armor goldArmor = new Armor("Gold Armor", "Both incredibly aesthetic and effective", 45, 45);
        Armor moonstoneArmor = new Armor("Moonstone Armor", "Armor crafted from the surface of the moon", 70, 100);
        // characters.get("Curtron Keysal").addItem(moonstoneArmor);
        // Merchant Potions
        Food apprenticeHealthPotion = new Food("Apprentice Health Potion", "A common brew with the locals", 5, 30);
        Food swiftHealthPotion = new Food("Swift Health Potion", "A bright orange liquid in a bottle", 10, 50);
        Food masterHealthPotion = new Food("Master Health Potion", "A deep red potion", 15, 100);
        //characters.get("Curtron Keysal").addItem(masterHealthPotion);

        // Starting zone
        //         Item metalDebris = new Item("Debris", "Metal debris. It is hot to the touch.", 2);
        //         rooms.get("Batchia Forest").addItem(metalDebris);

        // Batchia
        //         Item batchiaSignPost = new Item("Sign", "Trade Store: West, Inn: East", 1);
        //         Item batchiaExitKey = new Item("Rusty Key", "Looks like this hasn't been used in a while", 1);
        //         rooms.get("Batchia").addItem(batchiaSignPost);
        //         rooms.get("Batchia").getExit("north").setKey(batchiaExitKey);

    }

    /**
     * This method creates all of the individual places in this world
     * and all the doors connecting them.
     */
    private void createRooms() {
        // Rooms
        // Starting zone
        Room startingLivingRoom = this.addRoom("Living Room", "in a living room.\nBoth the dining room and kitchen are in view.");
        Room startingDiningRoom = this.addRoom("Dining Room", "in a dining room.\nBy your side is a large stairwell.");
        Room startingKitchen = this.addRoom("Kitchen", "in a kitchen.\nThrough the cracked window, you see the destroyed yard.");
        Room startingUpstairs = this.addRoom("Upstairs Hallway", "in the upstairs.\nYou see a few rooms but only one is able to be entered.");
        Room startingBedroom = this.addRoom("Bedroom", "in a small bedroom.\nIn the corner is a robot assistant.");

        // West Pittston
        Room atlanticAvenueMain = this.addRoom("Atlantic Avenue", "on Atlantic Avenue.\nThe destroyed house you were once in lays in front of you.\nIt isn't safe to stay here much longer, especially\nnot in the middle of the street.");
        Room atlanticAvenueRight = this.addRoom("Atlantic Avenue", "at the intersection of Atlantic Avenue and 4th Street.\nOnce large houses now stand in desolation.");
        Room atlanticWyomingAveEnter = this.addRoom("Wyoming Avenue", "on Wyoming Avenue.\nThe road stretches for miles in each direction.");
        Room baltimoreWyomingAveEnter = this.addRoom("Wyoming Avenue", "on Wyoming Avenue.\nThe road stretches for miles in each direction.");
        Room baltimoreAvenueRight = this.addRoom("Baltimore Avenue", "at the intersection of Baltimore Avenue and 4th Street.\nYou see a main road towards the east.");
        Room bostonWyomingAveEnter = this.addRoom("Boston Avenue", "on Wyoming Avenue.\nAn abandoned grocery store is in sight.");
        Room bostonAvenueLeft = this.addRoom("Boston Avenue", "at the intersection of Boston Avenue and 4th Street.\nThere is an old CVS building that seems to be inhabited.");
        Room bostonAvenueRight = this.addRoom("Boston Avenue", "by a local supermarket.");
        Room gerritysParkingLot = this.addRoom("Parking Lot", "in a grocery store parking lot.\nDecrepit shopping carts are scattered around the cracked pavement.\nThey sit in their own rust.");
        
        // Doors
        // Starting zone
        this.createDoor(startingLivingRoom, "south", atlanticAvenueMain);
        this.createDoor(startingLivingRoom, "north", startingDiningRoom);
        this.createDoor(startingDiningRoom, "south", startingLivingRoom);
        this.createDoor(startingDiningRoom, "north", startingKitchen);
        this.createDoor(startingDiningRoom, "up", startingUpstairs);
        this.createDoor(startingKitchen, "south", startingDiningRoom);
        this.createDoor(startingUpstairs, "east", startingBedroom);
        this.createDoor(startingUpstairs, "down", startingDiningRoom);
        this.createDoor(startingBedroom, "west", startingUpstairs);
        //West Pittston
        this.createDoor(atlanticAvenueMain, "north", startingLivingRoom);
        this.createDoor(atlanticAvenueMain, "east", atlanticAvenueRight);
        this.createDoor(atlanticAvenueRight, "west", atlanticAvenueMain);
        this.createDoor(atlanticAvenueRight, "east", atlanticWyomingAveEnter);
        this.createDoor(atlanticWyomingAveEnter, "west", atlanticAvenueRight);
        this.createDoor(atlanticWyomingAveEnter, "south", baltimoreWyomingAveEnter);
        this.createDoor(baltimoreWyomingAveEnter, "north", atlanticWyomingAveEnter);
        this.createDoor(baltimoreWyomingAveEnter, "south", bostonWyomingAveEnter);
        this.createDoor(baltimoreWyomingAveEnter, "west", baltimoreAvenueRight);
        this.createDoor(baltimoreAvenueRight, "east", baltimoreWyomingAveEnter);
        this.createDoor(baltimoreAvenueRight, "north", atlanticAvenueRight);
        this.createDoor(baltimoreAvenueRight, "south", bostonAvenueLeft);
        this.createDoor(bostonAvenueLeft, "north", baltimoreAvenueRight);
        this.createDoor(bostonAvenueLeft, "east", bostonWyomingAveEnter);
        this.createDoor(bostonWyomingAveEnter, "west", bostonAvenueLeft);
        this.createDoor(bostonWyomingAveEnter, "north", baltimoreWyomingAveEnter);
        this.createDoor(bostonWyomingAveEnter, "east", bostonAvenueRight);
        this.createDoor(bostonAvenueRight, "south", gerritysParkingLot);
        this.createDoor(bostonAvenueRight, "west", bostonWyomingAveEnter);
        this.createDoor(gerritysParkingLot, "north", bostonAvenueRight);
        //this.createDoor(gerritysParkingLot, "east", gerritysEntrance);
    }

    /** Constructs a monster and puts it into a room. */
    private void createMonsters() {
        Room batchiaDeadWilderness = rooms.get("BatchiaDeadWilderness");
        Room deadBatchiaWilderness = rooms.get("DeadBatchiaWilderness");
        Room deadSvalbardWilderness = rooms.get("DeadSvalbardWilderness");
        Room svalbardDeadWilderness = rooms.get("SvalbardDeadWilderness");
        Room svalbardBeaconofAscensionWilderness = rooms.get("SvalbardBeaconofAscensionWilderness");
        Room beaconofAscensionSvalbardWilderness = rooms.get("BeaconofAscensionSvalbardWilderness");
        Room beaconMoonwellPassWilderness = rooms.get("BeaconMoonwellPassWilderness");
        Room moonwellPassBeaconWilderness = rooms.get("MoonwellPassBeaconWilderness");
        Room xelientWilderness = rooms.get("Xelient");

        // Monsters
        Monster bat = new Monster("Forest Bat", 10, 4, 60, 10);
        Monster direWolf = new Monster("Dire Wolf", 15, 6, 57, 15);
        Monster blueSlime = new Monster("Blue Slime", 35, 9, 65, 25);
        Monster redSlime = new Monster("Red Slime", 45, 12, 50, 30);
        Monster caveSpider = new Monster("Cave Spider", 28, 11, 62, 20); 
        Monster goblinWarrior = new Monster("Goblin Warrior", 30, 12, 45, 25);
        Monster goblinChief = new Monster("Goblin Chief", 50, 15, 58, 30); 
        Monster bandit = new Monster("Bandit", 47, 18, 80, 50);
        Monster purpleSlime = new Monster("Purple Slime", 70, 25, 75, 60);
        Monster wonderingSpirit = new Monster("Wondering Spirit", 75, 20, 70, 0); 
        Monster skeletonArcher = new Monster("Skeleton Archer", 60, 30, 45, 40);

        // Xelient
        Monster xelient = new Monster("King Xelient", 500, 110, 75, 0);

        if (batchiaDeadWilderness instanceof RandomMaze) {
            RandomMaze wilderness = (RandomMaze)batchiaDeadWilderness;
            wilderness.addMonster(bat);
            wilderness.addMonster(direWolf);
            wilderness.addMonster(blueSlime);
        }

        if (deadBatchiaWilderness instanceof RandomMaze) {
            RandomMaze wilderness = (RandomMaze)deadBatchiaWilderness;
            wilderness.addMonster(bat);
            wilderness.addMonster(direWolf);
            wilderness.addMonster(blueSlime);
        }

        if (deadSvalbardWilderness instanceof RandomMaze) {
            RandomMaze wilderness = (RandomMaze)deadSvalbardWilderness;
            wilderness.addMonster(bat);
            wilderness.addMonster(direWolf);
            wilderness.addMonster(blueSlime);
            wilderness.addMonster(redSlime);
            wilderness.addMonster(caveSpider);
            wilderness.addMonster(goblinWarrior);
        }

        if (svalbardDeadWilderness instanceof RandomMaze) {
            RandomMaze wilderness = (RandomMaze)svalbardDeadWilderness;
            wilderness.addMonster(bat);
            wilderness.addMonster(direWolf);
            wilderness.addMonster(blueSlime);
            wilderness.addMonster(redSlime);
            wilderness.addMonster(caveSpider);
            wilderness.addMonster(goblinWarrior);
        }

        if (svalbardBeaconofAscensionWilderness instanceof RandomMaze) {
            RandomMaze wilderness = (RandomMaze)svalbardBeaconofAscensionWilderness;
            wilderness.addMonster(blueSlime);
            wilderness.addMonster(redSlime);
            wilderness.addMonster(goblinChief);
            wilderness.addMonster(goblinWarrior);
            wilderness.addMonster(bandit);
        }

        if (beaconofAscensionSvalbardWilderness instanceof RandomMaze) {
            RandomMaze wilderness = (RandomMaze)beaconofAscensionSvalbardWilderness;
            wilderness.addMonster(blueSlime);
            wilderness.addMonster(redSlime);
            wilderness.addMonster(goblinChief);
            wilderness.addMonster(goblinWarrior);
            wilderness.addMonster(bandit);
        }

        if (beaconMoonwellPassWilderness instanceof RandomMaze) {
            RandomMaze wilderness = (RandomMaze)beaconMoonwellPassWilderness;
            wilderness.addMonster(redSlime);
            wilderness.addMonster(goblinChief);
            wilderness.addMonster(goblinWarrior);
            wilderness.addMonster(bandit);
            wilderness.addMonster(purpleSlime);
            wilderness.addMonster(wonderingSpirit);
            wilderness.addMonster(skeletonArcher);
        }

        if (moonwellPassBeaconWilderness instanceof RandomMaze) {
            RandomMaze wilderness = (RandomMaze)moonwellPassBeaconWilderness;
            wilderness.addMonster(redSlime);
            wilderness.addMonster(goblinChief);
            wilderness.addMonster(goblinWarrior);
            wilderness.addMonster(bandit);
            wilderness.addMonster(purpleSlime);
            wilderness.addMonster(wonderingSpirit);
            wilderness.addMonster(skeletonArcher);
        }

        if (xelientWilderness instanceof RandomMaze) {
            RandomMaze wilderness = (RandomMaze)xelientWilderness;
            wilderness.addMonster(xelient);
        }
    }

    /**
     * Helper method for recreating a Room.  Ensure that the room is created
     * and installed in to the collection of Rooms
     * @param name The name for the new room.
     * @param description The description for the new room.
     * @return The Room that was created.
     */
    private Room addRoom(String name, String description) {
        Room aRoom = new Room(name, description);
        rooms.put(name, aRoom);
        return aRoom;
    }

    /**
     * Helper method for recreating a Room.  Ensure that the room is created
     * and installed in to the collection of Rooms
     * @param name The name for the new room.
     * @param description The description for the new room.
     * @param requiredCount The required amount of screens required to exit the Wilderness.
     * @return The Room that was created.
     */
    private Room addRandomMaze(String name, String description, int requiredCount) {
        Room aRoom = new RandomMaze(name, description, requiredCount);
        rooms.put(name, aRoom);
        return aRoom;
    }

    /**
     * Helper method for creating doors between rooms.
     * @param from The room where you are creating the door.
     * @param direction The direction of the exit for the door.
     * @param to The room in the direction provided.
     */
    private void createDoor(Room from, String direction, Room to) {
        if (direction != null) {
            Door door = new Door(to);
            from.setExit(direction, door);
        }
    }
}

