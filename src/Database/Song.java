package Database;

import java.io.Serializable;
import java.util.ArrayList;

public class Song extends Item implements Serializable {

    private String artistId;
    private long duration;
    private Artist artist;

    public Song(ArrayList<String> fields, Database db){
        super(fields.get(0));
        artistId =  fields.get(1);

        duration =  Long.parseLong(fields.get(2));
        String name = fields.get(3);
        this.setName(name);

        artist = null;
        swapArtistName(db);
        this.setRating(0);

    }

    private void swapArtistName(Database db){

        for(Artist a: db.getArtists()){

            if(a.equalsGuid(artistId)){
               artist = a;
                break;
            }
        }
    }

    public String getArtistId() {
        return artistId;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString(){
        return this.getName() + " by " + artist;
    }

    public boolean equalsGuid(String id){
        return this.getGuid().equals(id);
    }

    public Artist getArtist(){
        return artist;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Song){
            Song temp = (Song)o;
            return equalsGuid(temp.getGuid());
        }
        else{
            return false;
        }

    }
}
