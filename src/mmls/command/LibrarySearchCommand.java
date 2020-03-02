package mmls.command;

import Database.Item;
import mmls.library.Library;

import java.util.List;
import java.util.regex.Matcher;

public abstract class LibrarySearchCommand implements Command {
    protected Library library;
    protected Matcher matcher;
    protected CommandFactory commandFactory;

    public LibrarySearchCommand(Library library, Matcher matcher, CommandFactory commandFactory) {
        this.library = library;
        this.matcher = matcher;
        this.commandFactory = commandFactory;
    }

    protected void notifyCommandFactory(List<Item> searchResults) {
        commandFactory.updateSearchResults(searchResults);
    }
}
