package mmls.command.filters;

import Database.Item;
import Database.Release;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TitleFilter<T extends Item> extends KeywordFilter<T> {

    @Override
    protected List<T> filterByExact(Collection<T> items, String title) {
        Stream<T> itemStream = items.stream();

        List<T> results = itemStream.filter(item -> {
            String itemTitle = item.getName();
            return itemTitle.contains(title);
        }).collect(Collectors.toList());

        return results;
    }

    @Override
    protected List<T> filterByKeywords(Collection<T> items, String title) {
        Stream<T> itemStream = items.stream();

        String[] titleKeywords = splitKeywords(title);

        List<T> results = itemStream.filter(item -> {
            String itemTitle = item.getName();
            for (String keyword : titleKeywords) {
                if (itemTitle.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        return results;
    }
}
