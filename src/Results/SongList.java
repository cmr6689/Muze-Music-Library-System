package Results;

import Database.Song;
import mmls.library.Library;

import java.util.ArrayList;
import java.util.Collection;

public class SongList implements ResultSorter {

    public void sortType(ArrayList<String> updatedList) {
        String type = " ";
        //Gets how the user wants the results to be sorted Ex: Alphabetically, Acquist Date, Rating
        if(type.equals("Alphabetically")) {
            sortAlphabetically(updatedList);
        }

        if(type.equals("Date")) {
            sortAquistDate(updatedList);
        }

        if(type.equals("Rating")) {
            sortByRating(updatedList);
        }


    }

    private ArrayList<String> createSongNames() {
        ArrayList<String> SongNames = new ArrayList<>();
        Library library = new Library();
        Collection<Song> songs = library.getSongs();
        for (Song song : songs) {
            String current = " ";
            //Add Song Title
            String songTitle = song.getTitle();
            //Add Rating
            String Rating = Double.toString(song.getRating());
            //Add Acquisition
            //TODO Make sure to add this once Song has it

        }


        return SongNames;
    }




    private void sortSongsName(String words) {
        //Create a List of Artist Names First
        ArrayList<String> SongNames = createSongNames();

        ArrayList<String> updatedSongs = new ArrayList<>();
        int length = SongNames.size();
        for(int index = 0; index < length; index++) {
            String songTitle = SongNames.get(index);
            if(songTitle.contains(words)) {
                updatedSongs.add(songTitle);
            }
        }
        sortType(updatedSongs);
    }




    private void sortAquistDate(ArrayList<String> updatedList) {

    }

    private void sortByRating(ArrayList<String> updatedList) {

    }


    @Override
    public void sortAlphabetically(ArrayList<String> updatedList) {
        java.util.Collections.sort(updatedList);
        System.out.println(updatedList);

    }

    @Override
    public void sortRating(int userRating) {
        //All Song Ratings must be greater than or equal to the user Rating input

        //Create the ArtistRatingsList First:
        //ArrayList<String> ArtistRatings = createArtistRatingsList();

//        ArrayList<String> updatedList = new ArrayList<>();
//        int length = ArtistRatings.size();
//        for(int index = 0; index < length; index++) {
//            String artistInfo = ArtistRatings.get(index);
//            String[] info = artistInfo.split("-");
//            String rate = info[1];
//            int Rating = Integer.getInteger(rate);
//            if(Rating >= userRating) {
//                updatedList.add(info[0]);
//            }
//
//        }
//
//        sortAlphabetically(updatedList);


    }

    //How user may search for songs:
    /*
    One or more words in song title DONE
    By Specifying the artist name or GUID
    By Specifying the release Title or GUID
    By Specifying a min/max song duration
    By Specifying a minimum Rating

     */


}
