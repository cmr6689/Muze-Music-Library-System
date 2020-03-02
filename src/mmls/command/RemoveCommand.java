package mmls.command;

import Database.Database;
import Database.Item;
import Database.Release;
import Database.Song;
import mmls.library.Library;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * This class is responsible for removing songs or releases from the library based on a search
 * result list that the user can choose from.
 * @author Cameron Riu, Shane Burke
 */
public class RemoveCommand extends LibraryCommand implements Command {

    /**
     * Constructor that initializes all objects used by the command
     * @param library : global library
     * @param database : global database
     * @param matcher : used to give regex groups
     * @param results : search results from a search
     */
    public RemoveCommand(Library library, Database database, Matcher matcher, List<Item> results) {
        super(library, database, matcher, results);
    }

    /**
     * This method loops through the songs in the library first and if the requested search result
     * matches the song it deletes it from the library. If no song is found then it checks releases.
     */
    public void executeCommand() {
        boolean found = false;
        for (Song song : library.getSongs()) {
            if (results.get(Integer.parseInt(matcher.group("id"))).getGuid().equals(song.getGuid())) {
                library.removeSong(song.getGuid());
                found = true;
            }
        }
        if (!found) {
            for (Release release : library.getReleases()) {
                if (results.get(Integer.parseInt(matcher.group("id"))).getGuid().equals(release.getGuid())) {
                    library.removeRelease(release.getGuid());
                }
            }
        }
    }
}
