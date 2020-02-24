package mmls.command;

import mmls.library.*;

public abstract class LibraryCommand implements Command {
    protected Library library;
    protected LibraryItem targetItem;

    public LibraryCommand(Library library, LibraryItem targetItem) {
        this.library = library;
        this.targetItem = targetItem;
    }
}
