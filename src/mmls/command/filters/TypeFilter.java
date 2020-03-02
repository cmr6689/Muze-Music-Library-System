package mmls.command.filters;

import Database.Item;
import Database.Artist;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TypeFilter extends KeywordFilter<Artist> {

    @Override
    protected List<Artist> filterByExact(Collection<Artist> items, String type) {
        Stream<Artist> itemStream = items.stream();

        List<Artist> results = itemStream.filter(item -> {
            String itemType = item.getGenre();
            return itemType.contains(type);
        }).collect(Collectors.toList());

        return results;
    }

    @Override
    protected List<Artist> filterByKeywords(Collection<Artist> items, String type) {
        Stream<Artist> itemStream = items.stream();

        String[] typeKeywords = splitKeywords(type);

        List<Artist> results = itemStream.filter(item -> {
            String itemType = item.getGenre();
            for (String keyword : typeKeywords) {
                if (itemType.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return results;
    }
}
