package Results;

import java.util.ArrayList;

public class ReleaseList implements ResultSorter {

    private void sortRelease() {

    }


    private void sortAquistDate() {

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


    @Override
    public void sortRating(ArrayList<String> arrayList, int userRating) {


    }


    //Sorting Release Results:
    //Title
    //Artist Name
    //Release Title
    //Duration
    //Rating
}
