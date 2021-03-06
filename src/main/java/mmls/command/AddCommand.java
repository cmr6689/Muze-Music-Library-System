package mmls.command;

import Database.Database;
import Database.Item;
import Database.Release;
import Database.Song;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

/**
 * This class is responsible for adding a song or a release to the library based on the search
 * results and the number input by the user in the request.
 * @author Cameron Riu, Shane Burke, Ryan Borger
 */
public class AddCommand extends LibraryCommand implements Command {

    /**
     * Constructor that initializes all objects used by the command
     * @param library : global library
     * @param database : global database
     * @param matcher : used to give regex groups
     * @param results : search results from a search
     */
    public AddCommand(Library library, Database database, Matcher matcher, List<Item> results) {
        super(library, database, matcher, results);
    }

    /**
     * This method uses the matcher to identify whether the user is adding a song or a release and
     * loops through the songs in the database to find the matching song/release from the requested
     * search results and adds it to the library.
     */
    @Override
    public void executeCommand() {
        int resultId = Integer.parseInt(matcher.group("id").trim());
        if (resultId >= results.size()) {
            System.out.println("Invalid search result ID. Please enter one of the ID numbers shown above.");
            return;
        }

        if (matcher.group("type").equals("song")) {
            for (Song song : database.getSongs()) {
                if (results.get(resultId).getGuid().equals(song.getGuid())) {
                    System.out.println("SONG FOUND");
                    if (matcher.group("date") != null) {
                        Date date = new Date(matcher.group("date"));
                        library.addSong(song, date);
                    } else {
                        library.addSong(song);
                    }
                    if (matcher.group("rating") != null) {
                        song.setRating(Double.parseDouble(matcher.group("rating")));
                    }
                }
            }
        } else if (matcher.group("type").equals("release")) {
            for (Release release : database.getReleases()) {
                if (results.get(resultId).getGuid().equals(release.getGuid())) {
                    if (matcher.group("date") != null) {
                        Date date = new Date(matcher.group("date"));
                        library.addRelease(release, date);
                    } else {
                        library.addRelease(release, new Date());
                    }
                    if (matcher.group("rating") != null) {
                        release.setRating(Double.parseDouble(matcher.group("rating")));
                    }
                }
            }
        }
    }
}
