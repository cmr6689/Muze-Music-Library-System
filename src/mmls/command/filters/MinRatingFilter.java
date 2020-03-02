package mmls.command.filters;

import Database.Item;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinRatingFilter<T extends Item> implements ItemFilter<T> {
    @Override
    public List<T> filter(Collection<T> items, String filter) {
        filter = filter.trim();
        double minRating = Integer.parseInt(filter);
        Stream<T> itemStream = items.stream();

        List<T> results = itemStream.filter(item -> {
            double itemRating = item.getRating();
            return (itemRating >= minRating);
        }).collect(Collectors.toList());

        return results;
    }
}
