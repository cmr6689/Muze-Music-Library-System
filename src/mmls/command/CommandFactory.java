package mmls.command;

import Database.Database;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandFactory implements Factory {
    private Library library;
    private Database database;

    public CommandFactory(Library library, Database database) {
        this.library = library;
        this.database = database;
    }
    @Override
    public Command createCommand(String request) {
        String[] req = request.split(" ");
        ArrayList<String> args = new ArrayList<>(Arrays.asList(req));
        args.remove(0);
        switch (req[0]) {
            case "database":
                return new DatabaseSearchCommand(req[0], args);
            case "library":
                switch (args.get(0)) {
                    case "search":
                        return new LibrarySearchCommand(library, null);
                    case "add":
                        createAddCommand(args);
                    case "remove":
                        return new RemoveCommand(library, null);
                }
            default:
                return new HelpCommand(req[0]);
        }
    }

    private AddCommand createAddCommand(List<String> args) {
        args.remove(0);

    }
}
