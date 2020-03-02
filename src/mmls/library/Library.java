package mmls.library;

import Database.Artist;
import Database.Release;
import Database.Song;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jarred Moyer
 * @author Shane Burke
 */
public class Library implements Serializable {
    private Map<String, Song> songs;
    private Map<String, Release> releases;
    private Map<String, Artist> artists;
    private HashMap<String,Date> acquisitionDates;
    PersistHelp persist = new PersistHelp();
    public Library() {

        songs = new HashMap<>();
        releases = new HashMap<>();
        artists = new HashMap<>();
        acquisitionDates =  new HashMap<>();
    }

    /**
     * Adds a song and records acquisition date for all input cases
     * @param song Song to be added
     * @param date Date song was acquired on
     */
    private void addSongDate(Song song, Date date){
        String songGuid = song.getGuid();
        Artist artist = song.getArtist();
        String artistGuid = song.getArtistId();
        if(!songs.containsKey(songGuid)) {
            songs.put(songGuid, song);
            addDate(songGuid,date);
            if (!artists.containsKey(artistGuid)) {
                artists.put(artistGuid, artist);
            }
        }
        persist.serialize(this);

    }

    /**
     * Add a song without specifying acquisition date
     * @param song Song to be added
     */
    public void addSong(Song song){
        Date now = new Date();
        addSongDate(song,now);
    }

    /**
     * Add a song while specifying the date to be added
     * @param song Song to be added
     * @param date Acquired date
     */
    public void addSong(Song song, Date date){
        addSongDate(song,date);
    }

    /**
     * Removes a song given its guid
     * @param guid guid of the song
     * @return true if the operation was successful, false otherwise
     */
    public boolean removeSong(String guid){
        boolean removed = false;
        if (songs.containsKey(guid)) {
            songs.remove(guid);
            removed = true;
        }
        return removed;
    }

    /**
     * Add a release to the library
     * @param release The release to be added
     * @param date The date when the acquisition was added
     */
    public void addRelease(Release release, Date date){
        String guid = release.getGuid();
        if(!releases.containsKey(guid)){
            releases.put(guid, release);

            for(Song s : release.getTracks()){
                addSongDate(s,date);
            }
        }
    }

    /**
     * Removes a release, given its guid, from the library
     * @param guid guid of the release
     * @return true if the operation was successful, false otherwise
     */
    public boolean removeRelease(String guid){
        boolean removed = false;
        if (releases.containsKey(guid)) {
            releases.remove(guid);
            removed = true;
        }
        persist.serialize(this);
        return removed;
    }

    /**
     * Removes a generic item from the library
     * @param guid id of the item
     * @return true if operation was successful, false otherwise
     */
    public boolean removeItem(String guid) {
        boolean removed = this.removeRelease(guid);
        if (!removed) {
            removed = this.removeSong(guid);
        }
        persist.serialize(this);
        return removed;

    }

    /**
     * Returns the collection of songs in the library
     * @return collection of songs
     */
    public Collection<Song> getSongs(){
        return songs.values();
    }

    /**
     * Returns the collection of releases in the library
     * @return collection of releases
     */
    public Collection<Release> getReleases(){
        return releases.values();
    }

    /**
     * Return the collection of artists in the library
     * @return collection of artists
     */
    public Collection<Artist> getArtists(){
        return artists.values();
    }

    /**
     * gets the acquisition date of an item id
     * @param id the id of the item in question
     * @return the acquisition date of the item
     */
    public Date getAcquisitionDate(String id){
        return acquisitionDates.get(id);
    }

    /**
     * Adds a date with a corresponding id to the acquisitionDate list
     * @param id id to be added
     * @param date acquired date of item mention above
     */
    private void addDate(String id, Date date){
        acquisitionDates.put(id,date);
    }

    /**
     * removes a date from the acquisitionList
     * @param id id of the item whose date is being removed
     */
    private void removeDate(String id){
        acquisitionDates.remove(id);
        persist.serialize(this);
    }

}
