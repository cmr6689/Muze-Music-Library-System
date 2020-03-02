package Database;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class holds the data for an artist from one line of the artist CSV file. It
 * contains the name, the GUID, and the type if applicable.
 * @author Jarred Moyer, Shane Burke, Cameron Riu
 */
public class Artist extends Item implements Serializable {
    private String genre;
    private double rating;
    private String guid;

    /**
     * Creates an artist with a GUID, and name, and a genre/type if applicable
     * @param fields from the parsed CSV line
     */
    public Artist(ArrayList<String> fields){
        super(fields.get(0));
        guid  = fields.get(0);

        setName(fields.get(1));
        rating = 0;
        try{
            genre =  fields.get(2);
        }
        catch (IndexOutOfBoundsException ioe){
            genre =  null;
        }

    }

    /**
     * Compare the GUID of this artist with another artist
     * @param id GUID of other artist
     * @return true if match
     */
    public boolean equalsGuid(String id){
        return getGuid().equals(id);
    }

    /**
     * Getter for the genre/type of the artist
     * @return genre
     */
    public String getGenre() {
        if(!hasGenre()){
            return "None";
        }
        else{
            return genre;
        }
    }

    /**
     * Check to see if the artist has a genre/type
     * @return
     */
    public boolean hasGenre(){
        return  genre !=  null;
    }

    /**
     * Prints out the name of the artist
     * @return name
     */
    @Override
    public String toString(){
        String result = getName();
        if (hasGenre()) {
            result += " (" + getGenre() + ")";
        }
        return result;
    }

    /**
     * Getter for the rating of the artist
     * @return rating
     */
    @Override
    public double getRating() {
        return rating;
    }

    /**
     * Set the rating of the artist
     * @param rate : number 1-5
     */
    @Override
    public void setRating(double rate) {
        rating = rate;
    }
}
