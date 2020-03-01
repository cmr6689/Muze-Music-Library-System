package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Parser {


    private String artistFile;
    private String songFile;
    private String releaseFile;

    public Parser(){
        String cwd = ("./files/");
        artistFile = cwd+"artists.csv";
        songFile =  cwd+"songs.csv";
        releaseFile =  cwd+"releases.csv";
    }

    public void runParse(Database db) {


        try {
            parse(db);
        } catch (IOException ioe) {
            System.out.println("There were issues Loading files: " + ioe);
            System.out.println("Now displaying files found in aimed current working director (//files)");
            System.out.println("Did you put your files in the /files?");
            File f = new File("./files");
            for (File i : f.listFiles()) {
                System.out.println(i.getName());
            }
        }
    }

    private BufferedReader createReader(String name) throws IOException{
        System.out.println(System.getProperty("user.dir"));
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

    private ArrayList<String> splitLine(String line){
        //System.out.println("Line is: " + line);
        ArrayList<String> returned =  new ArrayList<>();
        String[] tempField = line.split(",");
        if(!line.contains("\"")){
            for(String s: tempField){
                returned.add(s);
            }
            return  returned;
        }
        String tempRunning = "";
        for(String s: tempField){
            if(!s.contains("\"")){
                if(tempRunning!= ""){

                    returned.add(removeLeadingComma(tempRunning));
                    tempRunning = "";
                }
                returned.add(s);
            }
            else{
                tempRunning += "," + s;
            }
        }
        if(!tempRunning.equals("\"")){
            returned.add(removeLeadingComma(tempRunning));
        }

        return returned;
    }

    private void readArtists(Database db, String name)throws IOException{
        BufferedReader reader =createReader(name);
        String line= "";
        while((line = reader.readLine()) != null){
            ArrayList<String> fields = splitLine(line);
            Artist artist =  new Artist(fields);
            db.addArtist(artist);
        }

    }

    private void readSong(Database db, String name)throws IOException{
        BufferedReader reader =createReader(name);
        String line= "";

        while((line = reader.readLine()) != null){

            ArrayList<String> fields = splitLine(line);

            Song song =  new Song(fields,db);
            db.addSong(song);

        }
    }

    private void readRelease(Database db, String name) throws IOException{
        BufferedReader reader =createReader(name);
        String line= "";

        while((line = reader.readLine()) != null){

            ArrayList<String> fields = splitLine(line);
            Release release =  new Release(fields, db);
            db.addRelease(release);


        }
    }

    public void parse(Database database) throws IOException {

        readArtists(database,artistFile);
        readSong(database,songFile);
        readRelease(database,releaseFile);

    }


}
