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

public class ExploreCommand extends LibraryCommand implements Command {
    public ArrayList<Item> items;

    public ExploreCommand(Library library, Database database, Matcher matcher, List<Item> results, ArrayList<Item> items) {
        super(library, database, matcher, results);
        this.items = items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

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
                    for (Release release : library.getReleases()) {
                        if (release.getArtist().getGuid().equals(artist.getGuid())) {
                            System.out.println(release.toString());
                            items.add(release);
                        }
                    }
                    setItems(items);
                }
            }
        } else {
            for (Release release : library.getReleases()) {
                if (items.get(Integer.parseInt(matcher.group("id"))).getGuid().equals(release.getGuid())) {
                    System.out.println(release.getTitle() + " Tracks");
                    for (int i = 0; i < release.getTracks().size(); i++) {
                        System.out.println(release.getTracks().get(i));
                    }
                }
            }
        }
    }
}
