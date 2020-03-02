package mmls.command;

import Database.Database;
import Database.Item;
import Database.Release;
import Database.Song;
import mmls.library.Library;
import mmls.library.LibraryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RemoveCommand extends LibraryCommand implements Command {

    public RemoveCommand(Library library, Database database, Matcher matcher, List<Item> results) {
        super(library, database, matcher, results);
    }

    public void executeCommand() {
        int resultId = Integer.parseInt(matcher.group("id").trim());
        if (resultId >= results.size()) {
            System.out.println("Invalid search result ID. Please enter one of the ID numbers shown above.");
            return;
        }
        Item itemToRemove = results.get(resultId);
        String guid = itemToRemove.getGuid();
        library.removeItem(guid);
    }
}
