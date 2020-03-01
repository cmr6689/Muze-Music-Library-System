package mmls.command;

import Database.Item;
import Database.Release;
import Database.Song;
import mmls.library.Library;
import mmls.library.LibraryItem;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class RemoveCommand implements Command {
    private Library library;
    private Matcher matcher;
    private ArrayList<Item> items;

    public RemoveCommand(Library library, Matcher matcher, ArrayList<Item> items) {
        this.library = library;
        this.matcher = matcher;
        this.items = items;
    }

    public void executeCommand() {
        boolean found = false;
        for (Song song : library.getSongs()) {
            if (items.get(Integer.getInteger(matcher.group("id"))).getGuid().equals(song.getGuid())) {
                library.removeItem(song.getGuid());
                found = true;
            }
        }
        if (!found) {
            for (Release release : library.getReleases()) {
                if (items.get(Integer.getInteger(matcher.group("id"))).getGuid().equals(release.getGuid())) {
                    library.removeItem(release.getGuid());
                }
            }
        }
    }
}
