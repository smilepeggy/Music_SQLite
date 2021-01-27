package com.peggy;

import com.peggy.Model.Artist;
import com.peggy.Model.Datasource;
import com.peggy.Model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }
        List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_DESC);
        if (artists == null) {
            System.out.println("No artists!");
            return;
        }
        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        List<String> albumsForArtist = datasource.queryAlbumsForArtist("Iron Maiden", Datasource.ORDER_BY_ASC);
        for (String album : albumsForArtist) {
            System.out.println(album);
        }

        List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if (songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }
        for (SongArtist artist : songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() + ',' +
                    " Album name = " + artist.getAlbumName() + ',' +
                    " Track = " + artist.getTrack());
        }

        datasource.querySongMetadata();
        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        datasource.createViewForSongArtists();
// use for Statement and PreparedStatement Method
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter a song title: "); // for test "Go Your Own Way"
//        String title = scanner.nextLine();
//        List<SongArtist> songArtists1 = datasource.querySongInfoView(title); // Go Your Own Way" or 1=1 or "
//        if (songArtists1.isEmpty()) {
//            System.out.println("Couldn't find the artist for the song");
//            return;
//        }
//        for (SongArtist artist : songArtists1) {
//            System.out.println("FROM Artist_list View name = " + artist.getArtistName() + ',' +
//                    " Album name = " + artist.getAlbumName() + ',' +
//                    " Track number = " + artist.getTrack());
//        }

        // public void insertSong(String title, String artist, String album, int track)
//        datasource.insertSong("Touch of Grey", "Grateful Dead", "In The Dark", 1);
//        datasource.insertSong("My life Tainan", "Peggy Jim", "Hummingbird", 1);
        datasource.insertSong("My life Tainan", "Hummingbird(Peggy & Jim)", "Hummingbird", 1);

        datasource.close();
    }
}
