package mmls.command.filters;

import Database.Audio;
import Database.Item;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinDurationFilter<T extends Item & Audio> implements ItemFilter<T> {
    @Override
    public List<T> filter(Collection<T> items, String filter) {
        Long minDuration = Long.parseLong(filter);
        Stream<T> itemStream = items.stream();

        List<T> results = itemStream.filter(item -> {
            return (item.getDuration() >= minDuration);
        }).collect(Collectors.toList());

        return results;
    }
}
