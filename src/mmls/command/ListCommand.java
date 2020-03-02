package mmls.command;

import Database.Database;
import Database.Artist;
import Database.Song;
import Database.Item;

import mmls.command.filters.ArtistGuidFilter;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;

public class ListCommand implements Command {
    private Library library;
    private CommandFactory commandFactory;

    public ListCommand(Library library, CommandFactory commandFactory) {
        this.library = library;
        this.commandFactory = commandFactory;
    }

    @Override
    public void executeCommand() {
        List<Artist> artists = new ArrayList<>(library.getArtists());
        Collection<Song> songs = library.getSongs();
        ArtistGuidFilter<Song> artistGuidFilter = new ArtistGuidFilter<>();
        List<Long> durations = new ArrayList<>();

        for (int i = 0; i < artists.size(); i++) {
            long totalDuration = 0;
            List<Song> songsByArtist = artistGuidFilter.filter(songs, artists.get(i).getGuid());
            for (Song song : songsByArtist) {
                totalDuration += song.getDuration();
            }
            durations.add(totalDuration);
        }
        commandFactory.updateSearchResults(new ArrayList<>(artists), durations);
    }
}
