package mmls.command;


import java.util.ArrayList;

/**
 * This class’ only responsibility is to print out the list of possible commands and how to use them.
 * @author Cameron Riu, Shane Burke
 */
public class HelpCommand implements Command {
    private String command;

    /**
     * Creates the command
     */
    public HelpCommand() {}

    /**
     * Prints out the list of possible commands
     */
    @Override
    public void executeCommand() {
        System.out.println("The available commands are listed below.");
        System.out.println("Fields enclosed in brackets [] are optional.");
        System.out.println("Fields enclosed in curly braces {} are required.");
        System.out.println("Fields separated by | accept either one of the enclosed options.");
        System.out.println("All dates must be formatted \"yyyy-mm-dd\".");
        System.out.println("For \"sort method\" fields, these are the following options:");
        System.out.println("  * 1 - Sort alphabetically (default)");
        System.out.println("  * 2 - Sort by rating (descending)");
        System.out.println("  * 3 - Sort by acquisition date (ascending)");
        System.out.println("  * 4 - Sort by release date (ascending) [Note: This feature is for release searches only]");
        System.out.println("--- Available commands ---");
        System.out.println("database search artist [keywords]");
        System.out.println("database search song [-t <title>] [-an <artist name>] [-mind <min. duration>] [-maxd <max. duration>]");
        System.out.println("database search release [-t <title>] ([-an <artist name>] | [-aid <artist GUID>]) ([-tn <track name>] | [-tid <track GUID>]) [-dr <earliest date> <latest date>]");
        System.out.println("library search artist [-n <name>] [-t <type>] [-mr <min. rating>]");
        System.out.println("library search song [-t <title>] ([-an <artist name>] | [-aid <artist GUID>]) ([-rt <release title>] | [-rid <release GUID>]) [-mind <min. duration>] [-maxd <max. duration>] [-mr <min. rating>] [-s <sort method>]");
        System.out.println("library search release [-t <title>] ([-an <artist name>] | [-aid <artist GUID>]) ([-tn <track name>] | [-tid <track GUID>]) [-mind <min. duration>] [-maxd <max. duration>] [-mr <min. rating>] [-s <sort method>]");
        System.out.println("library add {song/release} {<search result ID>} [date=<acquisition date>] [rating=<rating>]");
        System.out.println("library remove <search result ID>");
        System.out.println("rate <search result ID> rating=<rating>");
        System.out.println("explore <search result ID>");
        System.out.println("library list");
        System.out.println("back");
    }
}
