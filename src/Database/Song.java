package Database;

import java.io.Serializable;
import java.util.ArrayList;

public class Song extends Item implements Serializable {

    private String artistId;
    private String duration;
    private String title;
    private Artist artist;
    private double rating;
    private String guid;
    public Song(ArrayList<String> fields, Database db){
        super(fields.get(0));
        guid =  fields.get(0);
        artistId =  fields.get(1);

        duration =  fields.get(2);
        title = fields.get(3);
        if(title.contains("My Sweet Lord")){
            for(String s: fields){
                System.out.println(s);
            }
        }
        artist = null;
        swapArtistName(db);
        rating = 0;
        System.out.println("Done : " + (artist ==  null));
    }

    private void swapArtistName(Database db){



        for(Artist a: db.getArtists()){

            if(a.equalsGuid(artistId)){
                ArrayList<String> vals =  new ArrayList<>();
                vals.add(a.getGuid());
                vals.add(a.getName());
                if(a.hasGenre()){
                    vals.add(a.getGenre());
                }
                Artist temp =  new Artist(vals);
                artist = temp;

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
