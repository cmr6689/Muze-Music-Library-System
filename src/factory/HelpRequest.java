package factory;

import java.util.ArrayList;

public class HelpRequest implements Request{
    private String command;

    public HelpRequest(String command) {
        this.command = command;
    }

    @Override
    public ArrayList<String> invokeRequest() {
        ArrayList<String> possibleCommands = new ArrayList<>();
        possibleCommands.add("search [arguments]");
        possibleCommands.add("library [add/remove] [arguments]");
        return possibleCommands;
    }
}
