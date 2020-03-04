package mmls.command.filters;

import Database.Item;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GuidFilter<T extends Item> implements ItemFilter<T> {
    @Override
    public List<T> filter(Collection<T> items, String filter) {
        Stream<T> itemStream = items.stream();
        List<T> results = itemStream.filter(item -> {
            return item.getGuid().equals(filter);
        }).collect(Collectors.toList());
        return results;
    }
}
