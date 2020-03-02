package mmls.command;

import Database.Item;
import mmls.library.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

public abstract class LibraryCommand implements Command {
    protected Library library;
    protected Matcher matcher;
    protected ArrayList<Item> results;

    public LibraryCommand(Library library, Matcher matcher, ArrayList<Item> results) {
        this.library = library;
        this.matcher = matcher;
        this.results = results;
    }
}
