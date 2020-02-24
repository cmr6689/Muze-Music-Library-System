package mmls.command;

public class SearchCommand implements Command {
    private String keyword;
    private String[] arguments;

    public SearchCommand(String keyword, String[] arguments) {
        this.keyword = keyword;
        this.arguments = arguments;
    }

    @Override
    public void executeCommand() {

    }
}
