package mmls.command;

import Database.Database;
import Database.Item;
import Database.Audio;
import Database.Song;
import Database.Release;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * This class is responsible for adding a rating to a song in the library by using the provided
 * search results and the rating given by the user.
 * @author Cameron Riu, Shane Burke
 */
public class RateCommand extends LibraryCommand implements Command {

    /**
     * Constructor that initializes all objects used by the command
     * @param library : global library
     * @param database : global database
     * @param matcher : used to give regex groups
     * @param results : search results from a search
     */
    public RateCommand(Library library, Database database, Matcher matcher, List<Item> results) {
        super(library, database, matcher, results);
    }

    /**
     * This method loops through the library songs to get the match from the requested search
     * result and then adds the rating to the song.
     */
    @Override
    public void executeCommand() {
        int resultId = Integer.parseInt(matcher.group("id").trim());
        if (resultId >= results.size()) {
            System.out.println("Invalid search result ID. Please enter one of the ID numbers shown above.");
            return;
        }
        String chosenItemGuid = results.get(resultId).getGuid();

        Item itemToRate = library.getAudioItem(chosenItemGuid);
        if (itemToRate != null) {
            double rating = Double.parseDouble(matcher.group("rating"));
            itemToRate.setRating(rating);
        } else {
            System.out.println("Only releases and songs may be rated.");
        }
    }
}
