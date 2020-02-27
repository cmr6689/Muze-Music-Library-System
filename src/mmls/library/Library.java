package mmls.library;

import Database.Artist;
import Database.Release;
import Database.Song;

import java.util.Date;
import java.util.ArrayList;

import java.util.HashMap;

public class Library {
    private ArrayList<Song> songs;
    private ArrayList<Release> releases;
    private ArrayList<Artist> artists;
    private HashMap<String,Date> aqqDB;
    public Library() {

        songs = new ArrayList<>();
        releases = new ArrayList<>();
        artists = new ArrayList<>();
        aqqDB =  new HashMap<>();
    }

    private void addSongDate(Song song, Date date){
        if(!songs.contains(song)) {
            songs.add(song);

            if (!artists.contains(song.getArtist())) {
                artists.add(song.getArtist());
            }
        }

    }

    public void addSong(Song song){
        Date now = new Date();
        addSongDate(song,now);
    }

    public void addSong(Song song, Date date){
        addSongDate(song,date);
    }

    private void removeSongID(String id, Artist artist){
        for(Song s: songs){
            if(s.equalsGuid(id)){
                songs.remove(s);
                for(Artist a : artists){
                    if(a.equalsGuid(artist.getGuid())){
                        artists.remove(a);
                        break;
                    }
                }
                break;
            }
        }
    }

    public void removeSong(Song song){
        String id = song.getGuid();
        Artist artist = song.getArtist();
        removeSongID(id,artist);
    }

    public boolean removeSong(String id){

        for(Song s: songs){
            if(s.equalsGuid(id)){
                Artist artist =  s.getArtist();
                removeSongID(id,artist);
                return true;
            }
        }
        return false;
    }

    public void addRelease(Release release, Date date){
        if(!releases.contains(release)){
            releases.add(release);

            for(Song s: release.getTracks()){
                addSongDate(s,date);
            }
        }
    }



    public void removeRelease(Release release){
        String id = release.getGuid();
        removeRelease(id);
    }

    public boolean removeRelease(String id){
        for(Release r: releases){
            if(r.getGuid().equals(id)){

                ArrayList<Song> tracks = r.getTracks();
                for(Song s: tracks){
                    removeSong(s);
                }
                releases.remove(r);
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(String guid) {
        boolean removed = this.removeRelease(guid);
        if (!removed) {
            removed = this.removeSong(guid);
        }
        return removed;
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public ArrayList<Release> getReleases(){
        return releases;
    }

    public ArrayList<Artist> getArtists(){
        return artists;
    }

    public Date getAqqDate(String id){
        return aqqDB.get(id);
    }

    private void addDate(String id, Date date){
        aqqDB.put(id,date);
    }

}
