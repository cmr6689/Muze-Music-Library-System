package mmls.command;

import Database.Item;
import Database.Song;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LibrarySearchSongCommand extends LibrarySearchCommand {
    public LibrarySearchSongCommand(Library library, Matcher matcher, CommandFactory commandFactory) {
        super(library, matcher, commandFactory);
    }

    @Override
    public void executeCommand() {
        Collection<Song> songs = library.getSongs();
        List<Song> results;

        String title = matcher.group("title");
        String artistName = matcher.group("artistName");
        String minDuration = matcher.group("minDuration");
        String maxDuration = matcher.group("maxDuration");

        if (title != null) {
            results = filterByTitle(songs, title);
        } else {
            results = new ArrayList<>(songs);
        }

        if (artistName != null) {
            results = filterByArtistName(songs, artistName);
        }

        if (minDuration != null) {
            long minDurationLong = Long.parseLong(minDuration);
            results = filterByMinDuration(results, minDurationLong);
        }

        if (maxDuration != null) {
            long maxDurationLong = Long.parseLong(maxDuration);
            results = filterByMaxDuration(results, maxDurationLong);
        }

        List<Item> resultList = new ArrayList<>(results);
        commandFactory.updateSearchResults(resultList);
    }

    private List<Song> filterByTitle(Collection<Song> songs, String title) {
        List<Song> results = filterByExactTitle(songs, title);

        if (results.size() == 0) {
            results = filterByNameKeywords(songs, title);
        }

        return results;
    }

    private List<Song> filterByExactTitle(Collection<Song> songs, String title) {
        Stream<Song> songStream = songs.stream();

        List<Song> results = songStream.filter(song -> {
            String songTitle = song.getName();
            return songTitle.contains(title);
        }).collect(Collectors.toList());

        return results;
    }

    private List<Song> filterByNameKeywords(Collection<Song> songs, String title) {
        Stream<Song> songStream = songs.stream();

        String[] titleKeywords = splitKeywords(title);

        List<Song> results = songStream.filter(song -> {
            String songTitle = song.getName();
            for (String keyword : titleKeywords) {
                if (songTitle.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return results;
    }

    private List<Song> filterByArtistName(Collection<Song> songs, String artistName) {
        List<Song> results = filterByExactArtistName(songs, artistName);

        if (results.size() == 0) {
            results = filterByArtistNameKeywords(songs, artistName);
        }

        return results;
    }

    private List<Song> filterByExactArtistName(Collection<Song> songs, String artistName) {
        Stream<Song> songStream = songs.stream();

        List<Song> results = songStream.filter(song -> {
            String thisArtistName = song.getArtist().getName();
            return thisArtistName.contains(artistName);
        }).collect(Collectors.toList());

        return results;
    }

    private List<Song> filterByArtistNameKeywords(Collection<Song> songs, String artistName) {
        Stream<Song> songStream = songs.stream();

        String[] artistNameKeywords = splitKeywords(artistName);

        List<Song> results = songStream.filter(song -> {
            String thisArtistName = song.getArtist().getName();
            for (String keyword : artistNameKeywords) {
                if (thisArtistName.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return results;
    }

    private List<Song> filterByMinDuration(Collection<Song> songs, long minDuration) {
        Stream<Song> songStream = songs.stream();

        List<Song> results = songStream.filter(song -> {
            return (song.getDuration() >= minDuration);
        }).collect(Collectors.toList());

        return results;
    }

    private List<Song> filterByMaxDuration(Collection<Song> songs, long maxDuration) {
        Stream<Song> songStream = songs.stream();

        List<Song> results = songStream.filter(song -> {
            return (song.getDuration() <= maxDuration);
        }).collect(Collectors.toList());

        return results;
    }
}
