package mmls.command;

import Database.Database;
import Database.Item;
import mmls.library.Library;

import java.util.List;
import java.util.regex.Matcher;

public abstract class DatabaseSearchCommand extends SearchCommand {
    protected Database database;

    public DatabaseSearchCommand(Database database, Matcher matcher, CommandFactory commandFactory) {
        super(matcher, commandFactory);
        this.database = database;
    }
}
