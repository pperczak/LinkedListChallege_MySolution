package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();
    private static LinkedList<Song> playlist = new LinkedList<Song>();

    public static void main(String[] args) {

        Song s1 = new Song("s1",4.01);
        Song s2 = new Song("s2",4.02);
        Song s3 = new Song("s3",4.03);
        Song s4 = new Song("s4",4.04);
        Song s5 = new Song("s5",4.05);

        Song g1 = new Song("g1",5.01);
        Song g2 = new Song("g2",5.02);
        Song g3 = new Song("g3",5.03);
        Song g4 = new Song("g4",5.04);
        Song g5 = new Song("g5",5.05);

        Album Album_s1 = new Album("Album_s1");
        //
        Album_s1.addSongToAlbum(s1);
        Album_s1.addSongToAlbum(s2);
        Album_s1.addSongToAlbum(s3);
        Album_s1.addSongToAlbum(s4);
        Album_s1.addSongToAlbum(s5);

        Album Album_g1 = new Album("Album_g1");

        Album_g1.addSongToAlbum(g1);
        Album_g1.addSongToAlbum(g2);
        Album_g1.addSongToAlbum(g3);
        Album_g1.addSongToAlbum(g4);
        Album_g1.addSongToAlbum(g5);

        albums.add(Album_s1);
        albums.add(Album_g1);

        /*
        System.out.println("czy s1 jest gdzies ?" + checkSongForAlbum("s1"));
        System.out.println("czy g1 jest gdzies ?" + checkSongForAlbum("g1"));
        System.out.println("czy g100 jest gdzies ?" + checkSongForAlbum("g100"));
        */

        addSongToPlaylist("s2");
        addSongToPlaylist("g1");
        addSongToPlaylist("s4");

        printLSongList();




    }
    /*
    public static boolean checkSongForAlbum(String piosenka) {
        if (albums.isEmpty()) return true;
        Iterator<Album> albumIterator = albums.iterator();

        while(albumIterator.hasNext()) {
            LinkedList<Song> s = albumIterator.next().getSongs();

            ListIterator<Song> s_iterator = s.listIterator();
            while (s_iterator.hasNext()) {
                if (s_iterator.next().getTitle().equals(piosenka)) {
                    return true;
                }
            }
        }
        return false;
    }
    */
    public static Song findSongForAlbum(String piosenka) {
        if (albums.isEmpty()) return null;
        Iterator<Album> albumIterator = albums.iterator();

        while(albumIterator.hasNext()) {
            LinkedList<Song> s = albumIterator.next().getSongs();

            ListIterator<Song> s_iterator = s.listIterator();
            while (s_iterator.hasNext()) {
                if (s_iterator.next().getTitle().equals(piosenka)) {
                    return s.get(s_iterator.nextIndex()-1);
                }
            }
        }
        return null;
    }

    public static boolean addSongToPlaylist(String piosenka) {
        if(findSongForAlbum(piosenka)== null) {
            System.out.println("Brak piosenki w albumach!");
            return false;
        } else {

            playlist.add(findSongForAlbum(piosenka));
        }
        return true;
    }
    public static void printLSongList() {

        ListIterator<Song> listapiosenek = playlist.listIterator();
        System.out.println("Playlista aktualnie zawiera: ");
        while (listapiosenek.hasNext()) {
            Song curr = listapiosenek.next();
            System.out.println("Indeks: " +listapiosenek.nextIndex()+" Tytuł: "+ curr.getTitle() + " , trwa: "+ curr.getDuration());
        }


    }
}
