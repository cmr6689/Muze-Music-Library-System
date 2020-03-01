package Database;

import java.util.ArrayList;

public class Artist extends Item {
    private String name;
    private String genre;
    private double rating;
    private String guid;

    public Artist(ArrayList<String> fields){
        guid  = fields.get(0);
        name =  fields.get(1);
        rating = 0;
        try{
            genre =  fields.get(2);
        }
        catch (IndexOutOfBoundsException ioe){
            genre =  null;
        }
    }

    public boolean equalsGuid(String id){
        return this.guid.equals(id);
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
