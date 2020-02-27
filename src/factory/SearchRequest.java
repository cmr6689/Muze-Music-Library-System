package factory;

import java.util.ArrayList;

public class SearchRequest implements Request {
    private String command;
    private ArrayList<String> args;

    public SearchRequest(String command, ArrayList<String> args) {
        this.command = command;
        this.args = args;
    }

    @Override
    public ArrayList<String> invokeRequest() {
        return null;
    }
}
