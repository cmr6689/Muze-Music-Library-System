package mmls;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLine {
    // e.g. search library artist The Beatles
    private static final String SEARCH_REQUEST_PATTERN = "^search (?<location>library|database) (?<type>artist|song|release) (?<keywords>[\\w]+[\\S ]*)$";
    private static final String LIBRARY_SEARCH_ARTIST_REQUEST_PATTERN = "^library search artist(?: -n (?<name>[\\S]+[\\S ]*?))??(?: -t (?<type>[\\S]+[\\S ]*?))??(?: -r (?<minRating>[1-5]{1}))??$";
    private static final String LIBRARY_SEARCH_SONG_REQUEST_PATTERN = "^library search song(?: -t (?<title>[\\S]+[\\S ]*?))??(?:(?: -an (?<artistName>[\\S]+[\\S ]*?))|(?: -aid (?<artistGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?:(?: -rt (?<releaseTitle>[\\S]+[\\S ]*?))|(?: -rid (?<releaseGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?: -mind (?<minDuration>[0-9]+?))??(?: -maxd (?<maxDuration>[0-9]+?))??(?: -mr (?<minRating>[1-5]{1}))??$";
    private static final String LIBRARY_SEARCH_RELEASE_REQUEST_PATTERN = "^library search release(?: -t (?<title>[\\S]+[\\S ]*?))??(?:(?: -an (?<artistName>[\\S]+[\\S ]*?))|(?: -aid (?<artistGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?:(?: -tn (?<trackName>[\\S]+[\\S ]*?))|(?: -tid (?<trackGUID>[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12})))??(?: -mind (?<minDuration>[0-9]+?))??(?: -maxd (?<maxDuration>[0-9]+?))??(?: -mr (?<minRating>[1-5]{1}))??$";

    // e.g. library add 19 date=02/04/2020 rating=5
    private static final String ADD_REQUEST_PATTERN = "^library add (?<id>[0-9]+)(?: date=(?<date>[0-9]{2}/[0-9]{2}/[0-9]{4}))?(?: rating=(?<rating>[1-5]{1}))?$";

    /// e.g. rate 14 rating=2
    private static final String RATE_REQUEST_PATTERN = "^rate (?<id>[0-9]+) rating=(?<rating>[1-5]{1})$";

    // e.g. library remove 3
    private static final String REMOVE_REQUEST_PATTERN = "^library remove (?<id>[0-9]+)$";

    // e.g. explore 4
    private static final String EXPLORE_REQUEST_PATTERN = "^explore (?<id>[0-9]+)$";

    private static final String BACK_REQUEST_PATTERN = "^back$";
    private static final String HELP_REQUEST_PATTERN = "^help";
    private static final String LIBRARY_LIST_REQUEST_PATTERN = "^library list$";

    private static final String WELCOME_TEXT = "Welcome to the Muze Music Library System.";
    private static final String INPUT_PROMPT_TEXT = "Please enter a command.\nUser: ";

    public static void main(String ...args) {


        Pattern r = Pattern.compile(LIBRARY_SEARCH_ARTIST_REQUEST_PATTERN);
        Scanner scanner = new Scanner(System.in);

        String userInput;
        System.out.println(WELCOME_TEXT);
        System.out.print(INPUT_PROMPT_TEXT);
        userInput = scanner.nextLine();

        Matcher m = r.matcher(userInput);
        if (m.matches()) {
            System.out.println(m.group("name"));
        }
    }
}
