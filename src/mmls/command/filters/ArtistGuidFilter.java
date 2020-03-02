package mmls.command.filters;

import Database.Audio;
import Database.Item;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArtistGuidFilter<T extends Item & Audio> implements ItemFilter<T> {

    @Override
    public List<T> filter(Collection<T> items, String filter) {
        String artistGuid = filter.trim();
        Stream<T> itemStream = items.stream();

        return itemStream.filter(item -> {
            String thisArtistGuid = item.getArtist().getGuid();
            return thisArtistGuid.contains(artistGuid);
        }).collect(Collectors.toList());
    }
}
