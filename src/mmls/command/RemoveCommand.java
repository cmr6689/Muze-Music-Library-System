package mmls.command;

import Database.Item;
import Database.Release;
import Database.Song;
import mmls.library.Library;
import mmls.library.LibraryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RemoveCommand extends LibraryCommand implements Command {

    public RemoveCommand(Library library, Matcher matcher, List<Item> results) {
        super(library, matcher, results);
    }

    public void executeCommand() {
        boolean found = false;
        for (Song song : library.getSongs()) {
            if (results.get(Integer.getInteger(matcher.group("id"))).getGuid().equals(song.getGuid())) {
                library.removeSong(song.getGuid());
                found = true;
            }
        }
        if (!found) {
            for (Release release : library.getReleases()) {
                if (results.get(Integer.getInteger(matcher.group("id"))).getGuid().equals(release.getGuid())) {
                    library.removeRelease(release.getGuid());
                }
            }
        }
    }
}
