package mmls.command;

import Database.Database;
import Database.Item;
import mmls.library.Library;

import java.util.List;
import java.util.regex.Matcher;

public abstract class DatabaseSearchCommand implements Command {
    protected Database database;
    protected Matcher matcher;
    protected CommandFactory commandFactory;

    public DatabaseSearchCommand(Database database, Matcher matcher, CommandFactory commandFactory) {
        this.database = database;
        this.matcher = matcher;
        this.commandFactory = commandFactory;
    }

    protected void notifyCommandFactory(List<Item> searchResults) {
        commandFactory.updateSearchResults(searchResults);
    }
}
