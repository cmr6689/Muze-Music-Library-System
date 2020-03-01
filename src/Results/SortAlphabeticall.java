package Results;

import Database.Item;

import java.util.Comparator;

public class SortAlphabeticall implements Comparator<Item> {


    @Override
    public int compare(Item o1, Item o2) {
        return (o1.getName().compareTo(o2.getName()));
    }
}
