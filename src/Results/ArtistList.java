package Results;

import java.util.ArrayList;
import java.util.List;

public class ArtistList implements ResultSorter {

    private static List<Integer> artistRating;
    ArrayList<String> artists;

    //ArrayList<Integer> artistRating;



    public ArtistList(ArrayList<String> Artists) {
        this.artists = Artists;

    }

    public void sortArtist() {
        sortAlphabetically();
    }

    @Override
    public void sortAlphabetically() {
        java.util.Collections.sort(artists);
        System.out.println(artists);

    }

    @Override
    public void sortRating() {
        int userRating = 9;
        for (int rating: artistRating){
            if(rating <= userRating){
                artistRating.remove(rating);
            }
        }
        System.out.println(artistRating);

    }

    //Sorting Artist Results:
    //By Rating
    //By Name
    //By Type

    public static void main(String args[]) {
        ArrayList<String> artists = new ArrayList<>();
        artists.add("Luke Combs");
        artists.add("JuiceWRLD");
        artists.add("Lil Tecca");
        artists.add("XXXTENTACION");
        artists.add("Kane Brown");

        ArtistList artistList = new ArtistList(artists);
        artistList.sortAlphabetically();
        artistRating.add(3);
        artistRating.add(3);
        artistRating.add(4);
        artistRating.add(5);
    }
}
