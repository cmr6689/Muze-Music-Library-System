package Database;


import mmls.library.Library;
import mmls.library.PersistHelp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

public class ParserTest {

    public static void main(String args[]){



        String cwd = ("./files/");
        String artistLoc = cwd+"artists.csv";
        String songLoc =  cwd+"songs.csv";
        String releaseLoc =  cwd+"releases.csv";

        Parser parser =  new Parser();
        Database db =  new Database();
        try{
            parser.parse(db);
        }
        catch (IOException | ParseException ioe) {
            System.out.println("There were issues Loading files: " + ioe);
            File f = new File("./files");
            for (File i : f.listFiles()) {
                System.out.println(i.getName());
            }
        }

        Library lib = new Library();
        for(Song s: db.getSongs()){
            lib.addSong(s);
        }
        PersistHelp ps = new PersistHelp();
        ps.serialize(lib);

        Library newLibrary = ps.deserialize();
        System.out.println(newLibrary);
        System.out.println(newLibrary.getSongs().size());
        System.out.println(newLibrary.getSongs().size() ==  lib.getSongs().size());
        System.out.println("a");

        System.out.println("Done");

    }

}
