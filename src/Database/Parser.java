package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
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
            //System.out.println(fields.toString());
            Song song =  new Song(fields,db);
            db.addSong(song);

        }
    }

    private void readRelease(Database db, String name) throws IOException, ParseException {
        BufferedReader reader =createReader(name);
        String line= "";

        while((line = reader.readLine()) != null){

            ArrayList<String> fields = splitLine(line);
            Release release =  new Release(fields, db);
            db.addRelease(release);


        }
    }

    public void parse(Database database) throws IOException, ParseException {

        readArtists(database,artistFile);
        readSong(database,songFile);
        readRelease(database,releaseFile);

    }


}
