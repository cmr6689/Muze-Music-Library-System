package Database;

import java.io.Serializable;
import java.util.ArrayList;

public class Song extends Item implements Serializable {

    private String artistId;
    private String duration;
    private Artist artist;
    private double rating;
    private String guid;
    public Song(ArrayList<String> fields, Database db){
        super(fields.get(0));
        guid =  fields.get(0);
        artistId =  fields.get(1);

        duration =  fields.get(2);
        String name = fields.get(3);
        this.setName(name);
        if(name.contains("My Sweet Lord")){
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

        boolean debug = artistId.equals("89ad4ac3-39f7-470e-963a-56509c546377");

        for(Artist a: db.getArtists()){
            System.out.println("Artist is: " + a.getGuid());
            if(debug){
                System.out.println("BERRY");
                System.out.println(a.getGuid().equals("89ad4ac3-39f7-470e-963a-56509c546377"));
                System.out.println("Name: " +  this.getName());
            }
            if(a.equalsGuid(artistId)){
               artist = a;
                break;
            }
        }
    }

    public String getArtistId() {
        return artistId;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString(){
        return this.getName() + " by " + artist;
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
