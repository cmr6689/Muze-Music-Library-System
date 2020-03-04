package mmls.command.filters;

import Database.Item;

import java.util.Collection;
import java.util.List;

public interface ItemFilter<T extends Item> {
    public List<T> filter(Collection<T> items, String filter);
}
