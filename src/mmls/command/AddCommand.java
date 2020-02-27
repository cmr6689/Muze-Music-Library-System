package mmls.command;

import Database.Item;
import mmls.library.Library;
import mmls.library.LibraryItem;

public class AddCommand extends LibraryCommand {
    public AddCommand(Library library, Item targetItem) {
        super(library, targetItem);
    }

    @Override
    public void executeCommand() {

    }
}
