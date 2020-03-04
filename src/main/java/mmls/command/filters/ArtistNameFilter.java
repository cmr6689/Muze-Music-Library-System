package mmls.command.filters;

import Database.Audio;
import Database.Item;
import Database.Song;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArtistNameFilter<T extends Item & Audio> extends KeywordFilter<T> {

    @Override
    protected List<T> filterByExact(Collection<T> items, String artistName) {
        Stream<T> itemStream = items.stream();

        List<T> results = itemStream.filter(item -> {
            String thisArtistName = item.getArtist().getName();
            return thisArtistName.contains(artistName);
        }).collect(Collectors.toList());

        return results;
    }

    @Override
    protected List<T> filterByKeywords(Collection<T> items, String artistName) {
        Stream<T> itemStream = items.stream();

        String[] artistNameKeywords = splitKeywords(artistName);

        List<T> results = itemStream.filter(item -> {
            String thisArtistName = item.getArtist().getName();
            for (String keyword : artistNameKeywords) {
                System.out.println(thisArtistName);
                if (thisArtistName.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return results;
    }
}
