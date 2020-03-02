package mmls.command;

import Database.Database;
import Database.Release;
import Database.Song;
import Database.Item;
import Database.Artist;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandFactory implements Factory {
    // e.g. search library artist The Beatles
    private static final String DATABASE_SEARCH_ARTIST_REQUEST_PATTERN = "^database search artist (?<keywords>[\\w]+[\\S ]*)$";
    private static final String DATABASE_SEARCH_SONG_REQUEST_PATTERN = "^database search song(?: -t (?<title>[\\S]+[\\S ]*?))??(?: -an (?<artistName>[\\S]+[\\S ]*?))??(?: -mind (?<minDuration>[0-9]+?))??(?: -maxd (?<maxDuration>[0-9]+?))??$";
    private static final String DATABASE_SEARCH_RELEASE_REQUEST_PATTERN = "^database search release(?: -t (?<title>[\\S]+[\\S ]*?))??(?:(?: -an (?<artistName>[\\S]+[\\S ]*?))|(?: -aid (?<artistGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?:(?: -tn (?<trackName>[\\S]+[\\S ]*?))|(?: -tid (?<trackGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?: -dr (?<minDate>[0-9]{4}-[0-9]{2}-[0-9]{2}) (?<maxDate>[0-9]{4}-[0-9]{2}-[0-9]{2}))??$";
    private static final String LIBRARY_SEARCH_ARTIST_REQUEST_PATTERN = "^library search artist(?: -n (?<name>[\\S]+[\\S ]*?))??(?: -t (?<type>[\\S]+[\\S ]*?))??(?: -mr (?<minRating>[1-5]))??$";
    private static final String LIBRARY_SEARCH_SONG_REQUEST_PATTERN = "^library search song(?: -t (?<title>[\\S]+[\\S ]*?))??(?:(?: -an (?<artistName>[\\S]+[\\S ]*?))|(?: -aid (?<artistGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?:(?: -rt (?<releaseTitle>[\\S]+[\\S ]*?))|(?: -rid (?<releaseGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?: -mind (?<minDuration>[0-9]+?))??(?: -maxd (?<maxDuration>[0-9]+?))??(?: -mr (?<minRating>[1-5]))??(?: -s (?<sortBy>[1-3]))??$";
    private static final String LIBRARY_SEARCH_RELEASE_REQUEST_PATTERN = "^library search release(?: -t (?<title>[\\S]+[\\S ]*?))??(?:(?: -an (?<artistName>[\\S]+[\\S ]*?))|(?: -aid (?<artistGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?:(?: -tn (?<trackName>[\\S]+[\\S ]*?))|(?: -tid (?<trackGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?: -mind (?<minDuration>[0-9]+?))??(?: -maxd (?<maxDuration>[0-9]+?))??(?: -mr (?<minRating>[1-5]))??(?: -s (?<sortBy>[1-4]))??$";

    // e.g. library add 19 date=02/04/2020 rating=5
    private static final String ADD_REQUEST_PATTERN =  "^library add (?<type>song|release) (?<id>[0-9]+)(?: date=(?<date>[0-9]{2}/[0-9]{2}/[0-9]{4}))?(?: rating=(?<rating>[1-5]{1}))?$";

    /// e.g. rate 14 rating=2
    private static final String RATE_REQUEST_PATTERN =  "^rate (?<id>[0-9]+) rating=(?<rating>[1-5])$";

    // e.g. library remove 3
    private static final String REMOVE_REQUEST_PATTERN =  "^library remove (?<id>[0-9]+)$";

    // e.g. explore 4
    private static final String EXPLORE_REQUEST_PATTERN =  "^explore (?<id>[0-9]+)$";

    private static final String BACK_REQUEST_PATTERN = "^back$";
    private static final String HELP_REQUEST_PATTERN = "^help";
    private static final String LIBRARY_LIST_REQUEST_PATTERN = "^library list$";

    private static final Pattern[] patterns = {
            Pattern.compile(DATABASE_SEARCH_ARTIST_REQUEST_PATTERN),
            Pattern.compile(DATABASE_SEARCH_SONG_REQUEST_PATTERN),
            Pattern.compile(DATABASE_SEARCH_RELEASE_REQUEST_PATTERN),
            Pattern.compile(LIBRARY_SEARCH_ARTIST_REQUEST_PATTERN),
            Pattern.compile(LIBRARY_SEARCH_SONG_REQUEST_PATTERN),
            Pattern.compile(LIBRARY_SEARCH_RELEASE_REQUEST_PATTERN),
            Pattern.compile(ADD_REQUEST_PATTERN),
            Pattern.compile(RATE_REQUEST_PATTERN),
            Pattern.compile(REMOVE_REQUEST_PATTERN),
            Pattern.compile(EXPLORE_REQUEST_PATTERN),
            Pattern.compile(BACK_REQUEST_PATTERN),
            Pattern.compile(HELP_REQUEST_PATTERN),
            Pattern.compile(LIBRARY_LIST_REQUEST_PATTERN)
    };

    private Library library;
    private Database database;
    private List<Item> searchResults;
    private ArrayList<Item> releases = new ArrayList<Item>();
    private Boolean exploring_artists = true;

    public CommandFactory(Library library, Database database) {
        this.library = library;
        this.database = database;
    }
    @Override
    public Command createCommand(String request) {
        Matcher matcher = getMatcherForInput(request);
        Command command = null;
        switch (matcher.pattern().pattern()) {
            case DATABASE_SEARCH_ARTIST_REQUEST_PATTERN:
                break;
            case DATABASE_SEARCH_SONG_REQUEST_PATTERN:
                command = new DatabaseSearchSongCommand(database, matcher, this);
                break;
            case DATABASE_SEARCH_RELEASE_REQUEST_PATTERN:
                command = new DatabaseSearchReleaseCommand(database, matcher, this);
                break;
            case LIBRARY_SEARCH_ARTIST_REQUEST_PATTERN:
                command = new LibrarySearchArtistCommand(library, matcher, this);
                break;
            case LIBRARY_SEARCH_SONG_REQUEST_PATTERN:
                command = new LibrarySearchSongCommand(library, matcher, this);
                break;
            case LIBRARY_SEARCH_RELEASE_REQUEST_PATTERN:
                command = new LibrarySearchReleaseCommand(library, matcher, this);
                break;
            case ADD_REQUEST_PATTERN:
                command = new AddCommand(library, database, matcher, searchResults);
                break;
            case RATE_REQUEST_PATTERN:
                command = new RateCommand(library, database, matcher, searchResults);
                break;
            case REMOVE_REQUEST_PATTERN:
                command = new RemoveCommand(library, database, matcher, searchResults);
                break;
            case EXPLORE_REQUEST_PATTERN:
                command = explore(matcher);
                break;
            case BACK_REQUEST_PATTERN:
                command = back();
                break;
            case HELP_REQUEST_PATTERN:
                command = new HelpCommand();
                break;
            case LIBRARY_LIST_REQUEST_PATTERN:
                break;
        }

        return command;
    }

    private Command explore(Matcher matcher) {
        if (exploring_artists) {
            return exploreArtists(matcher);
        } else {
            return exploreReleases(matcher);
        }
    }

    private Command exploreReleases(Matcher matcher) {
        ExploreCommand exploreCommand = new ExploreCommand(library, database, matcher, searchResults, releases);
        return exploreCommand;
    }

    private Command exploreArtists(Matcher matcher) {
        ExploreCommand command = new ExploreCommand(library, database, matcher, searchResults, releases);
        releases = command.getItems();

        exploring_artists = false;
        return command;
    }

    private Command back() {
        Command command = null;
        if (!exploring_artists) {
            exploring_artists = true;
            System.out.println("Exploring artists:");
            printSearchResults();
            releases.clear();
            command = makeEmptyCommand();
        }
        return command;
    }

    private Command makeEmptyCommand() {
        return () -> { };
    }

    private Matcher getMatcherForInput(String request) {
        Matcher matcher = null;
        for (Pattern pattern : patterns) {
            matcher = pattern.matcher(request);
            if (matcher.matches()) {
                return matcher;
            }
        }
        return matcher;
    }

    public void updateSearchResults(List<Item> searchResults) {
        this.searchResults = searchResults;
        printSearchResults();
    }

    private void printSearchResults() {
        if (searchResults.size() == 0) {
            System.out.println("No results found for the given parameters.");
        } else {
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println(i + ") " + searchResults.get(i));
            }
        }
    }

}
