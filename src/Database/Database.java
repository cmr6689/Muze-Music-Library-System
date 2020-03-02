package Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the collections of songs, artists, and releases that user and the music
 * library has access to.
 * @author Jarred Moyer, Shane Burke
 */
public class Database {

    //map of all songs
    private Map<String, Song> songs;
    //map of all artists
    private Map<String, Artist> artists;
    //map of all releases
    private Map<String, Release> releases;
    private Artist fauxArtist;

    /**
     * Constructor for the maps
     */
    public Database(){
        songs = new HashMap<String, Song>();
        artists =  new HashMap<String, Artist>();
        releases = new HashMap<String, Release>();
    }

    /**
     * Getter for the values in the song hashmap
     * @return all the available songs
     */
    public Collection<Song> getSongs(){
        return songs.values();
    }

    /**
     * Getter for the values in the artist hashmap
     * @return all available artists
     */
    public Collection<Artist> getArtists(){
        return artists.values();
    }

    /**
     * Getter for the values in the release hashmap
     * @return all available releases
     */
    public Collection<Release> getReleases(){
        return releases.values();
    }

    /**
     * Add a song to the song hashmap, uses the GUID of the song as the key and the song object
     * as the value.
     * @param song to be added to the database
     */
    public void addSong(Song song){
        String guid = song.getGuid();
        songs.put(guid, song);
    }

    /**
     * Add an artist to the artist hashmap, uses the GUID of the artist as the key and the
     * artist object as the value.
     * @param artist to be added to the database
     */
    public void addArtist(Artist artist){
        String guid = artist.getGuid();
        artists.put(guid, artist);
    }

    /**
     * Add a release to the release hashmap, uses the GUID of the release as the key and the
     * release object as the value.
     * @param release to be added to the database
     */
    public void addRelease(Release release){
        String guid = release.getGuid();
        releases.put(guid, release);
    }

}
