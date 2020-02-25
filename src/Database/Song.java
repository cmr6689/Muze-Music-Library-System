package Database;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Song {

    String artistId;
    String id;
    String duration;
    String title;
    Artist artist;
    public Song(ArrayList<String> fields, Database db){
        id =  fields.get(0);
        artistId =  fields.get(1);
        duration =  fields.get(2);
        title = fields.get(3);
        swapArtistName(db);
    }

    private void swapArtistName(Database db){
        for(Artist a: db.getArtists()){
            if(a.equalsID(artistId)){
                artist = a;
                break;
            }
        }
    }

    public String getArtistId() {
        return artistId;
    }

    public String getId() {
        return id;
    }

    public String getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString(){
        return title + " by " + artist;
    }

    public boolean equalsGUID(String id){
        return this.id.equals(id);
    }
}
