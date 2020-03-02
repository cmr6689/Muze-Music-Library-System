package mmls.command;

import Database.Artist;
import Database.Item;
import Results.ResultSorter;
import Results.SortAlphabetically;
import mmls.command.filters.TitleFilter;
import mmls.command.filters.TypeFilter;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class allows the user to search for artists given the parameters of
 * the search and filters the results based on those parameters.
 * @author Shane Burke
 */
public class LibrarySearchArtistCommand extends LibrarySearchCommand {
    public LibrarySearchArtistCommand(Library library, Matcher matcher, CommandFactory commandFactory) {
        super(library, matcher, commandFactory);
    }

    @Override
    public void executeCommand() {
        Collection<Artist> artists = this.library.getArtists();
        List<Artist> results;

        String name = matcher.group("name");
        String type = matcher.group("type");
        String minRating = matcher.group("minRating");

        if (name != null) {
            TitleFilter titleFilter = new TitleFilter();
            results = titleFilter.filter(artists, name.trim());
        } else {
            results = new ArrayList<>(artists);
        }

        if (type != null) {
            TypeFilter typeFilter = new TypeFilter();
            results = typeFilter.filter(results, type.trim());
        }

        if (minRating != null) {
            double minRatingDouble = Integer.parseInt(minRating);
            results = filterByMinRating(results, minRatingDouble);
        }

        List<Item> resultList = new ArrayList<>(results);
        ResultSorter resultSorter = new ResultSorter();
        List<Item> sortedResultList = resultSorter.sort(resultList, new SortAlphabetically());
        notifyCommandFactory(sortedResultList);
    }

    private List<Artist> filterByMinRating(Collection<Artist> artists, double minRating) {
        Stream<Artist> artistStream = artists.stream();

        List<Artist> results = artistStream.filter(artist -> {
            double artistRating = artist.getRating();
            return (artistRating >= minRating);
        }).collect(Collectors.toList());

        return results;
    }

}
