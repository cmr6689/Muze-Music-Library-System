package mmls.command;

import Database.Database;
import Database.Item;
import Database.Song;
import mmls.command.filters.ArtistNameFilter;
import mmls.command.filters.MaxDurationFilter;
import mmls.command.filters.MinDurationFilter;
import mmls.command.filters.TitleFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class allows the user to search for songs given the parameters
 * of the search and filters the results based on those parameters.
 * @author Shane Burke
 */
public class DatabaseSearchSongCommand extends DatabaseSearchCommand {
    public DatabaseSearchSongCommand(Database database, Matcher matcher, CommandFactory commandFactory) {
        super(database, matcher, commandFactory);
    }

    @Override
    public void executeCommand() {
        Collection<Song> songs = database.getSongs();
        List<Song> results;

        String title = matcher.group("title");
        String artistName = matcher.group("artistName");
        String minDuration = matcher.group("minDuration");
        String maxDuration = matcher.group("maxDuration");

        if (title != null) {
            TitleFilter<Song> titleFilter = new TitleFilter<>();
            results = titleFilter.filter(songs, title.trim());
        } else {
            results = new ArrayList<>(songs);
        }

        if (artistName != null) {
            ArtistNameFilter<Song> artistNameFilter = new ArtistNameFilter<>();
            results = artistNameFilter.filter(results, artistName.trim());
        }

        if (minDuration != null) {
            MinDurationFilter<Song> minDurationFilter = new MinDurationFilter<>();
            results = minDurationFilter.filter(results, minDuration);
        }

        if (maxDuration != null) {
            MaxDurationFilter<Song> maxDurationFilter = new MaxDurationFilter<>();
            results = maxDurationFilter.filter(results, maxDuration);
        }

        List<Item> resultList = new ArrayList<>(results);
        notifyCommandFactory(resultList);
    }
}
