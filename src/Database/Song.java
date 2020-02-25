package Database;

import java.util.ArrayList;

public class Song {

    String artistId;
    String id;
    String duration;
    String title;

    public Song(ArrayList<String> fields){
        id =  fields.get(0);
        artistId =  fields.get(1);
        duration =  fields.get(2);
        title = fields.get(3);
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
        return title + " by " + artistId;
    }

    public boolean equalsGUID(String id){
        return this.id.equals(id);
    }
}
