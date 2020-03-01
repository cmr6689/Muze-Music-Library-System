package Results;

import Database.Artist;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;

public class ArtistList implements ResultSorter {

    //Test Lists
    ArrayList<Integer> artistRating = new ArrayList<>();


    /**
     * When this method is called, it will create a List of ArtistNames
     * from the user's Library
     * Example: [twenty one pilots, Rolling Stones]
     */
    public ArrayList<String> createArtistNameList() {
        ArrayList<String> ArtistNames = new ArrayList<>();

        Library library = new Library();
        Collection<Artist> artists = library.getArtists();
        for(Artist artist : artists) {
            ArtistNames.add(artist.getName());

        }

        return ArtistNames;

    }

    /**
     * When this method is called, it will create a List of ArtistNames
     * with their associated RATING given by the user from the user's library.
     * Example: [twenty one pilots-3, Rolling Stones-4]
     */
    public ArrayList<String> createArtistRatingsList() {
        ArrayList<String> ArtistRatings = new ArrayList<>();

        Library library = new Library();
        Collection<Artist> artists = library.getArtists();
        for(Artist artist : artists) {
            String current = " ";
            String artistname = artist.getName();
            String artistRating = Double.toString(artist.getRating());
            current = (artistname + "-" + artistRating);
            ArtistRatings.add(current);

        }

        return ArtistRatings;

    }

    /**
     * When this method is called, it will create a List of ArtistNames
     * with their associated TYPE given by the user from the user's library.
     * Example: [twenty one pilots-Pop, Rolling Stones-Rock]
     */
    public ArrayList<String> createArtistTypeList() {
        ArrayList<String> artistType = new ArrayList<>();
        Library library = new Library();
        Collection<Artist> artists = library.getArtists();
        for(Artist artist : artists) {
            String current = " ";
            String artistName = artist.getName();
            String artistGenre = artist.getGenre();
            current = (artistName + "-" + artistGenre);
            artistType.add(current);
        }

        return artistType;

    }


    /**
     * Can find an artist by putting in PART of the name.
     * Example: "twenty one" will find "twenty one pilots"
     * Uses: createArtistNameList()
     */
    private void sortArtistName(String words) {
        //Create a List of Artist Names First
        ArrayList<String> artistNames = createArtistNameList();

        ArrayList<String> updatedArtists = new ArrayList<>();
        int length = artistNames.size();
        for(int index = 0; index < length; index++) {
            String artist = artistNames.get(index);
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
    public void sortRating(int userRating) {
        //Create the ArtistRatingsList First:
        ArrayList<String> ArtistRatings = createArtistRatingsList();

        ArrayList<String> updatedList = new ArrayList<>();
        int length = ArtistRatings.size();
        for(int index = 0; index < length; index++) {
            String artistInfo = ArtistRatings.get(index);
            String[] info = artistInfo.split("-");
            String rate = info[1];
            int Rating = Integer.getInteger(rate);
            if(Rating >= userRating) {
                updatedList.add(info[0]);
            }

        }

        sortAlphabetically(updatedList);

    }

    private void sortArtistType(String Type) {
        //Create The ArtistType List First:
        ArrayList<String> artistGenre = createArtistTypeList();

        ArrayList<String> updatedList = new ArrayList<>();
        int length = artistGenre.size();
        for(int index = 0; index < length; index++) {
            String artist = artistGenre.get(index);
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
    
}
