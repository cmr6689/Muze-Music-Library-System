package mmls.command;

import Database.Item;
import mmls.library.*;

public abstract class LibraryCommand implements Command {
    protected Library library;
    protected String guid;

    public LibraryCommand(Library library, String guid) {
        this.library = library;
        this.guid = guid;
    }
}
