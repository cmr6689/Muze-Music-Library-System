package Database;

import java.util.ArrayList;

public class Artist {

    private String id;
    private String name;
    private String genre;

    public Artist(ArrayList<String> fields){
        id  = fields.get(0);
        name =  fields.get(1);
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
}
