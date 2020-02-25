package Database;

import java.util.ArrayList;

public class Database {

    private ArrayList<Song> songs;
    private ArrayList<Artist> artists;
    private ArrayList<Release> releases;

    public Database(){
        songs = new ArrayList<Song>();
        artists =  new ArrayList<Artist>();
        releases = new ArrayList<Release>();
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public ArrayList<Artist> getArtists(){
        return artists;
    }

    public ArrayList<Release> getReleases(){
        return releases;
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public void addArtist(Artist artist){
        artists.add(artist);
    }

    public void addRelease(Release release){
        release.swapTracks(songs);
        releases.add(release);

    }

}
