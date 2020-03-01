package mmls.command;

import Database.Item;
import mmls.library.Library;
import mmls.library.LibraryItem;

public class RemoveCommand implements Command {
    private Library library;
    private String guid;

    public RemoveCommand(Library library, String guid) {
        this.library = library;
        this.guid = guid;
    }

    public void executeCommand() {
        library.removeItem(this.guid);
    }
}
