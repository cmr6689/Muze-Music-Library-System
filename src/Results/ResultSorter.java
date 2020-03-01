package Results;

import Database.Item;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultSorter {
    public List<Item> sort(List<Item> items, Comparator<Item> strategy) {
        return items.stream().sorted(strategy).collect(Collectors.toList());
    }
}
