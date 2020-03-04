package mmls.command;

import Database.Database;
import Database.Release;
import Database.Song;
import Database.Item;
import Database.Artist;
import mmls.library.Library;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class acts as the ConcreteCreator in the Factory Method pattern. It is responsible for
 * taking in a user request and determining which command to create based on the arguments of the
 * request. It uses search results from a search made by the user to modify the library and/or
 * act on results shown in the command line.
 * @author Shane Burke, Cameron Riu
 */
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

    /**
     * Constructor that intitializes the global library and database for the commands to use
     * @param library passed in from the start of the program
     * @param database passed in from the start of the progeam
     */
    public CommandFactory(Library library, Database database) {
        this.library = library;
        this.database = database;
    }

    /**
     * Uses the Matcher to check the command for matches to the regex statements and then sets
     * that command as the command to be returned and executed
     * The explore command uses a boolean to differentiate whether the explore command is being called a
     * second time to explore the releases by an artist.
     * @param request : inputted by the user
     * @return command being requested by the user
     */
    @Override
    public Command createCommand(String request) {
        Matcher matcher = getMatcherForInput(request);
        Command command = null;
        switch (matcher.pattern().pattern()) {
            case DATABASE_SEARCH_ARTIST_REQUEST_PATTERN:
                command = new DatabaseSearchArtistCommand(database, matcher, this);
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
                command = rate(matcher);
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
                command = new ListCommand(library, this);
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

    private Command rate(Matcher matcher) {
        List<Item> resultsToRate = exploring_artists ? searchResults : releases;
        return new RateCommand(library, database, matcher, resultsToRate);
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

    /**
     * Creates the Matcher to use based on the input
     * @param request inputted by the user
     * @return matcher to use
     */
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

    /**
     * After the user searches, update the list of search results to allow easy library commands
     * @param searchResults list of searched items
     */
    public void updateSearchResults(List<Item> searchResults) {
        this.searchResults = searchResults;
        printSearchResults();
    }

    /**
     * Update the list of search results, and print them with total duration associated
     * with each result
     * @param searchResults list of search results
     * @param durations list of durations (same indices as search results)
     */
    public void updateSearchResults(List<Item> searchResults, List<Long> durations) {
        this.searchResults = searchResults;
        printSearchResultsWithDuration(durations);
    }


    /**
     * Prints the search results so the user may perform actions on the numbered list
     */
    private void printSearchResults() {
        if (searchResults.size() == 0) {
            System.out.println("No results found for the given parameters.");
        } else {
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println(formatSearchResult(i));
            }
        }
    }

    /**
     * Formats a search result for printing (with the provided result ID)
     * @param index
     * @return
     */
    private String formatSearchResult(int index) {
        return (index + ") " + searchResults.get(index));
    }

    private void printSearchResultsWithDuration(List<Long> durations) {
        if (searchResults.size() == 0) {
            System.out.println("No results found for the given parameters.");
        } else {
            DateFormat durationFormatter = new SimpleDateFormat("mm:ss");

            for (int i = 0; i < searchResults.size(); i++) {
                Date durationDate = new Date(durations.get(i));
                String durationFormatted = durationFormatter.format(durationDate);
                System.out.println(formatSearchResult(i) + " | Total library duration: " + durationFormatted);
            }
        }
    }

}
