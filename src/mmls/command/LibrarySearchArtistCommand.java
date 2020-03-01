package mmls.command;

import Database.Artist;
import Database.Item;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LibrarySearchArtistCommand extends LibrarySearchCommand {
    public LibrarySearchArtistCommand(Library library, Matcher matcher, CommandFactory commandFactory) {
        super(library, matcher, commandFactory);
    }

    @Override
    public void executeCommand() {
        Collection<Artist> artists = library.getArtists();
        List<Artist> results;

        try {
            String name = matcher.group("name");
            results = filterByName(artists, name);
        } catch (IllegalArgumentException e) {
            results = new ArrayList<>(artists);
        }

        try {
            String type = matcher.group("type");
            results = filterByType(results, type);
        } catch (IllegalArgumentException ignored) {}

        try {
            double minRating = Double.parseDouble(matcher.group("minRating"));
            results = filterByMinRating(results, minRating);
        } catch (IllegalArgumentException ignored) {}

        List<Item> resultList = new ArrayList<>(results);
        commandFactory.updateSearchResults(resultList);
    }

    private List<Artist> filterByName(Collection<Artist> artists, String name) {
        List<Artist> results = filterByExactName(artists, name);

        if (results.size() == 0) {
            results = filterByNameKeywords(artists, name);
        }

        return results;
    }

    private List<Artist> filterByExactName(Collection<Artist> artists, String name) {
        Stream<Artist> artistStream = artists.stream();

        List<Artist> results = artistStream.filter(artist -> {
            String artistName = artist.getName();
            return artistName.contains(name);
        }).collect(Collectors.toList());

        return results;
    }

    private List<Artist> filterByNameKeywords(Collection<Artist> artists, String name) {
        Stream<Artist> artistStream = artists.stream();
        String[] nameKeywords = name.split(" ");

        List<Artist> results = artistStream.filter(artist -> {
            String artistName = artist.getName();
            for (String keyword : nameKeywords) {
                if (artistName.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return results;
    }

    private List<Artist> filterByType(Collection<Artist> artists, String type) {
        List<Artist> results = filterByExactType(artists, type);

        if (results.size() == 0) {
            results = filterByTypeKeywords(artists, type);
        }

        return results;
    }

    private List<Artist> filterByExactType(Collection<Artist> artists, String type) {
        Stream<Artist> artistStream = artists.stream();

        List<Artist> results = artistStream.filter(artist -> {
            String artistType = artist.getGenre();
            return artistType.contains(type);
        }).collect(Collectors.toList());

        return results;
    }

    private List<Artist> filterByTypeKeywords(Collection<Artist> artists, String type) {
        Stream<Artist> artistStream = artists.stream();

        String[] typeKeywords = type.split(" ");
        List<Artist> results = artistStream.filter(artist -> {
            String artistType = artist.getGenre();
            for (String keyword : typeKeywords) {
                if (artistType.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return results;
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
