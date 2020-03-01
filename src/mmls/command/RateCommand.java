package mmls.command;

import Database.Item;
import Database.Song;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class RateCommand implements Command {
    private Library library;
    private Matcher matcher;
    private ArrayList<Item> items;

    public RateCommand(Library library, Matcher matcher, ArrayList<Item> items) {
        this.library = library;
        this.matcher = matcher;
        this.items = items;
    }

    @Override
    public void executeCommand() {
        for (Song song : library.getSongs()) {
            if (items.get(Integer.getInteger(matcher.group("id"))).getGuid().equals(song.getGuid())) {
                song.setRating(Double.parseDouble(matcher.group("rating")));
            }
        }
    }
}
