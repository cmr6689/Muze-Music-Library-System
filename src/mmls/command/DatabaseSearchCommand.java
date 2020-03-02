package mmls.command;

import Database.Database;
import Database.Item;
import mmls.library.Library;

import java.util.List;
import java.util.regex.Matcher;

/**
 * The responsibility of this abstract class is to also give the global
 * database to the subclasses extending the functionality of the
 * SearchCommand class.
 * @author Shane Burke
 */
public abstract class DatabaseSearchCommand extends SearchCommand {
    protected Database database;

    public DatabaseSearchCommand(Database database, Matcher matcher, CommandFactory commandFactory) {
        super(matcher, commandFactory);
        this.database = database;
    }
}
