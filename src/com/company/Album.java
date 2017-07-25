package com.company;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by PLPK on 25.07.2017.
 */
public class Album {
    private String name;
    private LinkedList<Song> songs;

    public Album(String name, LinkedList<Song> songs) {
        this.name = name;
        this.songs = new LinkedList<Song>();
    }

    public String getName() {
        return name;
    }

    public LinkedList<Song> getSongs() {
        return songs;
    }

    public boolean addSongToAlbum(String name, double dur) {
        if (findSong(name)) {
            System.out.println("Song already on the list");
            return false;
        }
        songs.add(new Song(name, dur));
        return true;
    }

    private boolean findSong(String name) {
        ListIterator<Song> listiterator = songs.listIterator();
        if (listiterator.next().equals(name)) {
            return true;
        } else {
            return false;
        }
    }

}
