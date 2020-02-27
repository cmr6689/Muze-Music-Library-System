package mmls.command;

import factory.Request;

import java.util.ArrayList;

public class HelpCommand implements Command {
    private String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand() {
        ArrayList<String> possibleCommands = new ArrayList<>();
        possibleCommands.add("search [arguments]");
        possibleCommands.add("library [add/remove] [arguments]");
    }
}
