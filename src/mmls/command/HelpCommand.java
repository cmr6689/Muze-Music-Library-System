package mmls.command;


import java.util.ArrayList;

public class HelpCommand implements Command {
    private String command;

    public HelpCommand() {}

    @Override
    public void executeCommand() {
        System.out.println("The available commands are listed below.");
        System.out.println("Fields enclosed in brackets [] are optional.");
        System.out.println("Fields enclosed in curly braces {} are required.");
        System.out.println("Fields separated by | accept either one of the enclosed options.");
        System.out.println("All dates must be formatted \"yyyy-mm-dd\".");
        System.out.println("For \"sort by\" fields, these are the following options:");
        System.out.println("  * 1 - Sort alphabetically");
        System.out.println("  * 2 - Sort by rating (descending)");
        System.out.println("  * 3 - Sort by acquisition date (ascending)");
        System.out.println("  * 4 - Sort by release date (ascending) [Note: releases only!]");
        System.out.println("--- Available commands ---");
        System.out.println("database search artist [keywords]");
        System.out.println("database search song [-t <title>] [-an <artist name>] [-mind <min. duration>] [-maxd <max. duration>]");
        System.out.println("database search release [-t <title>] ([-an <artist name>] | [-aid <artist GUID>]) ([-tn <track name>] | [-tid <track GUID>]) [-dr <earliest date> <latest date>]");
        System.out.println("library search artist [name] [type] [min rating]");
        System.out.println("library search song [title] [artist name] [artist GUID] [release title] [release GUID] [min duration] [max duration] [min rating]");
        System.out.println("library search release [title] [artist name] [artist GUID] [track title] [track GUID] [min duration] [max duration] [min rating]");
        System.out.println("library add [song/release] [date] [rating]");
        System.out.println("library remove [GUID]");
        System.out.println("rate [GUID] [rating]");
        System.out.println("explore [guid]");
        System.out.println("library list");
        System.out.println("back");
    }
}
