package mmls.command;

import Database.Item;
import Database.Song;
import mmls.library.Library;
import mmls.library.LibraryItem;

import java.util.regex.Matcher;

public class RemoveCommand implements Command {
    private Library library;
    private Matcher matcher;

    public RemoveCommand(Library library, Matcher matcher) {
        this.library = library;
        this.matcher = matcher;
    }

    public void executeCommand() {
        for (Song song : library.getSongs()) {
            if (matcher.group("id").equals(song.getGuid())) {
                library.removeItem(song.getGuid());
            }
        }
    }
}
