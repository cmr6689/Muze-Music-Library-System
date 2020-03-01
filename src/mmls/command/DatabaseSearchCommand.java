package mmls.command;

import Database.Database;

import java.util.ArrayList;

public class DatabaseSearchCommand implements Command {
    private String keyword;
    private ArrayList<String> arguments;
    private Database database;

    public DatabaseSearchCommand(String keyword, ArrayList<String> arguments, Database database) {
        this.keyword = keyword;
        this.arguments = arguments;
        this.database = database;
    }

    @Override
    public void executeCommand() {

    }
}
