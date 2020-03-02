package mmls.command.filters;

import Database.Item;

import java.util.Collection;
import java.util.List;

public abstract class KeywordFilter<T extends Item> implements ItemFilter<T> {
    protected String[] splitKeywords(String searchInput) {
        String[] keywords;
        try {
            keywords = searchInput.trim().split(" ");
        } catch (NullPointerException e) {
            keywords = new String[]{searchInput};
        }
        return keywords;
    }

    @Override
    public List<T> filter(Collection<T> items, String filter) {
        List<T> results = filterByExact(items, filter);

        if (results.size() == 0) {
            results = filterByKeywords(items, filter);
        }

        return results;
    }

    protected abstract List<T> filterByExact(Collection<T> items, String filter);
    protected abstract List<T> filterByKeywords(Collection<T> items, String filter);

}
