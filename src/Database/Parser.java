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

    public Parser(String s1, String s2, String s3){
        artistFile =  s1;
        songFile = s2;
        releaseFile = s3;
    }

    private BufferedReader createReader(String name) throws IOException{
        System.out.println(System.getProperty("user.dir"));
        return new BufferedReader(new FileReader(name));

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
                    returned.add(tempRunning);
                    tempRunning = "";
                }
                returned.add(s);
            }
            else{
                tempRunning += s;
            }
        }
        if(!tempRunning.equals("\"")){
            returned.add(tempRunning);
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

    public void parse(Database database) throws IOException {

        readArtists(database,artistFile);

    }


}
