package Database;

import java.util.ArrayList;

public class Song extends Item {

    private String artistId;
    private String duration;
    private String title;
    private Artist artist;
    private double rating;
    private String guid;
    public Song(ArrayList<String> fields, Database db){
        guid =  fields.get(0);
        artistId =  fields.get(1);
        duration =  fields.get(2);
        title = fields.get(3);
        swapArtistName(db);
        rating = 0;
    }

    private void swapArtistName(Database db){
        for(Artist a: db.getArtists()){
            if(a.equalsGuid(artistId)){
                artist = a;
                break;
            }
        }
        artist = null;
    }

    public String getArtistId() {
        return artistId;
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

    public boolean equalsGuid(String id){
        return this.guid.equals(id);
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rate) {
        rating = rate;
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
