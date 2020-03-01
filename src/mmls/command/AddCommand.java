package mmls.command;

import Database.Item;
import Database.Release;
import Database.Song;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

public class AddCommand extends LibraryCommand implements Command {

    public AddCommand(Library library, Matcher matcher, List<Item> results) {
        super(library, matcher, results);
    }

    @Override
    public void executeCommand() {
        if (matcher.group("type").equals("song")) {
            for (Song song : library.getSongs()) {
                if (results.get(Integer.getInteger(matcher.group("id"))).getGuid().equals(song.getGuid())) {
                    if (matcher.group("date") != null) {
                        Date date = new Date(matcher.group("date"));
                        library.addSong(song, date);
                    } else {
                        library.addSong(song);
                    }
                    if (matcher.group("rating") != null) {
                        song.setRating(Double.parseDouble(matcher.group("rating")));
                    }
                }
            }
        } else if (matcher.group("type").equals("release")) {
            for (Release release : library.getReleases()) {
                if (results.get(Integer.getInteger(matcher.group("id"))).getGuid().equals(release.getGuid())) {
                    if (matcher.group("date") != null) {
                        Date date = new Date(matcher.group("date"));
                        library.addRelease(release, date);
                    } else {
                        library.addRelease(release, new Date());
                    }
                    if (matcher.group("rating") != null) {
                        release.setRating(Double.parseDouble(matcher.group("rating")));
                    }
                }
            }
        }
    }
}
