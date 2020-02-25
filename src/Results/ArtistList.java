package Results;

import java.util.ArrayList;

public class ArtistList implements ResultSorter {

    //Test Lists
    ArrayList<Integer> artistRating = new ArrayList<>();


    /**
     * When this method is called, it will create a List of ArtistNames
     * from the user's Library
     * Example: [twenty one pilots, Rolling Stones]
     */
    public void createArtistNameList() {

    }

    /**
     * When this method is called, it will create a List of ArtistNames
     * with their associated RATING given by the user from the user's library.
     * Example: [twenty one pilots-3, Rolling Stones-4]
     */
    public void createArtistRatingsList() {

    }

    /**
     * When this method is called, it will create a List of ArtistNames
     * with their associated TYPE given by the user from the user's library.
     * Example: [twenty one pilots-Pop, Rolling Stones-Rock]
     */
    public void createArtistTypeList() {

    }


    /**
     * Can find an artist by putting in PART of the name.
     * Example: "twenty one" will find "twenty one pilots"
     * Uses: createArtistNameList()
     */
    private void sortArtistName(ArrayList<String> artists, String words) {
        //Create a List of Artist Names First
        createArtistNameList();

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


    /**
     * Will Find Artists based off the user min rating.
     * Example: User inputs 4, finds all artists with rating better >= 4
     * Uses: createArtistRatingsList()
     */
    @Override
    public void sortRating(ArrayList<String> arrayList, int userRating) {
        //Create the ArtistRatingsList First:
        createArtistRatingsList();

        ArrayList<String> updatedList = new ArrayList<>();
        int length = arrayList.size();
        for(int index = 0; index < length; index++) {
            String artistInfo = arrayList.get(index);
            String[] info = artistInfo.split("-");
            String rate = info[1];
            int Rating = Integer.getInteger(rate);
            if(Rating >= userRating) {
                updatedList.add(info[0]);
            }

        }
        sortAlphabetically(updatedList);

    }

    private void sortArtistType(ArrayList<String> artistList, String Type) {
        //Create The ArtistType List First:
        createArtistTypeList();

        ArrayList<String> updatedList = new ArrayList<>();
        int length = artistList.size();
        for(int index = 0; index < length; index++) {
            String artist = artistList.get(index);
            if(artist.contains(Type)) {
                String[] artistInfo = artist.split("-");
                updatedList.add(artistInfo[0]);
            }
        }
        sortAlphabetically(updatedList);
    }

    /**
     * Will sort the results alphabetically
     * Every result will be sorted alphabetically
     * Uses: sortArtistName(), sortRating(), sortType()
     */
    @Override
    public void sortAlphabetically(ArrayList<String> updatedList) {
        java.util.Collections.sort(updatedList);
        System.out.println(updatedList);

    }


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
