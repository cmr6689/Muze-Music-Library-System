package mmls.command;

import Database.Database;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandFactory implements Factory {
    // e.g. search library artist The Beatles
    private static final String DATABASE_SEARCH_ARTIST_REQUEST_PATTERN = "^database search artist (?<keywords>[\\w]+[\\S ]*)$";
    private static final String DATABASE_SEARCH_SONG_REQUEST_PATTERN = "^database search song(?: -t (?<title>[\\S]+[\\S ]*?))??(?: -an (?<artistName>[\\S]+[\\S ]*?))??(?: -mind (?<minDuration>[0-9]+?))??(?: -maxd (?<maxDuration>[0-9]+?))??(?: -mr (?<minRating>[1-5]{1}))??$";
    private static final String DATABASE_SEARCH_RELEASE_REQUEST_PATTERN = "^database search release(?: -t (?<title>[\\S]+[\\S ]*?))??(?:(?: -an (?<artistName>[\\S]+[\\S ]*?))|(?: -aid (?<artistGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?:(?: -tn (?<trackName>[\\S]+[\\S ]*?))|(?: -tid (?<trackGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?: -dr (?<minDate>[0-9]{4}-[0-9]{2}-[0-9]{2}) (?<maxDate>[0-9]{4}-[0-9]{2}-[0-9]{2}))??$";
    private static final String LIBRARY_SEARCH_ARTIST_REQUEST_PATTERN = "^library search artist(?: -n (?<name>[\\S]+[\\S ]*?))??(?: -t (?<type>[\\S]+[\\S ]*?))??(?: -r (?<minRating>[1-5]{1}))??$";
    private static final String LIBRARY_SEARCH_SONG_REQUEST_PATTERN = "^library search song(?: -t (?<title>[\\S]+[\\S ]*?))??(?:(?: -an (?<artistName>[\\S]+[\\S ]*?))|(?: -aid (?<artistGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?:(?: -rt (?<releaseTitle>[\\S]+[\\S ]*?))|(?: -rid (?<releaseGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?: -mind (?<minDuration>[0-9]+?))??(?: -maxd (?<maxDuration>[0-9]+?))??(?: -mr (?<minRating>[1-5]{1}))??$";
    private static final String LIBRARY_SEARCH_RELEASE_REQUEST_PATTERN = "^library search release(?: -t (?<title>[\\S]+[\\S ]*?))??(?:(?: -an (?<artistName>[\\S]+[\\S ]*?))|(?: -aid (?<artistGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?:(?: -tn (?<trackName>[\\S]+[\\S ]*?))|(?: -tid (?<trackGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?: -mind (?<minDuration>[0-9]+?))??(?: -maxd (?<maxDuration>[0-9]+?))??(?: -mr (?<minRating>[1-5]{1}))??$";

    // e.g. library add 19 date=02/04/2020 rating=5
    private static final String ADD_REQUEST_PATTERN =  "^library add (?<type>song|release) (?<id>[0-9]+)(?: date=(?<date>[0-9]{2}/[0-9]{2}/[0-9]{4}))?(?: rating=(?<rating>[1-5]{1}))?$";

    /// e.g. rate 14 rating=2
    private static final String RATE_REQUEST_PATTERN =  "^rate (?<id>[0-9]+) rating=(?<rating>[1-5]{1})$";

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

    public CommandFactory(Library library, Database database) {
        this.library = library;
        this.database = database;
    }
    @Override
    public Command createCommand(String request) {
//        String[] req = request.split(" ");
//        ArrayList<String> args = new ArrayList<>(Arrays.asList(req));
//        args.remove(0);
//        switch (req[0]) {
//            case "database":
//                return new DatabaseSearchCommand(req[0], args);
//            case "library":
//                switch (args.get(0)) {
//                    case "search":
//                        return new LibrarySearchCommand(library, null);
//                    case "add":
//                        createAddCommand(args);
//                    case "remove":
//                        return new RemoveCommand(library, null);
//                }
//            default:
//                return new HelpCommand(req[0]);
//        }
        Matcher matcher = getMatcherForInput(request);
        switch (matcher.pattern().pattern()) {
            case DATABASE_SEARCH_ARTIST_REQUEST_PATTERN:
                break;
            case DATABASE_SEARCH_SONG_REQUEST_PATTERN:
                break;
            case DATABASE_SEARCH_RELEASE_REQUEST_PATTERN:
                break;
            case LIBRARY_SEARCH_ARTIST_REQUEST_PATTERN:
                break;
            case LIBRARY_SEARCH_SONG_REQUEST_PATTERN:
                break;
            case LIBRARY_SEARCH_RELEASE_REQUEST_PATTERN:
                break;
            case ADD_REQUEST_PATTERN:
                break;
            case RATE_REQUEST_PATTERN:
                break;
            case REMOVE_REQUEST_PATTERN:
                break;
            case EXPLORE_REQUEST_PATTERN:
                break;
            case BACK_REQUEST_PATTERN:
                break;
            case HELP_REQUEST_PATTERN:
                new HelpCommand().executeCommand();
            case LIBRARY_LIST_REQUEST_PATTERN:
                break;
        }
        return null;
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
}
