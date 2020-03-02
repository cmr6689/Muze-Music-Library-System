package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * This class uses the provided CSV files to parse each line correctly into the database class.
 * Each line in the file is converted to its respective object (Song, Artist, Release).
 * The files are parsed at every program start.
 * @author Jarred Moyer, Cameron Riu
 */
public class Parser {


    private String artistFile;
    private String songFile;
    private String releaseFile;

    /**
     * Constructor to set the proper file path
     */
    public Parser(){
        String cwd = ("./files/");
        artistFile = cwd+"artists.csv";
        songFile =  cwd+"songs.csv";
        releaseFile =  cwd+"releases.csv";
    }

    /**
     * Runs the parse method to parse the songs, artists, and releases into the database
     * @param db : database containing a HashMap of each collection
     */
    public void runParse(Database db) {


        try {
            parse(db);
        } catch (IOException | ParseException ioe) {
            System.out.println("There were issues Loading files: " + ioe);
            System.out.println("Now displaying files found in aimed current working director (//files)");
            System.out.println("Did you put your files in the /files?");
            File f = new File("./files");
            for (File i : f.listFiles()) {
                System.out.println(i.getName());
            }
        }
    }

    /**
     * Creates the file reader
     * @param name of the reader
     * @return the BufferedReader
     * @throws IOException
     */
    private BufferedReader createReader(String name) throws IOException{

        return new BufferedReader(new FileReader(name));
    }

    private String removeLeadingComma(String line){
        if(line.startsWith(",")){

            return line.substring(1);
        }
        else{
            return line;
        }
    }

    /**
     * Splits each line of the CSV file to gather each piece of data
     * @param line line of the CSV file being split
     * @return an arraylist containing each piece of data from a line
     */
    private ArrayList<String> splitLine(String line){

        ArrayList<String> returned =  new ArrayList<>();
        String[] tempField = line.split(",");
        if(!line.contains("\"")){
            for(String s: tempField){
                returned.add(s);
            }
            return  returned;
        }

        String tempRunning = "";
        char[] tempLine = line.toCharArray();
        int count = 0;
        for(char c: tempLine){
            if(c== ','){
                if(count%2 == 0){
                    returned.add(tempRunning);
                    tempRunning = "";
                }
                else{
                    tempRunning =  tempRunning + c;
                }
            }
            else if(c=='"'){
                count++;
                tempRunning = tempRunning + c;
            }
            else{
                tempRunning = tempRunning + c;
            }
            //count++;
        }
        returned.add(tempRunning);

        return returned;
    }

    /**
     * Used to read the artist CSV file into the database with each line representing an artist object.
     * @param db to add an artist to
     * @param name of the reader
     * @throws IOException
     */
    private void readArtists(Database db, String name)throws IOException{
        BufferedReader reader =createReader(name);
        String line= "";
        while((line = reader.readLine()) != null){
            ArrayList<String> fields = splitLine(line);
            Artist artist =  new Artist(fields);
            db.addArtist(artist);
        }

    }

    /**
     * Used to read the song csv file into the database with each line representing a song object.
     * @param db to add a song to
     * @param name of the reader
     * @throws IOException
     */
    private void readSong(Database db, String name)throws IOException{
        BufferedReader reader =createReader(name);
        String line= "";

        while((line = reader.readLine()) != null){

            ArrayList<String> fields = splitLine(line);
            //System.out.println(fields.toString());
            Song song =  new Song(fields,db);
            db.addSong(song);

        }
    }

    /**
     * Used to read the release csv file into the database with each line representing a release object
     * @param db to add a release to
     * @param name of the reader
     * @throws IOException
     * @throws ParseException
     */
    private void readRelease(Database db, String name) throws IOException, ParseException {
        BufferedReader reader =createReader(name);
        String line= "";

        while((line = reader.readLine()) != null){

            ArrayList<String> fields = splitLine(line);
            Release release =  new Release(fields, db);
            db.addRelease(release);


        }
    }

    /**
     * Calls the read methods for all 3 CSV files at once
     * @param database to be written to
     * @throws IOException
     * @throws ParseException
     */
    public void parse(Database database) throws IOException, ParseException {

        readArtists(database,artistFile);
        readSong(database,songFile);
        readRelease(database,releaseFile);

    }


}
