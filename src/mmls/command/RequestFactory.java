package mmls.command;

import Database.Database;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Arrays;

public class RequestFactory implements Factory {
    private String request;
    private Library library;
    private Database database;

    public RequestFactory(String request, Library library, Database database) {
        this.request = request;
    }

    @Override
    public Command createRequest(String request) {
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
                        return new AddCommand(library, null);
                    case "remove":
                        return new RemoveCommand(library, null);
                }
            default:
                return new HelpCommand(req[0]);
        }
    }
}
