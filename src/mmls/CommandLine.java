package mmls;

import Database.Database;
import mmls.command.CommandFactory;
import mmls.library.Library;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLine {

    private static final String WELCOME_TEXT = "Welcome to the Muze Music Library System.";
    private static final String INPUT_PROMPT_TEXT = "Please enter a command.\nUser: ";

    public static void main(String ...args) {


        //Pattern r = Pattern.compile(LIBRARY_SEARCH_ARTIST_REQUEST_PATTERN);
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        Database database = new Database();

        String userInput;
        System.out.println(WELCOME_TEXT);
        System.out.print(INPUT_PROMPT_TEXT);
        userInput = scanner.nextLine();
        CommandFactory request = new CommandFactory(library, database);
        request.createCommand(userInput);


        /*Matcher m = r.matcher(userInput);
        if (m.matches()) {
            System.out.println(m.group("name"));
        }*/
    }
}
