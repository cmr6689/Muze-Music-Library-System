package Results;

import Database.Item;

import java.util.Comparator;

public class SortByAcquistionDate implements Comparator<Item> {


    @Override
    public int compare(Item o1, Item o2) {
        return (o1.getAquisitionDate().compareTo(o2.getAquisitionDate()));
    }
}
