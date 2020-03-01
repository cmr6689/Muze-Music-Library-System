package mmls.command;

import Database.Artist;
import Database.Item;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class ExploreCommand extends LibraryCommand implements Command {

    public ExploreCommand(Library library, Matcher matcher, List<Item> results) {
        super(library, matcher, results);
    }

    @Override
    public void executeCommand() {
        for (Artist artist : library.getArtists()) {
            if (results.get(Integer.getInteger(matcher.group("id"))).getGuid().equals(artist.getGuid())) {

            }
        }
    }
}
