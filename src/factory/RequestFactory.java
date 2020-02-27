package factory;

import java.util.ArrayList;
import java.util.Arrays;

public class RequestFactory implements Factory{
    private String request;

    public RequestFactory(String request) {
        this.request = request;
    }

    @Override
    public void createRequest(String request) {
        String[] req = request.split(" ");
        ArrayList<String> args = new ArrayList<>(Arrays.asList(req));
        args.remove(0);
        switch (req[0]) {
            case "search":
                Request searchRequest = new SearchRequest(req[0], args);
            case "library":
                Request libraryRequest = new LibraryRequest(req[0], args);
            default:
                Request helpRequest = new HelpRequest(req[0]);
        }
    }
}
