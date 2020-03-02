package mmls.command;

import Database.Item;

import java.util.List;
import java.util.regex.Matcher;

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
