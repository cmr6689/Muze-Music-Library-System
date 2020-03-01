package mmls.command;

import Database.Item;
import mmls.library.Library;

import java.util.List;
import java.util.regex.Matcher;

public abstract class LibrarySearchCommand extends SearchCommand {
    protected Library library;
    protected Matcher matcher;
    protected CommandFactory commandFactory;

    public LibrarySearchCommand(Library library, Matcher matcher, CommandFactory commandFactory) {
        super(matcher, commandFactory);
        this.library = library;
    }
}
