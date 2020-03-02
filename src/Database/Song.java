package Database;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class holds the data for a song from one line of the song CSV file. It contains the artist
 * of the song, the duration, and the GUID of the song.
 * @author Jarred Moyer, Shane Burke, Cameron Riu
 */
public class Song extends Item implements Serializable {

    private String artistId;
    private long duration;
    private Artist artist;

    /**
     * Creates a song object and sets the GUID, artist GUID,the name, and sets the artist to an
     * artist object
     * @param fields from parsed CSV line
     * @param db to find the artist
     */
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

    /**
     * Used to assign the artist to the song using the artist GUID
     * @param db to use for finding the artist
     */
    private void swapArtistName(Database db){

        for(Artist a: db.getArtists()){

            if(a.equalsGuid(artistId)){
               artist = a;
                break;
            }
        }
    }

    /**
     * Getter fot the artist GUID
     * @return GUID of artist
     */
    public String getArtistId() {
        return artistId;
    }

    /**
     * Getter for the duration of the song
     * @return duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Provides a clean way to print the song name and the artist
     * @return clean string
     */
    @Override
    public String toString(){
        return this.getName() + " by " + artist;
    }

    /**
     * Check to see if the GUID of this song matches another
     * @param id of another song
     * @return true if a match
     */
    public boolean equalsGuid(String id){
        return this.getGuid().equals(id);
    }

    /**
     * Getter for the artist object of the song
     * @return artist
     */
    public Artist getArtist(){
        return artist;
    }

    /**
     * Check to see if two song object are the same
     * @param o objects to be compared
     * @return true if equal
     */
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
