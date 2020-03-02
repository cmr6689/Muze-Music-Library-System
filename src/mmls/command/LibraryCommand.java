package mmls.command;

import Database.Item;
import mmls.library.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public abstract class LibraryCommand implements Command {
    protected Library library;
    protected Matcher matcher;
    protected List<Item> results;

    public LibraryCommand(Library library, Matcher matcher, List<Item> results) {
        this.library = library;
        this.matcher = matcher;
        this.results = results;
    }
}
