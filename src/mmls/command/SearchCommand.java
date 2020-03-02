package mmls.command;

import Database.Item;

import java.util.List;
import java.util.regex.Matcher;

/**
 * The responsibility of the abstract search command class is to provide
 * the constructor that gives the subclasses the matcher and CommandFactory
 * objects.
 * @author Shane Burke, Cameron Riu
 */
public abstract class SearchCommand implements Command {
    protected Matcher matcher;
    protected CommandFactory commandFactory;

    public SearchCommand(Matcher matcher, CommandFactory commandFactory) {
        this.matcher = matcher;
        this.commandFactory = commandFactory;
    }

    protected void notifyCommandFactory(List<Item> searchResults) {
        commandFactory.updateSearchResults(searchResults);
    }
}
