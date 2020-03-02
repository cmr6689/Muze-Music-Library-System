package mmls;

import Database.Database;
import mmls.command.Command;
import mmls.command.CommandFactory;
import mmls.library.Library;
import mmls.library.PersistHelp;
import Database.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLine {

    private static final String WELCOME_TEXT = "Welcome to the Muze Music Library System. Type [exit] to close the program.";
    private static final String INPUT_PROMPT_TEXT = "Please enter a command.\nUser: ";
    private static final String INVALID_REQUEST_MESSAGE = "Invalid request. Please type \"help\" to see the list of available commands.";

    public static void main(String ...args) {


        //Pattern r = Pattern.compile(LIBRARY_SEARCH_ARTIST_REQUEST_PATTERN);
        Scanner scanner = new Scanner(System.in);
        PersistHelp persist = new PersistHelp();
        File saveFile = new File(persist.getName());
        Library library;
        if(saveFile.exists() && !saveFile.isDirectory()){
            library = persist.deserialize();
        }
        else{
            library = new Library();
        }

        Database database = new Database();
        
        Parser parser =  new Parser();
        parser.runParse(database);

        String userInput;
        CommandFactory commandFactory = new CommandFactory(library, database);
        System.out.println(WELCOME_TEXT);
        while (true) {
            System.out.print(INPUT_PROMPT_TEXT);
            userInput = scanner.nextLine();
            if (userInput.equals("exit")) break;
            processRequest(userInput, commandFactory);
            System.out.println();
        }
    }

    private static void processRequest(String userInput, CommandFactory commandFactory) {
        Command command = commandFactory.createCommand(userInput);
        if (command != null) {
            command.executeCommand();
        } else {
            System.out.println(INVALID_REQUEST_MESSAGE);
        }
    }
}
