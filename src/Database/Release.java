package Database;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Release extends Item implements Serializable {


    private double rating;
    private String artistId;
    private String title;
    private Date issueDate;
    private String medium;
    private Artist artist;
    private List<String> tracksID;
    private ArrayList<Song> tracks;
    private String guid;
    private final String defaultSeg = "01";

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

    public Release(ArrayList<String> fields, Database db) throws ParseException {
        super(fields.get(0));

        guid = fields.get(0);
        artistId = fields.get(1);
        title = fields.get(2);
        issueDate = getDate(fields.get(4));
        medium =  fields.get(3);
        tracksID = fields.subList(5,fields.size());
        tracks = new ArrayList<>();
        swapTracks(new ArrayList<>(db.getSongs()));
        swapArtist(new ArrayList<>(db.getArtists()));
        rating = 0;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rate){
        rating = rate;
    }

    private void swapArtist(ArrayList<Artist> artists){
        for(Artist a: artists){
            if(a.equalsGuid(artistId)){
                artist = a;
                break;
            }
        }
    }
    private void swapTracks(ArrayList<Song> songs){
        for(String s: tracksID){
            for(Song song: songs){
                if(song.equalsGuid(s)){
                    tracks.add(song);
                }
            }
        }
    }

    public String getArtistId() {
        return artistId;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist(){
        return artist;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public String getMedium() {
        return medium;
    }

    public List<String> getTrackIds() {
        return tracksID;
    }

    public ArrayList<Song> getTracks(){
        return tracks;
    }

    @Override
    public String toString(){
        return title + " by " + artist;
    }
}
