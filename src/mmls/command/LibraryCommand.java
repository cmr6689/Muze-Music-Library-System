package mmls.command;

import Database.Database;
import Database.Item;
import mmls.library.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public abstract class LibraryCommand implements Command {
    protected Library library;
    protected Database database;
    protected Matcher matcher;
    protected List<Item> results;

    public LibraryCommand(Library library, Database database, Matcher matcher, List<Item> results) {
        this.library = library;
        this.matcher = matcher;
        this.results = results;
    }
}
