package com.company;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();
    private static LinkedList<Song> playlist = new LinkedList<Song>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Song s1 = new Song("s1", 4.01);
        Song s2 = new Song("s2", 4.02);
        Song s3 = new Song("s3", 4.03);
        Song s4 = new Song("s4", 4.04);
        Song s5 = new Song("s5", 4.05);

        Song g1 = new Song("g1", 5.01);
        Song g2 = new Song("g2", 5.02);
        Song g3 = new Song("g3", 5.03);
        Song g4 = new Song("g4", 5.04);
        Song g5 = new Song("g5", 5.05);

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

        addSongToPlaylist("s1");
        addSongToPlaylist("g1");
        addSongToPlaylist("s2");
        addSongToPlaylist("g2");
        addSongToPlaylist("s3");
        addSongToPlaylist("g3");
        addSongToPlaylist("s4");
        addSongToPlaylist("g4");


        printLSongList();
        playThePlaylist(playlist);

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

        while (albumIterator.hasNext()) {
            LinkedList<Song> s = albumIterator.next().getSongs();

            ListIterator<Song> s_iterator = s.listIterator();
            while (s_iterator.hasNext()) {
                if (s_iterator.next().getTitle().equals(piosenka)) {
                    return s.get(s_iterator.nextIndex() - 1);
                }
            }
        }
        return null;
    }

    public static boolean addSongToPlaylist(String piosenka) {
        if (findSongForAlbum(piosenka) == null) {
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
            System.out.println("Indeks: " + listapiosenek.nextIndex() + " Tytuł: " + curr.getTitle() + " , trwa: " + curr.getDuration());
        }


    }

    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("0 - quit\n" +
                "1 - skip forward\n" +
                "2 - skip backwards\n" +
                "3 - replay current song\n" +
                "4 - print menu\n" +
                "5 - remove current song\n");
    }


    public static void playThePlaylist(LinkedList playlist) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true;

        ListIterator<Song> listIterator = playlist.listIterator();

        if (playlist.isEmpty()) {
            System.out.println("No songs in playlist");
        } else {
            System.out.println("Now playing: " + listIterator.next().getTitle());
            printMenu();
        }

        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Quiting");
                    quit = true;
                    break;
                case 1:
                    if (!goingForward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if (listIterator.hasNext()) {

                        System.out.println("Now playing: " + listIterator.next().getTitle());
                    } else {
                        System.out.println("That was the last song");
                        goingForward = false;
                    }
                    break;
                case 2:
                    if (goingForward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if (listIterator.hasPrevious()) {

                        System.out.println("Now playing: " + listIterator.previous().getTitle());
                    } else {
                        System.out.println("That's the first song on the list");
                        goingForward = true;
                    }
                    break;

                case 3:
                    System.out.println("Replaying current song.");

                    if (!listIterator.hasNext()) {

                        System.out.println("Now playing: " + listIterator.previous().getTitle());
                        listIterator.next();


                    } else if (!listIterator.hasPrevious()) {

                        System.out.println("Now playing: " + listIterator.next().getTitle());
                        listIterator.previous();


                    } else {
                        //listIterator.next();
                        System.out.println("Now playing: " + listIterator.previous().getTitle());
                        listIterator.next();
                    }

                    break;
                case 4:
                    printMenu();
                    break;
                case 5:
                    listIterator.remove();
                    System.out.println("Current records successfully removed.");
                    break;
            }
        }

    }
}
