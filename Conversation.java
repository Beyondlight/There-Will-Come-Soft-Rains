import java.util.HashMap;
import java.util.Scanner;
/**
 * This class implements a simple multiple-choice based conversation between an NPC and the player.
 * It works by keeping a HashMap of replies where each reply contains the NPC's part of the conversation and the choices of responses by the Player.
 * The conversation ends when the Player enters an invalid choice and the NPC gets confused, or the conversation comes to an end with a reply from the NPC containing sign-off text.
 *
 * See the ConversationTest class for an example of how to use this class.
 *
 * @author Maria Jump, Chad Hogg
 * @version 2015-04-03
 */
public class Conversation {
    /** Stores the name of the NPC. */
    private String name;
    /** Stores the custom sign-off text. */
    private String signoff;
    /** Stores the replies for each key. */
    private HashMap<String, String> replies;
    /** The scanner so we can read responses. */
    private Scanner input;

    /**
     * Constructor for objects of class Conversation.
     * 
     * @param theName The name of the NPC.
     * @param theSignoff The custom sign-off text.
     */
    public Conversation(String theName, String theSignoff) {
        name = theName;
        signoff = theSignoff.toLowerCase();
        replies = new HashMap<String, String>();
        input = new Scanner(System.in);
    }

    /**
     * Add a reply to the choices of replies that the NPC can make.
     * Each reply should either contain the sign-off text (to end the conversation), or provide multiple-choice responses for the Player (this is a simple conversation scenario and one we can do easily).
     * See the text in the startConversation method comment as an example.
     * 
     * @param keyword The keyword for each reply.
     * @param reply The associated reply.
     */
    public void addReply(String keyword, String reply) {
        replies.put(keyword, reply);
    }

    /**
     * This method uses the replies you set up using the addReply method to have a conversation with the player.
     * 
     * For example:
     *      Sam: My job here is to help you win.  So what can I do for you?
     *          A: You can give me the key that I need.
     *          B: You can make it so that I win the game right now.
     *          C: You can go jump off a bridge.
     *      Enter the letter of your response: B
     *      Sam: That's easy ... you win!  Goodbye.
     *      
     * @param key The keyword that starts the conversation.
     * @return The key at the stop of the conversation so the game can react if appropriate.
     */
    public String startConversation(String key) {
        boolean done = false;
        String keyValue = key;
        while(!done) {
            keyValue = keyValue.toLowerCase();
            //System.out.println("key: " + keyValue);
            String response = replies.get(keyValue);
            if(response == null) {
                System.out.println(name + " looks confused at your response and stops talking to you.");
                done = true;
            }
            else if(response.toLowerCase().contains(signoff)) {
                System.out.println(name + ": " + response);
                done = true;
            }
            else {
                System.out.println(name + ": " + response);
                //System.out.print("Enter the letter of your response: ");
                String reply = input.next();
                keyValue += reply.trim().toLowerCase();
            }
        }
        return keyValue;
    }
    
    /**
     * Returns the name of the NPC.
     * 
     * @return The NPC's name.
     */
    public String getName() {
        return name;
    }
}