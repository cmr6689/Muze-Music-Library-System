package mmls.command;

import Database.Artist;
import Database.Item;
import Database.Song;
import Results.ResultSorter;
import Results.SortAlphabetically;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
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
        Collection<Song> songs = this.library.getSongs();
        List<Song> results;

        String title = matcher.group("title");
        String artistName = matcher.group("artistName");
        String artistGuid = matcher.group("artistGUID");
        String releaseTitle = matcher.group("releaseTitle");
        String releaseGuid = matcher.group("releaseGUID");
        String minDuration = matcher.group("minDuration");
        String maxDuration = matcher.group("maxDuration");
        String minRating = matcher.group("minRating");
        String sortBy = matcher.group("sortBy");

        if (title != null) {
            results = filterByTitle(songs, title.trim());
        } else {
            results = new ArrayList<>(songs);
        }

        if (artistName != null) {
            results = filterByArtistName(results, artistName.trim());
        } else if (artistGuid != null) {
            results = filterByArtistGuid(results, artistGuid.trim());
        }

        if (releaseTitle != null) {

        }

        List<Item> resultList = new ArrayList<>(results);
        ResultSorter resultSorter = new ResultSorter();
        Comparator<Item> sortStrategy;

        if (sortBy != null) {
            int sortMethodNum = Integer.parseInt(sortBy);
            sortStrategy = resultSorter.getAppropriateStrategy(sortMethodNum);
        } else {
            sortStrategy = new SortAlphabetically();
        }
        List<Item> sortedResults = resultSorter.sort(resultList, sortStrategy);

        notifyCommandFactory(sortedResults);
    }

    private List<Song> filterByTitle(Collection<Song> songs, String title) {
        List<Song> results = filterByExactTitle(songs, title);

        if (results.size() == 0) {
            results = filterByTitleKeywords(songs, title);
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

    private List<Song> filterByTitleKeywords(Collection<Song> songs, String title) {
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

    private List<Song> filterByArtistGuid(Collection<Song> songs, String artistGuid) {
        Stream<Song> songStream = songs.stream();
        List<Song> results = songStream.filter(song -> {
            return song.getArtist().getGuid().contains(artistGuid);
        }).collect(Collectors.toList());
        return results;
    }

//    private List<Song> filterByReleaseTitle(Collection<Song> songs, String title) {
//        List<Song> results = filterByExactReleaseTitle(songs, title);
//
//        if (results.size() == 0) {
//            results = filterByReleaseTitleKeywords(songs, title);
//        }
//
//        return results;
//    }
//
//    private List<Song> filterByExactReleaseTitle(Collection<Song> songs, String title) {
//        Stream<Song> songStream = songs.stream();
//
//        List<Song> results = songStream.filter(song -> {
//            return songTitle.contains(title);
//        }).collect(Collectors.toList());
//
//        return results;
//    }
//
//    private List<Song> filterByReleaseTitleKeywords(Collection<Song> songs, String title) {
//        Stream<Song> songStream = songs.stream();
//
//        String[] titleKeywords = splitKeywords(title);
//
//        List<Song> results = songStream.filter(song -> {
//            String songTitle = song.getName();
//            for (String keyword : titleKeywords) {
//                if (songTitle.contains(keyword)) {
//                    return true;
//                }
//            }
//            return false;
//        }).collect(Collectors.toList());
//
//        return results;
//    }
}
