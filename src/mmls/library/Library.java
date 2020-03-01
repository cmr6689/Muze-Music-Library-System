package mmls.library;

import Database.Artist;
import Database.Release;
import Database.Song;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

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

    public void addSong(Song song){
        Date now = new Date();
        addSongDate(song,now);
    }

    public void addSong(Song song, Date date){
        addSongDate(song,date);
    }

//    private void removeSongID(String id, Artist artist){
//        for(Song s: songs){
//            if(s.equalsGuid(id)){
//                songs.remove(s);
//                for(Artist a : artists){
//                    if(a.equalsGuid(artist.getGuid())){
//                        artists.remove(a);
//                        break;
//                    }
//                }
//                break;
//            }
//        }
//    }

//    public void removeSong(Song song){
//        String guid = song.getGuid();
//        songs.remove(guid);
//    }

    public boolean removeSong(String guid){
        boolean removed = false;
        if (songs.containsKey(guid)) {
            songs.remove(guid);
            removed = true;
        }
        return removed;
    }

    public void addRelease(Release release, Date date){
        String guid = release.getGuid();
        if(!releases.containsKey(guid)){
            releases.put(guid, release);

            for(Song s : release.getTracks()){
                addSongDate(s,date);
            }
        }
    }

    public boolean removeRelease(String guid){
        boolean removed = false;
        if (releases.containsKey(guid)) {
            releases.remove(guid);
            removed = true;
        }
        persist.serialize(this);
        return removed;
    }

    public boolean removeItem(String guid) {
        boolean removed = this.removeRelease(guid);
        if (!removed) {
            removed = this.removeSong(guid);
        }
        persist.serialize(this);
        return removed;

    }

    public Collection<Song> getSongs(){
        return songs.values();
    }

    public Collection<Release> getReleases(){
        return releases.values();
    }

    public Collection<Artist> getArtists(){
        return artists.values();
    }

    public Date getAcquisitionDate(String id){
        return acquisitionDates.get(id);
    }

    private void addDate(String id, Date date){
        acquisitionDates.put(id,date);
    }

    private void removeDate(String id){
        acquisitionDates.remove(id);
        persist.serialize(this);
    }

}
