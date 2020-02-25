package Results;

import java.util.ArrayList;

public class ArtistList implements ResultSorter {

    ArrayList<String> artists;




    public ArtistList(ArrayList<String> Artists) {
        this.artists = Artists;

    }

    //Can find an artist by putting in part of the name ex: "twenty one" will find "twenty one pilots"
    private void sortArtistName(ArrayList<String> artists, String words) {
        int length = artists.size();
        for(int index = 0; index < length; index++) {
            String artist = artists.get(index);
            if(artist.contains(words)) {
                System.out.println("Found: " + artist);
            }
        }


    }

    //Testing
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
        artistList.sortArtistName(artists, "Lil");
    }
}
