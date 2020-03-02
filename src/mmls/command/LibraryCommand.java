package mmls.command;

import Database.Database;
import Database.Item;
import mmls.library.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * This abstract class provides the constructor for all commands that act within or on the library
 * by providing the global library and database, the matcher used in the CommandFactory, and the
 * search results from a user search.
 * @author Shane Burke, Cameron Riu
 */
public abstract class LibraryCommand implements Command {
    protected Library library;
    protected Database database;
    protected Matcher matcher;
    protected List<Item> results;

    /**
     * Constructor that initializes all objects used by any library command
     * @param library : global library
     * @param database : global database
     * @param matcher : used to give regex groups
     * @param results : search results from a search
     */
    public LibraryCommand(Library library, Database database, Matcher matcher, List<Item> results) {
        this.library = library;
        this.database = database;
        this.matcher = matcher;
        this.results = results;
    }
}
