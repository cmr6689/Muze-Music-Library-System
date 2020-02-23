package Results;

import java.util.ArrayList;

public class ArtistList implements ResultSorter {

    ArrayList<String> artists;




    public ArtistList(ArrayList<String> Artists) {
        this.artists = Artists;

    }

    private void sortArtist() {
        sortAlphabetically();
    }

    @Override
    public void sortAlphabetically() {
        java.util.Collections.sort(artists);
        System.out.println(artists);

    }

    @Override
    public void sortRating() {

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
        artists.add("Luke Bryan");
        artists.add("Kane Brown");
        ArtistList artistList = new ArtistList(artists);
        artistList.sortAlphabetically();
    }
}
