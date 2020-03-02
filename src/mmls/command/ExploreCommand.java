package mmls.command;

import Database.Artist;
import Database.Database;
import Database.Item;
import Database.Release;
import Database.Song;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * This class is responsible for letting the user explore an artist by displaying the songs and
 * the releases by that artist. The user can also explore the releases of the artist which will
 * list the track names.
 * @author Cameron Riu, Shane Burke
 */
public class ExploreCommand extends LibraryCommand implements Command {
    public ArrayList<Item> items;


    /**
     * Constructor that initializes all objects used by the command
     * @param library : global library
     * @param database : global database
     * @param matcher : used to give regex groups
     * @param results : search results from a search
     * @param items : the list of releases that can be explored further
     */
    public ExploreCommand(Library library, Database database, Matcher matcher, List<Item> results, ArrayList<Item> items) {
        super(library, database, matcher, results);
        this.items = items;
    }

    /**
     * Set the item list for releases each time a new artist is explored
     * @param items : list of releases
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Getter for the list of releases
     * @return list of releases
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * This method loops through the artists in the library to find the requested search result
     * then prints out all the artists songs and releases. The releases are stored in a list and
     * displayed as search results to the user who can then explore those releases.
     */
    @Override
    public void executeCommand() {
        if (items.size() <= 0) {
            for (Artist artist : library.getArtists()) {
                if (results.get(Integer.parseInt(matcher.group("id"))).getGuid().equals(artist.getGuid())) {
                    System.out.println("\n" + artist.getName() + " Songs:");
                    for (Song song : library.getSongs()) {
                        if (song.getArtist().getGuid().equals(artist.getGuid())) System.out.println(song.toString());
                    }
                    System.out.println();
                    System.out.println(artist.getName() + " Releases: (releases may be explored)");
                    int i = 0;
                    for (Release release : library.getReleases()) {
                        if (release.getArtist().getGuid().equals(artist.getGuid())) {
                            System.out.println(i + ") " + release.toString());
                            items.add(release);
                            i++;
                        }
                    }
                    setItems(items);
                }
            }
        } else {
            for (Release release : library.getReleases()) {
                if (items.get(Integer.parseInt(matcher.group("id"))).getGuid().equals(release.getGuid())) {
                    System.out.println(release.getName() + " Tracks");
                    for (int i = 0; i < release.getTracks().size(); i++) {
                        System.out.println(release.getTracks().get(i));
                    }
                }
            }
        }
    }
}
