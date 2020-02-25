package Database;

import java.util.ArrayList;

public class Artist implements RatableObject {

    private String id;
    private String name;
    private String genre;
    private double rating;

    public Artist(ArrayList<String> fields){
        id  = fields.get(0);
        name =  fields.get(1);
        rating = 0;
        try{
            genre =  fields.get(2);
        }
        catch (IndexOutOfBoundsException ioe){
            genre =  null;
        }
    }

    public boolean equalsID(String s){
        return id.equals(s);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        if(!hasGenre()){
            return "No genre";
        }
        else{
            return genre;
        }
    }

    public boolean hasGenre(){
        return  genre !=  null;
    }

    @Override
    public String toString(){
        return  name;
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
