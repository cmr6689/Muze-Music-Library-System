package mmls.command;

import Database.Database;
import Database.Item;
import Database.Song;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RateCommand extends LibraryCommand implements Command {

    public RateCommand(Library library, Database database, Matcher matcher, List<Item> results) {
        super(library, database, matcher, results);
    }

    @Override
    public void executeCommand() {
        for (Song song : library.getSongs()) {
            if (results.get(Integer.getInteger(matcher.group("id"))).getGuid().equals(song.getGuid())) {
                song.setRating(Double.parseDouble(matcher.group("rating")));
            }
        }
    }
}
