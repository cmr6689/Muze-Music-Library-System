package Database;


import java.util.ArrayList;
import java.util.List;

public class Release {

    String id;
    String artistId;
    String title;
    String issueDate;
    String medium;

    List<String> tracks;
    public Release(ArrayList<String> fields){
        id = fields.get(0);
        artistId = fields.get(1);
        title = fields.get(2);
        issueDate = fields.get(4);
        medium =  fields.get(3);
        tracks = fields.subList(5,fields.size());

    }

    public String getId() {
        return id;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getTitle() {
        return title;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getMedium() {
        return medium;
    }

    public List<String> getTracks() {
        return tracks;
    }

    @Override
    public String toString(){
        return title + " by " + artistId;
    }
}
