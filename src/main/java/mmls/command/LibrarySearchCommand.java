package mmls.command;

import Database.Item;
import mmls.library.Library;

import java.util.List;
import java.util.regex.Matcher;

/**
 * The responsibility of this abstract class is to also give the global
 * library to the subclasses extending the functionality of the
 * SearchCommand class.
 * @author Shane Burke, Cameron Riu
 */
public abstract class LibrarySearchCommand extends SearchCommand {
    protected Library library;

    public LibrarySearchCommand(Library library, Matcher matcher, CommandFactory commandFactory) {
        super(matcher, commandFactory);
        this.library = library;
    }
}
