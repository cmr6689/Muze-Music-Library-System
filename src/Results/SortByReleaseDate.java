package Results;


import Database.Item;
import Database.Song;
import Database.Release;

import java.util.Comparator;

public class SortByReleaseDate implements Comparator<Item> {
    //Sort by the release Date.


    @Override
    public int compare(Item o1, Item o2) {
        return (o1.getDate().compareTo(o2.getDate()));
    }
}
