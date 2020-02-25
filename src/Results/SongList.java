package Results;

import java.util.ArrayList;

public class SongList implements ResultSorter {

    private void sortSongs() {

    }

    private void sortAquistDate() {
    }


    private void createSongRatingsList(){

    }
    private void createSongNameList(){

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

    /**
     * Finds all songs based off the user min rating.
     * Example: User inputs 4, finds all artists with rating better >= 4
     * Uses: createSongRatingsList()
     */
    @Override
    public void sortRating(ArrayList<String> arrayList, int userRating) {
        createSongRatingsList();

        ArrayList<String> updatedList = new ArrayList<>();
        int length = arrayList.size();
        for(int index = 0; index < length; index++) {
            String songInfo = arrayList.get(index);
            String[] info = songInfo.split("-");
            String rate = info[1];
            int Rating = Integer.getInteger(rate);
            if(Rating >= userRating) {
                updatedList.add(info[0]);
            }
        }
        sortAlphabetically(updatedList);

    }

    private void sortTitle(ArrayList<String> songs, String words){
        createSongNameList();

        ArrayList<String> updatedSongs = new ArrayList<>();
        int length = songs.size();
        for(int index = 0; index < length; index++) {
            String song = songs.get(index);
            if(songs.contains(words)) {
                updatedSongs.add(song);
            }
        }
        sortAlphabetically(updatedSongs);
    }

    //Sorting Song Results:
    //Title
    //Artist Name
    //Track Name
    //Track Duration
    //Min Rating
}
