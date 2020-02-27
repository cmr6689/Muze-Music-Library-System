package Database;


import java.util.ArrayList;
import java.util.List;

public class Release extends Item {

    private double rating;
    private String artistId;
    private String title;
    private String issueDate;
    private String medium;
    private Artist artist;
    private List<String> tracksID;
    private ArrayList<Song> tracks;

    public Release(ArrayList<String> fields, Database db){
        guid = fields.get(0);
        artistId = fields.get(1);
        title = fields.get(2);
        issueDate = fields.get(4);
        medium =  fields.get(3);
        tracksID = fields.subList(5,fields.size());
        tracks = new ArrayList<>();
        swapTracks(db.getSongs());
        swapArtist(db.getArtists());
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

    public String getIssueDate() {
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
