package Results;

import Database.Release;

import java.util.ArrayList;

public class ReleaseList implements ResultSorter {

    /**
     * Can find a Release by putting in PART of the Release Title.
     * Example: "Guardians" will find "Guardians of the Galaxy:"
     * Uses: createArtistNameList()
     */
    private void sortReleaseTitle(ArrayList<String> Releases, String words) {
        //Create a List of Artist Names First
        //createArtistNameList();

        ArrayList<String> updatedTitles = new ArrayList<>();
        int length = Releases.size();
        for(int index = 0; index < length; index++) {
            String artist = Releases.get(index);
            if(artist.contains(words)) {
                updatedTitles.add(artist);
            }
        }
        sortAlphabetically(updatedTitles);
    }

    /**
     * Can find a Release by putting in an artist Name or Artist GUID
     * Example: ""
     * Uses:
     */
    private void sortByArtist() {

    }

    /**
     * Can find a Release by putting in the name or GUID of one of the tracks on the release
     * Example: "71709e8c-fb6b-48c6-972a-851ecff0a60d" will find: Guardians of the Galaxy
     * Example: "Escape (The Pina Coloda Song)" will find: Guardians of the Galaxy
     * Uses:
     */
    private void sortByTrack() {

    }

    /**
     * Finds all releases that have a combined track duration greater than or equal to the min
     * Uses:
     */
    private void sortByMinDuration() {

    }

    /**
     * Finds all releases that have a combined track duration less than or equal to the max
     * Uses:
     */
    private void sortByMaxDuration() {

    }

    /**
     * Finds all releases that have a rating greater than or equal to the input rating.
     * The Rating for the release is calculated by taking the average of each tracks rating on the release
     * Uses:
     */
    @Override
    public void sortRating(ArrayList<String> arrayList, int userRating) {

    }

    /**
     * When sorting the release results the user can specify to have them sorted in the following ways:
     * Alphabetically
     * chronologically by release date
     * chronologically by acquisition date
     * By Rating
     */

    @Override
    public void sortAlphabetically(ArrayList<String> updatedList) {
        java.util.Collections.sort(updatedList);
        System.out.println(updatedList);
    }

    //User has to specify this from the commandline
    public void sortbyReleaseDate() {

    }

    //User has to specify this from the commandline
    public void sortbyAcquistionDate() {

    }

    //User has to specify this from the commandline
    public void sortByRating() {

    }

}
