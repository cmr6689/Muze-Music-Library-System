package Results;

import Database.Item;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultSorter {
    public Comparator<Item> getAppropriateStrategy(int sortMethodNum) {
        Comparator<Item> sortStrategy = new SortAlphabetically();

        switch (sortMethodNum) {
            case 2:
                sortStrategy = new SortByRating();
                break;
            case 3:
                sortStrategy = new SortByAcquistionDate();
                break;
            case 4:
                sortStrategy = new SortByReleaseDate();
                break;
        }

        return sortStrategy;
    }

    public List<Item> sort(List<Item> items, Comparator<Item> strategy) {
        return items.stream().sorted(strategy).collect(Collectors.toList());
    }
}
