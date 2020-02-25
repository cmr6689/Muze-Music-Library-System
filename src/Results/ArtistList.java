package Results;

import java.util.ArrayList;

public class ArtistList implements ResultSorter {

    //Test Lists
    ArrayList<Integer> artistRating = new ArrayList<>();



    //Can find an artist by putting in part of the name ex: "twenty one" will find "twenty one pilots"
    private void sortArtistName(ArrayList<String> artists, String words) {
        ArrayList<String> updatedArtists = new ArrayList<>();
        int length = artists.size();
        for(int index = 0; index < length; index++) {
            String artist = artists.get(index);
            if(artist.contains(words)) {
                updatedArtists.add(artist);
            }
        }
        sortAlphabetically(updatedArtists);
    }


    //Will sort the Artists alphabetically
    //* Every result gets sorted alphabetically
    @Override
    public void sortAlphabetically(ArrayList<String> updatedList) {
        java.util.Collections.sort(updatedList);
        System.out.println(updatedList);

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
        artists.add("Luke Bryan");
        artists.add("Kane Brown");
        artists.add("Lil Mosey");
        artists.add("Lil Pump");
        artists.add("Lil Tjay");
        ArtistList artistList = new ArtistList();
        artistList.sortArtistName(artists, "Lil");
    }
}
