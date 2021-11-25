package com.spotify.demo.helpers;

import com.spotify.demo.dto.MusicTrackDTO;
import com.spotify.demo.model.MusicTrack;

import java.util.Comparator;
import java.util.List;

public class MusicTrackSort {
    public static List<MusicTrack> sortByName(List<MusicTrack> musicTracks) {
        musicTracks.sort(Comparator.comparing(MusicTrack::getName));
        return musicTracks;
    }

    public static List<MusicTrack> sortByAuthor(List<MusicTrack> musicTracks) {
        musicTracks.sort(Comparator.comparing(musicTrack -> musicTrack.getAuthor().getName()));
        return musicTracks;
    }

    public static List<MusicTrack> sortByYear(List<MusicTrack> musicTracks) {
        musicTracks.sort(Comparator.comparing(MusicTrack::getYear));
        return musicTracks;
    }

    public static List<MusicTrack> sortByCategory(List<MusicTrack> musicTracks) {
        musicTracks.sort(Comparator.comparing(musicTrack -> musicTrack.getCategory().getName()));
        return musicTracks;
    }
}
