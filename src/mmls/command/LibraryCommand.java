package mmls.command;

import mmls.library.*;

public abstract class LibraryCommand implements Command {
    private Library library;
    private LibraryItem targetItem;

    public LibraryCommand(Library library, LibraryItem targetItem) {
        this.library = library;
        this.targetItem = targetItem;
    }
}
