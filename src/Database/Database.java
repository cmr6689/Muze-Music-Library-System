package Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private Map<String, Song> songs;
    private Map<String, Artist> artists;
    private Map<String, Release> releases;
    private Artist fauxArtist;

    public Database(){
        songs = new HashMap<String, Song>();
        artists =  new HashMap<String, Artist>();
        releases = new HashMap<String, Release>();
    }

    public Collection<Song> getSongs(){
        return songs.values();
    }

    public Collection<Artist> getArtists(){
        return artists.values();
    }

    public Collection<Release> getReleases(){
        return releases.values();
    }

    public void addSong(Song song){
        String guid = song.getGuid();
        songs.put(guid, song);
    }

    public void addArtist(Artist artist){
        String guid = artist.getGuid();
        artists.put(guid, artist);
    }

    public void addRelease(Release release){
        String guid = release.getGuid();
        releases.put(guid, release);
    }

}
