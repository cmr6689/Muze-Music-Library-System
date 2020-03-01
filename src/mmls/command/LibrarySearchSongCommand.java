package mmls.command;

import Database.Artist;
import Database.Item;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;

public class LibrarySearchSongCommand extends LibrarySearchCommand {
    public LibrarySearchSongCommand(Library library, Matcher matcher, CommandFactory commandFactory) {
        super(library, matcher, commandFactory);
    }

    @Override
    public void executeCommand() {
        Collection<Artist> artists = library.getArtists();
        List<Artist> results;

        String name = matcher.group("title");
        String type = matcher.group("artistName");
        String minDuration = matcher.group("minDuration");
        String maxDuration = matcher.group("maxDuration");

        if (name != null) {
            results = filterByName(artists, name);
        } else {
            results = new ArrayList<>(artists);
        }

        if (type != null) {
            results = filterByType(results, type);
        }

        if (minDuration != null) {
            double minRatingDouble = Integer.parseInt(minDuration);
            results = filterByMinRating(results, minRatingDouble);
        }

        List<Item> resultList = new ArrayList<>(results);
        commandFactory.updateSearchResults(resultList);
    }
}
