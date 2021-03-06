package mmls.command;

import Database.Artist;
import Database.Item;
import Database.Release;
import Database.Song;
import Results.ResultSorter;
import Results.SortAlphabetically;
import mmls.command.filters.*;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class allows the user to search for songs given the parameters of
 * the search and filters the results based on those parameters.
 * @author Shane Burke
 */
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
            TitleFilter<Song> titleFilter = new TitleFilter<>();
            results = titleFilter.filter(songs, title.trim());
        } else {
            results = new ArrayList<>(songs);
        }

        if (artistName != null) {
            ArtistNameFilter<Song> artistNameFilter = new ArtistNameFilter<>();
            results = artistNameFilter.filter(results, artistName.trim());
        } else if (artistGuid != null) {
            ArtistGuidFilter<Song> artistGuidFilter = new ArtistGuidFilter<>();
            results = artistGuidFilter.filter(results, artistGuid);
        }

        if (releaseTitle != null) {
            results = filterByReleaseTitle(results, releaseTitle.trim());
        } else if (releaseGuid != null) {
            results = filterByReleaseGuid(results, releaseGuid);
        }

        if (minDuration != null) {
            MinDurationFilter<Song> minDurationFilter = new MinDurationFilter<>();
            results = minDurationFilter.filter(results, minDuration);
        }

        if (maxDuration != null) {
            MaxDurationFilter<Song> maxDurationFilter = new MaxDurationFilter<>();
            results = maxDurationFilter.filter(results, maxDuration);
        }

        if (minRating != null) {
            MinRatingFilter<Song> minRatingFilter = new MinRatingFilter<>();
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

    private List<Release> getTitleFilteredReleases(String releaseTitle) {
        Collection<Release> releases = library.getReleases();
        TitleFilter<Release> releaseTitleFilter = new TitleFilter<>();
        return releaseTitleFilter.filter(releases, releaseTitle.trim());
    }

    private List<Release> getGuidFilteredReleases(String releaseGuid) {
        releaseGuid = releaseGuid.trim();
        Collection<Release> releases = library.getReleases();
        GuidFilter<Release> guidFilter = new GuidFilter<>();
        List<Release> filteredReleases = guidFilter.filter(releases, releaseGuid);
        return filteredReleases;
    }

    private List<Song> filterByReleaseTitle(Collection<Song> songs, String releaseTitle) {
        List<Release> filteredReleases = getTitleFilteredReleases(releaseTitle);

        List<Song> results = findSongsInReleases(songs, filteredReleases);

        return results;
    }

    private List<Song> filterByReleaseGuid(Collection<Song> songs, String releaseGuid) {
        releaseGuid = releaseGuid.trim();
        List<Release> filteredReleases = getGuidFilteredReleases(releaseGuid);

        List<Song> results = findSongsInReleases(songs, filteredReleases);
        return results;
    }

    private List<Song> findSongsInReleases(Collection<Song> songs, List<Release> releases) {
        List<Song> results = new ArrayList<>();
        for (Release release : releases) {
            List<Song> tracks = release.getTracks();
            for (Song track : tracks) {
                if (songs.contains(track)) {
                    results.add(track);
                }
            }
        }
        return results;
    }

}
