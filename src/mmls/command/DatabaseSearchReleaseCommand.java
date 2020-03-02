package mmls.command;

import Database.Database;
import Database.Release;
import Database.Item;
import mmls.command.filters.TitleFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseSearchReleaseCommand extends DatabaseSearchCommand {

    public DatabaseSearchReleaseCommand(Database database, Matcher matcher, CommandFactory commandFactory) {
        super(database, matcher, commandFactory);
    }

    @Override
    public void executeCommand() {
        Collection<Release> releases = database.getReleases();
        List<Release> results;

        String title = matcher.group("title");

        if (title != null) {
            TitleFilter<Release> titleFilter = new TitleFilter<>();
            results = titleFilter.filter(releases, title.trim());
        } else {
            results = new ArrayList<>(releases);
        }

        List<Item> resultList = new ArrayList<>(results);
        notifyCommandFactory(resultList);
    }

}
