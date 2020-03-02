package mmls.command;

import Database.*;
import Database.Release;
import Results.*;
import mmls.command.filters.*;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LibrarySearchReleaseCommand extends LibrarySearchCommand {
    public LibrarySearchReleaseCommand(Library library, Matcher matcher, CommandFactory commandFactory) {
        super(library, matcher, commandFactory);
    }

    @Override
    public void executeCommand() {
        Collection<Release> releases = library.getReleases();
        List<Release> results;

        String title = matcher.group("title");
        String artistName = matcher.group("artistName");
        String artistGuid = matcher.group("artistGUID");
        String trackName = matcher.group("trackName");
        String trackGuid = matcher.group("trackGUID");
        String minDuration = matcher.group("minDuration");
        String maxDuration = matcher.group("maxDuration");
        String minRating = matcher.group("minRating");
        String sortBy = matcher.group("sortBy");

        if (title != null) {
            TitleFilter<Release> titleFilter = new TitleFilter<>();
            results = titleFilter.filter(releases, title.trim());
        } else {
            results = new ArrayList<>(releases);
        }

        if (artistName != null) {
            ArtistNameFilter<Release> artistNameFilter = new ArtistNameFilter<>();
            results = artistNameFilter.filter(results, artistName.trim());
        } else if (artistGuid != null) {
            results = filterByArtistGuid(results, artistGuid.trim());
        }

        if (trackName != null) {
            results = filterByTrackName(results, trackName.trim());
        } else if (trackGuid != null) {
            results = filterByTrackGuid(results, trackGuid.trim());
        }

        if (minDuration != null) {
            MinDurationFilter<Release> minDurationFilter = new MinDurationFilter<>();
            results = minDurationFilter.filter(results, minDuration);
        }

        if (maxDuration != null) {
            MaxDurationFilter<Release> maxDurationFilter = new MaxDurationFilter<>();
            results = maxDurationFilter.filter(results, maxDuration);
        }

        if (minRating != null) {
            MinRatingFilter<Release> minRatingFilter = new MinRatingFilter<>();
            results = minRatingFilter.filter(results, minRating);
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

    private List<Release> filterByArtistGuid(Collection<Release> releases, String artistGuid) {
        Stream<Release> releaseStream = releases.stream();
        List<Release> results = releaseStream.filter(release -> {
            return release.getArtist().getGuid().contains(artistGuid);
        }).collect(Collectors.toList());
        return results;
    }

    private List<Release> filterByTrackName(Collection<Release> releases, String trackName) {
        Stream<Release> releaseStream = releases.stream();

        List<Release> results = releaseStream.filter(release -> {
            for (Song song : release.getTracks()) {
                if (song.getName().contains(trackName)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return results;
    }
    private List<Release> filterByTrackGuid(Collection<Release> releases, String trackGuid) {
        Stream<Release> releaseStream = releases.stream();

        List<Release> results = releaseStream.filter(release -> {
            for (Song song : release.getTracks()) {
                if (song.getGuid().contains(trackGuid)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return results;
    }

    private List<Release> filterByMinRating(Collection<Release> releases, double minRating) {
        Stream<Release> releaseStream = releases.stream();

        List<Release> results = releaseStream.filter(release -> {
            double releaseRating = release.getRating();
            return (releaseRating >= minRating);
        }).collect(Collectors.toList());

        return results;
    }

}
