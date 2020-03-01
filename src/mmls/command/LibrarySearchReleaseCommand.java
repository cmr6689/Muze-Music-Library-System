package mmls.command;

import Database.*;
import Database.Release;
import Results.*;
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
            results = filterByTitle(releases, title.trim());
        } else {
            results = new ArrayList<>(releases);
        }

        if (artistName != null) {
            results = filterByArtistName(results, artistName.trim());
        } else if (artistGuid != null) {
            results = filterByArtistGuid(results, artistGuid.trim());
        }

        if (trackName != null) {
            results = filterByTrackName(results, trackName.trim());
        } else if (trackGuid != null) {
            results = filterByTrackGuid(results, trackGuid.trim());
        }

        if (minDuration != null) {
            long minDurationLong = Long.parseLong(minDuration);
            results = filterByMinDuration(results, minDurationLong);
        }

        if (maxDuration != null) {
            long maxDurationLong = Long.parseLong(maxDuration);
            results = filterByMaxDuration(results, maxDurationLong);
        }

        if (minRating != null) {
            double minRatingDouble = Integer.parseInt(minRating);
            results = filterByMinRating(results, minRatingDouble);
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

    private List<Release> filterByTitle(Collection<Release> releases, String title) {
        List<Release> results = filterByExactTitle(releases, title);

        if (results.size() == 0) {
            results = filterByTitleKeywords(releases, title);
        }

        return results;
    }

    private List<Release> filterByExactTitle(Collection<Release> releases, String title) {
        Stream<Release> releaseStream = releases.stream();

        List<Release> results = releaseStream.filter(release -> {
            String releaseTitle = release.getName();
            return releaseTitle.contains(title);
        }).collect(Collectors.toList());

        return results;
    }

    private List<Release> filterByTitleKeywords(Collection<Release> releases, String title) {
        Stream<Release> releaseStream = releases.stream();

        String[] titleKeywords = splitKeywords(title);

        List<Release> results = releaseStream.filter(release -> {
            String releaseTitle = release.getName();
            for (String keyword : titleKeywords) {
                if (releaseTitle.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return results;
    }

    private List<Release> filterByArtistName(Collection<Release> releases, String artistName) {
        List<Release> results = filterByExactArtistName(releases, artistName);

        if (results.size() == 0) {
            results = filterByArtistNameKeywords(releases, artistName);
        }

        return results;
    }

    private List<Release> filterByExactArtistName(Collection<Release> releases, String artistName) {
        Stream<Release> releaseStream = releases.stream();

        List<Release> results = releaseStream.filter(release -> {
            String thisArtistName = release.getArtist().getName();
            return thisArtistName.contains(artistName);
        }).collect(Collectors.toList());

        return results;
    }

    private List<Release> filterByArtistNameKeywords(Collection<Release> releases, String artistName) {
        Stream<Release> releaseStream = releases.stream();

        String[] artistNameKeywords = splitKeywords(artistName);

        List<Release> results = releaseStream.filter(release -> {
            String thisArtistName = release.getArtist().getName();
            for (String keyword : artistNameKeywords) {
                if (thisArtistName.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return results;
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

    private List<Release> filterByMinDuration(Collection<Release> releases, long minDuration) {
        Stream<Release> releaseStream = releases.stream();

        List<Release> results = releaseStream.filter(release -> {
            return (release.getDuration() >= minDuration);
        }).collect(Collectors.toList());

        return results;
    }

    private List<Release> filterByMaxDuration(Collection<Release> releases, long maxDuration) {
        Stream<Release> releaseStream = releases.stream();

        List<Release> results = releaseStream.filter(release -> {
            return (release.getDuration() <= maxDuration);
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
