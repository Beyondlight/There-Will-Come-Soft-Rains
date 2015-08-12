import java.util.HashMap;
import java.util.Set;
/**
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognize commands as they are typed in.
 * 
 * @author Maria Jump
 * @version 2015.02.01
 */

public class CommandWords {
    /** The constant for the number of commands on a line. */
    private static final int LINE_COUNT = 5;
    /** The HashMap that holds all valid command words. */
    private HashMap<String, CommandEnum> validCommands;

    /**
     * Constructor - initialize the command words.
     */
    public CommandWords() {
        validCommands = new HashMap<String, CommandEnum>();
        for (CommandEnum commandEnum : CommandEnum.values()) {
            validCommands.put(commandEnum.getName(), commandEnum);
        }
    }

    /**
     * Check whether a given String is a valid command word.
     * 
     * @param aString The string to determine whether it is a valid command.
     * @return true if a given string is a valid command, false if it isn't.
     */
    public boolean isCommand(String aString) {
        boolean valid = false;
        int i = 0;
        if (validCommands.containsKey(aString)) {
            valid = true;
        }
        // if we get here, the string was not found in the commands
        return valid;
    }

    /**
     * Return a list of the available commands.
     * 
     * @return A string containing the list of available commands.
     */
    public String getCommandString() {
        String words = "";
        Set<String> availableCommands = validCommands.keySet();
        int wordCount = 0;
        words += "\n";
        for(String currentKey : availableCommands) {
            wordCount++;
            if (wordCount % LINE_COUNT == 0) {
                words = words + "\n";
            }
            if (wordCount < availableCommands.size()) {
                words = words + currentKey + ", ";
            }else{
                words = words + currentKey;
            }
        }
        return words;
    }

    /**
     * Convert a string into a CommandEnum object.
     *
     * @param aString The string containing the command word.
     * @return The CommandEnum object representing the command. */
    public CommandEnum getCommand(String aString) {
        return validCommands.get(aString);
    }
}

