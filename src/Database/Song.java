package Database;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Song implements RatableObject {

    private String artistId;
    private String id;
    private String duration;
    private String title;
    private Artist artist;
    private double rating;
    public Song(ArrayList<String> fields, Database db){
        id =  fields.get(0);
        artistId =  fields.get(1);
        duration =  fields.get(2);
        title = fields.get(3);
        swapArtistName(db);
        rating = 0;
    }

    private void swapArtistName(Database db){
        for(Artist a: db.getArtists()){
            if(a.equalsID(artistId)){
                artist = a;
                break;
            }
        }
        artist = null;
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

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rate) {
        rating = rate;
    }
}
