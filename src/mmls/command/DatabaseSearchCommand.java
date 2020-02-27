package mmls.command;

import java.util.ArrayList;

public class DatabaseSearchCommand implements Command {
    private String keyword;
    private ArrayList<String> arguments;

    public DatabaseSearchCommand(String keyword, ArrayList<String> arguments) {
        this.keyword = keyword;
        this.arguments = arguments;
    }

    @Override
    public void executeCommand() {

    }
}
