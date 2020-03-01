package mmls.command;


import java.util.ArrayList;

public class HelpCommand implements Command {
    private String command;

    public HelpCommand() {}

    @Override
    public void executeCommand() {
        System.out.println("ALL POSSIBLE COMMANDS:");
        System.out.println("database search artist [keywords]");
        System.out.println("database search song [title] [artist name] [min duration] [max duration] [min rating]");
        System.out.println("database search release [title] [artist name] [artist GUID] [track name] [track GUID] [min date] [max date]");
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
