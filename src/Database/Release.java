package Database;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class holds the data for an artist from one line of the Release CSV file
 * @author Jarred Moyer, Ryan Borger, Cameron Riu, Shane Burke
 */
public class Release extends Item implements Serializable, Audio {

    private long duration;
    private double rating;
    private String artistId;
    private Date issueDate;
    private String medium;
    private Artist artist;
    private List<String> tracksID;
    private ArrayList<Song> tracks;
    private String guid;
    private final String defaultSeg = "01";

    /**
     * Method to get the date from a provided String
     * @param date string date
     * @return Java SimpleDateFormat object of string provided
     * @throws ParseException
     */
    private Date getDate(String date) throws ParseException{
        String[]  split =  date.split("-");
        if(split.length == 3){
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }
        else if(split.length == 1){
            String refactoredDate = date + "-" + defaultSeg + "-" + defaultSeg;
            return new SimpleDateFormat("yyyy-MM-dd").parse(refactoredDate);
        }
        else{
            String refactoredDate = date + "-" + defaultSeg;
            return new SimpleDateFormat("yyyy-MM-dd").parse(refactoredDate);
        }

    }

    /**
     * Creates a release that has a GUID, a duration, an artist GUID, a title, an issue date,
     * a medium, the tracklist, and the track GUID's
     * @param fields from the parsed CSV line
     * @param db database to find the song and artist objects
     * @throws ParseException
     */
    public Release(ArrayList<String> fields, Database db) throws ParseException {
        super(fields.get(0));
        duration = 0;
        guid = fields.get(0);
        artistId = fields.get(1);
        setName(fields.get(2));
        issueDate = getDate(fields.get(4));
        medium =  fields.get(3);
        tracksID = fields.subList(5,fields.size());
        tracks = new ArrayList<>();
        swapTracks(new ArrayList<>(db.getSongs()));
        swapArtist(new ArrayList<>(db.getArtists()));
        rating = 0;
    }

    /**
     * Getter fot the rating of the release
     * @return rating
     */
    @Override
    public double getRating() {
        return rating;
    }

    /**
     * Set the rating of the release
     * @param rate number 1-5
     */
    @Override
    public void setRating(double rate){
        rating = rate;
    }

    /**
     * Used to find the artist object in the database to assign to the release
     * @param artists list of artists
     */
    private void swapArtist(ArrayList<Artist> artists){
        for(Artist a: artists){
            if(a.equalsGuid(artistId)){
                artist = a;
                break;
            }
        }
    }

    /**
     * Used to find the song objects in the database to assign the tracks to the release
     * @param songs list of songs to be added as tracks
     */
    private void swapTracks(ArrayList<Song> songs){
        for(String s: tracksID){
            for(Song song: songs){
                if(song.equalsGuid(s)){
                    tracks.add(song);
                    duration += song.getDuration();
                }
            }
        }
    }

    /**
     * Getter for the artist of the release
     * @return artist
     */
    public Artist getArtist(){
        return artist;
    }

    /**
     * Getter for the track list of the release
     * @return list of songs
     */
    public ArrayList<Song> getTracks(){
        return tracks;
    }

    /**
     * Getter for the duration of the release
     * @return duration
     */
    public long getDuration(){
        return duration;
    }

    /**
     * Clean way to print the release
     * @return title by artist
     */
    @Override
    public String toString(){
        return getName() + " by " + artist;
    }
}
