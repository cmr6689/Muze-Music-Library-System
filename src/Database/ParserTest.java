package Database;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class ParserTest {

    public static void main(String args[]){



        String cwd = (".\\files\\");


        String artistLoc = cwd+"artists.csv";
        String songLoc =  cwd+"songs.csv";
        String releaseLoc =  cwd+"releases.csv";

        Parser parser =  new Parser(artistLoc,songLoc,releaseLoc);
        Database db =  new Database();
        try{
            parser.parse(db);
        }
        catch (IOException ioe) {
            System.out.println("There were issues Loading files: " + ioe);
            File f = new File(".\\files");
            for (File i : f.listFiles()) {
                System.out.println(i.getName());
            }
        }





    }

}
