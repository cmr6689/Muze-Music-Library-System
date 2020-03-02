package mmls.command;

import Database.Database;
import Database.Artist;
import Database.Item;
import mmls.command.filters.TitleFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;

public class DatabaseSearchArtistCommand extends DatabaseSearchCommand {
    public DatabaseSearchArtistCommand(Database database, Matcher matcher, CommandFactory commandFactory) {
        super(database, matcher, commandFactory);
    }

    @Override
    public void executeCommand() {
        Collection<Artist> artists = database.getArtists();
        String keywords = matcher.group("keywords");
        TitleFilter<Artist> titleFilter = new TitleFilter<>();
        List<Artist> results = titleFilter.filter(artists, keywords);

        List<Item> itemList = new ArrayList<>(results);
        commandFactory.updateSearchResults(itemList);
    }
}
