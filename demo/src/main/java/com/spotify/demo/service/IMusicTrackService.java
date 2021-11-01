package com.spotify.demo.service;

import com.spotify.demo.model.Author;
import com.spotify.demo.model.Category;
import com.spotify.demo.model.MusicTrack;

import java.util.List;
import java.util.Optional;

public interface IMusicTrackService {

    List<MusicTrack> getAllMusicTracks();

    List<MusicTrack> getMusicTrackByName(String name);

    Optional<MusicTrack> getMusicTrackById(long id);

    void updateMusicTrack(MusicTrack musicTrack);

    void addMusicTrack(String name, Author author, int year, Category category);

    void deleteMusicTrack(long id);

    void saveMusicTrack(MusicTrack musicTrack);
}
