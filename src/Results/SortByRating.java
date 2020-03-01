package Results;

import Database.Item;

import java.util.Comparator;

public class SortByRating implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        return item2.getRating() - item1.getRating();
    }
}
