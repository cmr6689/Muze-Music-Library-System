package mmls.command;

import mmls.library.Library;
import mmls.library.LibraryItem;

public class RemoveCommand extends LibraryCommand {
    public RemoveCommand(Library library, LibraryItem targetItem) {
        super(library, targetItem);
    }

    @Override
    public void executeCommand() {

    }
}
